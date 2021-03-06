package com.medved.support.rest.interfaces;

import java.util.Date;
import java.util.List;

public interface IStatisticsRestController {

	public long numberOfEnterprises();
	public long numberOfExternalTickets();
	public long numberOfInternalTickets();
	public long numberOfSyncRegisters();
	public List numberOfInternalTicketsPerEnterprise();
	public int[] numberOfCreatedInternalTicketsPerMonth(Date start, Date end);
	public int[] numberOfClosedInternalTicketsPerMonth(Date start, Date end);
}
