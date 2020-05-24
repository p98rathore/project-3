
package in.co.rays.dto;

// TODO: Auto-generated Javadoc
/**
 * Course Javabean encapsulate course attribute.
 *
 * @author proxy
 * copyright (c) proxy
 * @version 1.0
 */
public class CourseDTO extends BaseDTO {

	/** Default serial version Id. */
	private static final long serialVersionUID = 1L;
	
	/** Name of Course. */
	private String name;
	
	/** Duration of Course. */
	private String duration;
	
	/** Description of project. */
	private String description;

	/**
	 * accessor .
	 *
	 * @return string
	 */

	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
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

	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}
}
