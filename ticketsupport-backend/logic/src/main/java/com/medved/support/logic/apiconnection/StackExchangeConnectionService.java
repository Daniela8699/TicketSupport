package com.medved.support.logic.apiconnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medved.support.logic.implementations.ExternalTicketService;
import com.medved.support.logic.implementations.SynchronizationRegisterService;
import com.medved.support.model.ExternalTicket;
import com.medved.support.model.Source;
import com.medved.support.model.SynchronizationRegister;
import com.medved.support.model.Ticket;

@Service
public class StackExchangeConnectionService implements IStackExchangeConnectionService {
	
	public static final int QUERYS_PER_DAY_BACKOFF_TIME = 86400000;
	
	private RestTemplate restTemplate;
	
	@Autowired
	private ExternalTicketService externalTicketService;
	
	@Autowired
	private SynchronizationRegisterService syncRegisterService;
		
	public StackExchangeConnectionService() {
		
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
		restTemplate = new RestTemplate(clientHttpRequestFactory);
		
        ObjectMapper mapper = new ObjectMapper();
        
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);
        
        mapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        
        restTemplate.setMessageConverters(Collections.singletonList(converter));

	}
	
	@Override
	@Transactional
	public void initialLoad(Source source, Date firstDate, Date lastDate) {
		//Query: https://api.stackexchange.com/2.2/questions?pagesize=100&fromdate=1585699200&todate=1585785600&order=asc&sort=creation&site=stackoverflow&filter=!)IMJZJUZw2sD59lw02zOfpag_-1gWYW-fq8y
		//Página configuración: https://api.stackexchange.com/docs/questions#pagesize=100&fromdate=2020-04-01&todate=2020-04-02&order=asc&sort=creation&filter=!E-PL9L5w-FgK9aDAAAgclM.995*FbofS)P4(1u&site=stackoverflow&run=true
		Long startTime = System.currentTimeMillis();
		long firstDateTimestamp = firstDate.getTime()/1000;
		long lastDateTimestamp = lastDate.getTime()/1000;
		long previousFirstDateTimestamp = 0;
		
		int reimainingQuerys = 1;
		
		ArrayList<ExternalTicketDTO[]> ticketlist = new ArrayList<>();
		int cant = 0;
		
		TicketWrapper ticketWrapper = null;
		
		System.out.println("InitialFisrtDate:\t======>\t" + firstDate + " (" + firstDateTimestamp + ")");
		System.out.println("LastDate:\t\t======>\t" + lastDate + " (" + lastDateTimestamp + ")");
		
		do
		{
			try 
			{
				ticketWrapper = restTemplate.getForObject("https://api.stackexchange.com/2.2/questions?pagesize=100&fromdate=" + firstDateTimestamp + "&todate=" + lastDateTimestamp + "&order=asc&sort=creation&site=" + source.getSourceName() + "&filter=!)IMJZJUZw2sD59lw02zOfpag_-1gWYW-fq8y", TicketWrapper.class);
				reimainingQuerys = ticketWrapper.getRemainingQuerys();
				
				ticketlist.add(ticketWrapper.getTickets());
				cant += ticketWrapper.getTickets().length;	
				
				if(ticketWrapper.getTickets().length != 0)
				{
					
					previousFirstDateTimestamp = firstDateTimestamp;
					firstDateTimestamp = (ticketWrapper.getTickets()[ticketWrapper.getTickets().length-1].getOpenDate().getTime()/1000);
					
					System.out.println("Difference:\t\t======>\t"+ (lastDateTimestamp-firstDateTimestamp));
					System.out.println("CurrentFirstDate:\t======>\t" + new Date(firstDateTimestamp) + " (" + firstDateTimestamp + ")");
					System.out.println("LastDate:\t\t======>\t" + lastDate + " (" + lastDateTimestamp + ")");
				
				}
				else 
				{
					firstDateTimestamp += 300;
					
					System.out.println("NO TICKETS RETRIEVED");
				}
				
				System.out.println("Remaining_Querys:\t======>\t"+ticketWrapper.getRemainingQuerys());
				System.out.println("Backoff:\t\t======>\t"+ticketWrapper.getBackoff());
				
				try 
				{
					
					if(ticketWrapper.getBackoff() > 0) 
					{
						Thread.sleep(ticketWrapper.getBackoff()*1000 + 2000);
					}
					
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				
				System.out.println("Tickets_Retrieved:\t======>\t"+ticketWrapper.getTickets().length);
				System.out.println("--------------------------------------------------------------");
			}
			catch (HttpClientErrorException e)
			{
				
				System.out.println(e.getMessage());
				try 
				{
					ExternalTicketDTO[] result = new ExternalTicketDTO[cant];
					
					int x = 0;
					for (int j = 0; j < ticketlist.size();j++) 
					{
						for (int i = 0; i < ticketlist.get(j).length; i++) 
						{
							result[x]=ticketlist.get(j)[i];
							x++;
						}
					}
					
					saveTicketBatch(result, source.getId(), firstDate, new Date(), startTime);
					System.out.println("Ticket_Batch_Retrieved:\t======>\t" + result.length);
					
					cant = 0;
					reimainingQuerys = 1;
					ticketlist = new ArrayList<>();
					startTime = System.currentTimeMillis();
					
					Thread.sleep(QUERYS_PER_DAY_BACKOFF_TIME);
					
				} catch (InterruptedException e1) 
				{
					e1.printStackTrace();
				}
			}
		}while(firstDateTimestamp < lastDateTimestamp && firstDateTimestamp != previousFirstDateTimestamp && reimainingQuerys >= 1);
		
		if(ticketlist.size() != 0)
		{
			ExternalTicketDTO[] result = new ExternalTicketDTO[cant];
			
			int x = 0;
			for (int j = 0; j < ticketlist.size();j++) 
			{
				for (int i = 0; i < ticketlist.get(j).length; i++) 
				{
					result[x]=ticketlist.get(j)[i];
					x++;
				}
			}
			saveTicketBatch(result, source.getId(), firstDate, new Date(), startTime);
		}
		
		
	}
	
	@Override
	@Transactional
	public void saveTicketBatch(ExternalTicketDTO[] externalTicketDTOs, long sourceId, Date initialDate, Date finalDate, long startTime) {
		
		Iterable<ExternalTicket> externalTickets = dtoMappingExternalTicket(externalTicketDTOs);
		
		SynchronizationRegister syncRegister = externalTicketService.initialLoad(externalTickets, sourceId, initialDate, finalDate);
		
		Long finishTime = System.currentTimeMillis();
		syncRegister.setSecondsToComplete((finishTime-startTime)/1000);
		syncRegisterService.save(syncRegister);
	}
	
	public Iterable<ExternalTicket> dtoMappingExternalTicket(ExternalTicketDTO[] externalTicketDTOs)
	{
		ArrayList<ExternalTicket> externalTicketList = new ArrayList<>();

		for (ExternalTicketDTO ticketDTO : externalTicketDTOs) {

			if (ticketDTO != null) {
				Ticket ticket = new Ticket();
				ticket.setId(System.nanoTime());
				ticket.setCloseDate(ticketDTO.getCloseDate());
				ticket.setAnswered(ticketDTO.isAnswered());
				ticket.setDescription(ticketDTO.getDescription());
				ticket.setLastActivityDate(ticketDTO.getLastActivityDate());
				ticket.setOpenDate(ticketDTO.getOpenDate());
				ticket.setTitle(ticketDTO.getTitle());
				ticket.setAnswers(ticketDTO.getAnswers());

				ExternalTicket externalTicket = new ExternalTicket();
				externalTicket.setLink(ticketDTO.getLink());
				externalTicket.setScore(ticketDTO.getScore());

				externalTicket.setTicket(ticket);

				externalTicketList.add(externalTicket);
			}
		}
		
		return externalTicketList;
	}

	@Override
	@Transactional
	public Iterable<ExternalTicket> synchronization() {
		// TODO Auto-generated method stub
		return null;
	}

}
