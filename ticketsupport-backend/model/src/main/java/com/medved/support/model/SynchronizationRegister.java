package com.medved.support.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.ToString;


/**
 * The persistent class for the SINCRONIZATION_REGISTER database table.
 * 
 */
@Entity
@Table(name="SINCRONIZATION_REGISTER")
@NamedQuery(name="SynchronizationRegister.findAll", query="SELECT s FROM SynchronizationRegister s")
@ToString
public class SynchronizationRegister implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	private long id;

	@Column(name="DELETED_TICKETS")
	@NotNull
	private BigDecimal deletedTickets;

	@Column(name="SAVED_TICKETS")
	@NotNull
	private BigDecimal savedTickets;

	@Column(name="SECONDS_TO_COMPLETE")
	@NotNull
	private long secondsToComplete;

	@Column(name="INITIAL_DATE")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date initialDate;
	
	@Column(name="FINAL_DATE")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date finalDate;

	public SynchronizationRegister() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getDeletedTickets() {
		return this.deletedTickets;
	}

	public void setDeletedTickets(BigDecimal deletedTickets) {
		this.deletedTickets = deletedTickets;
	}

	public BigDecimal getSavedTickets() {
		return this.savedTickets;
	}

	public void setSavedTickets(BigDecimal savedTickets) {
		this.savedTickets = savedTickets;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public long getSecondsToComplete() {
		return secondsToComplete;
	}

	public void setSecondsToComplete(long secondsToComplete) {
		this.secondsToComplete = secondsToComplete;
	}
	
}