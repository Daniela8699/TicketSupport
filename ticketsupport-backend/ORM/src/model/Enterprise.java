package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ENTERPRISE database table.
 * 
 */
@Entity
@NamedQuery(name="Enterprise.findAll", query="SELECT e FROM Enterprise e")
public class Enterprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ENTERPRISE_NIT_GENERATOR", sequenceName="ENTERPRISE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENTERPRISE_NIT_GENERATOR")
	private String nit;

	@Column(name="CONTACT_MAIL")
	private String contactMail;

	@Column(name="CONTACT_PHONE")
	private String contactPhone;

	private String description;

	private String name;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	private EntityState entityState;

	//bi-directional many-to-one association to InternalTicket
	@OneToMany(mappedBy="enterprise")
	private List<InternalTicket> internalTickets;

	//bi-directional many-to-many association to Source
	@ManyToMany(mappedBy="enterprises")
	private List<Source> sources;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="enterprises")
	private List<User> users;

	public Enterprise() {
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getContactMail() {
		return this.contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

	public List<InternalTicket> getInternalTickets() {
		return this.internalTickets;
	}

	public void setInternalTickets(List<InternalTicket> internalTickets) {
		this.internalTickets = internalTickets;
	}

	public InternalTicket addInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().add(internalTicket);
		internalTicket.setEnterprise(this);

		return internalTicket;
	}

	public InternalTicket removeInternalTicket(InternalTicket internalTicket) {
		getInternalTickets().remove(internalTicket);
		internalTicket.setEnterprise(null);

		return internalTicket;
	}

	public List<Source> getSources() {
		return this.sources;
	}

	public void setSources(List<Source> sources) {
		this.sources = sources;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}