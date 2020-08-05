package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.InternalTicket;

public interface IInternalTicketDAO {

	public void save(InternalTicket internalTicket);
	public void update(InternalTicket internalTicket);
	public void remove(InternalTicket internalTicket);
	public InternalTicket findById(long id);
	public List<InternalTicket> findAll();
	public void removeState (InternalTicket internalTicket);
	public Iterable<InternalTicket> findByKeyWord(String keyword);
	
}
