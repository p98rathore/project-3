
package in.co.rays.dto;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Timetable Javabean  encapsulates Timetable attributes.
 *
 * @author Proxy
 * @version 1.0
 * Copyight (C) Proxy
 */
public class TimetableDTO extends BaseDTO {

	/** subId of Timetable. */
	private long subId;
	
	/** subName of Timetable. */
	private String subName;
	
	/** courseId of Timetable. */
	private long courseId;
	
	/** courseName of Timetable. */
	private String courseName;
	
	/** semester of Timetable. */
	private String semester;
	
	/** examDate of Timetable. */
	private Date examDate;
	
	/** examTime of Timetable. */
	private String examTime;
	
	/** description of Timetable. */
	private String description;
	
	
	/**
	 * accessor.
	 *
	 * @return String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the sub id.
	 *
	 * @return the sub id
	 */
	public long getSubId() {
		return subId;
	}
	
	/**
	 * Sets the sub id.
	 *
	 * @param subId the new sub id
	 */
	public void setSubId(long subId) {
		this.subId = subId;
	}
	
	/**
	 * Gets the sub name.
	 *
	 * @return the sub name
	 */
	public String getSubName() {
		return subName;
	}
	
	/**
	 * Sets the sub name.
	 *
	 * @param subName the new sub name
	 */
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	/**
	 * Gets the course id.
	 *
	 * @return the course id
	 */
	public long getCourseId() {
		return courseId;
	}
	
	/**
	 * Sets the course id.
	 *
	 * @param courseId the new course id
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	
	/**
	 * Sets the semester.
	 *
	 * @param semester the new semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	/**
	 * Gets the exam date.
	 *
	 * @return the exam date
	 */
	public Date getExamDate() {
		return examDate;
	}
	
	/**
	 * Sets the exam date.
	 *
	 * @param examDate the new exam date
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	
	/**
	 * Gets the exam time.
	 *
	 * @return the exam time
	 */
	public String getExamTime() {
		return examTime;
	}
	
	/**
	 * Sets the exam time.
	 *
	 * @param examTime the new exam time
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	@Override
	public String getKey() {
		return id+"";
	}
	
	@Override
	public String getValue() {
		return subName;
	}
}
