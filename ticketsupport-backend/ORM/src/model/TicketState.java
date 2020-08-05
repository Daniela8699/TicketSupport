package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TICKET_STATE database table.
 * 
 */
@Entity
@Table(name="TICKET_STATE")
@NamedQuery(name="TicketState.findAll", query="SELECT t FROM TicketState t")
public class TicketState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TICKET_STATE_ID_GENERATOR", sequenceName="TICKET_STATE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TICKET_STATE_ID_GENERATOR")
	private long id;

	@Column(name="STATE_NAME")
	private String stateName;

	//bi-directional many-to-one association to InternalTicket
	@OneToMany(mappedBy="ticketState")
	private List<InternalTicket> internalTickets;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	private EntityState entityState;

	public TicketState() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<InternalTicket> getInternalTickets() {
		return this.internalTickets;
	}

	public void setInternalTickets(List<InternalTicket> internalTickets) {
		this.internalTickets = internalTickets;
	}

	public InternalTicket addInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().add(internalTicket);
		internalTicket.setTicketState(this);

		return internalTicket;
	}

	public InternalTicket removeInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().remove(internalTicket);
		internalTicket.setTicketState(null);

		return internalTicket;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

}