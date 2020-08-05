package com.medved.support.rest.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.apiconnection.UserDTO;
import com.medved.support.logic.interfaces.IEnterpriseService;
import com.medved.support.logic.interfaces.IRolService;
import com.medved.support.logic.interfaces.IUserService;
import com.medved.support.model.Rol;
import com.medved.support.model.User;
import com.medved.support.rest.interfaces.IUserRestController;
import com.medved.support.rest.jwt.JwtRequestFilter;
import com.medved.support.rest.jwt.JwtUtil;
import com.medved.support.rest.security.AuthenticationRequest;
import com.medved.support.rest.security.MyCustomUserDetailsService;


@CrossOrigin(origins="*")
@RequestMapping("/medvedapi/users")
@RestController
public class UserRestController implements IUserRestController {

	@Autowired
	private IUserService userServ;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyCustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtRequestFilter jwt;
	
	@Autowired
	IEnterpriseService enterpriseService;
	
	@Autowired
	private IRolService rolService;
	
	@Override
	@GetMapping("")
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return userServ.findAll();
	}

	@Override
	@GetMapping("/agents")
	public Iterable<User>findAllAgentsByMyCompany(@RequestHeader("Authorization") String token){
		String nit = userServ.getUsersCompanyNIT(jwt.extractUsername(token));
		return userServ.getUsersByCompany(nit);
	}
	
	@Override
	@GetMapping("/enterprise/{nit}")
	public Iterable<User>findAllAgentsByCompany(@RequestHeader("Authorization") String token, @PathVariable("nit") String nit){
		return userServ.getUsersByCompany(nit);
	}

	@Override
	@PostMapping("/authenticate")
	public String authenticate(AuthenticationRequest authenticationRequest) {
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(
				authenticationRequest.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return jwt;
		}catch (Exception e) {
			return "Invalid";
		}
	}
	@PostMapping("")
	@Override
	public void save(@RequestBody UserDTO userDTO) {
//		System.out.println("-------------------------------------------------Usuario guardado");
		User newUser = map(userDTO, true);

		userServ.save(newUser);
	}
	
	@PutMapping("")
	@Override
	public void update(UserDTO userDTO) {
		User user = map(userDTO, false);
		userServ.update(user);
		
	}
	
	public User map(UserDTO userDTO, boolean newUser) {
		User user=null;
		if(newUser) {
			user = new User();
		}else {
			user = userServ.getUser(userDTO.getUsername());
		}
		user.setEmail(userDTO.getEmail());
		user.setLastName(userDTO.getLastName());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		user.setEnterprise(enterpriseService.findById(userDTO.getEnterpriseNit().get(0)));
		ArrayList<Rol> rols = new ArrayList<>();
		rols.add(rolService.findById(userDTO.getRolID().get(0)));
		user.setRols(rols);
//		System.out.println("--------------------------Lastname---------------"+user.getLastName());
//		System.out.println("--------------------------NAME---------------"+user.getName());
//		System.out.println("--------------------------PASSWORD---------------"+user.getPassword());
//		System.out.println("--------------------------USERNAME---------------"+user.getUsername());
//		System.out.println("--------------------------ENTERPRISE---------------"+user.getEnterprises().getName());
//		System.out.println("--------------------------Rol---------------"+user.getRols().get(0).getName()s);
		
		return user;
	}


	@DeleteMapping("/{username}")
	@Override
	public void remove(@PathVariable String username) {
		// TODO Auto-generated method stub
		User dele = getUser(username);
		userServ.remove(dele);
	}
	
	@GetMapping("/{username}")
	@Override
	public User getUser(@PathVariable String username) {
		// TODO Auto-generated method stub
		return userServ.getUser(username);
	}

	@DeleteMapping("state/{username}")
	@Override
	public void removeState(String username) {
		User toRemove = userServ.getUser(username);
		if (toRemove != null)
			userServ.remove(toRemove);
		
	}

}
