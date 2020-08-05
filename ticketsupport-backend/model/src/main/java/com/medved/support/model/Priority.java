package com.medved.support.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the PRIORIDAD database table.
 * 
 */
@Entity
@NamedQuery(name="Priority.findAll", query="SELECT p FROM Priority p")
public class Priority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRIORITY_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRIORITY_ID_GENERATOR")
	@NotNull
	private long id;

	@Size(max = 40)
	@NotBlank
	private String name;

	//bi-directional many-to-one association to InternalTicket
	@OneToMany(mappedBy="priority")
	@JsonIgnore
	private List<InternalTicket> internalTickets;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	@NotNull
	private EntityState entityState;

	public Priority() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
	}

	public List<InternalTicket> getInternalTickets() {
		return this.internalTickets;
	}

	public void setInternalTickets(List<InternalTicket> internalTickets) {
		this.internalTickets = internalTickets;
	}

	public InternalTicket addInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().add(internalTicket);
		internalTicket.setPrioridad(this);

		return internalTicket;
	}

	public InternalTicket removeInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().remove(internalTicket);
		internalTicket.setPrioridad(null);

		return internalTicket;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

}