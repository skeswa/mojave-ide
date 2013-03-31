package com.darkside.mojave.web.mvp.chat.event;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class ChatUpdateEvent extends Event {
	private static final long serialVersionUID = 1749227663564454357L;
	
	private long sessionId;
	private String userName;
	private String message;
	private String timeStamp;
	
	public ChatUpdateEvent() {
	}
	
	public ChatUpdateEvent(long sessionId, String userName, String message, String timeStamp) {
		this.sessionId = sessionId;
		this.userName = userName;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public String getUserName() {
		return userName;
	}

	public String getMessage() {
		return message;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
