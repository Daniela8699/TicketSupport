package com.medved.support.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.Answer;
import com.medved.support.model.EntityState;
import com.medved.support.model.Ticket;
import com.medved.support.repository.interfaces.IEntityStateDAO;

@Repository
public class EntityStateDAO implements IEntityStateDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(EntityState entityState) {
		entityManager.persist(entityState);
		
	}

	@Override
	public void update(EntityState entityState) {
		entityManager.merge(entityState);
		
	}

	@Override
	public void remove(EntityState entityState) {
		entityManager.remove(entityManager.find(Answer.class, entityState.getId()));
		
	}

	@Override
	public EntityState findById(long id) {
		return entityManager.find(EntityState.class, id);
	}

	@Override
	public List<EntityState> findAll() {
		String query = "SELECT a FROM EntitySta a";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void removeState(EntityState entityState) {
		entityManager.remove(entityManager.find(Ticket.class, entityState.getId()));
		
	}

}
