package com.wipro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(MeasurementId.class)
public class MonthlyMeasurement {

	@Id
	private String userId;
	@Id
	private String date;
	
	@Column
	private int weight;
	
	@Column
	private int height;
	
	@Column
	private int chest;
	
	@Column
	private int waist;
	
	@Column
	private int shoulders;
	
	@Column
	private int biceps;
	
	@Column
	private int forearm;
	
	@Column
	private int legs;
	
	@Column
	private int thighs;
	
	public MonthlyMeasurement() {
		super();
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getChest() {
		return chest;
	}

	public void setChest(int chest) {
		this.chest = chest;
	}

	public int getWaist() {
		return waist;
	}

	public void setWaist(int waist) {
		this.waist = waist;
	}

	public int getShoulders() {
		return shoulders;
	}

	public void setShoulders(int shoulders) {
		this.shoulders = shoulders;
	}

	public int getBiceps() {
		return biceps;
	}

	public void setBiceps(int biceps) {
		this.biceps = biceps;
	}

	public int getForearm() {
		return forearm;
	}

	public void setForearm(int forearm) {
		this.forearm = forearm;
	}

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}

	public int getThighs() {
		return thighs;
	}

	public void setThighs(int thighs) {
		this.thighs = thighs;
	}

	@Override
	public String toString() {
		return "MonthlyMeasurement [userId=" + userId + ", date=" + date + ", weight=" + weight + ", height=" + height
				+ ", chest=" + chest + ", waist=" + waist + ", shoulders=" + shoulders + ", biceps=" + biceps
				+ ", forearm=" + forearm + ", legs=" + legs + ", thighs=" + thighs + "]";
	}
}
