package com.darkside.mojave.web.mvp.model;

import java.util.HashSet;

public class BuildConfiguration extends BaseEntity {
	private HashSet<BuildConfigurationGoal> goals = new HashSet<BuildConfigurationGoal>();

	public HashSet<BuildConfigurationGoal> getGoals() {
		return goals;
	}
}
