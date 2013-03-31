package com.darkside.mojave.web.mvp.model;

import java.util.Date;

public class Build extends BaseEntity {
	private boolean succeeded;
	private String binaryFilePath;
	private String logFilePath;


	private Date timeStarted;

	private Date timeCompleted;
	

	private Project project;

	public boolean isSucceeded() {
		return succeeded;
	}

	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}

	public String getBinaryFilePath() {
		return binaryFilePath;
	}

	public void setBinaryFilePath(String binaryFilePath) {
		this.binaryFilePath = binaryFilePath;
	}

	public String getLogFilePath() {
		return logFilePath;
	}

	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getTimeStarted() {
		return timeStarted;
	}

	public void setTimeStarted(Date timeStarted) {
		this.timeStarted = timeStarted;
	}

	public Date getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(Date timeCompleted) {
		this.timeCompleted = timeCompleted;
	}
}
