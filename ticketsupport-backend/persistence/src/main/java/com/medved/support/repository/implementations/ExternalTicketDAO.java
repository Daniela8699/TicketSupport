package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.ExternalTicket;
import com.medved.support.repository.interfaces.IExternalTicketDAO;
	
@Repository
public class ExternalTicketDAO implements IExternalTicketDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void save(ExternalTicket externalTicket) {
		entityManager.persist(externalTicket);
	}

	@Override
	public void update(ExternalTicket externalTicket) {
		entityManager.merge(externalTicket);
	}

	@Override
	public void remove(ExternalTicket externalTicket) {
		entityManager.remove(entityManager.find(ExternalTicket.class, externalTicket.getTicket().getId()));
	}		
	
	@Override
	public List<ExternalTicket> findAll() {
		String query = "SELECT a FROM ExternalTicket a";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public ExternalTicket findById(long id) {
		return entityManager.find(ExternalTicket.class, id);
	}

	@Override
	public Iterable<ExternalTicket> findByKeyWord(String keyword) {
		// TODO Auto-generated method stub
		String query = "SELECT a FROM ExternalTicket a WHERE a.ticket.title LIKE '%"+keyword+"%' OR a.ticket.description LIKE '%"+ keyword+ "%'";
		return entityManager.createQuery(query).getResultList();
		}
	
}
