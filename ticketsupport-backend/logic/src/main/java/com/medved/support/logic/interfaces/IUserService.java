package com.medved.support.logic.interfaces;

import com.medved.support.model.User;

public interface IUserService {

	public User getUser(String username);
	
	public Iterable<User> findAll();
	public void save(User user);	
	public void update(User user);
	public String getUsersCompanyNIT(String username);
	public void removeState(User user);
	public void remove(User user);
	public Iterable<User> getUsersByCompany(String nit);


}
