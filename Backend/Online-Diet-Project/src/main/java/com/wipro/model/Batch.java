package com.wipro.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Batch {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String batchId;
	
	@Column
	private String batchName;
	
	@Column // need to add @Temporal
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@Column
	private Date measurmentDate;
	
	@OneToMany(targetEntity = User.class, mappedBy = "batchId")
	private Set<User> users;
	
	public Batch() {
		super();
	}

	public Batch(String batchName, Date startDate, Date endDate, Date measurmentDate) {
		super();
		this.batchName = batchName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.measurmentDate = measurmentDate;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}
	
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getMeasurmentDate() {
		return measurmentDate;
	}
	public void setMeasurmentDate(Date measurmentDate) {
		this.measurmentDate = measurmentDate;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
