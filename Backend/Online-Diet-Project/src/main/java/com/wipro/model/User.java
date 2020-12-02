package com.wipro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.wipro.commons.StringPrefixedSequenceIdGenerator;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "com.wipro.commons.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "DMS_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@Column(nullable = false, unique = true)
	private String id;
	
	@Column
	private String fullName;

	@Column
	private String mobileNo;
	
	@Column(unique = true,nullable = false)
	private String email;
	
	@Column
	private String userType;
	
	@Column( nullable = false)
	private String password;
	
	@Column(length = 20,unique = true)
	private String referralCode;
	
	
	//@ManyToOne(targetEntity = GroupEntity.class)
	//@JoinColumn(name="groupId")	
	@Column
	private String groupId;
	
	//@ManyToOne(targetEntity = Batch.class)
	//@JoinColumn(name="batchId")
	@Column
	private String batchId;
	
	public User() {
		super();
	}

	public User( String fullName, String mobileNo, String email, String userType,
			String password, String groupId, String batchId, String referralCode) {
		super();
		this.fullName = fullName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.userType = userType;
		this.password = password;
		this.batchId = batchId;
		this.groupId = groupId;
		this.referralCode = referralCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [name=" + fullName + ", mobileNo=" + mobileNo + ", email=" + email + ", userType=" + userType
				+ ", referralCode=" + referralCode + "]";
	}
}
