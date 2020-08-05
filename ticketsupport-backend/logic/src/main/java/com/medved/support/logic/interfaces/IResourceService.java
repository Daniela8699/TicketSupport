package com.medved.support.logic.interfaces;

import com.medved.support.model.Resource;

public interface IResourceService {
	
	public Resource findById(long id);
	public Iterable<Resource> findAll();
	public void save(Resource resource);
	public void update(Resource resource);
	public void remove(Resource resource);

}
