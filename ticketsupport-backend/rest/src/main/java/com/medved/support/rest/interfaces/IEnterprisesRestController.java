package com.medved.support.rest.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.medved.support.logic.apiconnection.EnterpriseDTO;
import com.medved.support.model.Enterprise;

public interface IEnterprisesRestController {

	public Enterprise findById (@PathVariable("nit") String nit);
	
	public Iterable<Enterprise> findAll();
	
	public void save(@RequestBody EnterpriseDTO enterprise);
	
	public void updateEntity(@RequestBody EnterpriseDTO enterprise);
	
	public void remove (@PathVariable String nit);
	
	public void removeState (@PathVariable String nit);
}
