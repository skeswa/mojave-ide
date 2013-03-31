package com.darkside.mojave.web.mvp.console.event;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class ConsoleUpdateEvent extends Event {
	private static final long serialVersionUID = 3958239383645428955L;

	private String consoleContent;
	private long sessionId;
	
	public ConsoleUpdateEvent(){
	}
	
	public ConsoleUpdateEvent(long sessionId, String consoleContent) {
		this.setSessionId(sessionId);
		this.consoleContent = consoleContent;
	}

	public String getConsoleContent() {
		return consoleContent;
	}

	public void setConsoleContent(String consoleContent) {
		this.consoleContent = consoleContent;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
}
