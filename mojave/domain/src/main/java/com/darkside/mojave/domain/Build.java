package com.darkside.mojave.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "builds")
public class Build extends BaseEntity {
	private boolean succeeded;
	private String binaryFilePath;
	private String logFilePath;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStarted;
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeCompleted;
	
	@ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL)
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
