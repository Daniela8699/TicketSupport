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

import com.medved.support.logic.implementations.InternalTicketService;
import com.medved.support.model.EntityState;
import com.medved.support.model.ExternalTicket;
import com.medved.support.model.InternalTicket;
import com.medved.support.model.Ticket;
import com.medved.support.model.TicketState;
import com.medved.support.repository.interfaces.IEntityStateDAO;
import com.medved.support.repository.interfaces.IInternalTicketDAO;
import com.medved.support.repository.interfaces.ITicketDAO;
import com.medved.support.repository.interfaces.ITicketStateDAO;

public class InternalTicketServiceTest {

	@InjectMocks
	private InternalTicketService internalTicketService;

	@Mock
	private IInternalTicketDAO internalTicketDao;

	@Mock
	private ITicketDAO ticketDao;

	@Mock
	private IEntityStateDAO entityStateDao;

	@Mock
	private ITicketStateDAO ticketStateDao;

	@Before
	public void setup() {
		internalTicketService = new InternalTicketService();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @Name: testA
	 * @Method: findById
	 * @Description:TestA verifies that the findById method of the
	 *                    internalTicketService class calls the findById method of
	 *                    the internalTicketDao class. Also simulates an
	 *                    internalTicket object
	 * 
	 */
	@Test
	public void testA() {
		InternalTicket internalTicket = new InternalTicket();
		when(internalTicketDao.findById(8)).thenReturn(internalTicket);
		Assert.assertEquals(internalTicketService.findById(8), internalTicket);
		verify(internalTicketDao).findById(8);
	}

	/**
	 * @Name: testB
	 * @Method: findById
	 * @Description: The testB method verifies that the findBy method of the
	 *               internalTicketService class throws an exception when a negative
	 *               number is passed to it as a parameter.
	 */
	@Test(expected = RuntimeException.class)
	public void testB() {
		internalTicketService.findById(-1);
	}

	/**
	 * @Name: testC
	 * @Method: findAll
	 * @Description: The testC method verifies that the findAll method of the
	 *               internalTicketService class calls the findAll method of the
	 *               class internalTicketDao. It also simulates the return of an
	 *               internalTicket array.
	 */
	@Test
	public void testC() {
		when(internalTicketDao.findAll()).thenReturn(new ArrayList<InternalTicket>());
		Assert.assertNotEquals(internalTicketService.findAll(), null);
		verify(internalTicketDao).findAll();
	}

	/**
	 * @Name: testD
	 * @Method: save
	 * @Description: The testD method verifies that the save method of the
	 *               internalTicketService class calls the save method of the class
	 *               entityStateDao. It also verifies the calls to the findById
	 *               methods of the entityStateDao class and the findById method of
	 *               the ticketStateDao class.
	 */
	@Test
	public void testD() {
		InternalTicket internalTicket = new InternalTicket();
		Ticket ticket = new Ticket();
		internalTicket.setTicket(ticket);
		ticket.setInternalTicket(internalTicket);
		EntityState entityState = new EntityState();
		TicketState ticketState = new TicketState();
		internalTicket.setEntityState(entityState);
		internalTicket.setTicketState(ticketState);
		when(entityStateDao.findById(1)).thenReturn(entityState);
		when(ticketStateDao.findById(0)).thenReturn(ticketState);
		internalTicketService.save(internalTicket);
		verify(entityStateDao).findById(1);
		verify(ticketStateDao).findById(0);
		verify(ticketDao).save(internalTicket.getTicket());
		verify(internalTicketDao).save(internalTicket);
	}

	/**
	 * @Name: testE
	 * @Method: save
	 * @Description: The testE method verifies that the save method of the
	 *               internalTicketService class throws an exception when a null
	 *               value is passed.
	 */
	@Test(expected = RuntimeException.class)
	public void testE() {
		internalTicketService.save(null);
	}

	/**
	 * @Name: testF
	 * @Method: save
	 * @Description: The testF method verifies that the save method of the
	 *               internalTicketService class throws an exception when the
	 *               internal ticket of the parent class must be the one you are
	 *               trying to add
	 */
	@Test(expected = RuntimeException.class)
	public void testF() {
		ExternalTicket externalTicket = new ExternalTicket();
		InternalTicket internalTicket = new InternalTicket();
		Ticket ticket = new Ticket();
		internalTicket.setTicket(ticket);
		ticket.setExternalTicket(externalTicket);
		internalTicketService.save(internalTicket);
	}

	/**
	 * @Name: testG
	 * @Method: save
	 * @Description: The testG method verifies that the save method of the
	 *               internalTicketService class throws an exception when an
	 *               external and internal ticket are the same ticket
	 */
	@Test(expected = RuntimeException.class)
	public void testG() {
		ExternalTicket ExternalTicketB = new ExternalTicket();
		InternalTicket internalTicketA = new InternalTicket();
		Ticket ticket = new Ticket();
		internalTicketA.setTicket(ticket);
		ticket.setExternalTicket(ExternalTicketB);
		internalTicketService.save(internalTicketA);

	}

	/**
	 * @Name: testH
	 * @Method: save
	 * @Description:The testH method verifies that the save method of the
	 *               internalTicketService class throws an exception when the parent
	 *               class (Ticket) is null 
	 * 
	 
	 */
	@Test(expected = RuntimeException.class)
	public void testH() {
		InternalTicket internalTicket = new InternalTicket();
		internalTicketService.save(internalTicket);

	}

	/**
	 * @Name: testI
	 * @Method: save
	 * @Description: The testI method verifies that the save method of the
	 *               internalTicketService class throws an exception when the
	 *               internal ticket of the parent class is the one trying to add
	 */
	@Test(expected = RuntimeException.class)
	public void testI() {
		InternalTicket internalTicket = new InternalTicket();
		Ticket ticket = new Ticket();
		internalTicket.setTicket(ticket);
		ticket.setInternalTicket(internalTicket);
		Date dateA = new Date(2020, 1, 5);
		Date dateC = new Date(2020, 1, 2);
		ticket.setCloseDate(dateC);
		ticket.setOpenDate(dateA);
		internalTicketService.save(internalTicket);

	}

	/**
	 * @Name: testJ
	 * @Method: update
	 * @Description: The testJ method verifies that the update method of the
	 *               internalTicketService class calls the update method of the class
	 *               entityStateDao. 	 */
	@Test
	public void testJ() {
		InternalTicket internalTicket = new InternalTicket();
		Ticket ticket = new Ticket();
		internalTicket.setTicket(ticket);
		ticket.setInternalTicket(internalTicket);
		internalTicketService.update(internalTicket);
		verify(ticketDao).update(internalTicket.getTicket());
		verify(internalTicketDao).update(internalTicket);
	}


	/**
	 * @Name: testK
	 * @Method: save
	 * @Description: The testK method verifies that the update method of the
	 *               internalTicketService class throws an exception when a null
	 *               value is passed.
	 */
	@Test(expected = RuntimeException.class)
	public void testK() {
		internalTicketService.update(null);
	}



	/**
	 * @Name: testl
	 * @Method: update
	 * @Description: The testl method verifies that the update method of the
	 *               internalTicketService class throws an exception when the
	 *               internal ticket of the parent class must be the one you are
	 *               trying to add
	 */
	@Test(expected = RuntimeException.class)
	public void testl() {
		ExternalTicket externalTicket = new ExternalTicket();
		InternalTicket internalTicket = new InternalTicket();
		Ticket ticket = new Ticket();
		internalTicket.setTicket(ticket);
		ticket.setExternalTicket(externalTicket);
		internalTicketService.update(internalTicket);
	}


	/**
	 * @Name: testH
	 * @Method: save
	 * @Description: The testH method verifies that the save method of the
	 *               internalTicketService class throws an exception when an
	 *               external and internal ticket are the same ticket
	 */
	@Test(expected = RuntimeException.class)
	public void testM() {
		InternalTicket internalTicketB = new InternalTicket();
		InternalTicket internalTicketA = new InternalTicket();
		Ticket ticket = new Ticket();
		internalTicketA.setTicket(ticket);
		ticket.setInternalTicket(internalTicketB);
		internalTicketService.update(internalTicketA);

	}

	/**
	 * @Name: testN
	 * @Method: update
	 * @Description:The testN method verifies that the update method of the
	 *               internalTicketService class throws an exception when the parent
	 *               class (Ticket) is null 
	 * 
	 
	 */
	@Test(expected = RuntimeException.class)
	public void testN() {
		InternalTicket internalTicket = new InternalTicket();
		internalTicketService.update(internalTicket);

	}


	/**
	 * @Name: testD
	 * @Method: update
	 * @Description: The testO method verifies that the update method of the
	 *               internalTicketService class throws an exception when the
	 *               internal ticket of the parent class is the one trying to add
	 */
	@Test(expected = RuntimeException.class)
	public void testO() {
		InternalTicket internalTicket = new InternalTicket();
		Ticket ticket = new Ticket();
		internalTicket.setTicket(ticket);
		ticket.setInternalTicket(internalTicket);
		Date dateA = new Date(2020, 1, 5);
		Date dateC = new Date(2020, 1, 2);
		ticket.setCloseDate(dateC);
		ticket.setOpenDate(dateA);
		internalTicketService.update(internalTicket);

	}

	

	/**
	 * @Name: testA
	 * @Method: findById
	 * @Description:
	 */
	
	//Some changes made
	
	
	@Test
	public void testQ() {
		InternalTicket internalTicket = new InternalTicket();
		internalTicketService.remove(internalTicket);
		verify(internalTicketDao).remove(internalTicket);
		verify(ticketDao).remove(internalTicket.getTicket());
	}

	/**
	 * @Name: testA
	 * @Method: findById
	 * @Description:
	 */
//	@Test
//	public void testsR() {
//		InternalTicket internalTicket = new InternalTicket();
//		internalTicketService.removeState(internalTicket);
//		verify(internalTicketDao).removeState(internalTicket);
//	}

}
