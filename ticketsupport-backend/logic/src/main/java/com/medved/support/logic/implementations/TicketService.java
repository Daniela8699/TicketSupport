package com.medved.support.logic.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.ITicketService;
import com.medved.support.model.Ticket;
import com.medved.support.repository.interfaces.IEntityStateDAO;
import com.medved.support.repository.interfaces.ITicketDAO;

@Service
public class TicketService implements ITicketService {

	@Autowired
	private ITicketDAO ticketDao;
	
	@Autowired
	private IEntityStateDAO entityStateDao;
	
	@Override
	public Ticket findById(long id) {
		return ticketDao.findById(id);
	}

	@Override
	public Iterable<Ticket> findAll() {
		return ticketDao.findAll();
	}

	@Override
	public void save(Ticket externalTicket) {
		ticketDao.save(externalTicket);

	}

	@Override
	public void update(Ticket externalTicket) {
		ticketDao.update(externalTicket);

	}

	@Override
	public void remove(Ticket externalTicket) {
		ticketDao.remove(externalTicket);

	}

	public ITicketDAO getTicketDao() {
		return ticketDao;
	}

	public void setTicketDao(ITicketDAO ticketDao) {
		this.ticketDao = ticketDao;
	}

	public IEntityStateDAO getEntityStateDao() {
		return entityStateDao;
	}

	public void setEntityStateDao(IEntityStateDAO entityStateDao) {
		this.entityStateDao = entityStateDao;
	}
	
	
	
}
