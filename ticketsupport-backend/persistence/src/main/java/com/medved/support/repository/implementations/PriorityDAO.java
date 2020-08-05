package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.Priority;
import com.medved.support.repository.interfaces.IPriorityDAO;

@Repository
public class PriorityDAO implements IPriorityDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Priority priority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Priority priority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Priority priority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Priority findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Priority.class, id);
	}

	@Override
	public List<Priority> findAll() {
		String query = "SELECT p FROM Priority p";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void removeState(Priority priority) {
		// TODO Auto-generated method stub
		
	}

}
