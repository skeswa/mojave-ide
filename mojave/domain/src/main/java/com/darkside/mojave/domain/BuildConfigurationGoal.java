package com.darkside.mojave.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "buildconfiggoals")
public class BuildConfigurationGoal extends BaseEntity {
	@ManyToOne(targetEntity = BuildConfiguration.class, cascade = CascadeType.ALL)
	private BuildConfiguration buildConfiguration;
	
	private String goal;

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}
	
	public BuildConfiguration getBuildConfiguration() {
		return buildConfiguration;
	}

	public void setBuildConfiguration(BuildConfiguration buildConfiguration) {
		this.buildConfiguration = buildConfiguration;
	}
}
