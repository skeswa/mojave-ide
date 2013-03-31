package com.darkside.mojave.web.session;

import javax.persistence.EntityManager;

import org.phakama.vaadin.github.session.GitHubSession;

import com.darkside.mojave.domain.User;

public class MojaveSession {
	private final EntityManager entityManager;
	private final GitHubSession gitHubSession;
	private final User user;
	private final long timeStarted = System.currentTimeMillis();
	
	public MojaveSession(GitHubSession gitHubSession, User user, EntityManager entityManager) {
		this.gitHubSession = gitHubSession;
		this.user = user;
		this.entityManager = entityManager;
	}

	public GitHubSession getGitHubSession() {
		return gitHubSession;
	}

	public User getUser() {
		return user;
	}

	public long getTimeStarted() {
		return timeStarted;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
