package com.medved.support.repository.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medved.support.model.InternalTicket;

@Repository
public interface IInternalTIcketPageable extends PagingAndSortingRepository<InternalTicket, Long> {
	@Query( value = "SELECT a FROM InternalTicket a WHERE a.assignedTo.username = :username AND a.ticketState.stateName = 'Open' AND (a.ticket.title LIKE CONCAT('%', :keyword, '%') OR a.ticket.description LIKE CONCAT('%', :keyword, '%'))", countQuery = "SELECT count(*) FROM InternalTicket a WHERE a.assignedTo.username = :username AND a.ticketState.stateName = 'Open' AND (a.ticket.title LIKE CONCAT('%', :keyword, '%') OR a.ticket.description LIKE CONCAT('%', :keyword, '%'))")
	Page<InternalTicket> findByKeywordAssigned(@Param("keyword")String keyword, @Param("username")String username, @Param("pageable")Pageable pageable);
	@Query( value ="SELECT a FROM InternalTicket a WHERE a.enterprise.nit = :company AND a.ticketState.stateName = 'Closed' AND (a.ticket.title LIKE CONCAT('%', :keyword, '%') OR a.ticket.description LIKE CONCAT('%', :keyword, '%'))", countQuery = "SELECT count(*) FROM InternalTicket a WHERE a.enterprise.nit = :company AND a.ticketState.stateName = 'Closed' AND (a.ticket.title LIKE CONCAT('%', :keyword, '%') OR a.ticket.description LIKE CONCAT('%', :keyword, '%'))")
	Page<InternalTicket> findByKeywordResolved(@Param("keyword")String keyword,@Param("company")String company, @Param("pageable")Pageable pageable);
	
	@Query( value ="SELECT a FROM InternalTicket a WHERE a.assignedTo.username = :username AND a.ticketState.stateName = 'Open'",countQuery = "SELECT count(*) FROM InternalTicket a WHERE a.assignedTo.username = :username AND a.ticketState.stateName = 'Open'")
	Page<InternalTicket> findAllByAssigned(@Param("username")String username, @Param("pageable")Pageable pageable);
	@Query( value = "SELECT a FROM InternalTicket a WHERE a.enterprise.nit = :company AND a.ticketState.stateName = 'Closed'",countQuery = "SELECT count(*) FROM InternalTicket a WHERE a.enterprise.nit = :company AND a.ticketState.stateName = 'Closed'")
	Page<InternalTicket> findAllByCompany(@Param("company")String keyword, @Param("pageable")Pageable pageable);
}
