package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.Source;
import com.medved.support.repository.interfaces.ISourceDAO;

@Repository
public class SourceDAO implements ISourceDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Source source) {
		entityManager.persist(source);
	}

	@Override
	public void update(Source source) {
		entityManager.merge(source);
	}

	@Override
	public void remove(Source source) {
		entityManager.remove(entityManager.find(Source.class, source.getId()));
	}

	@Override
	public Source findById(long id) {
		return entityManager.find(Source.class, id);
	}

	@Override
	public List<Source> findAll() {
		String query = "SELECT a FROM Source a";
		return entityManager.createQuery(query).getResultList();
	}
	
	

}
