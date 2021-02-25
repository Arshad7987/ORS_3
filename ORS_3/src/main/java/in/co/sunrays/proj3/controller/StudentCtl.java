package in.co.sunrays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.sunrays.proj3.util.ServletUtility;

import in.co.sunrays.proj3.dto.BaseDTO; 
import in.co.sunrays.proj3.dto.StudentDTO; 
import in.co.sunrays.proj3.exception.ApplicationException; 
import in.co.sunrays.proj3.exception.DuplicateRecordException; 
import in.co.sunrays.proj3.model.CollegeModelInt; 
import in.co.sunrays.proj3.model.ModelFactory; 
import in.co.sunrays.proj3.model.StudentModelInt; 
import in.co.sunrays.proj3.util.DataUtility; 
import in.co.sunrays.proj3.util.DataValidator; 
import in.co.sunrays.proj3.util.PropertyReader; 
 
import java.util.List; 
 

import org.apache.log4j.Logger; 
 
/** 
 * Student functionality Controller. Performs operation for add, update, 
 * delete and get Student 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
@WebServlet(name="StudentCtl",urlPatterns="/ctl/StudentCtl") 
public class StudentCtl extends BaseCtl { 
 
    private static Logger log = Logger.getLogger(StudentCtl.class); 
 
    @Override 
    protected void preload(HttpServletRequest request) { 
        CollegeModelInt model = ModelFactory.getInstance().getCollegeModel(); 
        try { 
            List l = model.list(); 
            request.setAttribute("collegeList", l); 
        } catch (ApplicationException e) { 
            log.error(e); 
        } 
 
    } 
 
    @Override 
    protected boolean validate(HttpServletRequest request) { 
 
        log.debug("StudentCtl Method validate Started"); 
 
        boolean pass = true; 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
        String email = request.getParameter("email"); 
        String dob = request.getParameter("dob"); 
 
        if (DataValidator.isNull(request.getParameter("firstName"))) { 
            request.setAttribute("firstName", 
                    PropertyReader.getValue("error.require", "First Name")); 
            pass = false; 
        } 
        if (DataValidator.isNull(request.getParameter("lastName"))) { 
            request.setAttribute("lastName", 
                    PropertyReader.getValue("error.require", "Last Name")); 
            pass = false; 
        } 
        if (DataValidator.isNull(dob)) { 
            request.setAttribute("dob", 
                    PropertyReader.getValue("error.require", "Date Of Birth")); 
            pass = false; 
        } else if (!DataValidator.isDate(dob)) { 
            request.setAttribute("dob", 
                    PropertyReader.getValue("error.date", "Date Of Birth")); 
            pass = false; 
        } 
        if (DataValidator.isNull(request.getParameter("mobileNo"))) { 
            request.setAttribute("mobileNo", 
                    PropertyReader.getValue("error.require", "Mobile No")); 
            pass = false; 
        } 
        if (DataValidator.isNull(email)) { 
            request.setAttribute("email", 
                    PropertyReader.getValue("error.require", "Email ")); 
            pass = false; 
        } else if (!DataValidator.isEmail(email)) { 
            request.setAttribute("email", 
                    PropertyReader.getValue("error.email", "Email ")); 
            pass = false; 
        } 
        if (DataValidator.isNull(request.getParameter("collegeId"))) { 
            request.setAttribute("collegeId", 
                    PropertyReader.getValue("error.require", "College Name")); 
            pass = false; 
        } 
 
        log.debug("StudentCtl Method validate Ended"); 
 
        return pass; 
    } 
 
    @Override 
    protected BaseDTO populateDTO(HttpServletRequest request) { 
 
        log.debug("StudentCtl Method populateDTO Started"); 
 
        StudentDTO dto = new StudentDTO(); 
 
        dto.setId(DataUtility.getLong(request.getParameter("id"))); 
 
        dto.setFirstName(DataUtility.getString(request 
                .getParameter("firstName"))); 
 
        dto.setLastName(DataUtility.getString(request.getParameter("lastName"))); 
 
        dto.setDob(DataUtility.getDate(request.getParameter("dob"))); 
 
        dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo"))); 
 
        dto.setEmail(DataUtility.getString(request.getParameter("email"))); 
 
        dto.setCollegeId(DataUtility.getLong(request.getParameter("collegeId"))); 
 
        log.debug("StudentCtl Method populateDTO Ended"); 
 
        return dto; 
    } 
 
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
     *      response) 
     */ 
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException { 
 
        log.debug("StudentCtl Method doGet Started"); 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
 
        // get model 
 
        StudentModelInt model = ModelFactory.getInstance().getStudentModel(); 
 
        long id = DataUtility.getLong(request.getParameter("id")); 
 
        if (OP_SAVE.equalsIgnoreCase(op)) { 
 
            StudentDTO dto = (StudentDTO) populateDTO(request); 
 
            try { 
                if (id > 0) { 
                    model.update(dto); 
                } else { 
                    long pk = model.add(dto); 
                    dto.setId(pk); 
                } 
 
                ServletUtility.setDto(dto, request); 
                ServletUtility.setSuccessMessage("Data is successfully saved", 
                        request); 
 
            } catch (ApplicationException e) { 
                log.error(e); 
                ServletUtility.handleException(e, request, response); 
                return; 
            } catch (DuplicateRecordException e) { 
                ServletUtility.setDto(dto, request); 
                ServletUtility.setErrorMessage( 
                        "Student Email Id already exists", request); 
            } 
 
        } 
 
        else if (OP_DELETE.equalsIgnoreCase(op)) { 
 
            StudentDTO dto = (StudentDTO) populateDTO(request); 
            try { 
                model.delete(dto); 
                ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, 
                        response); 
                return; 
 
            } catch (ApplicationException e) { 
                log.error(e); 
                ServletUtility.handleException(e, request, response); 
                return; 
            } 
 
        } else if (OP_CANCEL.equalsIgnoreCase(op)) { 
 
            ServletUtility 
                    .redirect(ORSView.STUDENT_LIST_CTL, request, response); 
            return; 
 
        } else { // View page 
 
            if (id > 0 || op != null) { 
                StudentDTO dto; 
                try { 
                    dto = model.findByPK(id); 
                    ServletUtility.setDto(dto, request); 
                } catch (ApplicationException e) { 
                    log.error(e); 
                    ServletUtility.handleException(e, request, response); 
                    return; 
                } 
            } 
        } 
 
        ServletUtility.forward(ORSView.STUDENT_VIEW, request, response); 
 
        log.debug("StudentCtl Method doGet Ended"); 
    } 
 
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    log.debug("StudentCtl Method doPost Started"); 
    
    String op = DataUtility.getString(request.getParameter("operation")); 

    // get model 

    StudentModelInt model = ModelFactory.getInstance().getStudentModel(); 

    long id = DataUtility.getLong(request.getParameter("id")); 

    if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) { 

        StudentDTO dto = (StudentDTO) populateDTO(request); 

        try { 
            if (id > 0) { 
                model.update(dto); 
                ServletUtility.setDto(dto, request); 
                ServletUtility.setSuccessMessage("Data updated successfully ", 
                        request);
            } else { 
                long pk = model.add(dto); 
                dto.setId(pk); 
               // ServletUtility.setDto(dto, request); 
                ServletUtility.setSuccessMessage("Data saved successfully ", 
                        request);
            } 

             

        } catch (ApplicationException e) { 
            log.error(e); 
            ServletUtility.handleException(e, request, response); 
            return; 
        } catch (DuplicateRecordException e) { 
            ServletUtility.setDto(dto, request); 
            ServletUtility.setErrorMessage( 
                    "Student Email Id already exists", request); 
        } 

    } 

    else if (OP_DELETE.equalsIgnoreCase(op)) { 

        StudentDTO dto = (StudentDTO) populateDTO(request); 
        try { 
            model.delete(dto); 
            ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, 
                    response); 
            return; 

        } catch (ApplicationException e) { 
            log.error(e); 
            ServletUtility.handleException(e, request, response); 
            return; 
        } 

    } else if (OP_CANCEL.equalsIgnoreCase(op)) { 

        ServletUtility 
                .redirect(ORSView.STUDENT_LIST_CTL, request, response); 
        return; 

    } else { // View page 

        if (id > 0 || op != null) { 
            StudentDTO dto; 
            try { 
                dto = model.findByPK(id); 
                ServletUtility.setDto(dto, request); 
            } catch (ApplicationException e) { 
                log.error(e); 
                ServletUtility.handleException(e, request, response); 
                return; 
            } 
        } 
    } 

    ServletUtility.forward(ORSView.STUDENT_VIEW, request, response); 

    log.debug("StudentCtl Method doPost Ended"); 

}
    @Override 
    protected String getView() { 
        return ORSView.STUDENT_VIEW; 
    } 
 
} 

