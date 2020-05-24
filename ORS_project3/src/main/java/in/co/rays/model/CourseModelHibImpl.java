package in.co.rays.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;


/**
 * Hibernate Implementation of Course Model
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) Proxy
 */
public class CourseModelHibImpl implements CourseModelInt {
	Logger log = Logger.getLogger(CourseModelHibImpl.class);
    
	
	/** Add course
	 * @param dto
	 * @return
	 * @throws DuplicateRecordException 
	 * @throws ApplicationException 
	 */
	public long add(CourseDTO dto) throws DuplicateRecordException, ApplicationException{
		log.debug("coursemodel add method start");
		long pk = 0;
		CourseDTO duplicateCourse = findByName(dto.getName());
		if(duplicateCourse != null){
			throw new DuplicateRecordException("course name already exist");
		}
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibDataSource.getSession();
			transaction = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			transaction.commit();
		} catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in User Add "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("courseModel add End");
        return dto.getId();
	}
	
	 /**
     * Delete a User
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void delete(CourseDTO dto) throws ApplicationException {
        log.debug("course Model delete Started");
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
            throw new ApplicationException("Exception in course Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
    }
	
	
	/**  find course by name
	 * @param name
	 * @return
	 * @throws ApplicationException
	 */
	public CourseDTO findByName(String name) throws ApplicationException{
		log.debug("course model findbyname start");
		Session session = null;
		CourseDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CourseDTO.class);
			criteria.add(Restrictions.eq("name", name));
			 List list =  criteria.list();
			if(list.size() == 1){
			dto = (CourseDTO) list.get(0);
			}
		} catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception in getting User by Login " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByName End");
        return dto;
	}
		
	
	 /**
     * Find User by Login
     * 
     * @param login
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
	
	public CourseDTO findByPK(long pk) throws ApplicationException{
		log.debug("course model findbypk start");
		Session session = null;
		CourseDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (CourseDTO) session.get(CourseDTO.class, pk);
		}  catch (HibernateException e) {
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
     * Find User by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
	 * @throws DuplicateRecordException 
	 * @throws ApplicationException 
     * @throws DatabaseException
     */
	
	public void update(CourseDTO dto) throws DuplicateRecordException, ApplicationException{
		log.debug("model update method start");
		CourseDTO duplicateCourse = findByName(dto.getName());
		if(duplicateCourse != null && duplicateCourse.getId() != dto.getId()){
			throw new DuplicateRecordException("course is already exist");
		}
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
     * Searches User
     * 
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
    public List search(CourseDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }
    
    
    
    /**
	 * Search Course with pagination
	 * 
	 * @return list : List of Course
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */
    
    public List search(CourseDTO dto , int pageNo, int pageSize) throws ApplicationException{
    	log.debug("model search method start");
    	Session session = null;
    	List list = null;
    	try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CourseDTO.class);
			if(dto.getId() > 0){
			criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if(dto.getName() != null && dto.getName().length() > 0){
			criteria.add(Restrictions.like("firstName", dto.getName()+"%"));
			}
			if(dto.getDuration() != null && dto.getDuration().length() > 0){
			criteria.add(Restrictions.like("duration", dto.getDuration()+"%"));
			}
			if(dto.getDescription() != null && dto.getDescription().length() > 0){
			criteria.add(Restrictions.like("description", dto.getDescription()+"%"));
			}
			if(pageSize > 0){
			criteria.setFirstResult(((pageNo-1)*pageSize));
			criteria.setMaxResults(pageSize);
			}
		    list = criteria.list();
		    }catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in user search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;
    }
	
    
    /**
	 * Get List of Course
	 * 
	 * @return list : List of Course
	 * @throws ApplicationException
	 */

	public List<CourseDTO> list() throws ApplicationException {
		return list(0, 0);
	}
	
	public List list(int pageNo, int pageSize) throws ApplicationException{
	log.debug("model lis method start");
	Session session = null;
	List list = null;
    try {
    	session = HibDataSource.getSession();
		Criteria criteria = session.createCriteria(CourseDTO.class);
		  if (pageSize > 0) {
              pageNo = ((pageNo - 1) * pageSize);
              criteria.setFirstResult(pageNo);
              criteria.setMaxResults(pageSize);
          }
		  list=criteria.list();
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
