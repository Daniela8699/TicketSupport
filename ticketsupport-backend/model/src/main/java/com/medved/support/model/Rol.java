package com.medved.support.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the ROL database table.
 * 
 */
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	
	public static int ADMIN = 0;
	public static int OPERATOR = 1;
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROL_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROL_ID_GENERATOR")
	@NotNull
	private long id;

	@Size(max = 40)
	@NotNull
	private String name;

	//bi-directional many-to-one association to EntityState
	@ManyToOne
	@JoinColumn(name="ENTITY_STATE_ID")
	@NotNull
	private EntityState entityState;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="rols")
	@JsonIgnore
	private List<User> users;

	public Rol() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}