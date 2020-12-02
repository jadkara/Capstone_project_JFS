package com.wipro.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Challenger {

	@Id
	private String email;

	@Column(nullable = false)
	private int age;

	@Column
	private String gender;

	@Column
	private String fullName;
	
	@Column
	private String mobile;
	
	@Column
	private String address;

	@Column(nullable = false)
	private String city;

	@Column
	private String state;

	@Column
	private String country;

	@Column
	private int pincode;

	@Column
	private double height;

	@Column
	private double weight;
	/*
	 * BMI will be internally calculated as per weight(in KG)/square[Height(in
	 * meters)] d(meter) = d(″) / 39.37
	 */
	@Column
	private double bmi;

	@Column
	private String reason;

	@Column
	private String medicalCondition;

	@Column
	private String dietRestriction;
	/*
	 * Diet type of user like Veg /Non-Veg /Vegan
	 */
	private String dietType;
	
	/*
	 * Pregnancy status for females
	 */
	@Column
	private boolean pregnancyStatus;
	/*
	 * A existing member's referral Code using which a new member is registering
	 */
	@Column(nullable = false)
	private String referredCode;
	/* Status of challenger after Approve/Reject by admin */

	@Column
	private String status;

	@Column
	private String rejectionReason;

	public Challenger() {
		super();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}



	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMedicalCondition() {
		return medicalCondition;
	}

	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}

	public String getDietRestriction() {
		return dietRestriction;
	}

	public void setDietRestriction(String dietRestriction) {
		this.dietRestriction = dietRestriction;
	}

	public String getDietType() {
		return dietType;
	}

	public void setDietType(String dietType) {
		this.dietType = dietType;
	}

	public String getReferredCode() {
		return referredCode;
	}

	public void setReferredCode(String referredCode) {
		this.referredCode = referredCode;
	}

	public boolean isPregnancyStatus() {
		return pregnancyStatus;
	}

	public void setPregnancyStatus(boolean pregnancyStatus) {
		this.pregnancyStatus = pregnancyStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		return "Challenger [age=" + age + ", gender=" + gender + ", address=" + address + ", city=" + city + ", state="
				+ state + ", country=" + country + ", pincode=" + pincode + ", height=" + height + ", weight=" + weight
				+ ", bmi=" + bmi + ", reasonForJoining=" + reason + ", medicalCondition=" + medicalCondition
				+ ", dietRestriction=" + dietRestriction + ", dietType=" + dietType + ", pregnancyStatus="
				+ pregnancyStatus + ", referredCode=" + referredCode + ", status=" + status + ", rejectionReason="
				+ rejectionReason + "]";
	}

}
