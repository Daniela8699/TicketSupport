package com.medved.support.repository.implementations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.TicketState;
import com.medved.support.repository.interfaces.ITicketStateDAO;

@Repository
public class TicketStateDAO implements ITicketStateDAO {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public TicketState findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TicketState.class, id);
	}

}
