package com.medved.support.logic.apiconnection;

import java.util.Date;

import com.medved.support.model.ExternalTicket;
import com.medved.support.model.Source;

public interface IStackExchangeConnectionService {
	
	public Iterable<ExternalTicket> synchronization();
	public void initialLoad(Source source, Date firstDate, Date lastDate);
	public void saveTicketBatch(ExternalTicketDTO[] externalTicketDTOs, long sourceId, Date initialDate, 
																					Date finalDate, long startTime);
}
