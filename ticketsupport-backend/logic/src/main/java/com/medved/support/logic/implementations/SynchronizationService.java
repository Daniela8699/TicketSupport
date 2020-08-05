package com.medved.support.logic.implementations;

import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.medved.support.logic.apiconnection.ExternalTicketDTO;
import com.medved.support.logic.apiconnection.StackExchangeConnectionService;
import com.medved.support.logic.interfaces.IExternalTicketService;
import com.medved.support.logic.interfaces.ISourceService;
import com.medved.support.model.ExternalTicket;
import com.medved.support.model.Source;
import com.medved.support.model.Ticket;
import com.medved.support.repository.implementations.SynchronizationDAO;

@Component
@Scope("singleton")
public class SynchronizationService {

	public static final long TWENTYFOUR_HOURS = 86400000;

	@Autowired
	private StackExchangeConnectionService connection;

	@Autowired
	private SynchronizationDAO synchronizationDAO;
	private Thread synchronizationThread;
	@Autowired
	private ISourceService sourceService;
	@Autowired
	private IExternalTicketService externalTicketService;

	private long actualDate;

//	@PostConstruct

	@Transactional
	public void automaticSynchronization(ArrayList<Long> listSourcesId, long synchronizationHour, Date lastDate,
			int repeat, Long tiempoInicio) {

		actualDate = System.currentTimeMillis(); // hora actual del computador

		Runnable runnable = new Runnable() {

			// repeats: cuantas veces se va a repetir, 0 = 1 vez al dia
			// synchronizationHour: hora en que el usuario quiere hacer la sincronizacion

			int flag = repeat;
			Date lastSynchronizationDate;
			Source source;
			long nextSynchronization = actualDate;

			@Override
			public void run() {

				while (true) {
					if (synchronizationDAO.getSynchronizationDate() == null) {
						lastSynchronizationDate = lastDate;
					} else {
						lastSynchronizationDate = new Date(synchronizationDAO.getSynchronizationDate().getTime());
					}

					if (flag == -1 && actualDate > synchronizationHour) {
						flag = repeat;
						nextSynchronization = actualDate;
					}

					if (actualDate > synchronizationHour && actualDate >= nextSynchronization && flag >= 0) {

						for (int i = 0; i < listSourcesId.size(); i++) {
							source = sourceService.findById(listSourcesId.get(i));

							connection.initialLoad(source, lastSynchronizationDate, new Date(actualDate));
						}

						flag--;
						nextSynchronization = actualDate + TWENTYFOUR_HOURS / ((repeat + 1) + 2);

					} else {

						try {

							synchronizationThread.sleep(TWENTYFOUR_HOURS / ((repeat + 1) + 2));

						} catch (InterruptedException e) {
							e.printStackTrace();

						}
					}
				}
			}
		};
		synchronizationThread = new Thread(runnable);
		synchronizationThread.start();
	}

	public long getLastSynchronizationDate() {
		return synchronizationDAO.getSynchronizationDate().getTime();
	}

	private ArrayList<ExternalTicket> getExternalTicketList(ExternalTicketDTO[] retrievedExternalTicketsDTOs) {
		ArrayList<ExternalTicket> externalTicketList = new ArrayList<>();

		for (ExternalTicketDTO ticketDTO : retrievedExternalTicketsDTOs) {

			if (ticketDTO != null) {
				Ticket ticket = new Ticket();
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
}
