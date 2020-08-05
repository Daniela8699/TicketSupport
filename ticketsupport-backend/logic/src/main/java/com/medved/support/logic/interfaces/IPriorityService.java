package com.medved.support.logic.interfaces;

import com.medved.support.model.Priority;

public interface IPriorityService {

	public Priority findById(long id);
	public Iterable<Priority> findAll();
	public void save(Priority newPriority);
	public void update(Priority newPriority);
	public void remove(Priority newPriority);
	
}
