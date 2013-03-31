package com.darkside.mojave.web.mvp.login.event;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class LoginAttemptEvent extends Event {
	private static final long serialVersionUID = 2833608544478176140L;

	private String userName;
	private String password;
	
	public LoginAttemptEvent() {
	}
	
	public LoginAttemptEvent(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
