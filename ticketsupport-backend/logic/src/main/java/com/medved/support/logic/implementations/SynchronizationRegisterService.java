package com.medved.support.logic.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.ISynchronizationRegisterService;
import com.medved.support.model.SynchronizationRegister;
import com.medved.support.repository.interfaces.ISynchronizationRegisterDAO;

@Service
public class SynchronizationRegisterService implements ISynchronizationRegisterService {

	@Autowired
	private ISynchronizationRegisterDAO syncRegisterDAO;

	@Override
	@Transactional
	public SynchronizationRegister findById(long id) {
		return syncRegisterDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(SynchronizationRegister synchRegister) {
		if (synchRegister == null)
			throw new RuntimeException("La entidad no puede ser nula");
		
		syncRegisterDAO.save(synchRegister);
	}

	@Override
	@Transactional
	public void update(SynchronizationRegister synchRegister) {
		if (synchRegister == null)
			throw new RuntimeException("La entidad no puede ser nula");
		syncRegisterDAO.update(synchRegister);

	}

	@Override
	@Transactional
	public void remove(SynchronizationRegister synchRegister) {
		syncRegisterDAO.remove(synchRegister);

	}

	@Override
	@Transactional
	public void removeState(SynchronizationRegister synchRegister) {
		syncRegisterDAO.removeState(synchRegister);
	}
	
	@Override
	@Transactional
	public Iterable<SynchronizationRegister> findAll() {
		return syncRegisterDAO.findAll();
	}

}
