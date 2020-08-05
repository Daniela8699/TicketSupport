package com.medved.support.rest.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.interfaces.ISynchronizationRegisterService;
import com.medved.support.model.SynchronizationRegister;
import com.medved.support.rest.interfaces.ISynchronizationRegisterRestController;

@RequestMapping("/medvedapi/synchronizationRegister")
@RestController
@CrossOrigin(origins="*")
public class SynchronizationRegisterRestController implements ISynchronizationRegisterRestController {

	@Autowired
	private ISynchronizationRegisterService service;
	
	@Override
	@GetMapping("/{id}")
	public SynchronizationRegister findById(@PathVariable("id")Long id) {
		try {
			return service.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	@GetMapping("")
	public Iterable<SynchronizationRegister> findAll() {
		return service.findAll();
	}

	@Override
	@PostMapping("")
	public void save(SynchronizationRegister syncRegister) {

		try {
			service.save(syncRegister);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@PutMapping("")
	public void updateEntity(SynchronizationRegister syncRegister) {
		service.update(syncRegister);
	}

	@Override
	@DeleteMapping("/{id}")
	public void remove(Long id) {
		SynchronizationRegister synRegister = service.findById(id);
		if (synRegister != null)
			service.remove(synRegister);
	}

}
