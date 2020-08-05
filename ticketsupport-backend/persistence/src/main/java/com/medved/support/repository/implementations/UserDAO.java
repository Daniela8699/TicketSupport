package com.medved.support.repository.implementations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medved.support.model.User;
import com.medved.support.repository.interfaces.IUserDAO;

@Repository
public class UserDAO implements IUserDAO {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		System.out.println(username);
		System.out.println(entityManager.find(User.class, username));

		return entityManager.find(User.class, username);
	}

	@Override
	public Iterable<User> findAll() {
		String query = "SELECT u FROM User u WHERE u.entityState.id = 1";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void save(User user) {
		entityManager.persist(user);
		
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}
	public String getUsersCompanyNIT(String username) {
		// TODO Auto-generated method stub
		String query = "SELECT u.enterprise.nit FROM User u WHERE u.username = '" + username + "'";
		return (String) entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public void removeState(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(User user) {
		// TODO Auto-generated method stub
		entityManager.merge(user);
	}

	@Override
	public Iterable<User> findAllUsersByCompany(String nit) {
		// TODO Auto-generated method stub
		String query = "SELECT u FROM User u WHERE u.enterprise.nit = '" + nit + "' AND u.entityState.id = 1";
		return entityManager.createQuery(query).getResultList();
	}

}
