package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.Source;

public interface ISourceDAO {

	public void save(Source newSource);
	public void update(Source source);
	public void remove(Source source);
	public Source findById(long id);
	public List<Source> findAll();
	
}
