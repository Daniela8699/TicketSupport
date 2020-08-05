package com.medved.support.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
//import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import lombok.AllArgsConstructor;
//import lombok.ToString;


/**
 * The persistent class for the TICKET database table.
 * 
 */
@Entity
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
//@AllArgsConstructor
//@Embeddable
//@ToString
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	private long id;

	@Column(name="CLOSE_DATE")
	private Date closeDate;

	@NotBlank
	private String description;
	
	@Column(name="IS_ANSWERED")
	@NotNull
	private boolean isAnswered;

	@Column(name="LAST_ACTIVITY_DATE")
	@PastOrPresent
	@NotNull
	private Date lastActivityDate;

	@Column(name="OPEN_DATE")
	@NotNull
	@PastOrPresent
	private Date openDate;

	@Size(max = 200)
	@NotBlank
	private String title;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="ticket")
	//@JsonIgnore
	private List<Answer> answers;

	//bi-directional one-to-one association to ExternalTicket
	@OneToOne(mappedBy="ticket")
	@JsonIgnore
	private ExternalTicket externalTicket;

	//bi-directional one-to-one association to InternalTicket
	@OneToOne(mappedBy="ticket")
	@JsonIgnore
	private InternalTicket internalTicket;

	//bi-directional many-to-one association to Resource
	@OneToMany(mappedBy="ticket")
	@JsonIgnore
	private List<Resource> resources;

	public Ticket() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Date date) {
		this.closeDate = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastActivityDate() {
		return this.lastActivityDate;
	}

	public void setLastActivityDate(Date date) {
		this.lastActivityDate = date;
	}

	public Date getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Date date) {
		this.openDate = date;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setTicket(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setTicket(null);

		return answer;
	}

	public ExternalTicket getExternalTicket() {
		return this.externalTicket;
	}

	public void setExternalTicket(ExternalTicket externalTicket) {
		this.externalTicket = externalTicket;
	}

	public InternalTicket getInternalTicket() {
		return this.internalTicket;
	}

	public void setInternalTicket(InternalTicket internalTicket) {
		this.internalTicket = internalTicket;
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Resource addResource(Resource resource) {
		getResources().add(resource);
		resource.setTicket(this);

		return resource;
	}

	public Resource removeResource(Resource resource) {
		getResources().remove(resource);
		resource.setTicket(null);

		return resource;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

}