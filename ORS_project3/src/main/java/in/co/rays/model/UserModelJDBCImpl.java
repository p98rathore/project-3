package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.util.EmailBuilder;
import in.co.rays.util.EmailMessage;
import in.co.rays.util.EmailUtility;
import in.co.rays.util.JDBCDataSource;

/**
 * JDBC Implementation of UserModel
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class UserModelJDBCImpl implements UserModelInt {
    private static Logger log = Logger.getLogger(UserModelJDBCImpl.class);

    /**
     * Find next PK of user
     * 
     * @throws DatabaseException
     */
    public Integer nextPK() throws DatabaseException {
        log.debug("Model nextPK Started");
        Connection conn = null;
        int pk = 0;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM userdto");
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
     * Add a User
     * 
     * @param dto
     * @throws DatabaseException
     * 
     */
    public long add(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;

        UserDTO existDto = findByLogin(dto.getLogin());

        if (existDto != null) {
            throw new DuplicateRecordException("Login Id already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO userdto VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, dto.getFirstName());
            pstmt.setString(3, dto.getLastName());
            pstmt.setString(4, dto.getLogin());
            pstmt.setString(5, dto.getPassword());
            pstmt.setDate(6, new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(7, dto.getMobileNo());

            pstmt.setString(8, dto.getGender());
            pstmt.setLong(9, dto.getRoleId());
            
            pstmt.setString(10, dto.getCreatedBy());
            pstmt.setString(11, dto.getModifiedBy());
            pstmt.setTimestamp(12,dto.getCreatedDatetime());
            pstmt.setTimestamp(13,dto.getModifiedDatetime());
        
           
            
         
          
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            e.printStackTrace();
            throw new ApplicationException("Exception : Exception in add User");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
    }

    /**
     * Delete a User
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void delete(UserDTO dto) throws ApplicationException {
        log.debug("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM userdto WHERE ID=?");
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
                    "Exception : Exception in delete User");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model delete Started");
    }

    /**
     * Find User by Login
     * 
     * @param login
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    public UserDTO findByLogin(String login) throws ApplicationException {
        log.debug("Model findByLogin Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM userdto WHERE LOGIN=?");
        UserDTO dto = null;
        Connection conn = null;
        System.out.println("sql" + sql);

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setGender(rs.getString(8));
                dto.setRoleId(rs.getLong(9));
                
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by login");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByLogin End");
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
    public UserDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM userdto WHERE ID=?");
        UserDTO dto = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	 dto = new UserDTO();
                 dto.setId(rs.getLong(1));
                 dto.setFirstName(rs.getString(2));
                 dto.setLastName(rs.getString(3));
                 dto.setLogin(rs.getString(4));
                 dto.setPassword(rs.getString(5));
                 dto.setDob(rs.getDate(6));
                 dto.setMobileNo(rs.getString(7));
                 dto.setGender(rs.getString(8));
                 dto.setRoleId(rs.getLong(9));
                 
                 dto.setCreatedBy(rs.getString(10));
                 dto.setModifiedBy(rs.getString(11));
                 dto.setCreatedDatetime(rs.getTimestamp(12));
                 dto.setModifiedDatetime(rs.getTimestamp(13));


            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
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
     * Update a user
     * 
     * @param dto
     * @throws DatabaseException
     */
    public void update(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Connection conn = null;

       

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE userdto SET FIRSTNAME=?,LASTNAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILENO=?,ROLEID=?,GENDER=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setString(1, dto.getFirstName());
            pstmt.setString(2, dto.getLastName());
            pstmt.setString(3, dto.getLogin());
            pstmt.setString(4, dto.getPassword());
            pstmt.setDate(5, new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(6, dto.getMobileNo());
            pstmt.setLong(7, dto.getRoleId());
            pstmt.setString(8, dto.getGender());
            pstmt.setString(9, dto.getCreatedBy());
            pstmt.setString(10, dto.getModifiedBy());
            pstmt.setTimestamp(11,dto.getCreatedDatetime());
            pstmt.setTimestamp(12,dto.getModifiedDatetime());
            pstmt.setLong(13, dto.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating User ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model update End");
    }

    /**
     * Search User
     * 
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
    public List search(UserDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Search User with pagination
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
    public List search(UserDTO dto, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM userdto WHERE 1=1");

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
            if (dto.getLogin() != null && dto.getLogin().length() > 0) {
                sql.append(" AND LOGIN like '" + dto.getLogin() + "%'");
            }
            if (dto.getPassword() != null && dto.getPassword().length() > 0) {
                sql.append(" AND PASSWORD like '" + dto.getPassword() + "%'");
            }
            if (dto.getDob() != null && dto.getDob().getDate() > 0) {
                sql.append(" AND DOB = " + dto.getGender());
            }
            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
                sql.append(" AND MOBILENO = " + dto.getMobileNo());
            }
            if (dto.getRoleId() > 0) {
                sql.append(" AND ROLEID = " + dto.getRoleId());
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
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setRoleId(rs.getLong(9));
                dto.setGender(rs.getString(8));
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            e.printStackTrace();
            throw new ApplicationException(
                    "Exception : Exception in search user");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Get List of User
     * 
     * @return list : List of User
     * @throws DatabaseException
     */
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of User with pagination
     * 
     * @return list : List of users
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        UserDTO dto=null;
        
        StringBuffer sql = new StringBuffer("select * from userdto");
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
            	dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setRoleId(rs.getLong(9));
                dto.setGender(rs.getString(8));
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
                    "Exception : Exception in getting list of users");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }

    /**
     * @param id
     *            : long id
     * @param old
     *            password : String oldPassword
     * @param new password : String newPassword
     * @throws DatabaseException
     */
    public UserDTO authenticate(String login, String password)
            throws ApplicationException {
        log.debug("Model authenticate Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM userdto WHERE LOGIN = ? AND PASSWORD = ?");
        UserDTO dto = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setRoleId(rs.getLong(9));
                dto.setGender(rs.getString(8));
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

            }
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception : Exception in get roles");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model authenticate End");
        return dto;
    }

    
    


    /**
     * @param id
     *            : long id
     * @param old
     *            password : String oldPassword
     * @param newpassword
     *            : String newPassword
     * @throws DatabaseException
     */
    public boolean changePassword(long id, String oldPassword,
            String newPassword) throws RecordNotFoundException,
            ApplicationException {

        log.debug("model changePassword Started");
        boolean flag = false;
        UserDTO dtoExist = null;

        dtoExist = findByPK(id);
        if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
            dtoExist.setPassword(newPassword);
            try {
                update(dtoExist);
            } catch (Exception e) {
                log.error(e);
                e.printStackTrace();
                throw new ApplicationException("LoginId is already exist");
            }
            flag = true;
        } else {
            throw new RecordNotFoundException("Login not exist");
        }

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("login", dtoExist.getLogin());
        map.put("password", dtoExist.getPassword());
        map.put("firstName", dtoExist.getFirstName());
        map.put("lastName", dtoExist.getLastName());

        String message = EmailBuilder.getChangePasswordMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dtoExist.getLogin());
        msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        log.debug("Model changePassword End");
        return flag;

    }

   

    /**
     * Register a user
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when user already exists
     */
    public long registerUser(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {

        log.debug("Model add Started");

        long pk = add(dto);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", dto.getLogin());
        map.put("password", dto.getPassword());

        String message = EmailBuilder.getUserRegistrationMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dto.getLogin());
        msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);
        return pk;
    }

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
            RecordNotFoundException {
        UserDTO userData = findByLogin(login);
        boolean flag = false;

        if (userData == null) {
            throw new RecordNotFoundException("Email Id Does not matched.");

        }

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", userData.getLogin());
        map.put("password", userData.getPassword());
        map.put("firstName", userData.getFirstName());
        map.put("lastName", userData.getLastName());
        String message = EmailBuilder.getForgetPasswordMessage(map);
        EmailMessage msg = new EmailMessage();
        msg.setTo(login);
        msg.setSubject("SUNARYS ORS Password reset");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);
        EmailUtility.sendMail(msg);
        flag = true;

        return flag;
    }
}
