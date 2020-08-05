package com.medved.support.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.IUserService;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	private IUserService userService;

	@Autowired
	public MyCustomUserDetailsService(IUserService userService) 
	{
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		com.medved.support.model.User userApp = userService.getUser(username);
		if (userApp != null && userApp.getEntityState().getId() == 1) 
		{
			System.out.println("------------USERNAME: " + userApp.getUsername());
			System.out.println("-------------ROLE: " + userApp.getRols().get(0).getName());
			return User.builder().username(userApp.getUsername()).
					password(userApp.getPassword()).
					roles(userApp.getRols().get(0).getName()).build();
		} 
		else
		{
			throw new UsernameNotFoundException("User not found.");
		}
	}
}