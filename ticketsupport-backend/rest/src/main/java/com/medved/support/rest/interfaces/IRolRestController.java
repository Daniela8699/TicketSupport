package com.medved.support.rest.interfaces;

import org.springframework.web.bind.annotation.PathVariable;

import com.medved.support.model.Rol;


public interface IRolRestController {
	public Rol findById(@PathVariable("id") Long rolID);

	public Iterable<Rol> findAll();
}
