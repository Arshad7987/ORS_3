package in.co.sunrays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.FacultyModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
/** 
* Faculty functionality Controller. Performs operation for add, update, 
* delete and get Faculty 
*  
* @author SUNRAYS Technologies 
* @version 1.0 
* @Copyright (c) SUNRAYS Technologies 
*/
@WebServlet(urlPatterns = "/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(UserCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("FacultyCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isFname(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isFname(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile Numeber"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile", "Mobile Numeber"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("loginId"))) {
			request.setAttribute("loginId", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("loginId"))) {
			request.setAttribute("loginId", PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification", PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		} else if (!DataValidator.isFname(request.getParameter("qualification"))) {
			request.setAttribute("qualification", PropertyReader.getValue("error.name", "Qualification"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))
				|| !(Integer.parseInt(request.getParameter("collegeId")) > 0)) {
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectId"))
				|| !(Integer.parseInt(request.getParameter("subjectId")) > 0)) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		}

		log.debug("FacultyCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		FacultyDTO dto = new FacultyDTO();
		log.debug("UserCtl Method populateDTO Started");

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
		dto.setEmailId(DataUtility.getString(request.getParameter("loginId")));
		dto.setGender(DataUtility.getString(request.getParameter("gender")));
		dto.setMobNo(DataUtility.getString(request.getParameter("mobileNo")));
		dto.setDob(DataUtility.getDate(request.getParameter("dob")));
		dto.setQualification(DataUtility.getString(request.getParameter("qualification")));
		dto.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		dto.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		
		populateDateTime(dto, request);
		log.debug("UserCtl Method populateDTO Ended");
		return dto;
	}

	@Override
	protected void preload(HttpServletRequest request) {
		try {
			List slist = ModelFactory.getInstance().getSubjectModel().list();
			List clist = ModelFactory.getInstance().getCollegeModel().list();
			request.setAttribute("subjectList", slist);
			request.setAttribute("collegeList", clist);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = DataUtility.getInt(request.getParameter("id"));
		if (id > 0) {

			FacultyDTO dto = null;
			try {
				dto = ModelFactory.getInstance().getFacultyModel().findByPK(id);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletUtility.setDto(dto, request);
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
			return;
		}if(OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}
		int id = DataUtility.getInt(request.getParameter("id"));
		FacultyDTO dto = (FacultyDTO) populateDTO(request);
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			try {
				if (id > 0) {
					model.update(dto);
				ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("Data updated successfully", request);
				} else {
					dto.setId(model.add(dto));
					ServletUtility.setSuccessMessage("Data saved successfully", request);
				}
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				ServletUtility.setErrorMessage("Faculty is already exists", request);

				e.printStackTrace();
			}
		}
ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FACULTY_VIEW;
	}

}
