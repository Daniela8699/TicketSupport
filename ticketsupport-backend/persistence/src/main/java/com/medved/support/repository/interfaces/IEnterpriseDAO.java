package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.Enterprise;

public interface IEnterpriseDAO {
	
	public void save(Enterprise enterprise);
	public void update(Enterprise enterprise);
	public void remove(Enterprise enterprise);
	public Enterprise findById (String nit);
	public List<Enterprise> findAll();
	public void removeState (Enterprise enterprise);

}
