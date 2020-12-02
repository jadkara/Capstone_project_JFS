package com.wipro.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyLog {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
	private Long logId;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private Date activityDate;
	
	@Column
	private String breakfast;
	
	@Column
	private String lunch;
	
	@Column
	private String dinner;
	
	@Column
	private String fruits;
	
	@Column
	private String vegetables;
	
	@Column
	private String workouts;
	
	public DailyLog() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

	public String getDinner() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = dinner;
	}

	public String getFruits() {
		return fruits;
	}

	public void setFruits(String fruits) {
		this.fruits = fruits;
	}

	public String getVegetables() {
		return vegetables;
	}

	public void setVegetables(String vegetables) {
		this.vegetables = vegetables;
	}

	public String getWorkouts() {
		return workouts;
	}

	public void setWorkouts(String workouts) {
		this.workouts = workouts;
	}

}
