package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.GeneralParameter;

public interface IParametersDAO {
	
	public void save(GeneralParameter generalParameter);
	public void update(GeneralParameter generalParameter);
	public void remove(GeneralParameter generalParameter);
	public GeneralParameter findById(String id);
	public List<GeneralParameter> findAll();

}
