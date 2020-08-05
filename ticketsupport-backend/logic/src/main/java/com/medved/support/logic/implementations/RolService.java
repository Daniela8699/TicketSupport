package com.medved.support.logic.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.IRolService;
import com.medved.support.model.Rol;
import com.medved.support.repository.interfaces.IRolDAO;

@Service
public class RolService implements IRolService{

	@Autowired
	private IRolDAO rolDao;

	@Override
	public Rol findById(long id) {
		// TODO Auto-generated method stub
		return rolDao.findById(id);
	}

	@Override
	public Iterable<Rol> findAll() {
		// TODO Auto-generated method stub
		return rolDao.findAll();
	}
	
}
