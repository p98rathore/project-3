package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.FacultyDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;


/**
 * Data Access Object of College
 * 
 * @author Proxy
 * @version 1.0
 * @Copyright (c) Proxy
 */

public interface FacultyModelInt {


	 /**
    * Add a College
    * 
    * @param dto
    * @throws ApplicationException
    * @throws DuplicateRecordException
    *             : throws when college already exists
    */
   public long add(FacultyDTO dto) throws ApplicationException,
           DuplicateRecordException;

   /**
    * Update a College
    * 
    * @param dto
    * @throws ApplicationException
    * @throws DuplicateRecordException
    *             : if updated user record is already exist
    */
   public void update(FacultyDTO dto) throws ApplicationException,
           DuplicateRecordException;

   /**
    * Delete a College
    * 
    * @param dto
    * @throws ApplicationException
    */
   public void delete(FacultyDTO dto) throws ApplicationException;

   /**
    * Find College by Name
    * 
    * @param name
    *            : get parameter
    * @return dto
    * @throws ApplicationException
    */
   public FacultyDTO findByEmail(String name) throws ApplicationException;

   /**
    * Find College by PK
    * 
    * @param pk
    *            : get parameter
    * @return dto
    * @throws ApplicationException
    */
   public FacultyDTO findByPK(long pk) throws ApplicationException;

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
   public List search(FacultyDTO dto, int pageNo, int pageSize)
           throws ApplicationException;

   /**
    * Search College
    * 
    * @return list : List of College
    * @param dto
    *            : Search Parameters
    * @throws ApplicationException
    */
   public List search(FacultyDTO dto) throws ApplicationException;

   /**
    * Gets List of College
    * 
    * @return list : List of College
    * @throws DatabaseException
    */
   public List list() throws ApplicationException;

   /**
    * get List of College with pagination
    * 
    * @return list : List of College
    * @param pageNo
    *            : Current Page No.
    * @param pageSize
    *            : Size of Page
    * @throws ApplicationException
    */
   public List list(int pageNo, int pageSize) throws ApplicationException;

}

