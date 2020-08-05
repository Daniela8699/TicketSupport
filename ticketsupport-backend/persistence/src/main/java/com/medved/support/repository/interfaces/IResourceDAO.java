package com.medved.support.repository.interfaces;

import java.util.List;
import com.medved.support.model.Resource;

public interface IResourceDAO {

	public void save(Resource resource);
	public void update(Resource resource);
	public void remove(Resource resource);
	public Resource findById (long id);
	public List<Resource> findAll();
}
