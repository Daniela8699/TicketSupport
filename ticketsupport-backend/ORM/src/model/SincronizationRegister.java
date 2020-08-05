package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SINCRONIZATION_REGISTER database table.
 * 
 */
@Entity
@Table(name="SINCRONIZATION_REGISTER")
@NamedQuery(name="SincronizationRegister.findAll", query="SELECT s FROM SincronizationRegister s")
public class SincronizationRegister implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SINCRONIZATION_REGISTER_ID_GENERATOR", sequenceName="SINCRONIZATION_REGISTER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SINCRONIZATION_REGISTER_ID_GENERATOR")
	private long id;

	@Column(name="DELETED_TICKETS")
	private BigDecimal deletedTickets;

	@Column(name="SAVED_TICKETS")
	private BigDecimal savedTickets;

	@Column(name="SECONDS_TO_COMPLETE")
	private BigDecimal secondsToComplete;

	@Column(name="SINCRONIZATION_DATE")
	private Timestamp sincronizationDate;

	public SincronizationRegister() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getDeletedTickets() {
		return this.deletedTickets;
	}

	public void setDeletedTickets(BigDecimal deletedTickets) {
		this.deletedTickets = deletedTickets;
	}

	public BigDecimal getSavedTickets() {
		return this.savedTickets;
	}

	public void setSavedTickets(BigDecimal savedTickets) {
		this.savedTickets = savedTickets;
	}

	public BigDecimal getSecondsToComplete() {
		return this.secondsToComplete;
	}

	public void setSecondsToComplete(BigDecimal secondsToComplete) {
		this.secondsToComplete = secondsToComplete;
	}

	public Timestamp getSincronizationDate() {
		return this.sincronizationDate;
	}

	public void setSincronizationDate(Timestamp sincronizationDate) {
		this.sincronizationDate = sincronizationDate;
	}

}