package com.medved.support.logic.interfaces;

import com.medved.support.model.Enterprise;

public interface IEnterpriseService {
	
	public Enterprise findById(String nit);
	public Iterable<Enterprise> findAll();
	public void save(Enterprise newEnterprise);
	public void update(Enterprise updateEnterprise);
	public void remove(Enterprise enterpriseRemove);
	public void removeState(Enterprise enterprise);

}
