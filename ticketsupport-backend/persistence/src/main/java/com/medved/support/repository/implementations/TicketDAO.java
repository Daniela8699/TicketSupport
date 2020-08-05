package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.Ticket;
import com.medved.support.repository.interfaces.ITicketDAO;

@Repository
public class TicketDAO implements ITicketDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Ticket ticket) {
		entityManager.persist(ticket);
	}

	@Override
	public void update(Ticket ticket) {
		entityManager.merge(ticket);
	}

	@Override
	public void remove(Ticket ticket) {
		entityManager.remove(entityManager.find(Ticket.class, ticket.getId()));
	}

	@Override
	public Ticket findById(long id) {
		return entityManager.find(Ticket.class, id);
	}

	
	@Override
	public List<Ticket> findAll() {
		String query = "SELECT a FROM Ticket a";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void removeState(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}
	
	

}
