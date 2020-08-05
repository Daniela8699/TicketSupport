package com.medved.support.rest.notifications;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationMessageController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public NotificationMessage notification(NotificationMessage notification)
	{
		return new NotificationMessage(NotificationMessage.INITIAL_LOAD_NOTIFICATION, "Se realizaron las consultas diarias");
		
	}
}
