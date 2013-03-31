package com.darkside.mojave.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "projects")
public class Project extends BaseEntity {
	private String name;
	private String path;
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Build.class)
	private Set<Build> builds = new HashSet<Build>();

	@ManyToMany(cascade = CascadeType.ALL, targetEntity = User.class, mappedBy = "projects")
	private Set<User> collaborators = new HashSet<User>();
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = BuildConfiguration.class)
	private Set<BuildConfiguration> buildConfiguations = new HashSet<BuildConfiguration>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getCollaborators() {
		return collaborators;
	}

	public Set<Build> getBuilds() {
		return builds;
	}

	public Set<BuildConfiguration> getBuildConfiguations() {
		return buildConfiguations;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
