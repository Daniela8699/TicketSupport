package com.medved.support.rest.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.medved.support.logic.apiconnection.InternalTicketDTO;
import com.medved.support.model.InternalTicket;

public interface IInternalTicketRestController {

	public InternalTicket findById(@PathVariable("id") Long ticketId);

	public Iterable<InternalTicket> findAll();
	
	public Iterable<InternalTicket> findAllResolvedPageable(@PathVariable("currentPage") int currPage, @PathVariable("perPage") int pageSize , @RequestHeader("Authorization") String token);
	
	public Iterable<InternalTicket> findAllAssignedPageable(@PathVariable("currentPage") int currPage, @PathVariable("perPage") int pageSize,  @RequestHeader("Authorization") String token);
	
	public Iterable<InternalTicket> findByKeyWord(@PathVariable("keyword") String keyword);
	
	public Iterable<InternalTicket> findByKeyWordAssignedPageable(@PathVariable("keyword") String keyword,@PathVariable("currentPage") int currPage, @PathVariable("perPage") int pageSize, @RequestHeader("Authorization") String token);
	
	public Iterable<InternalTicket> findByKeyWordResolvedPageable(@PathVariable("keyword") String keyword,@PathVariable("currentPage") int currPage, @PathVariable("perPage") int pageSize, @RequestHeader("Authorization") String token);
	
	public void save(@RequestBody InternalTicketDTO serv, @RequestHeader("Authorization") String token);
	
	public void updateEntity(@RequestBody InternalTicketDTO ticket);

	public void remove(@PathVariable Long ticketId);
	
	public void removeState(Long ticketId);
	
	public long answerTicket(@RequestBody InternalTicketDTO ticket);
	
}
