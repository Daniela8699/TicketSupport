package com.medved.support.rest.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.interfaces.ISynchronizationRegisterService;
import com.medved.support.model.SynchronizationRegister;
import com.medved.support.rest.interfaces.ISynchRegisterRestController;

@RequestMapping("/medvedapi/reports")
@RestController
@CrossOrigin(origins="*")
public class SynchRegisterRestController implements ISynchRegisterRestController{
	
	@Autowired
	ISynchronizationRegisterService synchRegisterService;

	@GetMapping("")
	@Override
	public Iterable<SynchronizationRegister> findAll() {
		return synchRegisterService.findAll();
	}
	
	

}
