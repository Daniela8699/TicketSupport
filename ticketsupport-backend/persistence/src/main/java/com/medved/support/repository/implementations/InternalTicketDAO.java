package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.InternalTicket;
import com.medved.support.repository.interfaces.IInternalTicketDAO;

@Repository
public class InternalTicketDAO implements IInternalTicketDAO {


	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(InternalTicket internalTicket) {
		entityManager.persist(internalTicket);
	}

	@Override
	public void update(InternalTicket internalTicket) {
		entityManager.merge(internalTicket);
	}

	@Override
	public void remove(InternalTicket internalTicket) {
		entityManager.remove(entityManager.find(InternalTicket.class, internalTicket.getTicket().getId()));	
	}
	
	@Override
	public InternalTicket findById(long id) {
		return entityManager.find(InternalTicket.class, id);
	}

	@Override
	public List<InternalTicket> findAll() {
		String query = "SELECT a FROM InternalTicket a";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void removeState(InternalTicket internalTicket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<InternalTicket> findByKeyWord(String keyword) {
		// TODO Auto-generated method stub
		String query = "SELECT a FROM InternalTicket a WHERE a.ticket.title LIKE '%"+keyword+"%' OR a.ticket.description LIKE '%"+ keyword+ "%'";
		
		return entityManager.createQuery(query).getResultList();
	}

}
