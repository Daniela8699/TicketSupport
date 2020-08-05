package com.medved.support.logic.implementations;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.IStatisticsService;
import com.medved.support.repository.interfaces.IStatisticsDAO;

@Service
public class StatisticsService implements IStatisticsService{

	@Autowired
	private IStatisticsDAO statisticsDAO;

	@Override
	@Transactional
	public long numberOfEnterprises() {
		return statisticsDAO.numberOfEnterprises();
	}

	@Override
	@Transactional
	public long numberOfExternalTickets() {
		return statisticsDAO.numberOfExternalTickets();
	}

	@Override
	@Transactional
	public long numberOfInternalTickets() {
		return statisticsDAO.numberOfInternalTickets();
	}

	@Override
	@Transactional
	public long numberOfSyncRegisters() {
		return statisticsDAO.numberOfSyncRegisters();

	}

	@Override
	@Transactional
	public List numberOfInternalTicketsPerEnterprise() {
		return statisticsDAO.numberOfInternalTicketsPerEnterprise();
	}

	@Override
	@Transactional
	public int[] numberOfClosedInternalTicketsPerMonth(Date start, Date end) {
		return statisticsDAO.numberOfClosedInternalTicketsPerMonth(start, end);
	}

	@Override
	@Transactional
	public int[] numberOfCreatedInternalTicketsPerMonth(Date start, Date end) {
		return statisticsDAO.numberOfCreatedInternalTicketsPerMonth(start, end);
	}
	
	
}
