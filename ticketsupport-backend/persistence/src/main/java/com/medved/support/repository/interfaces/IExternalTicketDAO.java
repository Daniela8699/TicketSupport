package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.ExternalTicket;

public interface IExternalTicketDAO {

	public void save(ExternalTicket externalTicket);
	public void update(ExternalTicket externalTicket);
	public void remove(ExternalTicket externalTicket);
	public List<ExternalTicket> findAll();
	public ExternalTicket findById(long id);
	public Iterable<ExternalTicket> findByKeyWord(String keyword);
	
}
