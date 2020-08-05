package com.medved.support.logica;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.medved.support.logic.implementations.ExternalTicketService;
import com.medved.support.model.ExternalTicket;
import com.medved.support.model.InternalTicket;
import com.medved.support.model.Ticket;
import com.medved.support.repository.interfaces.IAnswerDAO;
import com.medved.support.repository.interfaces.IEntityStateDAO;
import com.medved.support.repository.interfaces.IExternalTicketDAO;
import com.medved.support.repository.interfaces.ISourceDAO;
import com.medved.support.repository.interfaces.ITicketDAO;

public class ExternalTicketServiceTest {


	@InjectMocks
	private ExternalTicketService externalTicketService;
	
	@Mock
	private IExternalTicketDAO externalTicketDao;
	
	@Mock
	private ITicketDAO ticketDao;
	
	@Mock
	private ISourceDAO sourceDao;
	
	@Mock
	private IEntityStateDAO entityStateDao;
	
	@Mock
	private IAnswerDAO answerDao;
	
	
	@Before
	public void setup() {
		externalTicketService = new ExternalTicketService();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testA() {
		ExternalTicket externalTicket = new ExternalTicket();
		Ticket ticket = new Ticket();
		ticket.setId(2);
		externalTicket.setTicket(ticket);
		when(externalTicketDao.findById(2)).thenReturn(externalTicket);
		Assert.assertEquals(externalTicketService.findById(2), externalTicket);
		verify(externalTicketDao).findById(2);

	}
	
	@Test
	public void testB() {
		when(externalTicketDao.findAll()).thenReturn(new ArrayList<ExternalTicket>());
		Assert.assertNotEquals(externalTicketService.findAll(), null);
		verify(externalTicketDao).findAll();
	}
//	@Test
//	public void testC() {
//		ExternalTicket externalTicket = new ExternalTicket();
//		Ticket ticket = new Ticket();
//		externalTicket.setTicket(ticket);
//		ticket.setExternalTicket(externalTicket);
//		EntityState entityState = new EntityState();
//		externalTicketService.save(externalTicket);
//		verify(ticketDao).save(externalTicket.getTicket());
//		verify(externalTicketDao).save(externalTicket);
//	}
	
	@Test(expected = RuntimeException.class)
	public void testE() {
		externalTicketService.save(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void testH() {
		ExternalTicket externalTicket = new ExternalTicket();
		externalTicketService.save(externalTicket);

	}
	
	@Test(expected = RuntimeException.class)
	public void testI() {
		InternalTicket internalTicketB = new InternalTicket();
		ExternalTicket ExternalTicketA = new ExternalTicket();
		Ticket ticket = new Ticket();
		ExternalTicketA.setTicket(ticket);
		ticket.setInternalTicket(internalTicketB);
		externalTicketService.save(ExternalTicketA);
	}
	
	@Test(expected = RuntimeException.class)
	public void testJ() {
		ExternalTicket externalTicket = new ExternalTicket();
		Ticket ticket = new Ticket();
		externalTicket.setTicket(ticket);
		ticket.setExternalTicket(externalTicket);
		Date dateA = new Date(2020, 1, 5);
		Date dateC = new Date(2020, 1, 2);
		ticket.setCloseDate(dateC);
		ticket.setOpenDate(dateA);
		externalTicketService.save(externalTicket);

	}
	
	@Test
	public void testK() {
		ExternalTicket externalTicket = new ExternalTicket();
		Ticket ticket = new Ticket();
		externalTicket.setTicket(ticket);
		externalTicketService.update(externalTicket);
		verify(ticketDao).update(externalTicket.getTicket());
		verify(externalTicketDao).update(externalTicket);
	}
	
	@Test(expected = RuntimeException.class)
	public void testl() {
		externalTicketService.update(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void testM() {
		ExternalTicket externalTicket = new ExternalTicket();
		externalTicketService.update(externalTicket);

	}
	
	@Test(expected = RuntimeException.class)
	public void testN() {
		ExternalTicket externalTicket = new ExternalTicket();
		Ticket ticket = new Ticket();
		externalTicket.setTicket(ticket);
		ticket.setExternalTicket(externalTicket);
		Date dateA = new Date(2020, 1, 5);
		Date dateC = new Date(2020, 1, 2);
		ticket.setCloseDate(dateC);
		ticket.setOpenDate(dateA);
		externalTicketService.update(externalTicket);

	}
	
	/**
	 * @Override




	@Override
	@Transactional
	
	@Override
	@Transactional
	public void remove(ExternalTicket externalTicket) {
		externalTicketDao.remove(externalTicket);

		ticketDao.removeState(externalTicket.getTicket());
	}
	
	@Override
	@Transactional
	public void initialLoad(Iterable<ExternalTicket> externalTickets, long sourceId) {
		
		Source source = sourceDao.findById(sourceId);
		for (ExternalTicket externalTicketicket : externalTickets) {
			
			externalTicketicket.setSource(source);
			this.save(externalTicketicket);
		}	
	}

	public IExternalTicketDAO getExternalTicketDao() {
		return externalTicketDao;
	}

	public void setExternalTicketDao(IExternalTicketDAO externalTicketDao) {
		this.externalTicketDao = externalTicketDao;
	}

	public ITicketDAO getTicketDao() {
		return ticketDao;
	}

	public void setTicketDao(ITicketDAO ticketDao) {
		this.ticketDao = ticketDao;
	}

	public ISourceDAO getSourceDao() {
		return sourceDao;
	}

	public void setSourceDao(ISourceDAO sourceDao) {
		this.sourceDao = sourceDao;
	}

	public IEntityStateDAO getEntityStateDao() {
		return entityStateDao;
	}

	public void setEntityStateDao(IEntityStateDAO entityStateDao) {
		this.entityStateDao = entityStateDao;
	}

	public IAnswerDAO getAnswerDao() {
		return answerDao;
	}

	public void setAnswerDao(IAnswerDAO answerDao) {
		this.answerDao = answerDao;
	}
	
	 */
}
