package com.darkside.mojave.web.mvp.ide.event;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class NotificationEvent extends Event{
	private static final long serialVersionUID = 3889636895172134767L;

	private long sessionId;
	private String message;
	
	public NotificationEvent(long sessionId, String message){
		this.setSessionId(sessionId);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
}
