
package in.co.rays.dto;

import java.io.Serializable;
import java.sql.Timestamp;
// TODO: Auto-generated Javadoc
/**
 * Parent class of all Beans in application. It contains generic attributes.
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) Proxy
 * 
 */

public abstract class BaseDTO implements Serializable,Comparable<BaseDTO>,DropdownListDTO {
	
  /** non business primary key. */
protected long id;
  
  /** Contains USER ID who created this database record. */
private String createdBy;

  /** Contains USER ID who modified this database record. */
private String modifiedBy;
  
  /** Contains Created Timestamp of database record. */
private Timestamp createdDatetime;
  
  /** Contains modified Timestamp of database record. */
private Timestamp modifiedDatetime;
  
/**
 * Gets the id.
 *
 * @return the id
 */
public long getId() {
	return id;
}

/**
 * Sets the id.
 *
 * @param id the new id
 */
public void setId(long id) {
	this.id = id;
}

/**
 * Gets the created by.
 *
 * @return the created by
 */
public String getCreatedBy() {
	return createdBy;
}

/**
 * Sets the created by.
 *
 * @param createdby the new created by
 */
public void setCreatedBy(String createdby) {
	this.createdBy = createdby;
}

/**
 * Gets the modified by.
 *
 * @return the modified by
 */
public String getModifiedBy() {
	return modifiedBy;
}

/**
 * Sets the modified by.
 *
 * @param modifiedby the new modified by
 */
public void setModifiedBy(String modifiedby) {
	this.modifiedBy = modifiedby;
}

/**
 * Gets the created datetime.
 *
 * @return the created datetime
 */
public Timestamp getCreatedDatetime() {
	return createdDatetime;
}

/**
 * Sets the created datetime.
 *
 * @param createdDatetime the new created datetime
 */
public void setCreatedDatetime(Timestamp createdDatetime) {
	this.createdDatetime = createdDatetime;
}

/**
 * Gets the modified datetime.
 *
 * @return the modified datetime
 */
public Timestamp getModifiedDatetime() {
	return modifiedDatetime;
}

/**
 * Sets the modified datetime.
 *
 * @param modifiedDatetime the new modified datetime
 */
public void setModifiedDatetime(Timestamp modifiedDatetime) {
	this.modifiedDatetime = modifiedDatetime;
}

public String getValue() {
	// TODO Auto-generated method stub
	return null;
}

public String getKey() {
	// TODO Auto-generated method stub
	return null;
}

public int compareTo(BaseDTO o) {
	// TODO Auto-generated method stub
	return getValue().compareTo(o.getValue());
}
}
