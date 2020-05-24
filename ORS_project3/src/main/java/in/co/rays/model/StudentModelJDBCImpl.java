package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * JDBC Implementation of Student Model
 * 
 * @author Proxy
 * @version 1.0
 * @Copyright (c) Proxy
 */
public class StudentModelJDBCImpl implements StudentModelInt {

    private static Logger log = Logger.getLogger(StudentModelJDBCImpl.class);

    /**
     * Find next PK of Student
     * 
     * @throws DatabaseException
     */
    public Long nextPK() throws DatabaseException {
        log.debug("Model nextPK Started");
        Connection conn = null;
        long pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM studentdto");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
            rs.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model nextPK End");
        return pk + 1;
    }

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
        Connection conn = null;
        
        // get College Name

    	// CollegeModelInt cModel= new CollegeModelJDBCImpl();
       CollegeModelInt cModel = ModelFactory.getInstance().getCollegeModel();
        CollegeDTO collegeDTO = cModel.findByPK(dto.getCollegeID());
        dto.setCollegeName(collegeDTO.getName());
        
        StudentDTO duplicateName = findByEmailId(dto.getEmail());
        long pk = 0;
        
        if (duplicateName != null) {
            throw new DuplicateRecordException("Email already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO studentdto VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setLong(1, pk);
           pstmt.setString(2, dto.getFirstName());
           pstmt.setString(3,dto.getLastName());
           pstmt.setLong(4, dto.getCollegeID());
           pstmt.setString(5, dto.getCollegeName());
           pstmt.setString(6, dto.getGender());
           pstmt.setDate(7, new java.sql.Date(dto.getDob().getTime()));
           pstmt.setString(8, dto.getMobileNo());
           pstmt.setString(9, dto.getEmail());
           pstmt.setString(10, dto.getCreatedBy());
           pstmt.setString(11, dto.getModifiedBy());
           pstmt.setTimestamp(12, dto.getCreatedDatetime());
           pstmt.setTimestamp(13, dto.getModifiedDatetime());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in add Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
    }

    /**
     * Delete a Student
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void delete(StudentDTO dto) throws ApplicationException {
        log.debug("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM studentdto WHERE ID=?");
            pstmt.setLong(1, dto.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in delete Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model delete Started");
    }

    /**
     * Find User by Student
     * 
     * @param Email
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    public StudentDTO findByEmailId(String Email) throws ApplicationException {
        log.debug("Model findBy Email Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM studentdto WHERE EMAIL=?");
        StudentDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, Email);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new StudentDTO();
                dto.setId(rs.getLong(1));
               dto.setFirstName(rs.getString(2));
               dto.setLastName(rs.getString(3));
               dto.setCollegeID(rs.getLong(4));
               dto.setCollegeName(rs.getString(5));
               dto.setGender(rs.getString(6));
               dto.setDob(rs.getDate(7));
               dto.setMobileNo(rs.getString(8));
               dto.setEmail(rs.getString(9));
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by Email");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findBy Email End");
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
        StringBuffer sql = new StringBuffer("SELECT * FROM studentdto WHERE ID=?");
        StudentDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	 dto = new StudentDTO();
                 dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setCollegeID(rs.getLong(4));
                dto.setCollegeName(rs.getString(5));
                dto.setGender(rs.getString(6));
                dto.setDob(rs.getDate(7));
                dto.setMobileNo(rs.getString(8));
                dto.setEmail(rs.getString(9));
                 dto.setCreatedBy(rs.getString(10));
                 dto.setModifiedBy(rs.getString(11));
                 dto.setCreatedDatetime(rs.getTimestamp(12));
                 dto.setModifiedDatetime(rs.getTimestamp(13));
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
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
    public void update(StudentDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Connection conn = null;
        
        StudentDTO dtoExist = findByEmailId(dto.getEmail());

      
        
        // get College Name
                CollegeModelInt cModel = ModelFactory.getInstance().getCollegeModel();
                CollegeDTO collegeDTO = cModel.findByPK(dto.getCollegeID());
                dto.setCollegeName(collegeDTO.getName());
        
        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE STUDENTDTO SET COLLEGEID=?,COLLEGENAME=?,FIRSTNAME=?,LASTNAME=?,GENDER,DOB=?,MOBILENO=?,EMAIL=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setLong(1, dto.getCollegeID());
            pstmt.setString(2, dto.getCollegeName());
            pstmt.setString(3, dto.getFirstName());
            pstmt.setString(4, dto.getLastName());
            pstmt.setString(5, dto.getGender());
            pstmt.setDate(6, new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(7, dto.getMobileNo());
            pstmt.setString(8, dto.getEmail());
            pstmt.setString(9, dto.getCreatedBy());
            pstmt.setString(10, dto.getModifiedBy());
            pstmt.setTimestamp(11, dto.getCreatedDatetime());
            pstmt.setTimestamp(12, dto.getModifiedDatetime());
            pstmt.setLong(13, dto.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating Student ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model update End");
    }

    /**
     * Search Student
     * 
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
    public List search(StudentDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Search Student with pagination
     * 
     * @return list : List of Students
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
        StringBuffer sql = new StringBuffer("SELECT * FROM STUDENTDTO WHERE 1=1");

        if (dto != null) {
            if (dto.getId() > 0) {
                sql.append(" AND id = " + dto.getId());
            }
            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
                sql.append(" AND FIRSTNAME like '" + dto.getFirstName() + "%'");
            }
            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
                sql.append(" AND LASTNAME like '" + dto.getLastName() + "%'");
            }
            if (dto.getDob() != null && dto.getDob().getDate() > 0) {
                sql.append(" AND DOB = " + dto.getDob());
            }
            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
                sql.append(" AND MOBILENO like '" + dto.getMobileNo() + "%'");
            }
            if (dto.getEmail() != null && dto.getEmail().length() > 0) {
                sql.append(" AND EMAIL like '" + dto.getEmail() + "%'");
            }
            if (dto.getCollegeName() != null && dto.getCollegeName().length() > 0) {
                sql.append(" AND COLLEGENAME = " + dto.getCollegeName());
            }

        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	dto = new StudentDTO();
                dto.setId(rs.getLong(1));
               dto.setFirstName(rs.getString(2));
               dto.setLastName(rs.getString(3));
               dto.setCollegeID(rs.getLong(4));
               dto.setCollegeName(rs.getString(5));
               dto.setGender(rs.getString(6));
               dto.setDob(rs.getDate(7));
               dto.setMobileNo(rs.getString(8));
               dto.setEmail(rs.getString(9));
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));
                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Get List of Student
     * 
     * @return list : List of Student
     * @throws DatabaseException
     */
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Student with pagination
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
        ArrayList list = new ArrayList();
        StudentDTO dto=null;
        StringBuffer sql = new StringBuffer("select * from STUDENTDTO");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	dto = new StudentDTO();
                dto.setId(rs.getLong(1));
               dto.setFirstName(rs.getString(2));
               dto.setLastName(rs.getString(3));
               dto.setCollegeID(rs.getLong(4));
               dto.setCollegeName(rs.getString(5));
               dto.setGender(rs.getString(6));
               dto.setDob(rs.getDate(7));
               dto.setMobileNo(rs.getString(8));
               dto.setEmail(rs.getString(9));
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));
                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }
}
