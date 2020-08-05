package com.medved.support.repository.interfaces;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medved.support.model.ExternalTicket;

@Repository
public interface IExternalTicketPageable extends PagingAndSortingRepository<ExternalTicket, Long> {
	
	@Query(value ="SELECT a FROM ExternalTicket a WHERE a.source.id IN :sourcesId AND (a.ticket.title LIKE CONCAT('%', :keyword, '%') OR a.ticket.description LIKE CONCAT('%', :keyword, '%'))",countQuery = "SELECT count(*) FROM ExternalTicket a WHERE a.source.id IN :sourcesId AND (a.ticket.title LIKE CONCAT('%', :keyword, '%') OR a.ticket.description LIKE CONCAT('%', :keyword, '%'))")
	Page<ExternalTicket> findByKeyword(@Param("keyword")String keyword, @Param("pageable")Pageable pageable, @Param("sourcesId") long[] sourcesId);
	
	@Query(value = "SELECT a FROM ExternalTicket a WHERE a.source.id IN :sourcesId ")
	Page<ExternalTicket> findBySourcesId(@Param("sourcesId") long[] sourcesId,@Param("pageable")Pageable pageable);
	
}
