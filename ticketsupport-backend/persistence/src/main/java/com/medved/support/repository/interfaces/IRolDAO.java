package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.Rol;

public interface IRolDAO {

	public Rol findById (long id);
	public List<Rol> findAll();
}
