package com.medved.support.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the INTERNAL_TICKET database table.
 * 
 */
@Entity
@Table(name="INTERNAL_TICKET")
@NamedQuery(name="InternalTicket.findAll", query="SELECT i FROM InternalTicket i")
public class InternalTicket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	
	//bi-directional many-to-one association to HistoricalAsignment
	@OneToMany(mappedBy="internalTicket")
	@JsonIgnore
	private List<HistoricalAsignment> historicalAsignments;

	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	@NotNull
	private Enterprise enterprise;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	@NotNull
	private EntityState entityState;

	//bi-directional many-to-one association to Priority
	@ManyToOne
	@NotNull
	@JoinColumn(name="PRIORITY_ID")
	private Priority priority;

	//bi-directional one-to-one association to Ticket
	@OneToOne
	@MapsId
    @JoinColumn(name="TICKET_ID")
	private Ticket ticket;

	//bi-directional many-to-one association to TicketState
	@ManyToOne
	@JoinColumn(name="TICKET_STATE_ID")
	@NotNull
	private TicketState ticketState;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="ASSIGNED_USERNAME")
	private User assignedTo;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="CREATED_BY_USERNAME")
	@NotNull
	private User createdBy;

	public InternalTicket() {
	}

	public List<HistoricalAsignment> getHistoricalAsignments() {
		return this.historicalAsignments;
	}

	public void setHistoricalAsignments(List<HistoricalAsignment> historicalAsignments) {
		this.historicalAsignments = historicalAsignments;
	}

	public HistoricalAsignment addHistoricalAsignment(HistoricalAsignment historicalAsignment) {
		getHistoricalAsignments().add(historicalAsignment);
		historicalAsignment.setInternalTicket(this);

		return historicalAsignment;
	}

	public HistoricalAsignment removeHistoricalAsignment(HistoricalAsignment historicalAsignment) {
		getHistoricalAsignments().remove(historicalAsignment);
		historicalAsignment.setInternalTicket(null);

		return historicalAsignment;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

	public Priority getPrioridad() {
		return this.priority;
	}

	public void setPrioridad(Priority priority) {
		this.priority = priority;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public TicketState getTicketState() {
		return this.ticketState;
	}

	public void setTicketState(TicketState ticketState) {
		this.ticketState = ticketState;
	}

	public User getUserAssigned() {
		return this.assignedTo;
	}

	public void setUserAssigned(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public User getUserCreator() {
		return this.createdBy;
	}

	public void setUserCreator(User user2) {
		this.createdBy = user2;
	}

}