package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the EXTERNAL_TICKET database table.
 * 
 */
@Entity
@Table(name="EXTERNAL_TICKET")
@NamedQuery(name="ExternalTicket.findAll", query="SELECT e FROM ExternalTicket e")
public class ExternalTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXTERNAL_TICKET_TICKETID_GENERATOR", sequenceName="EXTERNAL_TICKET_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXTERNAL_TICKET_TICKETID_GENERATOR")
	@Column(name="TICKET_ID")
	private long ticketId;

	private String link;

	private BigDecimal score;

	//bi-directional many-to-one association to Source
	@ManyToOne
	private Source source;

	//bi-directional one-to-one association to Ticket
	@OneToOne
	private Ticket ticket;

	public ExternalTicket() {
	}

	public long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
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