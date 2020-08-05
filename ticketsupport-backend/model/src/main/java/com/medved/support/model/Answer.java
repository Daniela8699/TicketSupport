package com.medved.support.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

/**
 * The persistent class for the ANSWER database table.
 * 
 */
@Entity
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
@ToString
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long id;

	@Column(name="BODY") 
	@NotBlank
	@JsonProperty("body")
	private String body;
	
	@Column(name="CREATION_DATE")
	@NotNull
	@JsonProperty("creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name="IS_ACCEPTED")
	@NotNull
	@JsonProperty("is_accepted")
	private boolean isAccepted;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	@NotNull
	@JsonIgnore
	private Ticket ticket;

	public Answer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate= new Date(creationDate.getTime()*1000);
		//this.creationDate = creationDate;
	}
	
	public void setCreationDateAnswer(Date creationDate) {
		this.creationDate=creationDate;
	}
	

	public boolean getIsAccepted() {
		return this.isAccepted;
	}

	public void setIsAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}