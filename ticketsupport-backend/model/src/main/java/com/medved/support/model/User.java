package com.medved.support.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the "User" database table.
 * 
 */
@Entity
@Table(name="APP_USER")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Size(max = 20)
	@NotBlank
	private String username;

	@Column(name="API_KEY")
	@Size(max = 100)
	private String apiKey;

	@Size(max = 40)
	@NotBlank
	private String email;

	@Column(name="LAST_NAME")
	@Size(max = 20)
	@NotBlank
	private String lastName;

	@Size(max = 20)
	@NotBlank
	private String name;

	@Size(max = 200)
	@NotBlank
	private String password;

	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	private Enterprise enterprise;

	//bi-directional many-to-one association to HistoricalAsignment
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<HistoricalAsignment> historicalAsignments;

	//bi-directional many-to-one association to InternalTicket
	@OneToMany(mappedBy="assignedTo")
	@JsonIgnore
	private List<InternalTicket> internalTickets1;

	//bi-directional many-to-one association to InternalTicket
	@OneToMany(mappedBy="createdBy")
	@JsonIgnore
	private List<InternalTicket> internalTickets2;

	//bi-directional many-to-one association to SearchMade
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<SearchMade> searchMades;

	//bi-directional many-to-many association to Rol
	@ManyToMany(fetch = FetchType.EAGER)
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

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	@NotNull
	private EntityState entityState;

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

	public Enterprise getEnterprises() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
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
		internalTickets1.setUserAssigned(this);

		return internalTickets1;
	}

	public InternalTicket removeInternalTickets1(InternalTicket internalTickets1) {
		getInternalTickets1().remove(internalTickets1);
		internalTickets1.setUserAssigned(null);

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
		internalTickets2.setUserCreator(this);

		return internalTickets2;
	}

	public InternalTicket removeInternalTickets2(InternalTicket internalTickets2) {
		getInternalTickets2().remove(internalTickets2);
		internalTickets2.setUserCreator(null);

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

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

}