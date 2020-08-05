package com.medved.support.rest.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.interfaces.IRolService;
import com.medved.support.model.Rol;
import com.medved.support.rest.interfaces.IRolRestController;
@RequestMapping("/medvedapi/rol")
@RestController
@CrossOrigin(origins="*")
public class RolRestController implements IRolRestController {
	@Autowired
	private IRolService service;
	
	@Override
	@GetMapping("/{id}")
	public Rol findById(@PathVariable("id") Long rolID) {
		try {
			return service.findById(rolID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	@GetMapping("")
	public Iterable<Rol> findAll() {
		return service.findAll();
	}

}
