package com.medved.support.rest.interfaces;


import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.medved.support.model.ExternalTicket;

public interface IExternalTicketRestController {

	public ExternalTicket findById(@PathVariable("id") Long ticketId);

	public Iterable<ExternalTicket> findByKeyWord(@PathVariable("keyword") String keyword);
	
	public Iterable<ExternalTicket> findAll();

	public void save(@RequestBody ExternalTicket serv);
	
	public void updateEntity(@RequestBody ExternalTicket answer);

	public void remove(Long ticketId);

	public void initialLoad(long sourceId, Date firstDate, Date lastDate);
	
	public Iterable<ExternalTicket> findAllPageable(@PathVariable("currentPage") int requestPage, @PathVariable("perPage") int pageSize, @RequestHeader String token);
	
	public Iterable<ExternalTicket> findByKeyWordPageable(@PathVariable("keyword") String keyword,@PathVariable("currentPage") int requestPage, @PathVariable("perPage") int pageSize, @RequestHeader String token);
}
