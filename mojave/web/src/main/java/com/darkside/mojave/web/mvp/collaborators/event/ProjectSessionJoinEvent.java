package com.darkside.mojave.web.mvp.collaborators.event;

import org.pakhama.vaadin.mvp.event.impl.Event;

import com.darkside.mojave.domain.User;

public class ProjectSessionJoinEvent extends Event{
	private static final long serialVersionUID = -485390229261381581L;

	private User user;
	private long sessionId;
	
	public ProjectSessionJoinEvent(){
	}
	
	public ProjectSessionJoinEvent(long sessionId,User user){
		this.user = user;
		this.sessionId = sessionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
}
