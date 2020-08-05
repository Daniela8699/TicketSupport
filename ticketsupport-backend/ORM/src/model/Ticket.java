package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the TICKET database table.
 * 
 */
@Entity
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TICKET_ID_GENERATOR", sequenceName="TICKET_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TICKET_ID_GENERATOR")
	private long id;

	@Column(name="CLOSE_DATE")
	private Timestamp closeDate;

	private String description;

	private BigDecimal id1;

	@Column(name="IS_ANSWERED")
	private String isAnswered;

	@Column(name="LAST_ACTIVITY_DATE")
	private Timestamp lastActivityDate;

	@Column(name="OPEN_DATE")
	private Timestamp openDate;

	private String title;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="ticket")
	private List<Answer> answers;

	//bi-directional one-to-one association to ExternalTicket
	@OneToOne(mappedBy="ticket")
	private ExternalTicket externalTicket;

	//bi-directional one-to-one association to InternalTicket
	@OneToOne(mappedBy="ticket")
	private InternalTicket internalTicket;

	//bi-directional many-to-one association to Resource
	@OneToMany(mappedBy="ticket")
	private List<Resource> resources;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	private EntityState entityState;

	public Ticket() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Timestamp closeDate) {
		this.closeDate = closeDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getId1() {
		return this.id1;
	}

	public void setId1(BigDecimal id1) {
		this.id1 = id1;
	}

	public String getIsAnswered() {
		return this.isAnswered;
	}

	public void setIsAnswered(String isAnswered) {
		this.isAnswered = isAnswered;
	}

	public Timestamp getLastActivityDate() {
		return this.lastActivityDate;
	}

	public void setLastActivityDate(Timestamp lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public Timestamp getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setTicket(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setTicket(null);

		return answer;
	}

	public ExternalTicket getExternalTicket() {
		return this.externalTicket;
	}

	public void setExternalTicket(ExternalTicket externalTicket) {
		this.externalTicket = externalTicket;
	}

	public InternalTicket getInternalTicket() {
		return this.internalTicket;
	}

	public void setInternalTicket(InternalTicket internalTicket) {
		this.internalTicket = internalTicket;
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Resource addResource(Resource resource) {
		getResources().add(resource);
		resource.setTicket(this);

		return resource;
	}

	public Resource removeResource(Resource resource) {
		getResources().remove(resource);
		resource.setTicket(null);

		return resource;
	}

	public EntityState getEntityState() {
		return this.entityState;
	}

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

}