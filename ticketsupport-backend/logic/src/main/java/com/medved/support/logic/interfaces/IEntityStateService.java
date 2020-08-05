package com.medved.support.logic.interfaces;

import com.medved.support.model.EntityState;

public interface IEntityStateService {

	public EntityState findById(long id);
	public Iterable<EntityState> findAll();
	public void save(EntityState entityState);
	public void update(EntityState entityState);
	public void remove(EntityState entityState);
}
