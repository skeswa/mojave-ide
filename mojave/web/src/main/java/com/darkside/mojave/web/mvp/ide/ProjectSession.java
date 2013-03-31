package com.darkside.mojave.web.mvp.ide;

import java.io.File;
import java.util.HashSet;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.event.IEventDispatcher;
import org.phakama.maven.MavenAnalyzer;
import org.phakama.maven.model.MavenBuildProject;

import com.darkside.mojave.domain.Project;
import com.darkside.mojave.domain.User;
import com.darkside.mojave.web.EditorSession;
import com.darkside.mojave.web.MojaveApplicationData;
import com.darkside.mojave.web.mvp.collaborators.event.ProjectSessionJoinEvent;
import com.darkside.mojave.web.mvp.collaborators.event.ProjectSessionLeaveEvent;

public class ProjectSession {
	private long sessionId;
	private Project project;
	private EditorSession es;
	private HashSet<User> users = new HashSet<User>();

	public ProjectSession(long sessionId, Project project) {
		this.sessionId = sessionId;
		this.project = project;
		this.es = new EditorSession();
	}

	public MavenBuildProject getBuildProject() {
		return MavenAnalyzer.analyze(new File(this.project.getPath()));
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public EditorSession getEs() {
		return es;
	}

	public void setEs(EditorSession es) {
		this.es = es;
	}

	public HashSet<User> getUsers() {
		return users;
	}

	public void setUsers(HashSet<User> users) {
		this.users = users;
	}

	public void joinSession(IEventDispatcher dispatcher){
		User user = MojaveApplicationData.getSession().getUser();
		if (!users.contains(user)) {
			users.add(user);
			dispatcher.dispatch(new ProjectSessionJoinEvent(sessionId, user), EventScope.UNIVERSAL);
		}
	}

	public void leaveSession(IEventDispatcher dispatcher){
		User user = MojaveApplicationData.getSession().getUser();
		if(users.remove(user)) dispatcher.dispatch(new ProjectSessionLeaveEvent(sessionId, user), EventScope.UNIVERSAL);
	}
}
