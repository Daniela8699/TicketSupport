package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.GeneralParameter;
import com.medved.support.repository.interfaces.IParametersDAO;

@Repository
public class ParametersDAO implements IParametersDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(GeneralParameter generalParameter) {
		entityManager.persist(generalParameter);
	}

	@Override
	public void update(GeneralParameter generalParameter) {
		entityManager.merge(generalParameter);
	}

	@Override
	public void remove(GeneralParameter generalParameter) {
		entityManager.remove(entityManager.find(GeneralParameter.class, generalParameter.getName()));
	}

	@Override
	public GeneralParameter findById(String id) {
		return entityManager.find(GeneralParameter.class, id);
	}

	@Override
	public List<GeneralParameter> findAll() {
		String query = "SELECT a FROM GeneralParameter a";
		return entityManager.createQuery(query).getResultList();
	}

	
	
	
	
}
