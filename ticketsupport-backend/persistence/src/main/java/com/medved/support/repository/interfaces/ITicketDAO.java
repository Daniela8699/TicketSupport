package com.medved.support.repository.interfaces;

import java.util.List;
import com.medved.support.model.Ticket;

public interface ITicketDAO {

	public void save(Ticket ticket);
	public void update(Ticket ticket);
	public void remove(Ticket ticket);
	public Ticket findById(long id);
	public List<Ticket> findAll();
	public void removeState (Ticket ticket);
	
}
