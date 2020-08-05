package com.medved.support.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the EXTERNAL_TICKET database table.
 * 
 */
@Entity
@Table(name="EXTERNAL_TICKET")
@NamedQuery(name="ExternalTicket.findAll", query="SELECT e FROM ExternalTicket e")
//@AllArgsConstructor
//@ToString
public class ExternalTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	private String link;

	@NotNull
	private BigDecimal score;

	//bi-directional many-to-one association to Source
	@ManyToOne
	@NotNull
	@JsonIgnore
	private Source source;

	//bi-directional one-to-one association to Ticket
	@OneToOne
	@MapsId
    @JoinColumn(name="TICKET_ID")
	private Ticket ticket;

	public ExternalTicket() {
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public BigDecimal getScore() {
		return this.score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Source getSource() {
		return this.source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}