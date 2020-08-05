package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the INTERNAL_TICKET database table.
 * 
 */
@Entity
@Table(name="INTERNAL_TICKET")
@NamedQuery(name="InternalTicket.findAll", query="SELECT i FROM InternalTicket i")
public class InternalTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INTERNAL_TICKET_TICKETID_GENERATOR", sequenceName="INTERNAL_TICKET_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INTERNAL_TICKET_TICKETID_GENERATOR")
	@Column(name="TICKET_ID")
	private long ticketId;

	//bi-directional many-to-one association to HistoricalAsignment
	@OneToMany(mappedBy="internalTicket")
	private List<HistoricalAsignment> historicalAsignments;

	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	private Enterprise enterprise;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	private EntityState entityState;

	//bi-directional many-to-one association to Prioridad
	@ManyToOne
	private Prioridad prioridad;

	//bi-directional one-to-one association to Ticket
	@OneToOne
	private Ticket ticket;

	//bi-directional many-to-one association to TicketState
	@ManyToOne
	@JoinColumn(name="TICKET_STATE_ID")
	private TicketState ticketState;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_USERNAME")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_USERNAME2")
	private User user2;

	public InternalTicket() {
	}

	public long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public List<HistoricalAsignment> getHistoricalAsignments() {
		return this.historicalAsignments;
	}

	public void setHistoricalAsignments(List<HistoricalAsignment> historicalAsignments) {
		this.historicalAsignments = historicalAsignments;
	}

	public HistoricalAsignment addHistoricalAsignment(HistoricalAsignment historicalAsignment) {
		getHistoricalAsignments().add(historicalAsignment);
		historicalAsignment.setInternalTicket(this);

		return historicalAsignment;
	}

	public HistoricalAsignment removeHistoricalAsignment(HistoricalAsignment historicalAsignment) {
		getHistoricalAsignments().remove(historicalAsignment);
		historicalAsignment.setInternalTicket(null);

		return historicalAsignment;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

	public Prioridad getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public TicketState getTicketState() {
		return this.ticketState;
	}

	public void setTicketState(TicketState ticketState) {
		this.ticketState = ticketState;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}