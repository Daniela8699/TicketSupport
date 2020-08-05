package com.medved.support.rest.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.medved.support.logic.apiconnection.UserDTO;

import com.medved.support.model.User;
import com.medved.support.rest.security.AuthenticationRequest;

public interface IUserRestController {
	
	public Iterable<User> findAll();
	
	public Iterable<User> findAllAgentsByMyCompany(@RequestHeader("Authorization") String token);
	
	public Iterable<User> findAllAgentsByCompany(@RequestHeader("Authorization") String token, @PathVariable("nit") String nit);
	
	public void remove (@PathVariable String name);
	
	public String authenticate(@RequestBody AuthenticationRequest authenticationRequest);

	public void save(@RequestBody UserDTO userDTO);
	
	public void update(@RequestBody UserDTO userDTO);

	public User getUser (@PathVariable("username") String username);
	
	public void removeState(@PathVariable("username") String username);
	
	

}
