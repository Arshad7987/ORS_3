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
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.TimeTableModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
/** 
 * Time Table registration functionality Controller. Performs operation for Time Table add  
 * update and edit
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
@WebServlet(urlPatterns="/ctl/TimeTableCtl")
public class TimeTableCtl extends BaseCtl{
private static Logger log=Logger.getLogger(TimeTableCtl.class);
@Override
	protected void preload(HttpServletRequest request) {
	log.debug("time table ctl preload started");
	try {
		List clist=ModelFactory.getInstance().getCourseModel().list();
	    List slist=ModelFactory.getInstance().getSubjectModel().list();
	    request.setAttribute("courseList", clist);
	    request.setAttribute("subjectList", slist);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	log.debug("timetable preload ended");
	}
@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
	log.debug("time table populate started");
	TimeTableDTO dto=new TimeTableDTO();
	dto.setId(DataUtility.getLong(request.getParameter("id")));
	dto.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
	dto.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
	dto.setSemester(DataUtility.getString(request.getParameter("semester")));
	dto.setTime(DataUtility.getString(request.getParameter("examTime")));
	dto.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
	populateDateTime(dto, request);
	log.debug("time table populate ended");	
	return dto;
	}
@Override
	protected boolean validate(HttpServletRequest request) {
	log.debug("time table validate started");
	boolean pass=true;
	if(DataValidator.isNull(request.getParameter("courseId"))||!(DataUtility.getInt(request.getParameter("courseId"))>0)
			||request.getParameter("courseId").trim().equals("")){
		request.setAttribute("courseId", PropertyReader.getValue("error.require","Course name"));
	pass=false;
	}if(DataValidator.isNull(request.getParameter("subjectId"))||!(DataUtility.getInt(request.getParameter("subjectId"))>0)
			||request.getParameter("subjectId").trim().equals("")){
		request.setAttribute("subjectId", PropertyReader.getValue("error.require","Subject name"));
	pass=false;
	}if(DataValidator.isNull(request.getParameter("examTime"))||request.getParameter("examTime").trim().equals("")){
		request.setAttribute("examTime", PropertyReader.getValue("error.require","Exam time"));
pass=false;
	}if(DataValidator.isNull(request.getParameter("examDate"))){
		request.setAttribute("examDate", PropertyReader.getValue("error.require","Exam date"));
pass=false;
	}else if(!DataValidator.isDate(request.getParameter("examDate"))){
		request.setAttribute("examDate", PropertyReader.getValue("error.date","Exam date"));
		pass=false;
	}
	if(DataValidator.isNull(request.getParameter("semester"))||request.getParameter("semester").trim().equals("")){
		request.setAttribute("semester", PropertyReader.getValue("error.require","Semester"));
pass=false;
	}
	log.debug("time table validate ended");
		return pass;
	}
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
log.debug("TimeTable ctl doGet started");
	TimeTableDTO dto=(TimeTableDTO)populateDTO(request);

	try {
		if(dto.getId()>0){
		dto=ModelFactory.getInstance().getTimeTableModel().findByPK(dto.getId());
	    ServletUtility.setDto(dto, request);  
		}
		} catch (ApplicationException e) {
         log.error(e);
		e.printStackTrace();
	}
ServletUtility.forward(getView(), request, response);
log.debug("TimeTable ctl doGet Ended");
}
@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	log.debug("time table doPost started");
	String op=DataUtility.getString(request.getParameter("operation"));
if(OP_RESET.equalsIgnoreCase(op)){
	ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
	return;
}if(OP_CANCEL.equalsIgnoreCase(op)){
	ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
	return;
}

TimeTableDTO dto=(TimeTableDTO)populateDTO(request);
TimeTableModelInt model=ModelFactory.getInstance().getTimeTableModel();	
try {
		if(OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)){
			if(dto.getId()>0){
				model.update(dto);
				ServletUtility.setDto(dto, request);
		ServletUtility.setSuccessMessage("Data updated successfully", request);
			}else{
			dto.setId(model.add(dto));
			
			ServletUtility.setSuccessMessage("Data saved successfully", request);
			}
			
		}
	} catch (ApplicationException e) {
		log.error(e);
		ServletUtility.handleException(e, request, response);
	}catch(DuplicateRecordException e){
		log.error(e);
		ServletUtility.setDto(dto, request);
		ServletUtility.setErrorMessage("Time table already exists", request);
	} catch (RecordNotFoundException e) {
	log.error(e);
		ServletUtility.setDto(dto, request);
		ServletUtility.setErrorMessage("This Subject is not Available For This Course", request);
		e.printStackTrace();
	}
ServletUtility.forward(getView(), request, response);
log.debug("time table doPost ended");
}
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_VIEW;
	}

}
