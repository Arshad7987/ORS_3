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
import in.co.sunrays.proj3.model.FacultyModelInt;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.UserModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;

/** 
 * Faculty List functionality Controller. Performs operation for list, search  
 * operations of Faculty 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
@WebServlet(urlPatterns="/ctl/FacultyListCtl")
public class FacultyListCtl extends BaseCtl {
	 private static Logger log = Logger.getLogger(FacultyListCtl.class);

	 @Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
	FacultyDTO dto=new FacultyDTO();
dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));
dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
dto.setEmailId(DataUtility.getString(request.getParameter("loginId")));

	return dto;
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       log.debug("FacultyListCtl doGet Start"); 
	      

	       List list = null; 

	       int pageNo = DataUtility.getInt(request.getParameter("pageNo")); 
	       int pageSize = DataUtility.getInt(request.getParameter("pageSize")); 

	       pageNo = (pageNo == 0) ? 1 : pageNo; 
	       pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader 
	               .getValue("page.size")) : pageSize; 

	       FacultyDTO dto = (FacultyDTO) populateDTO(request); 

	       String op = DataUtility.getString(request.getParameter("operation")); 

	       FacultyModelInt model = ModelFactory.getInstance().getFacultyModel(); 
	       
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

	       log.debug("Faculty List Ctl doGet End");
	}@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("Faculty List Ctl doPost Started");
		List list = null; 

	    int pageNo = DataUtility.getInt(request.getParameter("pageNo")); 
	    int pageSize = DataUtility.getInt(request.getParameter("pageSize")); 
	    
	    String op = DataUtility.getString(request.getParameter("operation"));
	    FacultyDTO dto = (FacultyDTO) populateDTO(request);
	    pageNo = (pageNo == 0) ? 1 : pageNo; 
	    pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader 
	            .getValue("page.size")) : pageSize; 

	     
	    
	    FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
	    try { 
	    	if(OP_RESET.equalsIgnoreCase(op)){
	    		ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
	    	return;
	    	}

	           if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) 
	                || "Previous".equalsIgnoreCase(op)) { 

	            if (OP_SEARCH.equalsIgnoreCase(op)) { 
	                pageNo = 1; 
	            } else if (OP_NEXT.equalsIgnoreCase(op)) { 
	                pageNo++; 
	            } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) { 
	                pageNo--; 
	            } 

	        } else if (OP_NEW.equalsIgnoreCase(op)) { 
	            ServletUtility.redirect(ORSView.FACULTY_CTL, request, response); 
	            return; 
	        } else if (OP_DELETE.equalsIgnoreCase(op)) { 
	            pageNo = 1; 
	            // get the selected checkbox ids array for delete list 
	            String[] ids = request.getParameterValues("ids"); 
	            if (ids != null && ids.length > 0) { 
	            	FacultyDTO deletedDto = new FacultyDTO(); 
	                for (String id : ids) { 
	                    deletedDto.setId(DataUtility.getLong(id)); 
	                    model.delete(deletedDto); 
	                ServletUtility.setSuccessMessage("Data deleted successfully", request);
	                } 
	            } else { 
	                ServletUtility.setErrorMessage( 
	                        "Select at least one record", request); 
	            } 
	        } 

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
			
	    log.debug("Faculty List Ctl doPost End");
	}
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FACULTY_LIST_VIEW;
	}

}
