package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

/**
 * Data Access Object of Student
 * 
 * @author proxy
 * @version 1.0
 * @Copyright (c) Proxy
 */
public interface StudentModelInt {

	/**
     * Add a Student
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when Student already exists
     */
    public long add(StudentDTO dto) throws ApplicationException, DuplicateRecordException
           ;

	
	
	/**
     * Find Student by EmailId
     * 
     * @param name
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public StudentDTO findByEmailId(String emailId) throws ApplicationException;
    
    /**
     * Update a Student
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(StudentDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Student
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(StudentDTO dto) throws ApplicationException;

    /**
     * Find Student by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException 
     * @throws DatabaseException
     */
  
    public StudentDTO findByPK(long pk) throws ApplicationException;



    /**
     * Searches Student
     * 
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
    public List search(StudentDTO dto) throws ApplicationException;
    
    /**
     * Searches Student with pagination
     * 
     * @return list : List of Student
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException 
     * 
     * @throws DatabaseException
     */
    public List search(StudentDTO dto, int pageNo, int pageSize) throws ApplicationException; 
    
    /**
     * Gets List of Student
     * 
     * @return list : List of Student
     * @throws ApplicationException 
     * @throws DatabaseException
     */
  
    public List list() throws ApplicationException ;
    /**
     * get List of Student with pagination
     * 
     * @return list : List of Student
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException 
     * @throws DatabaseException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;
}
