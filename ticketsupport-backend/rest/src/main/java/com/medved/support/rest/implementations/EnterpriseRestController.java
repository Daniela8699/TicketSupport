package com.medved.support.rest.implementations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.apiconnection.EnterpriseDTO;

import com.medved.support.logic.interfaces.IEnterpriseService;
import com.medved.support.logic.interfaces.ISourceService;
import com.medved.support.logic.interfaces.IUserService;
import com.medved.support.model.Enterprise;
import com.medved.support.model.Source;
import com.medved.support.model.User;
import com.medved.support.rest.interfaces.IEnterprisesRestController;

@RequestMapping("/medvedapi/enterprises")
@RestController
@CrossOrigin(origins="*")
public class EnterpriseRestController implements IEnterprisesRestController{

	@Autowired
	IEnterpriseService enterpriseService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ISourceService sourceService;
	
	@GetMapping("")
	@Override
	public Iterable<Enterprise> findAll() {
		// TODO Auto-generated method stub
		return enterpriseService.findAll();
	}
	

	@GetMapping("/{nit}")
	@Override
	public Enterprise findById(@PathVariable String nit) {
		// TODO Auto-generated method stub
		return enterpriseService.findById(nit);
	}


	@PostMapping("")
	@Override
	public void save(@RequestBody EnterpriseDTO enterprise) {
		// TODO Auto-generated method stub
		
		Enterprise newEnterprise = map(enterprise, true); 
		enterpriseService.save(newEnterprise);
	}


	@PutMapping("")
	@Override
	public void updateEntity(@RequestBody EnterpriseDTO enterprise) {
		// TODO Auto-generated method stub
		
		Enterprise newEnterprise = map(enterprise, false); 
		enterpriseService.update(newEnterprise);
	}


	@DeleteMapping("/{nit}")
	@Override
	public void remove(@PathVariable String nit) {
		Enterprise toRemove = enterpriseService.findById(nit);
		if (toRemove != null)
			enterpriseService.remove(toRemove);		
	}


	@DeleteMapping("state/{nit}")
	@Override
	public void removeState(String nit) {
		Enterprise toRemove = enterpriseService.findById(nit);
		if (toRemove != null)
			enterpriseService.remove(toRemove);
		
	}
	
	private Enterprise map(EnterpriseDTO edto, boolean serv) {

		Enterprise newEnterprise;
		if(serv) {
			newEnterprise = new Enterprise();
			newEnterprise.setNit(edto.getNit());

		}else {
			newEnterprise = enterpriseService.findById(edto.getNit());
		}

		
		ArrayList<Source> listSource = new ArrayList<Source>();
		

		for(int i=0;i<edto.getSources().size();i++) {
			Source source = sourceService.findById(edto.getSources().get(i));
			listSource.add(source);
		}
		newEnterprise.setContactMail(edto.getContactMail());
		newEnterprise.setContactPhone(edto.getContactPhone());
		newEnterprise.setDescription(edto.getDescription());
		newEnterprise.setName(edto.getName());
		newEnterprise.setSources(listSource);

		return newEnterprise;
	}
}
