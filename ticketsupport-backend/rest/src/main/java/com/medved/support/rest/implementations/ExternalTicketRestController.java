package com.medved.support.rest.implementations;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.apiconnection.ExternalTicketDTO;
import com.medved.support.logic.apiconnection.IStackExchangeConnectionService;
import com.medved.support.logic.implementations.SynchronizationService;
import com.medved.support.logic.interfaces.IExternalTicketService;
import com.medved.support.logic.interfaces.ISourceService;
import com.medved.support.logic.interfaces.ISynchronizationRegisterService;
import com.medved.support.model.ExternalTicket;
import com.medved.support.model.Source;
import com.medved.support.model.Ticket;
import com.medved.support.rest.interfaces.IExternalTicketRestController;
import com.medved.support.rest.jwt.JwtRequestFilter;

@RestController
@RequestMapping("/medvedapi/externalTickets")
@CrossOrigin(origins="*")
public class ExternalTicketRestController implements IExternalTicketRestController {

	private IStackExchangeConnectionService stackExchangeConnection;

	private IExternalTicketService externalTicketService;
	
	private ISourceService sourceService;

	private SynchronizationService synchronizationService;
	
	private ISynchronizationRegisterService synchRegisterService;
	
	private JwtRequestFilter jwt;

	@Autowired
	public ExternalTicketRestController(IStackExchangeConnectionService stackExchangeConnection,
										IExternalTicketService externalTicketService, 
										ISourceService sourceService,
										SynchronizationService synchronizationService,
										ISynchronizationRegisterService syncRegisterService,
										JwtRequestFilter jwt) {
		this.stackExchangeConnection = stackExchangeConnection;
		this.externalTicketService = externalTicketService;
		this.sourceService = sourceService;
		this.synchronizationService = synchronizationService;
		this.synchRegisterService = syncRegisterService;
		this.jwt = jwt;
	}

	@GetMapping("/{id}")
	public ExternalTicket findById(@PathVariable("id") Long ticketId) {

		try {
			return externalTicketService.findById(ticketId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@GetMapping("/meta/{keyword}")
	public Iterable<ExternalTicket> findByKeyWord(@PathVariable("keyword") String keyword) {

		try {
			return externalTicketService.findByKeyWord(keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

//	@GetMapping("/")
	public Iterable<ExternalTicket> findAll() {
		return externalTicketService.findAll();
	}

	@PostMapping("")
	public void save(@RequestBody ExternalTicket serv) {

		try {
			externalTicketService.save(serv);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PutMapping(value = "")
	public void updateEntity(@RequestBody ExternalTicket ticket) {
		externalTicketService.update(ticket);
	}

	@DeleteMapping(value = "/{id}")
	public void remove(Long ticketId) {
		ExternalTicket a = externalTicketService.findById(ticketId);
		if (a != null)
			externalTicketService.remove(a);
	}

	@Override
	@GetMapping("/source/{sourceId}/dates/{firstDate}/{lastDate}")
	public void initialLoad(@PathVariable("sourceId") long sourceId,
			@PathVariable("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date firstDate,
			@PathVariable("lastDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date lastDate) {
		System.out.println(sourceId+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Source source = sourceService.findById(sourceId);
		
		stackExchangeConnection.initialLoad(source,firstDate, lastDate);	
		
	}

	@GetMapping("/source/{sourceId}/dates/{lastDate}/hour/{synchronizationHour}/repeats/{repeat}")
	public void synchronizationLoad(@PathVariable("sourceId") ArrayList<Long> listSourcesId,
			@PathVariable("synchronizationHour") long synchronizationHour,
			@PathVariable("lastDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date lastDate,
			@PathVariable("repeat") int repeat) {
		Long startTime = System.currentTimeMillis();
		synchronizationService.automaticSynchronization(listSourcesId, synchronizationHour, lastDate, repeat, startTime);

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
	@GetMapping("/{perPage}/{currentPage}")
	public Iterable<ExternalTicket> findAllPageable(@PathVariable("perPage")int pageSize, @PathVariable("currentPage")int requestPage, @RequestHeader("Authorization") String token) {
		// TODO Auto-generated method stub
		return externalTicketService.findAllPageable(requestPage-1, pageSize, jwt.extractUsername(token));
	}

	@Override	
	@GetMapping("/meta/{keyword}/{perPage}/{currentPage}")
	public Iterable<ExternalTicket> findByKeyWordPageable(@PathVariable("keyword") String keyword,@PathVariable("currentPage") int requestPage, @PathVariable("perPage") int pageSize, @RequestHeader("Authorization") String token) {
		// TODO Auto-generated method stub
		return externalTicketService.findByKeywordPageable(keyword, requestPage-1, pageSize, jwt.extractUsername(token));
	}




}
