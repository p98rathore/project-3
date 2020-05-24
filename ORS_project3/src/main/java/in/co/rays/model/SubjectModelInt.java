package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;


/**
 * Data Access Object of subject
 * 
 * @author Proxy
 * @version 1.0
 * @Copyright (c) Proxy
 */

public interface SubjectModelInt {

	 /**
     * Add a subject
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when college already exists
     */
    public long add(SubjectDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a subject
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(SubjectDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a subject
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(SubjectDTO dto) throws ApplicationException;

    /**
     * Find subject by Name
     * 
     * @param name
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public SubjectDTO findByName(String name) throws ApplicationException;

    /**
     * Find subject by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public SubjectDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search subject with pagination
     * 
     * @return list : List of subject
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(SubjectDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search subject
     * 
     * @return list : List of subject
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(SubjectDTO dto) throws ApplicationException;

    /**
     * Gets List of subject
     * 
     * @return list : List of subject
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * get List of subject with pagination
     * 
     * @return list : List of subject
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;




}
