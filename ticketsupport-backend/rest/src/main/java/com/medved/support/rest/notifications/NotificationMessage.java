package com.medved.support.rest.notifications;

public class NotificationMessage {

	public static final String INITIAL_LOAD_NOTIFICATION = "Initial Load Notification";
	public static final String AUTOMATIC_SYNC_NOTIFICATION = "Automatic Synchronization Notification";
	
	private String id, message;
	
	public NotificationMessage(String id, String message) {
		this.id = id;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
