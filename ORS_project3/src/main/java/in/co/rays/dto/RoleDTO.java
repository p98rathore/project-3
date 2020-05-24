
package in.co.rays.dto;

// TODO: Auto-generated Javadoc
/**
 * Role Javabean Encapsulates Role attribute.
 *
 * @author proxy
 * @version 1.0
 * Copyright (C)   proxy
 */
public class RoleDTO extends BaseDTO {

    /** Predefined Role constants. */
    public static final int ADMIN = 1;
    
    /** The Constant STUDENT. */
    public static final int STUDENT = 2;
    
    /** The Constant COLLEGE. */
    public static final int COLLEGE = 3;
    
    /** The Constant KIOSK. */
    public static final int KIOSK = 4;
    
    /** The Constant FACULTY. */
    public static final int FACULTY=5;

    /** Role Name. */

    private String name;

    /** Role Description. */
    private String description;

    /**
     * accessor.
     *
     * @return String
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
        return id + "";
    }

    public String getValue() {
        return name;
    }

}



