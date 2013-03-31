package com.darkside.mojave.web.mvp.model;

import java.io.File;
import java.util.HashSet;

import org.phakama.maven.MavenAnalyzer;
import org.phakama.maven.model.MavenBuildProject;

import com.darkside.mojave.web.mvp.EditorSession;

public class ProjectSession {
	private long sessionId;
	private Project project;
	private EditorSession es;
	private HashSet<User> users = new HashSet<User>();
	
	public ProjectSession(long sessionId, Project project) {
		this.sessionId = sessionId;
		this.project = new Project();
		this.project.setName("McHardbody");
//		this.project = project;
		this.es = new EditorSession();
	}
	
	public MavenBuildProject getBuildProject(){
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
}
