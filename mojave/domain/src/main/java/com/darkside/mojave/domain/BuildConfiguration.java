package com.darkside.mojave.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "buildconfigs")
public class BuildConfiguration extends BaseEntity {
	@ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL)
	private Project project;
	
	@OneToMany(targetEntity = BuildConfigurationGoal.class, cascade = CascadeType.ALL)
	private Set<BuildConfigurationGoal> goals = new HashSet<BuildConfigurationGoal>();

	public Set<BuildConfigurationGoal> getGoals() {
		return goals;
	}
}
