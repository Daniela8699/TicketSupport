package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the ANSWER database table.
 * 
 */
@Entity
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ANSWER_ID_GENERATOR", sequenceName="ANSWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ANSWER_ID_GENERATOR")
	@Temporal(TemporalType.TIMESTAMP)
	private Date id;

	@Column(name="ANS_BODY")
	private String ansBody;

	@Column(name="CREATION_DATE")
	private Timestamp creationDate;

	@Column(name="IS_ACCEPTED")
	private String isAccepted;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	private Ticket ticket;

	public Answer() {
	}

	public Date getId() {
		return this.id;
	}

	public void setId(Date id) {
		this.id = id;
	}

	public String getAnsBody() {
		return this.ansBody;
	}

	public void setAnsBody(String ansBody) {
		this.ansBody = ansBody;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getIsAccepted() {
		return this.isAccepted;
	}

	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}