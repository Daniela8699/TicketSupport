package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HISTORICAL_ASIGNMENTS database table.
 * 
 */
@Entity
@Table(name="HISTORICAL_ASIGNMENTS")
@NamedQuery(name="HistoricalAsignment.findAll", query="SELECT h FROM HistoricalAsignment h")
public class HistoricalAsignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HISTORICAL_ASIGNMENTS_ID_GENERATOR", sequenceName="HISTORICAL_ASIGNMENTS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HISTORICAL_ASIGNMENTS_ID_GENERATOR")
	private long id;

	@Column(name="DATE_ASIGNED")
	private Timestamp dateAsigned;

	@Column(name="DATE_REMOVED")
	private Timestamp dateRemoved;

	@Column(name="REASON_REMOVED")
	private String reasonRemoved;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	private EntityState entityState;

	//bi-directional many-to-one association to InternalTicket
	@ManyToOne
	@JoinColumn(name="INTERNAL_TICKET_TICKET_ID")
	private InternalTicket internalTicket;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public HistoricalAsignment() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDateAsigned() {
		return this.dateAsigned;
	}

	public void setDateAsigned(Timestamp dateAsigned) {
		this.dateAsigned = dateAsigned;
	}

	public Timestamp getDateRemoved() {
		return this.dateRemoved;
	}

	public void setDateRemoved(Timestamp dateRemoved) {
		this.dateRemoved = dateRemoved;
	}

	public String getReasonRemoved() {
		return this.reasonRemoved;
	}

	public void setReasonRemoved(String reasonRemoved) {
		this.reasonRemoved = reasonRemoved;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

	public InternalTicket getInternalTicket() {
		return this.internalTicket;
	}

	public void setInternalTicket(InternalTicket internalTicket) {
		this.internalTicket = internalTicket;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}