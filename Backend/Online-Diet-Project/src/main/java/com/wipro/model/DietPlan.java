package com.wipro.model;

import java.io.File; 
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DietPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dietPlanId;
	
	@Column
    private String batchId;
	
	@Lob
	private byte[] dietPlanDetails;
	
	@Column
	private String fileName;
	
	public DietPlan() {
		super();
	}

	public DietPlan(String batchId, byte[] dietPlanDetails, String fileName) {
		super();
		this.batchId = batchId;
		this.dietPlanDetails = dietPlanDetails;
		this.fileName = fileName;
	}

	public Long getDietPlanId() {
		return dietPlanId;
	}

	public void setDietPlanId(Long dietPlanId) {
		this.dietPlanId = dietPlanId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public byte[] getDietPlanDetails() {
		return dietPlanDetails;
	}

	public void setDietPlanDetails(byte[] dietPlanDetails) {
		this.dietPlanDetails = dietPlanDetails;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
