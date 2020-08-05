package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.Resource;
import com.medved.support.model.Rol;
import com.medved.support.repository.interfaces.IRolDAO;

@Repository
public class RolDAO implements IRolDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Rol findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Rol.class, id);
	}

	@Override
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		String query = "SELECT a FROM Rol a";
		return entityManager.createQuery(query).getResultList();
	}
	
	

}
