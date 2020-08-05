package com.medved.support.logic.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.IEntityStateService;
import com.medved.support.model.EntityState;
import com.medved.support.repository.interfaces.IEntityStateDAO;

@Service
public class EntityStateService implements IEntityStateService {

	@Autowired
	private IEntityStateDAO entityStateDao;
	
	@Override
	@Transactional
	public EntityState findById(long id) {
		return entityStateDao.findById(id);
	}

	@Override
	@Transactional
	public Iterable<EntityState> findAll() {
		return entityStateDao.findAll();
	}

	@Override
	@Transactional
	public void save(EntityState entityState) {
		entityStateDao.save(entityState);
	}

	@Override
	@Transactional
	public void update(EntityState entityState) {
		entityStateDao.update(entityState);
	}

	@Override
	@Transactional
	public void remove(EntityState entityState) {
		entityStateDao.remove(entityState);
	}

}
