package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.Answer;
import com.medved.support.repository.interfaces.IAnswerDAO;

@Repository
public class AnswerDAO implements IAnswerDAO {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public void save(Answer answer) {
		entityManager.persist(answer);
	}

	@Override
	public void update(Answer answer) {
		entityManager.merge(answer);
		
	}

	@Override
	public void remove(Answer answer) {
		entityManager.remove(entityManager.find(Answer.class, answer.getId()));
	}


	@Override
	public Answer findById(long id) {
		return entityManager.find(Answer.class, id);
	}

	@Override
	public List<Answer> findAll() {
		String query = "SELECT a FROM Answer a";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void removeState(Answer answer) {
		// TODO Auto-generated method stub
		
	}

}
