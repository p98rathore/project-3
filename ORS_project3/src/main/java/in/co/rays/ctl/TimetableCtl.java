package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.TimetableDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.SubjectModelInt;
import in.co.rays.model.TimetableModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;


/**
 * Timetable functionality Controller. Performs operation for add, update,
 * delete and get Timetable
 * 
 * @author Proxy
 * @version 1.0 Copyright (c) Proxy
 */
@WebServlet(name = "TimetableCtl", urlPatterns = { "/ctl/TimetableCtl" })
public class TimetableCtl extends BaseCtl {

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(TimetableCtl.class);

	@SuppressWarnings("rawtypes")
	@Override
	protected void preload(HttpServletRequest request) {
		SubjectModelInt subjectModel = ModelFactory.getInstance().getSubjectModel();
		CourseModelInt courseModel = ModelFactory.getInstance().getCourseModel();

		try {
			List subjectList = subjectModel.list();
			request.setAttribute("subjectList", subjectList);

			List courseList = courseModel.list();
			request.setAttribute("courseList", courseList);

		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("TimetableCtl Method validate Started");

		boolean pass = true;

		@SuppressWarnings("unused")
		String op = DataUtility.getString(request.getParameter("operation"));
		String examDate = request.getParameter("examDate");

		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}
		if (DataValidator.isNull(examDate)) {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Date of Exam"));
			pass = false;
		} else if (!DataValidator.isDate(examDate)) {
			request.setAttribute("examDate", PropertyReader.getValue("error.date", "Date of Exam"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("examTime"))) {
			request.setAttribute("examTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}

		log.debug("TimetableCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("TimetableCtl Method populatedto Started");

		TimetableDTO dto = new TimetableDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setSemester(DataUtility.getString(request.getParameter("semester")));

		dto.setDescription(DataUtility.getString(request.getParameter("description")));

		dto.setExamTime(DataUtility.getString(request.getParameter("examTime")));

		dto.setExamDate(DataUtility.getDate(request.getParameter("examDate")));

		dto.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		dto.setSubId(DataUtility.getLong(request.getParameter("subjectId")));

		populateDTO(dto, request);

		log.debug("TimetableCtl Method populatedto Ended");

		return dto;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
System.out.println("do get time table>>>>");
		log.debug("TimetableCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		TimetableModelInt model = ModelFactory.getInstance().getTimetableModel();
		if (id > 0 || op != null) {
			TimetableDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("TimetableCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("timetable do post"+request.getParameter("operation"));

		log.debug("TimetableCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		TimetableModelInt model = ModelFactory.getInstance().getTimetableModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			System.out.println("time table op"+ op);

			TimetableDTO dto = (TimetableDTO) populateDTO(request);

			TimetableDTO dto1;
			TimetableDTO dto2;
			TimetableDTO dto3;
			try {
				dto1 = model.checkByCourseName(dto.getCourseId(), dto.getExamDate());

				dto2 = model.checkBySubjectName(dto.getCourseId(), dto.getSubId(), dto.getExamDate());

				dto3 = model.checkBySemester(dto.getCourseId(), dto.getSubId(), dto.getSemester(),
						dto.getExamDate());

				if (dto1 == null && dto2 == null && dto3 == null) {

					long pk = model.add(dto);
					//dto.setId(pk);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
				} else {
					dto = (TimetableDTO) populateDTO(request);
					ServletUtility.setDto(dto, request);
					ServletUtility.setErrorMessage("Exam already exist!", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Exam already exist!", request);
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			TimetableDTO dto = (TimetableDTO) populateDTO(request);

			TimetableDTO dto4;

			try {

				dto4 = model.checkByExamTime(dto.getCourseId(), dto.getSubId(), dto.getSemester(),
						dto.getExamDate(), dto.getExamTime());

				if (id > 0 && dto4 == null) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("Data is successfully updated", request);
				} else {
					dto = (TimetableDTO) populateDTO(request);
					ServletUtility.setDto(dto, request);
					ServletUtility.setErrorMessage("Exam already exist!", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Exam already exist!", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			TimetableDTO dto = (TimetableDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("TimetableCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

}