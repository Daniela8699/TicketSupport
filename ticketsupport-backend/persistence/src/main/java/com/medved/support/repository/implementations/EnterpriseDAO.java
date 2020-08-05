package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.Enterprise;
import com.medved.support.repository.interfaces.IEnterpriseDAO;

@Repository
public class EnterpriseDAO implements IEnterpriseDAO{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void save(Enterprise enterprise) {
		entityManager.persist(enterprise);
		
	}

	@Override
	public void update(Enterprise enterprise) {
		entityManager.merge(enterprise);
		
	}

	@Override
	public void remove(Enterprise enterprise) {
		entityManager.merge(enterprise);
		
	}

	@Override
	public Enterprise findById(String nit) {
		// TODO Auto-generated method stub
		return entityManager.find(Enterprise.class, nit);
	}

	@Override
	public List<Enterprise> findAll() {
		String query = "SELECT e FROM Enterprise e WHERE e.entityState.id = 1";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void removeState(Enterprise enterprise) {
		// TODO Auto-generated method stub
		
	}

	
}



