package com.medved.support.logic.interfaces;

import java.util.Date;
import java.util.List;

public interface IStatisticsService {

	public long numberOfEnterprises();
	public long numberOfExternalTickets();
	public long numberOfInternalTickets();
	public long numberOfSyncRegisters();
	public List numberOfInternalTicketsPerEnterprise();
	public int[] numberOfCreatedInternalTicketsPerMonth(Date start, Date end);
	public int[] numberOfClosedInternalTicketsPerMonth(Date start, Date end);
}
