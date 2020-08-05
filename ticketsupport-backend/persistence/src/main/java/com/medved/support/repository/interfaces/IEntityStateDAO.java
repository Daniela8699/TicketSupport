package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.EntityState;


public interface IEntityStateDAO {

	public void save(EntityState entityState);
	public void update(EntityState entityState);
	public void remove(EntityState entityState);
	public EntityState findById (long id);
	public List<EntityState> findAll();
	public void removeState (EntityState entityState);
}
