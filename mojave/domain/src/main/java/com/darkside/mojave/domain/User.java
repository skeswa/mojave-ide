package com.darkside.mojave.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class User extends BaseEntity {
	private String userName;
	private String password;
	private String email;
	private String avatarUrl;
	
	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Project.class)
	private Set<Project> projects = new HashSet<Project>();

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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(HashSet<Project> projects) {
		this.projects = projects;
	}

}
