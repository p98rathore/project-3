package in.co.rays.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;



/**
 * Hibernate Implementation of Subject Model
 * 
 * @author Proxy
 *  * @version 1.0
 * @Copyright (c) Proxy
 */
public class SubjectModelHibImpl implements SubjectModelInt {
Logger log = Logger.getLogger(SubjectModelHibImpl.class);

/**
 * Add a Subject
 * 
 * @param dto
 * @throws ApplicationException 
 * @throws DuplicateRecordException 
 * @throws DatabaseException
 * 
 */

public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException{
	
	log.debug("model add method start");
	long pk = 0;
	CourseModelHibImpl model = new CourseModelHibImpl();
	CourseDTO coursedto = model.findByPK(dto.getCourseId());
	dto.setCourseName(coursedto.getName());

	SubjectDTO duplicateSubject = findByName(dto.getName());
	// Check if create Subject Name already exist
	if (duplicateSubject != null) {
		throw new DuplicateRecordException("Subject Name already exists");
	}
	Session session = HibDataSource.getSession();
    Transaction transaction = null;
    try {
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
    log.debug("Model add End");
    return dto.getId();
}


/**
 * Delete a Subject
 * 
 * @param dto
 * @throws ApplicationException
 */

public void delete(SubjectDTO dto) throws ApplicationException{
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
        throw new ApplicationException("Exception in User Delete"
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
public SubjectDTO findByName(String name) throws ApplicationException{
	log.debug("course model findbyname start");
	Session session = null;
	SubjectDTO dto = null;
	try {
		session = HibDataSource.getSession();
		Criteria criteria = session.createCriteria(SubjectDTO.class);
		criteria.add(Restrictions.eq("name", name));
		 List list =  criteria.list();
		if(list.size() == 1){
		dto = (SubjectDTO) list.get(0);
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
 * Find User by PK
 * 
 * @param pk
 *            : get parameter
 * @return dto
 * @throws DatabaseException
 */

public SubjectDTO findByPK(long pk) throws ApplicationException {
    log.debug("Model findByPK Started");
    Session session = null;
    SubjectDTO dto = null;
    try {
        session = HibDataSource.getSession();
        dto = (SubjectDTO) session.get(SubjectDTO.class, pk);
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
 * Update a User
 * 
 * @param dto
 * @throws DatabaseException
 */

public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
	log.debug("Model update Started");
	Session session = null;
    Transaction transaction = null;

	// get Course Name
	CourseModelHibImpl courseModel = new CourseModelHibImpl();
	CourseDTO coursedto = courseModel.findByPK(dto.getCourseId());
	dto.setCourseName(coursedto.getName());

	SubjectDTO duplicateSubject = findByName(dto.getName());
	// Check if updated Subject already exist
	if (duplicateSubject != null && duplicateSubject.getId() != dto.getId()) {
		throw new DuplicateRecordException("Suject already exists");
	}
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
public List search(SubjectDTO dto) throws ApplicationException {
    return search(dto, 0, 0);
}

/**
 * Searches Users with pagination
 * 
 * @return list : List of Users
 * @param dto
 *            : Search Parameters
 * @param pageNo
 *            : Current Page No.
 * @param pageSize
 *            : Size of Page
 * 
 * @throws DatabaseException
 */
public List search(SubjectDTO dto, int pageNo, int pageSize)
        throws ApplicationException {

    
    log.debug("Model search Started");
    Session session = null;
    List list = null;
    try {
        session = HibDataSource.getSession();
        Criteria criteria = session.createCriteria(SubjectDTO.class);

        if (dto.getId() > 0) {
            criteria.add(Restrictions.eq("id", dto.getId()));
        }
        if(dto.getName() != null && dto.getName().length() > 0){
			criteria.add(Restrictions.like("name", dto.getName()+"%"));
		}
        if(dto.getCourseId() > 0){
        	criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
        }
       /* if(dto.getCourseName() == null && dto.getCourseName().length()>0){
        criteria.add(Restrictions.like("courseName", dto.getCourseName()+"%"));
        }
        if(dto.getDescription() == null && dto.getDescription().length()>0){
        criteria.add(Restrictions.like("description", dto.getDescription()+"%"));        }
*/
        // if page size is greater than zero the apply pagination
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
 * Gets List of subject
 * 
 * @return list : List of subject
 * @throws DatabaseException
 */
public List list() throws ApplicationException {
    return list(0, 0);
}

/**
 * get List of subject with pagination
 * 
 * @return list : List of Users
 * @param pageNo
 *            : Current Page No.
 * @param pageSize
 *            : Size of Page
 * @throws DatabaseException
 */
public List list(int pageNo, int pageSize) throws ApplicationException {
    log.debug("Model list Started");
    Session session = null;
    List list = null;
    try {
        session = HibDataSource.getSession();
        Criteria criteria = session.createCriteria(SubjectDTO.class);

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

