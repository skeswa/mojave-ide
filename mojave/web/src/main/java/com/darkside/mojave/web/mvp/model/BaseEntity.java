package com.darkside.mojave.web.mvp.model;

import java.util.Date;

public abstract class BaseEntity {

	protected Long id;
	protected Date dateCreated = new Date();
	protected Date dateLastUpdated = new Date();
	
	public Long getId() {
		return id;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}

	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}
}
