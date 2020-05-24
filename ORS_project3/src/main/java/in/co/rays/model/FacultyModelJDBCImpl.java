package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.FacultyDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;



/**
 * JDBC Implementation of Faculty Model
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) Proxy
 */

public class FacultyModelJDBCImpl implements FacultyModelInt {

	private static Logger log = Logger.getLogger(FacultyModelJDBCImpl.class);

	/**
	 * Find next PK of Faculty
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM facultydto");
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
	 * Add a Faculty
	 * 
	 * @param dto
	 * @return
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		// get College Name
		CollegeModelJDBCImpl collegeModel = new CollegeModelJDBCImpl();
		CollegeDTO collegedto = collegeModel.findByPK(dto.getCollegeId());
		dto.setCollegeName(collegedto.getName());

		// get Course Name
		CourseModelJDBCImpl courseModel = new CourseModelJDBCImpl();
		CourseDTO coursedto = courseModel.findByPK(dto.getCourseId());
		dto.setCourseName(coursedto.getName());

		// get Subject Name
		SubjectModelJDBCImpl subjectModel = new SubjectModelJDBCImpl();
		SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());
		dto.setSubjectName(subjectdto.getName());

		FacultyDTO existdto = findByEmail(dto.getEmail());
		if (existdto != null) {
			throw new DuplicateRecordException("Email Id already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_FACULTY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
			pstmt.setInt(1, pk);
			pstmt.setString(2, dto.getFirstName());
			pstmt.setString(3, dto.getLastName());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getMobileNo());
			pstmt.setDate(6, new java.sql.Date(dto.getDob().getTime()));
			pstmt.setString(7, dto.getQualification());
			pstmt.setString(8, dto.getCollegeName());
			pstmt.setLong(9, dto.getCollegeId());
			pstmt.setString(10, dto.getCourseName());
			pstmt.setLong(11, dto.getCourseId());
			pstmt.setString(12, dto.getGender());
			pstmt.setString(13, dto.getSubjectName());
			pstmt.setLong(14, dto.getSubjectId());
			pstmt.setString(15, dto.getCreatedBy());
			
			pstmt.setString(16, dto.getModifiedBy());
			pstmt.setTimestamp(17, dto.getCreatedDatetime());
			pstmt.setTimestamp(18, dto.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Faculty
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(FacultyDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM facultydto WHERE ID=?");
			pstmt.setLong(1, dto.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find Faculty by Email
	 * 
	 * @param email
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */

	public FacultyDTO findByEmail(String email) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM facultydto WHERE EMAIL=?");
		FacultyDTO dto = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setGender(rs.getString(4));
				dto.setMobileNo(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setQualification(rs.getString(7));
				dto.setCollegeName(rs.getString(8));
				dto.setCollegeId(rs.getLong(9));
				dto.setCourseName(rs.getString(10));
				dto.setCourseId(rs.getLong(11));
				dto.setEmail(rs.getString(12));
				dto.setSubjectName(rs.getString(13));
				dto.setSubjectId(rs.getLong(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Faculty by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByEmail End");
		return dto;
	}

	/**
	 * Find Faculty by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */

	public FacultyDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM facultydto WHERE ID=?");
		FacultyDTO dto = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setGender(rs.getString(4));
				dto.setMobileNo(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setQualification(rs.getString(7));
				dto.setCollegeName(rs.getString(8));
				dto.setCollegeId(rs.getLong(9));
				dto.setCourseName(rs.getString(10));
				dto.setCourseId(rs.getLong(11));
				dto.setEmail(rs.getString(12));
				dto.setSubjectName(rs.getString(13));
				dto.setSubjectId(rs.getLong(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Faculty by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
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
		Connection conn = null;

		// get College Name
		CollegeModelJDBCImpl collegeModel = new CollegeModelJDBCImpl();
		CollegeDTO collegedto = collegeModel.findByPK(dto.getCollegeId());
		dto.setCollegeName(collegedto.getName());

		// get Course Name
		CourseModelJDBCImpl courseModel = new CourseModelJDBCImpl();
		CourseDTO coursedto = courseModel.findByPK(dto.getCourseId());
		dto.setCourseName(coursedto.getName());

		// get Subject Name
		SubjectModelJDBCImpl subjectModel = new SubjectModelJDBCImpl();
		SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());
		dto.setSubjectName(subjectdto.getName());

		FacultyDTO dtoExist = findByEmail(dto.getEmail());
		// Check if updated Email Id already exist
		if (dtoExist != null && !(dtoExist.getId() == dto.getId())) {
			throw new DuplicateRecordException("EmailId is already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE facultydto SET COLLEGEID=?,SUBJECTID=?,COURSEID=?,FIRSTNAME=?,LASTNAME=?,GENDER=?,DOB=?,EMAIL=?"
							+ ",MOBILENO=?,COURSENAME=?,COLLEGENAME=?,SUBJECTNAME=?,"
							+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=?,QUALIFICATION=?, WHERE ID=?");

			pstmt.setLong(1, dto.getCollegeId());
			pstmt.setLong(2, dto.getSubjectId());
			pstmt.setLong(3, dto.getCourseId());
			pstmt.setString(4, dto.getFirstName());
			pstmt.setString(5, dto.getLastName());
			pstmt.setString(6, dto.getGender());
			pstmt.setDate(7, new java.sql.Date(dto.getDob().getTime()));
			pstmt.setString(8, dto.getEmail());
			pstmt.setString(9, dto.getMobileNo());
			pstmt.setString(10, dto.getCourseName());
			pstmt.setString(11, dto.getCollegeName());
			pstmt.setString(12, dto.getSubjectName());
			pstmt.setString(13, dto.getCreatedBy());
			pstmt.setString(14, dto.getModifiedBy());
			pstmt.setTimestamp(15, dto.getCreatedDatetime());
			pstmt.setTimestamp(16, dto.getModifiedDatetime());
			pstmt.setString(17, dto.getQualification());
			pstmt.setLong(18, dto.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Faculty ");
		} finally {
			JDBCDataSource.closeConnection(conn);
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
	 * Search Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */

	@SuppressWarnings("deprecation")
	public List<FacultyDTO> search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM facultydto WHERE 1=1");

		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND ID = " + dto.getId());
			}
			if (dto.getCollegeId() > 0) {
				sql.append(" AND COLLEGEID = " + dto.getCollegeId());
			}
			if (dto.getSubjectId() > 0) {
				sql.append(" AND SUBJECTID = " + dto.getSubjectId());
			}
			if (dto.getCourseId() > 0) {
				sql.append(" AND COURSEID = " + dto.getCourseId());
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				sql.append(" AND FIRSTNAME like '" + dto.getFirstName() + "%'");
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				sql.append(" AND LASTNAME like '" + dto.getLastName() + "%'");
			}
			if (dto.getGender() != null && dto.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + dto.getGender() + "%'");
			}
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + dto.getDob());
			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				sql.append(" AND EMAIL like '" + dto.getEmail() + "%'");
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				sql.append(" AND MOBILENO = " + dto.getMobileNo());
			}
			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				sql.append(" AND COURSENAME like '" + dto.getCourseName() + "%'");
			}
			if (dto.getCollegeName() != null && dto.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGENAME like '" + dto.getCollegeName() + "%'");
			}
			if (dto.getSubjectName() != null && dto.getSubjectName().length() > 0) {
				sql.append(" AND SUBJECTNAME like '" + dto.getSubjectName() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList<FacultyDTO> list = new ArrayList<FacultyDTO>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setGender(rs.getString(4));
				dto.setMobileNo(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setQualification(rs.getString(7));
				dto.setCollegeName(rs.getString(8));
				dto.setCollegeId(rs.getLong(9));
				dto.setCourseName(rs.getString(10));
				dto.setCourseId(rs.getLong(11));
				dto.setEmail(rs.getString(12));
				dto.setSubjectName(rs.getString(13));
				dto.setSubjectId(rs.getLong(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of Faculty
	 * 
	 * @return list : List of Faculty
	 * @throws ApplicationException
	 */

	public List<FacultyDTO> list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List<FacultyDTO> list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList<FacultyDTO> list = new ArrayList<FacultyDTO>();
		StringBuffer sql = new StringBuffer("select * from facultydto ORDER BY FIRSTNAME");
		// if page size is greater than zero then apply pagination
		FacultyDTO dto=null;
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
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setGender(rs.getString(4));
				dto.setMobileNo(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setQualification(rs.getString(7));
				dto.setCollegeName(rs.getString(8));
				dto.setCollegeId(rs.getLong(9));
				dto.setCourseName(rs.getString(10));
				dto.setCourseId(rs.getLong(11));
				dto.setEmail(rs.getString(12));
				dto.setSubjectName(rs.getString(13));
				dto.setSubjectId(rs.getLong(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
}
