package com.wipro.model;

import java.io.Serializable;

public class MeasurementId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

	private String date;
	public MeasurementId() 
	{
		super();
	}
	public MeasurementId(String userId, String date) 
	{
		super();
        this.userId = userId;
        this.date = date;
    }

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
