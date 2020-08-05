package com.medved.support.repository.implementations;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import com.medved.support.repository.interfaces.IStatisticsDAO;

import javassist.bytecode.Mnemonic;

@Repository
public class StatisticsDAO implements IStatisticsDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public long numberOfEnterprises() {
		String query = "SELECT count(*) FROM Enterprise a";
		return (long) entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public long numberOfExternalTickets() {
		String query = "SELECT count(*) FROM ExternalTicket a";
		return (long) entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public long numberOfInternalTickets() {
		String query = "SELECT count(*) FROM InternalTicket a";
		return (long) entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public long numberOfSyncRegisters() {
		String query = "SELECT count(*) FROM SynchronizationRegister a";
		return (long) entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public List numberOfInternalTicketsPerEnterprise() {
		String query = "SELECT e.name, e.internalTickets.size FROM Enterprise e";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public int[] numberOfClosedInternalTicketsPerMonth(Date start, Date end) {
		Query query = entityManager.createQuery("SELECT iT.ticket.closeDate FROM InternalTicket iT "
				+ "WHERE (iT.ticket.closeDate > :start) AND (iT.ticket.closeDate < :end)");
		query.setParameter("start",start,TemporalType.DATE);
		query.setParameter("end",end,TemporalType.DATE);
		
		return calculateTicketsPerMonth(query);
	}

	@Override
	public int[] numberOfCreatedInternalTicketsPerMonth(Date start, Date end) {		
		Query query = entityManager.createQuery("SELECT iT.ticket.openDate FROM InternalTicket iT "
				+ "WHERE (iT.ticket.openDate > :start) AND (iT.ticket.openDate < :end)");
		query.setParameter("start",start,TemporalType.DATE);
		query.setParameter("end",end,TemporalType.DATE);
		
		return calculateTicketsPerMonth(query);
	}
	
	public int[] calculateTicketsPerMonth(Query query){
		
		int[] ticketsPerMonth = new int[12];
		
		for (Object data : query.getResultList()) {
			
			int ticketMont = ((Timestamp) data).toLocalDateTime().getMonthValue();
			
			switch (ticketMont) {
			case 1:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 2:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 3:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 4:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 5:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 6:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 7:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 8:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 9:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 10:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 11:
				ticketsPerMonth[ticketMont-1]++;
				break;
			case 12:
				ticketsPerMonth[ticketMont-1]++;
				break;
				
			default:
				break;
			}
		}
		return ticketsPerMonth;
	}

}
