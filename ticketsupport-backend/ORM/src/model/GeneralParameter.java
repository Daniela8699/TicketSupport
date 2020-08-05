package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GENERAL_PARAMETERS database table.
 * 
 */
@Entity
@Table(name="GENERAL_PARAMETERS")
@NamedQuery(name="GeneralParameter.findAll", query="SELECT g FROM GeneralParameter g")
public class GeneralParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GENERAL_PARAMETERS_NAME_GENERATOR", sequenceName="GENERAL_PARAMETERS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GENERAL_PARAMETERS_NAME_GENERATOR")
	private String name;

	private String value;

	public GeneralParameter() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}