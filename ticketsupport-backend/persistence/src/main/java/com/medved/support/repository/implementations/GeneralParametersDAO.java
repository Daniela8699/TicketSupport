package com.medved.support.repository.implementations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.GeneralParameter;
import com.medved.support.repository.interfaces.IGeneralParametersDAO;

@Repository
public class GeneralParametersDAO implements IGeneralParametersDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public GeneralParameter getGeneralParameter(String parameterName) {
		// TODO Auto-generated method stub
		return entityManager.find(GeneralParameter.class, parameterName);
	}

}
