package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * JDBC Implementation of CollegeModel
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class CollegeModelJDBCImpl implements CollegeModelInt {

    private static Logger log = Logger.getLogger(CollegeModelJDBCImpl.class);

    /**
     * Find next PK of College
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
                    .prepareStatement("SELECT MAX(ID) FROM collegedto");
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
     * Add a College
     * 
     * @param dto
     * @throws DatabaseException
     * 
     */
    public long add(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        long pk = 0;

        CollegeDTO duplicateCollegeName = findByName(dto.getName());

        if (duplicateCollegeName != null) {
            throw new DuplicateRecordException("College Name already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO collegedto VALUES(?,?,?,?,?,?,?,?,?,?)");
            pstmt.setLong(1, pk);
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getAddress());
            pstmt.setString(5, dto.getState());
            pstmt.setString(4, dto.getCity());
            pstmt.setString(6, dto.getPhoneNo());
            pstmt.setString(7, dto.getCreatedBy());
            pstmt.setString(8, dto.getModifiedBy());
            pstmt.setTimestamp(9, dto.getCreatedDatetime());
            pstmt.setTimestamp(10, dto.getModifiedDatetime());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in add College");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
    }

    /**
     * Delete a College
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void delete(CollegeDTO dto) throws ApplicationException {
        log.debug("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM collegedto WHERE ID=?");
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
                    "Exception : Exception in delete college");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model delete Started");
    }

    /**
     * Find User by College
     * 
     * @param login
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    public CollegeDTO findByName(String name) throws ApplicationException {
        log.debug("Model findByName Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM collegedto WHERE NAME=?");
        CollegeDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new CollegeDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setAddress(rs.getString(3));
                dto.setState(rs.getString(5));
                dto.setCity(rs.getString(4));
                dto.setPhoneNo(rs.getString(6));
                dto.setCreatedBy(rs.getString(7));
                dto.setModifiedBy(rs.getString(8));
                dto.setCreatedDatetime(rs.getTimestamp(9));
                dto.setModifiedDatetime(rs.getTimestamp(10));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting College by Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByName End");
        return dto;
    }

    /**
     * Find User by College
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    public CollegeDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM collegedto WHERE ID=?");
        CollegeDTO dto = null;
        Connection conn = null;
        try {

            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new CollegeDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setAddress(rs.getString(3));
                dto.setState(rs.getString(4));
                dto.setCity(rs.getString(5));
                dto.setPhoneNo(rs.getString(6));
                dto.setCreatedBy(rs.getString(7));
                dto.setModifiedBy(rs.getString(8));
                dto.setCreatedDatetime(rs.getTimestamp(9));
                dto.setModifiedDatetime(rs.getTimestamp(10));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting College by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByPK End");
        return dto;
    }

    /**
     * Update a College
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void update(CollegeDTO dto) throws ApplicationException {
        log.debug("Model update Started");
        Connection conn = null;

       

        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE collegedto SET NAME=?,ADDRESS=?,STATE=?,CITY=?,PHONENO=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getAddress());
            pstmt.setString(3, dto.getState());
            pstmt.setString(4, dto.getCity());
            pstmt.setString(5, dto.getPhoneNo());
            pstmt.setString(6, dto.getCreatedBy());
            pstmt.setString(7, dto.getModifiedBy());
            pstmt.setTimestamp(8, dto.getCreatedDatetime());
            pstmt.setTimestamp(9, dto.getModifiedDatetime());
            pstmt.setLong(10, dto.getId());
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
            throw new ApplicationException("Exception in updating College ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model update End");
    }

    /**
     * Search College with pagination
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
    public List search(CollegeDTO dto, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM collegedto WHERE 1=1");

        if (dto != null) {
            if (dto.getId() > 0) {
                sql.append(" AND id = " + dto.getId());
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                sql.append(" AND NAME like '" + dto.getName() + "%'");
            }
            if (dto.getAddress() != null && dto.getAddress().length() > 0) {
                sql.append(" AND ADDRESS like '" + dto.getAddress() + "%'");
            }
            if (dto.getState() != null && dto.getState().length() > 0) {
                sql.append(" AND STATE like '" + dto.getState() + "%'");
            }
            if (dto.getCity() != null && dto.getCity().length() > 0) {
                sql.append(" AND CITY like '" + dto.getCity() + "%'");
            }
            if (dto.getPhoneNo() != null && dto.getPhoneNo().length() > 0) {
                sql.append(" AND PHONENO = " + dto.getPhoneNo());
            }

        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }
System.out.println(sql);
        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new CollegeDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setAddress(rs.getString(3));
                dto.setState(rs.getString(5));
                dto.setCity(rs.getString(4));
                dto.setPhoneNo(rs.getString(6));
                dto.setCreatedBy(rs.getString(7));
                dto.setModifiedBy(rs.getString(8));
                dto.setCreatedDatetime(rs.getTimestamp(9));
                dto.setModifiedDatetime(rs.getTimestamp(10));
                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search college");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Search College
     * 
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
    public List search(CollegeDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Get List of College
     * 
     * @return list : List of College
     * @throws DatabaseException
     */
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of College with pagination
     * 
     * @return list : List of College
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from collegedto");
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
                CollegeDTO dto = new CollegeDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setAddress(rs.getString(3));
                dto.setState(rs.getString(5));
                dto.setCity(rs.getString(4));
                dto.setPhoneNo(rs.getString(6));
                dto.setCreatedBy(rs.getString(7));
                dto.setModifiedBy(rs.getString(8));
                dto.setCreatedDatetime(rs.getTimestamp(9));
                dto.setModifiedDatetime(rs.getTimestamp(10));
                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of users");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }
}
