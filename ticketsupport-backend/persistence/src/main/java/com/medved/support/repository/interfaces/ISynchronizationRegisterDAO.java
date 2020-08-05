package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.SynchronizationRegister;

public interface ISynchronizationRegisterDAO {

	public void save(SynchronizationRegister syncRegister);
	public void update(SynchronizationRegister syncRegister);
	public void remove(SynchronizationRegister syncRegister);
	public SynchronizationRegister findById (long id);
	public List<SynchronizationRegister> findAll();
	public void removeState (SynchronizationRegister answer);
	
}
