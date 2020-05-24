package in.co.rays.model;

import java.util.List;

import javax.swing.text.StyledDocument;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;
/**
 * Hibernate Implementation of StudentModel
 * 
 * @author Proxy 
 * @version 1.0
 * @Copyright (c) Proxy
 */
public class StudentModelHibImpl implements StudentModelInt{
	Logger log =Logger.getLogger(StudentDTO.class);
	 /**
     * Add a Student
     * 
     * @param dto
     * @throws DatabaseException
     * 
     */
    public long add(StudentDTO dto) throws ApplicationException,
            DuplicateRecordException {

        log.debug("Model add Started");
        StudentDTO exist=null;
        long pk = 0;
        Session session = HibDataSource.getSession();
        Transaction transaction = null;
   exist    = findByEmailId(dto.getEmail());
        if(exist!=null){
        	throw new DuplicateRecordException("student already exist");
        }
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
            throw new ApplicationException("Exception in Student Add "
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model add End");
        return dto.getId();
    }

    /**
     * Delete a Student
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void delete(StudentDTO dto) throws ApplicationException {
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
            throw new ApplicationException("Exception in Student Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
    }

    /**
     * Find Student by Login
     * 
     * @param login
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    public StudentDTO findByEmailId(String email) throws ApplicationException {
        log.debug("Model findByLoginId Started");
        Session session = null;
        StudentDTO dto = null;
        Criteria criteria =null;
        try {
            session = HibDataSource.getSession();
        criteria   = session.createCriteria(StudentDTO.class);
            criteria.add(Restrictions.like("email", email));
            List list = criteria.list();

            if (list.size() == 1) {
                dto = (StudentDTO) list.get(0);
            }

        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception in getting Student by email " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByLoginId End");
        return dto;
    }

    /**
     * Find Student by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    public StudentDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        Session session = null;
        StudentDTO dto = null;
        List list=null;
        
        try {
            session = HibDataSource.getSession();
           Criteria crit = session.createCriteria(StudentDTO.class);
           crit.add(Restrictions.eq("id", pk));
             list = crit.list();

            if (list.size() == 1) {
                dto = (StudentDTO) list.get(0);
            }
        } catch (HibernateException e) {
        	e.printStackTrace();
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting Student by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;
    }

    /**
     * Update a Student
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void update(StudentDTO dto) throws ApplicationException {
        log.debug("Model update Started");
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
                throw new ApplicationException("Exception in Student Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
    }

    /**
     * Searches Student
     * 
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
    public List search(StudentDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

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
     * 
     * @throws DatabaseException
     */
    public List search(StudentDTO dto, int pageNo, int pageSize)
            throws ApplicationException {

        log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(StudentDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
                criteria.add(Restrictions.like("firstName", dto.getFirstName()
                        + "%"));
            }
            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
                criteria.add(Restrictions.like("lastName", dto.getLastName()
                        + "%"));
            }
            if (dto.getEmail() != null && dto.getEmail().length() > 0) {
                criteria.add(Restrictions.like("email", dto.getEmail()
                        + "%"));
            }
            if (dto.getCollegeID() > 0) {
                criteria.add(Restrictions.eq("collegeID", dto.getCollegeID()));
            }

            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in Student search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Gets List of Student
     * 
     * @return list : List of Student
     * @throws DatabaseException
     */
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * get List of Student with pagination
     * 
     * @return list : List of Student
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
            Criteria criteria = session.createCriteria(StudentDTO.class);

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
                    "Exception : Exception in  Student list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
    }}
