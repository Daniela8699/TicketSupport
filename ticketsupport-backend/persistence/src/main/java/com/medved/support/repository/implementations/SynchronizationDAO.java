package com.medved.support.repository.implementations;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class SynchronizationDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Timestamp getSynchronizationDate() {

		String query = "SELECT a.finalDate FROM SynchronizationRegister a";
		List<Timestamp> list = entityManager.createQuery(query).getResultList();

		if (list.isEmpty()) {
			return null;

		}

		return list.get(0);

	}

}
