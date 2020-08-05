package com.medved.support.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.sql.Timestamp;


/**
 * The persistent class for the SEARCH_MADE database table.
 * 
 */
@Entity
@Table(name="SEARCH_MADE")
@NamedQuery(name="SearchMade.findAll", query="SELECT s FROM SearchMade s")
public class SearchMade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEARCH_MADE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEARCH_MADE_ID_GENERATOR")
	@NotNull
	private long id;

	@Column(name="\"DATE\"")
	@NotNull
	private Timestamp date;

	@Size(max = 200)
	@NotBlank
	private String search;

	//bi-directional many-to-one association to User
	@ManyToOne
	@NotNull
	private User user;

	public SearchMade() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getSearch() {
		return this.search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}