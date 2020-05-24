package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;


/**
 * Data Access Object of course
 * 
 * @author Proxy
 * @version 1.0
 * @Copyright (c) Proxy
 */


public interface CourseModelInt {

	 /**
     * Add a course
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when college already exists
     */
    public long add(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a course
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a College
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(CourseDTO dto) throws ApplicationException;

    /**
     * Find College by Name
     * 
     * @param name
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public CourseDTO findByName(String name) throws ApplicationException;

    /**
     * Find College by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public CourseDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search College with pagination
     * 
     * @return list : List of College
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(CourseDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search course
     * 
     * @return list : List of course
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(CourseDTO dto) throws ApplicationException;

    /**
     * Gets List of course
     * 
     * @return list : List of course
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * get List of College with pagination
     * 
     * @return list : List of course
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}

