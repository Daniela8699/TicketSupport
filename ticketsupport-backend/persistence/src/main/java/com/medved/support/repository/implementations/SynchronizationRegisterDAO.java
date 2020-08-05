package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.SynchronizationRegister;
import com.medved.support.repository.interfaces.ISynchronizationRegisterDAO;

@Repository
public class SynchronizationRegisterDAO implements ISynchronizationRegisterDAO {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public void save(SynchronizationRegister syncRegister) {
		entityManager.persist(syncRegister);
	}

	@Override
	public void update(SynchronizationRegister syncRegister) {
		entityManager.merge(syncRegister);
		
	}

	@Override
	public void remove(SynchronizationRegister syncRegister) {
		entityManager.remove(entityManager.find(SynchronizationRegister.class, syncRegister.getId()));
	}


	@Override
	public SynchronizationRegister findById(long id) {
		return entityManager.find(SynchronizationRegister.class, id);
	}
	
	@Override
	public List<SynchronizationRegister> findAll() {
		String query = "SELECT s FROM SynchronizationRegister s";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void removeState(SynchronizationRegister syncRegister) {
		// TODO Auto-generated method stub
		
	}
}
