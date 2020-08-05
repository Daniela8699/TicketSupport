package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRIORIDAD database table.
 * 
 */
@Entity
@NamedQuery(name="Prioridad.findAll", query="SELECT p FROM Prioridad p")
public class Prioridad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRIORIDAD_ID_GENERATOR", sequenceName="PRIORIDAD_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRIORIDAD_ID_GENERATOR")
	private long id;

	private String nombre;

	//bi-directional many-to-one association to InternalTicket
	@OneToMany(mappedBy="prioridad")
	private List<InternalTicket> internalTickets;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	private EntityState entityState;

	public Prioridad() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<InternalTicket> getInternalTickets() {
		return this.internalTickets;
	}

	public void setInternalTickets(List<InternalTicket> internalTickets) {
		this.internalTickets = internalTickets;
	}

	public InternalTicket addInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().add(internalTicket);
		internalTicket.setPrioridad(this);

		return internalTicket;
	}

	public InternalTicket removeInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().remove(internalTicket);
		internalTicket.setPrioridad(null);

		return internalTicket;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

}