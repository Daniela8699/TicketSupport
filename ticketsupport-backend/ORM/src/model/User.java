package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "User" database table.
 * 
 */
@Entity
@Table(name="\"User\"")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;

	@Column(name="API_KEY")
	private String apiKey;

	private String email;

	@Column(name="LAST_NAME")
	private String lastName;

	private String name;

	private String password;

	//bi-directional many-to-one association to HistoricalAsignment
	@OneToMany(mappedBy="user")
	private List<HistoricalAsignment> historicalAsignments;

	//bi-directional many-to-one association to InternalTicket
	@OneToMany(mappedBy="user1")
	private List<InternalTicket> internalTickets1;

	//bi-directional many-to-one association to InternalTicket
	@OneToMany(mappedBy="user2")
	private List<InternalTicket> internalTickets2;

	//bi-directional many-to-one association to SearchMade
	@OneToMany(mappedBy="user")
	private List<SearchMade> searchMades;

	//bi-directional many-to-many association to Enterprise
	@ManyToMany
	@JoinTable(
		name="ENTERPRISE_USERS"
		, joinColumns={
			@JoinColumn(name="USER_USERNAME")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ENTERPRISE_NIT")
			}
		)
	private List<Enterprise> enterprises;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	private EntityState entityState;

	//bi-directional many-to-many association to Rol
	@ManyToMany
	@JoinTable(
		name="USER_ROLS"
		, joinColumns={
			@JoinColumn(name="USER_USERNAME")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ROL_ID_ROL")
			}
		)
	private List<Rol> rols;

	public User() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<HistoricalAsignment> getHistoricalAsignments() {
		return this.historicalAsignments;
	}

	public void setHistoricalAsignments(List<HistoricalAsignment> historicalAsignments) {
		this.historicalAsignments = historicalAsignments;
	}

	public HistoricalAsignment addHistoricalAsignment(HistoricalAsignment historicalAsignment) {
		getHistoricalAsignments().add(historicalAsignment);
		historicalAsignment.setUser(this);

		return historicalAsignment;
	}

	public HistoricalAsignment removeHistoricalAsignment(HistoricalAsignment historicalAsignment) {
		getHistoricalAsignments().remove(historicalAsignment);
		historicalAsignment.setUser(null);

		return historicalAsignment;
	}

	public List<InternalTicket> getInternalTickets1() {
		return this.internalTickets1;
	}

	public void setInternalTickets1(List<InternalTicket> internalTickets1) {
		this.internalTickets1 = internalTickets1;
	}

	public InternalTicket addInternalTickets1(InternalTicket internalTickets1) {
		getInternalTickets1().add(internalTickets1);
		internalTickets1.setUser1(this);

		return internalTickets1;
	}

	public InternalTicket removeInternalTickets1(InternalTicket internalTickets1) {
		getInternalTickets1().remove(internalTickets1);
		internalTickets1.setUser1(null);

		return internalTickets1;
	}

	public List<InternalTicket> getInternalTickets2() {
		return this.internalTickets2;
	}

	public void setInternalTickets2(List<InternalTicket> internalTickets2) {
		this.internalTickets2 = internalTickets2;
	}

	public InternalTicket addInternalTickets2(InternalTicket internalTickets2) {
		getInternalTickets2().add(internalTickets2);
		internalTickets2.setUser2(this);

		return internalTickets2;
	}

	public InternalTicket removeInternalTickets2(InternalTicket internalTickets2) {
		getInternalTickets2().remove(internalTickets2);
		internalTickets2.setUser2(null);

		return internalTickets2;
	}

	public List<SearchMade> getSearchMades() {
		return this.searchMades;
	}

	public void setSearchMades(List<SearchMade> searchMades) {
		this.searchMades = searchMades;
	}

	public SearchMade addSearchMade(SearchMade searchMade) {
		getSearchMades().add(searchMade);
		searchMade.setUser(this);

		return searchMade;
	}

	public SearchMade removeSearchMade(SearchMade searchMade) {
		getSearchMades().remove(searchMade);
		searchMade.setUser(null);

		return searchMade;
	}

	public List<Enterprise> getEnterprises() {
		return this.enterprises;
	}

	public void setEnterprises(List<Enterprise> enterprises) {
		this.enterprises = enterprises;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

}