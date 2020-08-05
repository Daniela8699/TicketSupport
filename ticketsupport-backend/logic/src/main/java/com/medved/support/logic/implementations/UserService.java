package com.medved.support.logic.implementations;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.IRolService;
import com.medved.support.logic.interfaces.IUserService;
import com.medved.support.model.EntityState;
import com.medved.support.model.Rol;
import com.medved.support.model.User;
import com.medved.support.repository.implementations.EntityStateDAO;
import com.medved.support.repository.interfaces.IEntityStateDAO;
import com.medved.support.repository.interfaces.IUserDAO;

@Service
public class UserService implements IUserService {

	
	private IUserDAO userDao;
	
	
	private IEntityStateDAO entityStateDao;
	
	
	private PasswordEncoder encoder;
	
	
	private IRolService rolService;
	
	@Autowired
	public UserService(IUserDAO userDao, IEntityStateDAO entityStateDao, PasswordEncoder encoder, IRolService rolService) {
		this.userDao = userDao;
		this.entityStateDao = entityStateDao;
		this.encoder = encoder;
		this.rolService = rolService;
		
	}
	
	@Override
	@Transactional
	public User getUser(String username) {
		// TODO Auto-generated method stub
		User user = userDao.getUser(username);
		if(user==null) {
			throw new RuntimeException("El usuario no existe");
		}
		return userDao.getUser(username);

	}

	@Override
	@Transactional
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void save(User user) {
		try {
			getUser(user.getUsername());
			throw new RuntimeException("El usuario ya existe");
		}catch(Exception e) {
			
		}
		if(user.getEmail() == null || user.getLastName() == null || user.getName() == null || user.getPassword() == null || user.getUsername() == null)
			throw new RuntimeException("Asegúrese de que no hayan espacios sin completar");
		EntityState entityState = entityStateDao.findById(1);
		user.setEntityState(entityState);
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
		
	}

	@Override
	@Transactional
	public void update(User user) {
		if(getUser(user.getUsername()) == null)
			throw new RuntimeException("Usuario a modificar no existe");
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.update(user);
		
	}

	@Override
	@Transactional
	public String getUsersCompanyNIT(String username) {
		// TODO Auto-generated method stub
		return userDao.getUsersCompanyNIT(username);

	}

	@Override
	@Transactional
	public void removeState(User user) {
		// TODO Auto-generated method stub
		if(user == null) {
			throw new RuntimeException("El usuario es null, no se puede eliminar");
		}
		userDao.removeState(user);
	}

	@Override
	@Transactional
	public void remove(User user) {
		// TODO Auto-generated method stub
		if(user == null) {
			throw new RuntimeException("El estado del usuario no se puede eliminar");
		}
		EntityState es = entityStateDao.findById(0);
		user.setEntityState(es);
		System.out.println(user.getEntityState().getId() + ":" + user.getEntityState().getDescription());
		userDao.remove(user);
	}

	@Override
	public Iterable<User> getUsersByCompany(String nit) {
		// TODO Auto-generated method stub
		return userDao.findAllUsersByCompany(nit);
	}




}
