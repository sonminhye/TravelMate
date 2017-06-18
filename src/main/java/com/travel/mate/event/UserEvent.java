package com.travel.mate.event;

import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent{
	String userName;

	public UserEvent(Object source, String userName) {
		super(source);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
	
}
