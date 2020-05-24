package in.co.rays.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.FacultyDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;


/**
 * Hibernate Implementation of Faculty Model
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) proxy
 */
public class FacultyModelHibImpl implements FacultyModelInt {
Logger log = Logger.getLogger(FacultyModelHibImpl.class);

/**
 * Add a Faculty
 * 
 * @param bean
 * @return
 * @throws ApplicationException
 * @throws DuplicateRecordException
 */
public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException{
	log.debug("model add method start");
	long pk = 0;
	//get college name
	CollegeModelHibImpl collegeModel = new CollegeModelHibImpl();
	CollegeDTO collegedto = collegeModel.findByPK(dto.getCollegeId());
	dto.setCollegeName(collegedto.getName());
	
	//get course name
	CourseModelHibImpl coursemodel = new CourseModelHibImpl();
	CourseDTO coursedto = coursemodel.findByPK(dto.getCourseId());
	dto.setCourseName(dto.getCourseName());
	
	//get subject name
	SubjectModelHibImpl subjectModel = new SubjectModelHibImpl();
	SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());
	dto.setSubjectName(dto.getSubjectName());
	
	FacultyDTO dtoExist = findByEmail(dto.getEmail());
	if(dtoExist != null){
		throw new DuplicateRecordException("email already exist");
	}
	Session session = null;
	Transaction transaction = null;
	try {
		session = HibDataSource.getSession();
		transaction = session.beginTransaction();
		session.save(dto);
		pk = dto.getId();
		transaction.commit();
	}  catch (HibernateException e) {
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
 * Delete a User
 * 
 * @param dto
 * @throws DatabaseException
 */
public void delete(FacultyDTO dto) throws ApplicationException {
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

/**
 * Find Faculty by Email
 * 
 * @param email
 *            : get parameter
 * @return dto
 * @throws ApplicationException
 */
public FacultyDTO findByEmail(String email) throws ApplicationException{
	log.debug("model find by email start");
	Session session = null;
	FacultyDTO dto = null;
	try {
		session = HibDataSource.getSession();
		Criteria criteria = session.createCriteria(FacultyDTO.class);
		criteria.add(Restrictions.eq("email", email));
		List list = criteria.list();
		if(list.size() == 1){
		dto = (FacultyDTO) list.get(0);
		}
	} catch (HibernateException e) {
        log.error("Database Exception..", e);
        throw new ApplicationException(
                "Exception in getting User by Login " + e.getMessage());

    } finally {
        session.close();
    }

    log.debug("Model findByLoginId End");
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
public FacultyDTO findByPK(long pk) throws ApplicationException {
    log.debug("Model findByPK Started");
    Session session = null;
    FacultyDTO dto = null;
    try {
        session = HibDataSource.getSession();
        dto = (FacultyDTO) session.get(FacultyDTO.class, pk);
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
 * Update a Faculty
 * 
 * @param dto
 * @throws ApplicationException
 * @throws DuplicateRecordException
 */
public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
	log.debug("Model update Started");
	Session session = null;
	Transaction transaction = null;
	//get college name
		CollegeModelHibImpl collegeModel = new CollegeModelHibImpl();
		CollegeDTO collegedto = collegeModel.findByPK(dto.getCollegeId());
		dto.setCollegeName(collegedto.getName());
		
		//get course name
		CourseModelHibImpl coursemodel = new CourseModelHibImpl();
		CourseDTO coursedto = coursemodel.findByPK(dto.getCourseId());
		dto.setCourseName(dto.getCourseName());
		
		//get subject name
		SubjectModelHibImpl subjectModel = new SubjectModelHibImpl();
		SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());
		dto.setSubjectName(dto.getSubjectName());
		
		FacultyDTO dtoExist = findByEmail(dto.getEmail());
		if(dtoExist != null && dtoExist.getId() != dto.getId()){
		throw new DuplicateRecordException("login id already exist");
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
 * Search Faculty
 * 
 * @param dto
 *            : Search Parameters
 * @throws ApplicationException
 */

public List<FacultyDTO> search(FacultyDTO dto) throws ApplicationException {
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
public List search(FacultyDTO dto, int pageNo, int pageSize)
        throws ApplicationException {

    log.debug("Model search Started");
    Session session = null;
    List list = null;
    try {
    	session = HibDataSource.getSession();
        Criteria criteria = session.createCriteria(FacultyDTO.class);
			if (dto.getId() > 0) {
			criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCollegeId() > 0) {
			criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
			}
			if (dto.getSubjectId() > 0) {
		    criteria.add(Restrictions.eq("subjectId", dto.getSubjectId()));
			}
			if (dto.getCourseId() > 0) {
			criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
			criteria.add(Restrictions.like("firstName", dto.getFirstName()+"%"));
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
			criteria.add(Restrictions.like("lastName", dto.getLastName()+"%"));
			}
			
			
			
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
 * Get List of Course
 * 
 * @return list : List of Course
 * @throws ApplicationException
 */

public List<FacultyDTO> list() throws ApplicationException {
	return list(0, 0);
}

/**
 * get List of User with pagination
 * 
 * @return list : List of faculty
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
        Criteria criteria = session.createCriteria(FacultyDTO.class);

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
