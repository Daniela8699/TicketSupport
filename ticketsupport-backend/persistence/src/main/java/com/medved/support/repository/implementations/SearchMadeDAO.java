package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.Answer;
import com.medved.support.model.SearchMade;
import com.medved.support.repository.interfaces.ISearchMadeDAO;

@Repository
public class SearchMadeDAO implements ISearchMadeDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<SearchMade> findAll() {
		String query = "SELECT a FROM SearchMade a";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void save(SearchMade searchMade) {
		entityManager.persist(searchMade);
		
	}

	@Override
	public void update(SearchMade searchMade) {
		entityManager.merge(searchMade);
		
	}

	@Override
	public void remove(SearchMade searchMade) {
		entityManager.remove(entityManager.find(SearchMade.class, searchMade.getId()));
		
	}

	@Override
	public SearchMade findById(long id) {
		return entityManager.find(SearchMade.class, id);
	}	
}
