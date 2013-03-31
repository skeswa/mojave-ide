package com.darkside.mojave.web.mvp.login.event;

import org.pakhama.vaadin.mvp.event.impl.Event;
import org.phakama.vaadin.github.session.GitHubSession;

public class LoginSuccessEvent extends Event {
	private static final long serialVersionUID = 2989470626357076870L;
	
	private GitHubSession gitHubSession;
	
	public LoginSuccessEvent() {
	}

	public LoginSuccessEvent(GitHubSession gitHubSession) {
		this.gitHubSession = gitHubSession;
	}

	public GitHubSession getGitHubSession() {
		return gitHubSession;
	}

	public void setGitHubSession(GitHubSession gitHubSession) {
		this.gitHubSession = gitHubSession;
	}
}
