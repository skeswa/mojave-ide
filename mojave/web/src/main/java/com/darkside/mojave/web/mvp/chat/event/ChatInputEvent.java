package com.darkside.mojave.web.mvp.chat.event;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class ChatInputEvent extends Event {
	private static final long serialVersionUID = -9029084367313087240L;

	private String message;
	
	public ChatInputEvent() {
	}
	
	public ChatInputEvent(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
