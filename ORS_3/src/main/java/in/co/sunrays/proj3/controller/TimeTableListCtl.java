package in.co.sunrays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.dto.TimeTableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.SubjectModelInt;
import in.co.sunrays.proj3.model.TimeTableModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
/**
 * Time Table List functionality Controller. Performs operation for list, search
 * and delete operations of Time Table
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

@WebServlet(urlPatterns="/ctl/TimeTableListCtl")
public class TimeTableListCtl extends BaseCtl {
	private static Logger log=Logger.getLogger(TimeTableListCtl.class);
@Override
protected void preload(HttpServletRequest request) {
try {
	List clist=ModelFactory.getInstance().getCourseModel().list();
	List slist=ModelFactory.getInstance().getSubjectModel().list();
	request.setAttribute("courseList", clist);
	request.setAttribute("subjectList", slist);
} catch (ApplicationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
	TimeTableDTO dto=new TimeTableDTO();
	dto.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
	dto.setSemester(DataUtility.getString(request.getParameter("semester")));
	dto.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
	
	return dto;
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("TimeTableListCtl doGet Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		TimeTableDTO dto = (TimeTableDTO) populateDTO(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		TimeTableModelInt model = ModelFactory.getInstance().getTimeTableModel();

		try {
			list = model.search(dto, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("TimeTable list ctl doGet End");

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("time table list ctl doPost atarted");
		String op = DataUtility.getString(request.getParameter("operation"));
		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		}
		if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}if(OP_BACK.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		}

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		TimeTableDTO dto = (TimeTableDTO) populateDTO(request);
		TimeTableModelInt model = ModelFactory.getInstance().getTimeTableModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				// get the selected checkbox ids array for delete list
				String[] ids = request.getParameterValues("ids");
				if (ids != null && ids.length > 0) {
					TimeTableDTO deletedDto = new TimeTableDTO();
					for (String id : ids) {
						deletedDto.setId(DataUtility.getLong(id));
						model.delete(deletedDto);
						ServletUtility.setSuccessMessage("Data deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}

			list = model.search(dto, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setDto(dto, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);

			ServletUtility.handleException(e, request, response);
			return;
		}

		log.debug("Time Table ListCtl doPost End");

	}
	@Override
	protected String getView() {
		
		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
