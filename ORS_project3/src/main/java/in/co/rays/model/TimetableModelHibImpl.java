package in.co.rays.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.dto.TimetableDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.util.EmailBuilder;
import in.co.rays.util.EmailMessage;
import in.co.rays.util.EmailUtility;
import in.co.rays.util.HibDataSource;


/**
* Hibernate Implementation of Timetable Model
* 
* @author Proxy
* @version 1.0 Copyright (c) Proxy
*/

public class TimetableModelHibImpl implements TimetableModelInt {
 
	Logger log = Logger.getLogger(TimetableModelHibImpl.class);
	
	/**
	 * Add a Timetable
	 * 
	 * @param dto
	 * @return
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(TimetableDTO dto) throws ApplicationException{
		log.debug("model add start");
		long pk = 0;
		//get course name
		CourseModelInt cmodel = new CourseModelHibImpl();
		CourseDTO cdto = cmodel.findByPK(dto.getCourseId());
		dto.setCourseName(cdto.getName());
		
		//get subject name
		SubjectModelInt smodel = new SubjectModelHibImpl();
		SubjectDTO sdto = smodel.findByPK(dto.getSubId());
		dto.setSubName(sdto.getName());
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibDataSource.getSession();
			transaction = session.beginTransaction();
			System.out.println("==+=====++++");
			session.save(dto);
		    pk = dto.getId();
		    
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in timetable Add "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model add End");
        return pk;
	}
	

	/**
	 * Delete a Timetable
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(TimetableDTO dto) throws ApplicationException{
		log.debug("Model delete Started");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in timetable Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
	}
	
	
	/**
	 * @param examTime
	 * @return
	 * @throws ApplicationException
	 */
	
	public TimetableDTO checkByExamTime(Long courseId, Long subjectId, String semester, Date examDate, String examTime){
		log.debug("model checkbyexamtime start");
		Session session = null;
		TimetableDTO dto = null;
			session = HibDataSource.getSession();
			Query q = session.createQuery("from TimetableDTO where courseId=? and subId=? and semester=? and examDate=? and examTime=?");
			q.setLong(0, courseId);
			q.setLong(1, subjectId);
			q.setString(2, semester);
			q.setDate(3, examDate);
			q.setString(4, examTime);
			 List list = q.list();
		        if (list.size() > 0) {
		            dto = (TimetableDTO) list.get(0);
		        } else {
		            dto = null;
		     }
		        log.debug("model exam time end");
		        return dto;
	}
	
	/**checkByCourseName
	 * @param courseId
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableDTO checkByCourseName(Long courseId, Date examDate){
		log.debug(" model checkByCourseName start");
		Session session = null;
		TimetableDTO dto = null;
		
		session = HibDataSource.getSession();
		Query q = session.createQuery("from TimetableDTO where courseId=? and examDate=?");
		q.setLong(0, courseId);
		q.setDate(1, examDate);
		List list = q.list();
		 if (list.size() > 0) {
	            dto = (TimetableDTO) list.get(0);
	        } else {
	            dto = null;
	     }
	        log.debug("model exam time end");
	        return dto;
	}
	
	/**
	 * @param courseId
	 * @param subjectId
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableDTO checkBySubjectName(Long courseId, Long subjectId, Date examDate){
	log.debug("model check by subject name start");
	Session session = null;
	TimetableDTO dto = null;
	session = HibDataSource.getSession();
	Query q = session.createQuery("from TimetableDTO where CourseId=? and subId=? and examDate=?");
	q.setLong(0, courseId);
	q.setLong(1, subjectId);
	q.setDate(2, examDate);
	List list = q.list();
	 if (list.size() > 0) {
           dto = (TimetableDTO) list.get(0);
       } else {
           dto = null;
    }
       log.debug("model checksubject end");
       return dto;
 }
	
	/**
	 * @param courseId
	 * @param subjectId
	 * @param semester
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	
	public TimetableDTO checkBySemester(Long courseId, Long subjectId, String semester, Date examDate){
		log.debug("model checkBySemester start");
		Session session = null;
		TimetableDTO dto = null;
		session = HibDataSource.getSession();
		Query q = session.createQuery("from TimetableDTO where courseId=? and subId=? and semester=? and examDate=?");
		q.setLong(0, courseId);
		q.setLong(1, subjectId);
		q.setString(2, semester);
		q.setDate(3, examDate);
		List list = q.list();
		 if (list.size() > 0) {
	           dto = (TimetableDTO) list.get(0);
	       } else {
	           dto = null;
	    }
	       log.debug("model checksubject end");
	       return dto;
	}
	
	 /**
     * Find timetable by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
	
	public TimetableDTO findByPK(long pk) throws ApplicationException {
	        log.debug("Model findByPK Started");
	        Session session = null;
	        TimetableDTO dto = null;
	        try {
	            session = HibDataSource.getSession();
	            dto =  (TimetableDTO) session.get(TimetableDTO.class, pk);
	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by pk");
	        } finally {
	            session.close();
	        }

	        log.debug("Model findByPK End");
	        return dto;
	    }

	 
		/**
		 * Update a Timetable
		 * 
		 * @param dto
		 * @throws ApplicationException
		 * @throws DuplicateRecordException
		 */
	 
	public void update(TimetableDTO dto) throws ApplicationException{
		//get course name
			CourseModelHibImpl cmodel = new CourseModelHibImpl();
			CourseDTO cdto = cmodel.findByPK(dto.getCourseId());
			dto.setCourseName(cdto.getName());
			
			//get subject name
			SubjectModelHibImpl smodel = new SubjectModelHibImpl();
			SubjectDTO sdto = smodel.findByPK(dto.getSubId());
			dto.setSubName(sdto.getName());
			
			Session session = null;
			Transaction transaction = null;	 
			try {
				session = HibDataSource.getSession();
				transaction = session.beginTransaction();
				session.update(dto);
				transaction.commit();
			} catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            if (transaction != null) {
	                transaction.rollback();
	                throw new ApplicationException("Exception in User Update"
	                        + e.getMessage());
	            }
	        } finally {
	            session.close();
	        }
	        log.debug("Model update End");
	 }
	

		/**
		 * Search Timetable
		 * 
		 * @param dto
		 *            : Search Parameters
		 * @throws ApplicationException
		 */


	public List<TimetableDTO> search(TimetableDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}
		
		/**
		 * Search Timetable with pagination
		 * 
		 * @return list : List of Timetable
		 * @param dto
		 *            : Search Parameters
		 * @param pageNo
		 *            : Current Page No.se
		 * @param pageSize
		 *            : Size of Page
		 * 
		 * @throws ApplicationException
		 */
         
	public List<TimetableDTO> search(TimetableDTO dto, int pageNo, int pageSize) throws ApplicationException{
			log.debug("model search method start");
			Session session = null;
			List list = null;
			
			try {
				session = HibDataSource.getSession();
				Criteria criteria = session.createCriteria(TimetableDTO.class);
				if (dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getCourseId() > 0) {
					criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
				}
				
				if (dto.getSubId() > 0) {
			    criteria.add(Restrictions.eq("subId", dto.getSubId()));
				}
				
				if (dto.getSemester() != null && dto.getSemester().length() > 0) {
			    criteria.add(Restrictions.like("semester", dto.getSemester()));
				}
				if (dto.getDescription() != null && dto.getDescription().length() > 0) {
			    criteria.add(Restrictions.like("description", dto.getDescription()));
				}
				if (dto.getExamDate() != null && dto.getExamDate().getDate() > 0) {
				criteria.add(Restrictions.eq("examDate", dto.getExamDate()));
				}
				if (dto.getExamTime() != null && dto.getExamTime().length() > 0) {
				criteria.add(Restrictions.like("examTime", dto.getExamTime()));
				}

				if (pageSize > 0) {
	                criteria.setFirstResult(((pageNo - 1) * pageSize));
	                criteria.setMaxResults(pageSize);
	            }

	            list = criteria.list();
	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException("Exception in user search");
	        } finally {
	            session.close();
	        }

	        log.debug("Model search End");
	        return list;
		}
	 

		/**
		 * Get List of Timetable
		 * 
		 * @return list : List of Timetable
		 * @throws ApplicationException
		 */

	public List<TimetableDTO> list() throws ApplicationException {
		return list(0, 0);
	}

		/**
		 * Get List of Timetable with pagination
		 * 
		 * @return list : List of Timetable
		 * @param pageNo
		 *            : Current Page No.
		 * @param pageSize
		 *            : Size of Page
		 * @throws ApplicationException
		 */

	public List<TimetableDTO> list(int pageNo, int pageSize) throws ApplicationException{
			log.debug("Model list Started");
	        Session session = null;
	        List list = null;
	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(TimetableDTO.class);

	            // if page size is greater than zero then apply pagination
	            if (pageSize > 0) {
	                pageNo = ((pageNo - 1) * pageSize) + 1;
	                criteria.setFirstResult(pageNo);
	                criteria.setMaxResults(pageSize);
	            }

	            list = criteria.list();
	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in  Users list");
	        } finally {
	            session.close();
	        }

	        log.debug("Model list End");
	        return list;
		}
	 
}
