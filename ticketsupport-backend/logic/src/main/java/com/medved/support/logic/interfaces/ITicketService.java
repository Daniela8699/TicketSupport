package com.medved.support.logic.interfaces;

import com.medved.support.model.Ticket;

public interface ITicketService {

	public Ticket findById(long id);
	public Iterable<Ticket> findAll();
	public void save(Ticket newTicket);
	public void update(Ticket ticket);
	public void remove(Ticket ticket);
}
