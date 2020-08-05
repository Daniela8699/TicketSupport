package com.medved.support.logic.interfaces;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.medved.support.model.ExternalTicket;
import com.medved.support.model.SynchronizationRegister;

public interface IExternalTicketService {

	public ExternalTicket findById(long id);
	public Iterable<ExternalTicket> findAll();
	public void save(ExternalTicket internalTicket);
	public void update(ExternalTicket internalTicket);
	public void remove(ExternalTicket internalTicket);
	public SynchronizationRegister initialLoad(Iterable<ExternalTicket> iterable, long sourceId, Date initialDate, Date finalDate);
	public Iterable<ExternalTicket> findByKeyWord(String keyword);
	public Page<ExternalTicket> findAllPageable(int requestedPage, int pageSize, String username);
	public Page<ExternalTicket> findByKeywordPageable(String keyword,int requestedPage, int pageSize, String username);
}
