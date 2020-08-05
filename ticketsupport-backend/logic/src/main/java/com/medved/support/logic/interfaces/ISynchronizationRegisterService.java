package com.medved.support.logic.interfaces;

import com.medved.support.model.SynchronizationRegister;

public interface ISynchronizationRegisterService {

	public SynchronizationRegister findById(long id);
	public void save(SynchronizationRegister syncRegister);
	public void update(SynchronizationRegister syncRegister);
	public void remove(SynchronizationRegister syncRegister);
	public void removeState (SynchronizationRegister syncRegister);
	public Iterable<SynchronizationRegister> findAll();
	
}
