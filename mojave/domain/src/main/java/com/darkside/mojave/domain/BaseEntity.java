package com.darkside.mojave.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@Column(name = "id", scale = 0)
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Basic
	@Column(name = "datecreated")
	@Temporal(TemporalType.DATE)
	protected Date dateCreated = new Date();
	
	@Basic
	@Column(name = "datelastupdated")
	@Temporal(TemporalType.DATE)
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
