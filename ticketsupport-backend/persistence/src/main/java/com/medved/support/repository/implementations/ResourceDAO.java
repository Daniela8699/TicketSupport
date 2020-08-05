package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.Resource;
import com.medved.support.repository.interfaces.IResourceDAO;

@Repository
public class ResourceDAO implements IResourceDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Resource resource) {
		entityManager.persist(resource);	
	}

	@Override
	public void update(Resource resource) {
		entityManager.merge(resource);
	}

	@Override
	public void remove(Resource resource) {
		entityManager.remove(entityManager.find(Resource.class, resource.getId()));	
	}

	@Override
	public Resource findById(long id) {
		return entityManager.find(Resource.class, id);
	}

	@Override
	public List<Resource> findAll() {
		String query = "SELECT a FROM Resource a";
		return entityManager.createQuery(query).getResultList();
	}

	
}
