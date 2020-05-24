package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;


/**
 * Data Access Object of Users
 * 
 * @author poxy
 * @version 1.0
 * @Copyright (c) Proxy
 */
public interface UserModelInt {
	
	
	 /**
     * Add a user
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when user already exists
     */
    public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * find a user by login
	 * @param login
	 * @return
	 * @throws ApplicationException 
	 */
	public UserDTO findByLogin(String login) throws ApplicationException;

	 /**
     * Update a User
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException ;
    
    /**
     * Delete a user
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(UserDTO dto) throws ApplicationException;
    
    /**
     * Find user by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     * @throws RecordNotFoundException 
     */
    public UserDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Users with pagination
     * 
     * @return list : List of Users
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(UserDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Users
     * 
     * @return list : List of Users
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(UserDTO dto) throws ApplicationException;
    /**
     * Get List of Users
     * 
     * @return list : List of Users
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;
    /**
     * Get List of Users with pagination
     * 
     * @return list : List of Users
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;
    /**
     * Register a User
     * 
     * @param dto
     * @return
     * @throws ApplicationException
     * @throws DuplicateRecordException 
     */
    public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException
            ;
    /**
     * change passwod by pk
     * 
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return boolean
     * @throws RecordNotFoundException
     * @throws ApplicationException
     * @throws DuplicateRecordException 
     */
    public boolean changePassword(long id, String oldPassword,
            String newPassword) throws RecordNotFoundException,
            ApplicationException;
    
    /**
     * authenticate user
     * 
     * @param login
     *            : String login
     * @param password
     *            : password
     * @throws RecordNotFoundException 
     * @throws DatabaseException
     */
    public UserDTO authenticate(String login, String password)
            throws ApplicationException;
    
    /**
     * Send the password of User to his Email
     * 
     * @return boolean : true if success otherwise false
     * @param login
     *            : User Login
     * @throws ApplicationException
     * @throws RecordNotFoundException
     *             : if user not found
     */
   
    public boolean forgetPassword(String login) throws ApplicationException,
            RecordNotFoundException ;
}

