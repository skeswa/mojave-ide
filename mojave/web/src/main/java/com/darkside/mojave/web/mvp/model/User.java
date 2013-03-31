package com.darkside.mojave.web.mvp.model;

import java.util.HashSet;

public class User extends BaseEntity {
	private String userName;
	private String password;
	private String email;
	private String avatarUrl;
	
	private HashSet<Project> projects = new HashSet<Project>();

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public HashSet<Project> getProjects() {
		return projects;
	}

	public void setProjects(HashSet<Project> projects) {
		this.projects = projects;
	}

}
