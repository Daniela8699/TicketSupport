package com.medved.support.rest.interfaces;

import com.medved.support.model.SynchronizationRegister;

public interface ISynchRegisterRestController {

	public Iterable<SynchronizationRegister> findAll();
}
