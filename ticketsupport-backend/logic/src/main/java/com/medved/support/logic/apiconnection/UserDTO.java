package com.medved.support.logic.apiconnection;


import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
	@JsonProperty()
	private String username;

	@JsonProperty()
	private String email;

	@JsonProperty()
	private String lastName;

	@JsonProperty()
	private String name;

	@JsonProperty()
	private String password;
	
	@JsonProperty()
	private ArrayList<String> enterpriseNit;
	
	@JsonProperty()
	private ArrayList<Long> rolID;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getEnterpriseNit() {
		return enterpriseNit;
	}

	public void setEnterpriseNit(ArrayList<String> enterpriseNit) {
		this.enterpriseNit = enterpriseNit;
	}

	public ArrayList<Long> getRolID() {
		return rolID;
	}

	public void setRolID(ArrayList<Long> rolID) {
		this.rolID = rolID;
	}

	
	
	
	
	
}
