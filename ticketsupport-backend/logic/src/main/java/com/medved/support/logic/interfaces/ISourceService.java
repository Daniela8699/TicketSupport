package com.medved.support.logic.interfaces;

import com.medved.support.model.Source;

public interface ISourceService {

	public Source findById(long id);
	public Iterable<Source> findAll();
	public void save(Source newSource);
	public void update(Source source);
	public void remove(Source source);
}
