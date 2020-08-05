package com.medved.support.logic.implementations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medved.support.logic.interfaces.IExternalTicketService;
import com.medved.support.model.Answer;
import com.medved.support.model.ExternalTicket;
import com.medved.support.model.Source;
import com.medved.support.model.SynchronizationRegister;
import com.medved.support.model.Ticket;
import com.medved.support.model.User;
import com.medved.support.repository.implementations.UserDAO;
import com.medved.support.repository.interfaces.IAnswerDAO;
import com.medved.support.repository.interfaces.IEntityStateDAO;
import com.medved.support.repository.interfaces.IExternalTicketDAO;
import com.medved.support.repository.interfaces.IExternalTicketPageable;
import com.medved.support.repository.interfaces.ISourceDAO;
import com.medved.support.repository.interfaces.ISynchronizationRegisterDAO;
import com.medved.support.repository.interfaces.ITicketDAO;


@Service
public class ExternalTicketService implements IExternalTicketService {

	@Autowired
	private IExternalTicketDAO externalTicketDao;
	
	@Autowired
	private ITicketDAO ticketDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private ISourceDAO sourceDao;
	
	@Autowired
	private IEntityStateDAO entityStateDao;
	
	@Autowired
	private IAnswerDAO answerDao;
	
	@Autowired
	private ISynchronizationRegisterDAO syncRegisterDao;
	
	@Autowired
	private IExternalTicketPageable externalTicketPageable;
	
	@Override
	@Transactional
	public ExternalTicket findById(long id) {
		return externalTicketDao.findById(id);
	}

	@Override
	@Transactional
	public Iterable<ExternalTicket> findAll() {
		return externalTicketDao.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(ExternalTicket externalTicket) {
		if (externalTicket == null)
			throw new RuntimeException("The ticket can't be null");
		
		if (externalTicket.getTicket() == null)
			throw new RuntimeException("The parent class (Ticket) can't be null");
		
//		if (externalTicket.getTicket().getExternalTicket()!= externalTicket) 
//			throw new RuntimeException("El ticket externo de la clase padre debe ser el que se está intentando agregar");
			
		if (externalTicket.getTicket().getInternalTicket() != null)
			throw new RuntimeException("A ticket can't be external and internal at the same time");
		
		Date closeDate = externalTicket.getTicket().getCloseDate();
		Date openDate = externalTicket.getTicket().getOpenDate();
		if ( closeDate != null && openDate.after(closeDate))
			throw new RuntimeException("The ticket could not be closed before being opened");
		
		Ticket ticket = externalTicket.getTicket();		

		List<Answer> answers = ticket.getAnswers();
		
		if(answers == null)
			throw new RuntimeException("Unanswered Ticket");
		
		ticketDao.save(ticket);
		
		for (Answer answer : answers) {
			
			answer.setId(System.nanoTime());
			answer.setTicket(ticket);
			answerDao.save(answer);
			
		}
		externalTicketDao.save(externalTicket);
	}

	@Override
	@Transactional
	public void update(ExternalTicket externalTicket) {
		if (externalTicket == null)
			throw new RuntimeException("The ticket can't be null");
		
		if (externalTicket.getTicket() == null)
			throw new RuntimeException("The parent class (Ticket) can't be null");
		
		Date closeDate = externalTicket.getTicket().getCloseDate();
		Date openDate = externalTicket.getTicket().getOpenDate();
		if ( closeDate != null && openDate.after(closeDate))
			throw new RuntimeException("The ticket could not be closed before being opened");
		
		ticketDao.update(externalTicket.getTicket());
		
		externalTicketDao.update(externalTicket);
	}

	@Override
	@Transactional
	public void remove(ExternalTicket externalTicket) {
		externalTicketDao.remove(externalTicket);

		ticketDao.removeState(externalTicket.getTicket());
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SynchronizationRegister initialLoad(Iterable<ExternalTicket> externalTickets, long sourceId, Date initialDate, Date finalDate) {
				
		int deletedTickets = 0;
		int savedTickets = 0;
		
		Source source = sourceDao.findById(sourceId);
		for (ExternalTicket externalTicketicket : externalTickets) {
			try {
				
				externalTicketicket.setSource(source);
				this.save(externalTicketicket);
				savedTickets++;
				
			} catch (Exception e) {
				deletedTickets++;
			}
		}
		
		SynchronizationRegister syncRegister = new SynchronizationRegister();
		syncRegister.setId(System.nanoTime());
		syncRegister.setInitialDate(initialDate);
		syncRegister.setFinalDate(new Date());
		syncRegister.setDeletedTickets(new BigDecimal(deletedTickets));
		syncRegister.setSavedTickets(new BigDecimal(savedTickets));
		
		return syncRegister;
	}

	public IExternalTicketDAO getExternalTicketDao() {
		return externalTicketDao;
	}

	public void setExternalTicketDao(IExternalTicketDAO externalTicketDao) {
		this.externalTicketDao = externalTicketDao;
	}

	public ITicketDAO getTicketDao() {
		return ticketDao;
	}

	public void setTicketDao(ITicketDAO ticketDao) {
		this.ticketDao = ticketDao;
	}

	public ISourceDAO getSourceDao() {
		return sourceDao;
	}

	public void setSourceDao(ISourceDAO sourceDao) {
		this.sourceDao = sourceDao;
	}

	public IEntityStateDAO getEntityStateDao() {
		return entityStateDao;
	}

	public void setEntityStateDao(IEntityStateDAO entityStateDao) {
		this.entityStateDao = entityStateDao;
	}

	public IAnswerDAO getAnswerDao() {
		return answerDao;
	}

	public void setAnswerDao(IAnswerDAO answerDao) {
		this.answerDao = answerDao;
	}

	@Override
	public Iterable<ExternalTicket> findByKeyWord(String keyword) {
		// TODO Auto-generated method stub
		return externalTicketDao.findByKeyWord(keyword);
	}

	@Override
	public Page<ExternalTicket> findAllPageable(int requestedPage, int pageSize, String username) {
		// TODO Auto-generated method stub
		Pageable page = PageRequest.of(requestedPage, pageSize); 	
		User user = userDao.getUser(username);
		List<Source> list =user.getEnterprises().getSources();
		long [] sourcesId = new long[list.size()];
		System.err.println(user.getEnterprises().getNit());
		
		for (int i = 0; i < list.size(); i++) {	
				sourcesId[i] = list.get(i).getId();
		}
		
		return externalTicketPageable.findBySourcesId(sourcesId , page);
		//return externalTicketPageable.findAll(page);
		
	}

	@Override
	public Page<ExternalTicket> findByKeywordPageable(String keyword, int requestedPage, int pageSize, String username) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(requestedPage, pageSize);
		
		User user = userDao.getUser(username);
		List<Source> list =user.getEnterprises().getSources();
		
		long [] sourcesId = new long[list.size()];
		
		for (int i = 0; i < list.size(); i++) {	
			sourcesId[i] = list.get(i).getId();
		}
		return externalTicketPageable.findByKeyword(keyword, pageable, sourcesId);
	}
	
	
	
	
	
	
}
