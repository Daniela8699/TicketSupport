package com.medved.support.repository.interfaces;

import java.util.List;
import com.medved.support.model.Priority;

public interface IPriorityDAO {
	
	public void save(Priority priority);
	public void update(Priority priority);
	public void remove(Priority priority);
	public Priority findById (long id);
	public List<Priority> findAll();
	public void removeState (Priority priority);

}
