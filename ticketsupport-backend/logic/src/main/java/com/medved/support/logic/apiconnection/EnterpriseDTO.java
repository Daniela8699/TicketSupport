package com.medved.support.logic.apiconnection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnterpriseDTO {
	
	@JsonProperty()
	private String nit;
	@JsonProperty()
	private String name;

	@JsonProperty()
	private String contactMail;
	
	@JsonProperty()
	private String contactPhone;
	
	@JsonProperty()
	private String description;

	@JsonProperty()
	private List<Long> sources;
	

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getSources() {
		return sources;
	}

	public void setSources(List<Long> ide) {
		this.sources = ide;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
