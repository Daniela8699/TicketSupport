package com.medved.support.logic.apiconnection;

import org.springframework.web.multipart.MultipartFile;

public class InternalTicketDTO {
	
	private long id;
	private String title;
	private String description;
	private long priority;
	private String agent;
	public String getAgent() {
		return agent;
	}



	public void setAgent(String agent) {
		this.agent = agent;
	}



	private String answer;
	private String name;
	private MultipartFile file;

	
	public InternalTicketDTO() {
		
	}
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public MultipartFile getFile() {
		return file;
	}



	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}
