package com.medved.support.logic.apiconnection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketWrapper {

	@JsonProperty("items")
	private ExternalTicketDTO[] tickets;
	
	@JsonProperty("quota_max")
	private int maxQuerys;
	
	@JsonProperty("quota_remaining")
	private int remainingQuerys;
	
	@JsonProperty("backoff")
	private long backoff;
	
	@JsonProperty("has_more")
	private boolean has_more;
	
	public TicketWrapper() {
		
	}
	
	public TicketWrapper(ExternalTicketDTO[] tickets) {
		this.tickets = tickets;
	}

	public ExternalTicketDTO[] getTickets() {
		return tickets;
	}

	public void setTickets(ExternalTicketDTO[] tickets) {
		this.tickets = tickets;
	}

	public int getMaxQuerys() {
		return maxQuerys;
	}

	public void setMaxQuerys(int maxQuerys) {
		this.maxQuerys = maxQuerys;
	}

	public int getRemainingQuerys() {
		return remainingQuerys;
	}

	public void setRemainingQuerys(int remainingQuerys) {
		this.remainingQuerys = remainingQuerys;
	}

	public long getBackoff() {
		return backoff;
	}

	public void setBackoff(long backoff) {
		this.backoff = backoff;
	}

	public boolean isHas_more() {
		return has_more;
	}

	public void setHas_more(boolean has_more) {
		this.has_more = has_more;
	}
	
	
}
