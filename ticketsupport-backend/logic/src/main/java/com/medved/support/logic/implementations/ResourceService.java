package com.medved.support.logic.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medved.support.logic.interfaces.IResourceService;
import com.medved.support.model.Resource;
import com.medved.support.repository.interfaces.IResourceDAO;

@Service
public class ResourceService implements IResourceService {

	@Autowired
	private IResourceDAO resourceDao;
	
	@Override
	@Transactional 
	public Resource findById(long id) {
		return resourceDao.findById(id);
	}

	@Override
	@Transactional 
	public Iterable<Resource> findAll() {
		return resourceDao.findAll();
	}

	@Override
	@Transactional 
	public void save(Resource resource) {
		resourceDao.save(resource);
	}

	@Override
	@Transactional 
	public void update(Resource resource) {
		resourceDao.update(resource);
	}

	@Override
	@Transactional 
	public void remove(Resource resource) {
		resourceDao.remove(resource);
	}
	
	
}
