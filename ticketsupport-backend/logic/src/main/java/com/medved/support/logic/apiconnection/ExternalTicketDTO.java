package com.medved.support.logic.apiconnection;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medved.support.model.Answer;


public class ExternalTicketDTO {

	@JsonProperty("closed_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeDate;

	@JsonProperty("body")
	private String description;

	@JsonProperty("is_answered")
	private boolean isAnswered;

	@JsonProperty("last_activity_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastActivityDate;

	@JsonProperty("creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date openDate;

	@JsonProperty("title")
	private String title;

	@JsonProperty("answers")
	private List<Answer> answers;

	@JsonProperty("link")
	private String link;

	@JsonProperty("score")
	private BigDecimal score;
	
	public ExternalTicketDTO() {
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate= new Date(closeDate.getTime()*1000);
		//this.closeDate = closeDate;
	}

	public Date getLastActivityDate() {
		return lastActivityDate;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate= new Date(lastActivityDate.getTime()*1000);
		//this.lastActivityDate = lastActivityDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate= new Date(openDate.getTime()*1000);
		//this.openDate = openDate;
	}
	
	
}
