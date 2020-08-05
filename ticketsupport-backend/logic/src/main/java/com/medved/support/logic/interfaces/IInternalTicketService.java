package com.medved.support.logic.interfaces;

import org.springframework.data.domain.Page;

import com.medved.support.model.InternalTicket;

public interface IInternalTicketService {

	public InternalTicket findById(long id);
	public Iterable<InternalTicket> findAll();
	public void save(InternalTicket internalTicket);
	public void update(InternalTicket internalTicket);
	public void remove(InternalTicket internalTicket);
	public void removeState (InternalTicket internalTicket);

	public Iterable<InternalTicket> findByKeyWord(String keyword);
	public Page<InternalTicket> findAllResolvedPageable(int pageSize, int page,String company);
	public Page<InternalTicket> findAllAssignedPageable(int pageSize, int page,String username);
	public Page<InternalTicket> findByKeywordResolvedPageable(String keyword,int pageSize, int page, String company);
	public Page<InternalTicket> findByKeywordAssignedPageable(String keyword,int pageSize, int page, String username);
	public void updateAnswer (InternalTicket internalTicket,long ticketStateId);

}
