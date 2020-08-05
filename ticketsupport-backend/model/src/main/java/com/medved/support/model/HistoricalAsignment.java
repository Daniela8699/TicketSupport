package com.medved.support.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * The persistent class for the HISTORICAL_ASIGNMENTS database table.
 * 
 */
@Entity
@Table(name="HISTORICAL_ASIGNMENTS")
@NamedQuery(name="HistoricalAsignment.findAll", query="SELECT h FROM HistoricalAsignment h")
public class HistoricalAsignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HISTORICAL_ASIGNMENTS_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HISTORICAL_ASIGNMENTS_ID_GENERATOR")
	@NotNull
	private long id;

	@Column(name="DATE_ASIGNED")
	@NotNull
	private Timestamp dateAsigned;

	@Column(name="DATE_REMOVED")
	@NotNull
	private Timestamp dateRemoved;

	@Column(name="REASON_REMOVED")
	@Size(max = 200)
	@NotBlank
	private String reasonRemoved;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	@NotNull
	private EntityState entityState;

	//bi-directional many-to-one association to InternalTicket
	@ManyToOne
	@JoinColumn(name="INTERNAL_TICKET_TICKET_ID", referencedColumnName = "TICKET_ID")
	@NotNull
	private InternalTicket internalTicket;

	//bi-directional many-to-one association to User
	@ManyToOne
	@NotNull
	private User user;

	public HistoricalAsignment() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDateAsigned() {
		return this.dateAsigned;
	}

	public void setDateAsigned(Timestamp dateAsigned) {
		this.dateAsigned = dateAsigned;
	}

	public Timestamp getDateRemoved() {
		return this.dateRemoved;
	}

	public void setDateRemoved(Timestamp dateRemoved) {
		this.dateRemoved = dateRemoved;
	}

	public String getReasonRemoved() {
		return this.reasonRemoved;
	}

	public void setReasonRemoved(String reasonRemoved) {
		this.reasonRemoved = reasonRemoved;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

	public InternalTicket getInternalTicket() {
		return this.internalTicket;
	}

	public void setInternalTicket(InternalTicket internalTicket) {
		this.internalTicket = internalTicket;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}