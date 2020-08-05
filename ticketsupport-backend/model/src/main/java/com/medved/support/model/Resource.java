package com.medved.support.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.ToString;


/**
 * The persistent class for the RESOURCES database table.
 * 
 */
@Entity
@Table(name="RESOURCES")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
@ToString
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RESOURCES_ID_GENERATOR", sequenceName = "RESOURCES_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESOURCES_ID_GENERATOR")
	private long id;

	
	@Column(name="ATTACHED_FILE")
	@NotNull
	@Lob
	private byte[] attachedFile;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@NotNull
	@JsonIgnore
	private EntityState entityState;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	@NotNull 
	@JsonIgnore
	private Ticket ticket;

	@ManyToOne
	@NotNull
	@JsonIgnore
	private Answer answer;


	public Resource() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public byte[] getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(byte[] attachedFile) {
		this.attachedFile = attachedFile;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}