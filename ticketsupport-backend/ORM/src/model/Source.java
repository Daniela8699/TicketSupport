package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SOURCE database table.
 * 
 */
@Entity
@NamedQuery(name="Source.findAll", query="SELECT s FROM Source s")
public class Source implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOURCE_ID_GENERATOR", sequenceName="SOURCE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOURCE_ID_GENERATOR")
	private long id;

	@Column(name="SOURCE_NAME")
	private String sourceName;

	//bi-directional many-to-one association to ExternalTicket
	@OneToMany(mappedBy="source")
	private List<ExternalTicket> externalTickets;

	//bi-directional many-to-many association to Enterprise
	@ManyToMany
	@JoinTable(
		name="SOURCES_ALLOWED"
		, joinColumns={
			@JoinColumn(name="SOURCE_ID")
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

	public Source() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public List<ExternalTicket> getExternalTickets() {
		return this.externalTickets;
	}

	public void setExternalTickets(List<ExternalTicket> externalTickets) {
		this.externalTickets = externalTickets;
	}

	public ExternalTicket addExternalTicket(ExternalTicket externalTicket) {
		getExternalTickets().add(externalTicket);
		externalTicket.setSource(this);

		return externalTicket;
	}

	public ExternalTicket removeExternalTicket(ExternalTicket externalTicket) {
		getExternalTickets().remove(externalTicket);
		externalTicket.setSource(null);

		return externalTicket;
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

}