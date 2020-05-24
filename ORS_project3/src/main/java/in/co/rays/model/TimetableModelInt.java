package in.co.rays.model;

import java.util.Date;
import java.util.List;

import in.co.rays.dto.TimetableDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;


/**
 * Data Access Object of  timetable
 * 
 * @author Proxy
 * @version 1.0
 * @Copyright (c) Proxy
 */

public interface TimetableModelInt {

	 /**
     * Add a Timrtable
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when college already exists
     */
    public long add(TimetableDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a  timetable
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(TimetableDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a  timetable
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(TimetableDTO dto) throws ApplicationException;

    /**
     * Check by ExamTime
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */

    public TimetableDTO checkByExamTime(Long courseId, Long subjectId, String semester, Date examDate, String examTime) throws ApplicationException; 
    
    /**
     * Check by Course Name
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */

	public TimetableDTO checkByCourseName(Long courseId, Date examDate) throws ApplicationException;
	
	 /**
     * Check by Course Name
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
	
	public TimetableDTO checkBySubjectName(Long courseId, Long subjectId, Date examDate) throws ApplicationException;
	
	
	 /**
     * Check by Course Name
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
	
	public TimetableDTO checkBySemester(Long courseId, Long subjectId, String semester, Date examDate)
			throws ApplicationException;
	
    /**
     * Find timetable by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public TimetableDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search  timetable with pagination
     * 
     * @return list : List of  timetable
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(TimetableDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search College
     * 
     * @return list : List of College
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(TimetableDTO dto) throws ApplicationException;

    /**
     * Gets List of Timrtable
     * 
     * @return list : List of College
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * get List of Timrtable with pagination
     * 
     * @return list : List of Timrtable
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}

