package com.medved.support.rest.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.medved.support.model.SynchronizationRegister;

public interface ISynchronizationRegisterRestController {

	public SynchronizationRegister findById(@PathVariable("id") Long id);

	public void save(@RequestBody SynchronizationRegister syncRegister);
	
	public void updateEntity(@RequestBody SynchronizationRegister syncRegister);
	
	public Iterable<SynchronizationRegister> findAll();

	public void remove(Long id);
}
