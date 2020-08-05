package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ENTITY_STATE database table.
 * 
 */
@Entity
@Table(name="ENTITY_STATE")
@NamedQuery(name="EntityState.findAll", query="SELECT e FROM EntityState e")
public class EntityState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ENTITY_STATE_ID_GENERATOR", sequenceName="ENTITY_STATE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENTITY_STATE_ID_GENERATOR")
	private long id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Enterprise
	@OneToMany(mappedBy="entityState")
	private List<Enterprise> enterprises;

	//bi-directional many-to-one association to HistoricalAsignment
	@OneToMany(mappedBy="entityState")
	private List<HistoricalAsignment> historicalAsignments;

	//bi-directional many-to-one association to InternalTicket
	@OneToMany(mappedBy="entityState")
	private List<InternalTicket> internalTickets;

	//bi-directional many-to-one association to Prioridad
	@OneToMany(mappedBy="entityState")
	private List<Prioridad> prioridads;

	//bi-directional many-to-one association to Resource
	@OneToMany(mappedBy="entityState")
	private List<Resource> resources;

	//bi-directional many-to-one association to Rol
	@OneToMany(mappedBy="entityState")
	private List<Rol> rols;

	//bi-directional many-to-one association to Source
	@OneToMany(mappedBy="entityState")
	private List<Source> sources;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="entityState")
	private List<Ticket> tickets;

	//bi-directional many-to-one association to TicketState
	@OneToMany(mappedBy="entityState")
	private List<TicketState> ticketStates;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="entityState")
	private List<User> users;

	public EntityState() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Enterprise> getEnterprises() {
		return this.enterprises;
	}

	public void setEnterprises(List<Enterprise> enterprises) {
		this.enterprises = enterprises;
	}

	public Enterprise addEnterpris(Enterprise enterpris) {
		getEnterprises().add(enterpris);
		enterpris.setEntityState(this);

		return enterpris;
	}

	public Enterprise removeEnterpris(Enterprise enterpris) {
		getEnterprises().remove(enterpris);
		enterpris.setEntityState(null);

		return enterpris;
	}

	public List<HistoricalAsignment> getHistoricalAsignments() {
		return this.historicalAsignments;
	}

	public void setHistoricalAsignments(List<HistoricalAsignment> historicalAsignments) {
		this.historicalAsignments = historicalAsignments;
	}

	public HistoricalAsignment addHistoricalAsignment(HistoricalAsignment historicalAsignment) {
		getHistoricalAsignments().add(historicalAsignment);
		historicalAsignment.setEntityState(this);

		return historicalAsignment;
	}

	public HistoricalAsignment removeHistoricalAsignment(HistoricalAsignment historicalAsignment) {
		getHistoricalAsignments().remove(historicalAsignment);
		historicalAsignment.setEntityState(null);

		return historicalAsignment;
	}

	public List<InternalTicket> getInternalTickets() {
		return this.internalTickets;
	}

	public void setInternalTickets(List<InternalTicket> internalTickets) {
		this.internalTickets = internalTickets;
	}

	public InternalTicket addInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().add(internalTicket);
		internalTicket.setEntityState(this);

		return internalTicket;
	}

	public InternalTicket removeInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().remove(internalTicket);
		internalTicket.setEntityState(null);

		return internalTicket;
	}

	public List<Prioridad> getPrioridads() {
		return this.prioridads;
	}

	public void setPrioridads(List<Prioridad> prioridads) {
		this.prioridads = prioridads;
	}

	public Prioridad addPrioridad(Prioridad prioridad) {
		getPrioridads().add(prioridad);
		prioridad.setEntityState(this);

		return prioridad;
	}

	public Prioridad removePrioridad(Prioridad prioridad) {
		getPrioridads().remove(prioridad);
		prioridad.setEntityState(null);

		return prioridad;
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Resource addResource(Resource resource) {
		getResources().add(resource);
		resource.setEntityState(this);

		return resource;
	}

	public Resource removeResource(Resource resource) {
		getResources().remove(resource);
		resource.setEntityState(null);

		return resource;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public Rol addRol(Rol rol) {
		getRols().add(rol);
		rol.setEntityState(this);

		return rol;
	}

	public Rol removeRol(Rol rol) {
		getRols().remove(rol);
		rol.setEntityState(null);

		return rol;
	}

	public List<Source> getSources() {
		return this.sources;
	}

	public void setSources(List<Source> sources) {
		this.sources = sources;
	}

	public Source addSource(Source source) {
		getSources().add(source);
		source.setEntityState(this);

		return source;
	}

	public Source removeSource(Source source) {
		getSources().remove(source);
		source.setEntityState(null);

		return source;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setEntityState(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setEntityState(null);

		return ticket;
	}

	public List<TicketState> getTicketStates() {
		return this.ticketStates;
	}

	public void setTicketStates(List<TicketState> ticketStates) {
		this.ticketStates = ticketStates;
	}

	public TicketState addTicketState(TicketState ticketState) {
		getTicketStates().add(ticketState);
		ticketState.setEntityState(this);

		return ticketState;
	}

	public TicketState removeTicketState(TicketState ticketState) {
		getTicketStates().remove(ticketState);
		ticketState.setEntityState(null);

		return ticketState;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setEntityState(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setEntityState(null);

		return user;
	}

}