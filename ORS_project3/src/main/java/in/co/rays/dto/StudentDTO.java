
package in.co.rays.dto;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Student  Javabean encapsulates Student attribute.
 *
 * @author Proxy
 * @version 1.0
 * Copyright (C) Proxy
 */
public class StudentDTO extends BaseDTO {

/** firstName of Student. */
private String firstName;

/** lastName of Student. */
private String lastName;

/** Date of birth of Student. */
private Date dob;

/** mobileNO of Studnet. */
private String mobileNo;

/** Email of Student. */
private String email;

/** collegeId of Student. */
private long collegeID;

/** collegeName of Student. */
private String collegeName;

private String gender;

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

/**
 * accessor.
 *
 * @return String
 */
public String getFirstName() {
	return firstName;
}

/**
 * Sets the first name.
 *
 * @param firstName the new first name
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

/**
 * Gets the last name.
 *
 * @return the last name
 */
public String getLastName() {
	return lastName;
}

/**
 * Sets the last name.
 *
 * @param lastName the new last name
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}

/**
 * Gets the dob.
 *
 * @return the dob
 */
public Date getDob() {
	return dob;
}

/**
 * Sets the dob.
 *
 * @param dob the new dob
 */
public void setDob(Date dob) {
	this.dob = dob;
}

/**
 * Gets the mobile no.
 *
 * @return the mobile no
 */
public String getMobileNo() {
	return mobileNo;
}

/**
 * Sets the mobile no.
 *
 * @param mobileNo the new mobile no
 */
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}

/**
 * Gets the email.
 *
 * @return the email
 */
public String getEmail() {
	return email;
}

/**
 * Sets the email.
 *
 * @param email the new email
 */
public void setEmail(String email) {
	this.email = email;
}

/**
 * Gets the college ID.
 *
 * @return the college ID
 */
public long getCollegeID() {
	return collegeID;
}

/**
 * Sets the college ID.
 *
 * @param collegeID the new college ID
 */
public void setCollegeID(long collegeID) {
	this.collegeID = collegeID;
}

/**
 * Gets the college name.
 *
 * @return the college name
 */
public String getCollegeName() {
	return collegeName;
}

/**
 * Sets the college name.
 *
 * @param collegeName the new college name
 */
public void setCollegeName(String collegeName) {
	this.collegeName = collegeName;
}

public String getKey() {
	// TODO Auto-generated method stub
	return id+"";
	
}

public String getValue(){
	return firstName+" "+lastName;
	
}
}