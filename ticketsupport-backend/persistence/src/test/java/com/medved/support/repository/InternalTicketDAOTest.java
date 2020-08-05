package com.medved.support.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medved.support.model.InternalTicket;
import com.medved.support.model.Ticket;
import com.medved.support.repository.implementations.InternalTicketDAO;

public class InternalTicketDAOTest {

	@InjectMocks
	private InternalTicketDAO internalTicketDAO;  
	
	@Mock
	private EntityManager entityManager;
	
	@BeforeTest
	public void setup() {
		internalTicketDAO = new InternalTicketDAO();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testA() {
		InternalTicket internalTicket = new InternalTicket();
		internalTicketDAO.save(internalTicket);
		verify(entityManager).persist(internalTicket);
	}
	
	@Test
	public void testB() {
		InternalTicket internalTicket = new InternalTicket();
		internalTicketDAO.update(internalTicket);
		verify(entityManager).merge(internalTicket);
	}
	
	@Test
	public void testC() {
		Long id = (long) 8; 
		InternalTicket internalTicket = new InternalTicket();
		Ticket ticket = new Ticket();
		ticket.setId(id);
		internalTicket.setTicket(ticket);
		when(entityManager.find(InternalTicket.class, id)).thenReturn(internalTicket);
		internalTicketDAO.findById(id);
		verify(entityManager).find(InternalTicket.class, id);
	}
	
	@Test
	public void testD() {
		Long id = (long) 8; 
		InternalTicket internalTicket = new InternalTicket();
		Ticket ticket = new Ticket();
		ticket.setId(id);
		internalTicket.setTicket(ticket);
		when(entityManager.find(InternalTicket.class, id)).thenReturn(internalTicket);
		Assert.assertEquals(internalTicketDAO.findById(id),internalTicket);
	}
	
	
	public void testE() {
		Long id = (long) 8; 
		InternalTicket internalTicket = new InternalTicket();
		Ticket ticket = new Ticket();
		ticket.setId(id);
		internalTicket.setTicket(ticket);
		internalTicketDAO.remove(internalTicket);
		verify(entityManager).remove(internalTicket);
	}
	

	public void testF() {
		String query = "SELECT a FROM InternalTicket a";
		List<InternalTicket> newList = new ArrayList<InternalTicket>();
		when(entityManager.createQuery(query).getResultList()).thenReturn(newList);
		internalTicketDAO.findAll();
		verify(entityManager).createQuery(query).getResultList();
	}
	
	
	
	
}
