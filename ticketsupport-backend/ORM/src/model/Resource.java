package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RESOURCES database table.
 * 
 */
@Entity
@Table(name="RESOURCES")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RESOURCES_ID_GENERATOR", sequenceName="RESOURCES_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESOURCES_ID_GENERATOR")
	private long id;

	private String format;

	private String path;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	private EntityState entityState;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	private Ticket ticket;

	public Resource() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}