package com.medved.support.logic.implementations;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medved.support.logic.interfaces.IInternalTicketService;
import com.medved.support.model.EntityState;
import com.medved.support.model.InternalTicket;
import com.medved.support.model.Ticket;
import com.medved.support.model.TicketState;
import com.medved.support.repository.interfaces.IEntityStateDAO;
import com.medved.support.repository.interfaces.IInternalTIcketPageable;
import com.medved.support.repository.interfaces.IInternalTicketDAO;
import com.medved.support.repository.interfaces.IPriorityDAO;
import com.medved.support.repository.interfaces.ITicketDAO;
import com.medved.support.repository.interfaces.ITicketStateDAO;

@Service
public class InternalTicketService implements IInternalTicketService {

	@Autowired
	private IInternalTicketDAO internalTicketDao;
	
	@Autowired
	private ITicketDAO ticketDao;
	
	@Autowired
	private IEntityStateDAO entityStateDao;
	
	@Autowired
	private ITicketStateDAO ticketStateDao;
	
	@Autowired
	private IInternalTIcketPageable internalTicketPageable;
	
	@Autowired
	private IPriorityDAO priorityDao;

	@Override
	@Transactional
	public InternalTicket findById(long id) {
		InternalTicket d = null;
		if(id<0) {
			throw new RuntimeException("El id del ticket no puede ser negativo");
		}else {
			d = internalTicketDao.findById(id);
		}
		return d;
	}
	
	
	@Override
	@Transactional
	public Iterable<InternalTicket> findByKeyWord(String keyword) {
		// TODO Auto-generated method stub
		return internalTicketDao.findByKeyWord(keyword);
	}
	@Override
	@Transactional
	public Iterable<InternalTicket> findAll() {
		return internalTicketDao.findAll();
	}

	@Override
	@Transactional
	public void save(InternalTicket internalTicket) {
		
		if (internalTicket == null)
			throw new RuntimeException("El ticket no puede ser nulo");
		
		if (internalTicket.getTicket() == null)
			throw new RuntimeException("La clase padre (Ticket) no puede ser nula");
			
		if (internalTicket.getTicket().getInternalTicket() != internalTicket) 
			throw new RuntimeException("El ticket interno de la clase padre debe ser el que se está intentando agregar");
			
		if (internalTicket.getTicket().getExternalTicket() != null) 
			throw new RuntimeException("Un ticket no puede ser externo e interno al mismo tiempo");
		
		
		Date closeDate = internalTicket.getTicket().getCloseDate();
		Date openDate = internalTicket.getTicket().getOpenDate();
		if ( closeDate != null && openDate.after(closeDate) ) 
			throw new RuntimeException("El ticket no puede haberse cerrado antes de estar abierto");
		
		EntityState entityState = entityStateDao.findById(1);
		
		TicketState ticketState = ticketStateDao.findById(0l);
		
		Ticket ticket = internalTicket.getTicket();
		
		internalTicket.setEntityState(entityState);
		internalTicket.setTicketState(ticketState);
		
		ticketDao.save(ticket);
		
		internalTicketDao.save(internalTicket);

	}

	@Override
	@Transactional
	public void update(InternalTicket internalTicket) {
		
		if (internalTicket == null)
			throw new RuntimeException("El ticket no puede ser nulo");
		
		if (internalTicket.getTicket() == null)
			throw new RuntimeException("La clase padre (Ticket) no puede ser nula");
			
		if (internalTicket.getTicket().getInternalTicket() != internalTicket) 
			throw new RuntimeException("El ticket interno de la clase padre debe ser el que se está intentando agregar");
			
		if (internalTicket.getTicket().getExternalTicket() != null) 
			throw new RuntimeException("Un ticket no puede ser externo e interno al mismo tiempo");	
	
		Date closeDate = internalTicket.getTicket().getCloseDate();
		Date openDate = internalTicket.getTicket().getOpenDate();
		if ( closeDate != null && openDate.after(closeDate) )
			throw new RuntimeException("El ticket no puede haberse cerrado antes de estar abierto");
		
		ticketDao.update(internalTicket.getTicket());
		internalTicketDao.update(internalTicket);

	}

	@Override
	@Transactional
	public void remove(InternalTicket internalTicket) {
		internalTicketDao.remove(internalTicket);
		ticketDao.remove(internalTicket.getTicket());
	}

	@Override
	@Transactional
	public void removeState(InternalTicket internalTicket) {
		internalTicket.setEntityState(entityStateDao.findById(0));
	}


	@Override
	public Page<InternalTicket> findAllAssignedPageable(int pageSize, int page,String username){
		Pageable p = PageRequest.of(page, pageSize);
		return internalTicketPageable.findAllByAssigned(username, p);
	}

	@Override
	public Page<InternalTicket> findAllResolvedPageable(int pageSize, int page,String company){
		Pageable p = PageRequest.of(page, pageSize);
		return internalTicketPageable.findAllByCompany(company, p);
	}

	@Override
	public Page<InternalTicket> findByKeywordResolvedPageable(String keyword,int pageSize, int page, String company) {
		Pageable p = PageRequest.of(page, pageSize);
		
		return internalTicketPageable.findByKeywordResolved(keyword, company, p);
		
   }
	@Override
	public Page<InternalTicket> findByKeywordAssignedPageable(String keyword,int pageSize, int page, String username) {
		Pageable p = PageRequest.of(page, pageSize);
		
		return internalTicketPageable.findByKeywordAssigned(keyword, username, p);
		
   }


	@Override
	@Transactional
	public void updateAnswer(InternalTicket internalTicket, long ticketStateId) {
		
		TicketState ticketState = null;
		try {
			ticketState = ticketStateDao.findById(ticketStateId);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		internalTicket.setTicketState(ticketState);
		internalTicket.getTicket().setAnswered(true);
		
		if(internalTicket.getTicket().getCloseDate()==null) {
			internalTicket.getTicket().setCloseDate(new Date());
		}
		update(internalTicket);
	}
}
