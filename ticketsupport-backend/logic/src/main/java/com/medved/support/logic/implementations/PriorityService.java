package com.medved.support.logic.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.IPriorityService;
import com.medved.support.model.Priority;
import com.medved.support.repository.interfaces.IPriorityDAO;

@Service
public class PriorityService implements IPriorityService {

	@Autowired
	IPriorityDAO priorityDao;
	
	@Override
	@Transactional
	public Priority findById(long id) {
		// TODO Auto-generated method stub
		return priorityDao.findById(id);
	}

	@Override
	@Transactional
	public Iterable<Priority> findAll() {
		// TODO Auto-generated method stub
		return priorityDao.findAll();
	}

	@Override
	@Transactional
	public void save(Priority newPriority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void update(Priority newPriority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void remove(Priority newPriority) {
		// TODO Auto-generated method stub
		
	}

}
