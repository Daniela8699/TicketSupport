package com.medved.support.logic.interfaces;

import com.medved.support.model.Rol;

public interface IRolService {
	
	public Rol findById(long id);
	public Iterable<Rol> findAll();
	

}
