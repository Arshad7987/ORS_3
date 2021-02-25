package in.co.sunrays.proj3.controller;

import in.co.sunrays.proj3.dto.BaseDTO; 
import in.co.sunrays.proj3.dto.CollegeDTO; 
import in.co.sunrays.proj3.exception.ApplicationException; 
import in.co.sunrays.proj3.exception.DuplicateRecordException; 
import in.co.sunrays.proj3.model.CollegeModelInt; 
import in.co.sunrays.proj3.model.ModelFactory; 
import in.co.sunrays.proj3.util.DataUtility; 
import in.co.sunrays.proj3.util.DataValidator; 
import in.co.sunrays.proj3.util.PropertyReader; 
import in.co.sunrays.proj3.util.ServletUtility; 

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import org.apache.log4j.Logger; 

/** 
* College functionality Controller. Performs operation for add, update, 
* delete and get College 
*  
* @author SUNRAYS Technologies 
* @version 1.0 
* @Copyright (c) SUNRAYS Technologies 
*/ 
@WebServlet(urlPatterns="/ctl/CollegeCtl")
public class CollegeCtl extends BaseCtl { 

   private static final long serialVersionUID = 1L; 

   private static Logger log = Logger.getLogger(CollegeCtl.class); 

   /* (non-Javadoc)
 * @see in.co.sunrays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
 */
@Override 
   protected boolean validate(HttpServletRequest request) { 

       log.debug("CollegeCtl Method validate Started"); 

       boolean pass = true; 

       if (DataValidator.isNull(request.getParameter("name"))) { 
           request.setAttribute("name", 
                   PropertyReader.getValue("error.require", "Name")); 
           pass = false; 
       }else if (!DataValidator.isFname(request.getParameter("name"))) { 
           request.setAttribute("name", 
                   PropertyReader.getValue("error.name", "Name")); 
           pass = false; 
       } 
       if (DataValidator.isNull(request.getParameter("address"))) { 
           request.setAttribute("address", PropertyReader.getValue("error.require", "Address")); 
           pass = false; 
       } 

       if (DataValidator.isNull(request.getParameter("state"))) { 
           request.setAttribute("state", PropertyReader.getValue("error.require", "State")); 
           pass = false; 
       } else if (!DataValidator.isFname(request.getParameter("state"))) { 
           request.setAttribute("state", PropertyReader.getValue("error.name", "State")); 
           pass = false; 
       } 
       if (DataValidator.isNull(request.getParameter("city"))) { 
           request.setAttribute("city", PropertyReader.getValue("error.require", "City")); 
           pass = false; 
       } else if (!DataValidator.isFname(request.getParameter("city"))) { 
           request.setAttribute("city", PropertyReader.getValue("error.name", "City")); 
           pass = false; 
       } 
       if (DataValidator.isNull(request.getParameter("phoneNo"))) { 
           request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "Phone No")); 
           pass = false; 
       } else if (!DataValidator.isMobileNo(request.getParameter("phoneNo"))) { 
           request.setAttribute("phoneNo", PropertyReader.getValue("error.mobile", "Phone No")); 
           pass = false; 
       } 

       log.debug("CollegeCtl Method validate Ended"); 

       return pass; 
   } 

   /* (non-Javadoc)
 * @see in.co.sunrays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
 */
@Override 
   protected BaseDTO populateDTO(HttpServletRequest request) { 

       log.debug("CollegeCtl Method populateDTO Started"); 

       CollegeDTO dto = new CollegeDTO(); 

       dto.setId(DataUtility.getLong(request.getParameter("id"))); 

       dto.setName(DataUtility.getString(request.getParameter("name"))); 

       dto.setAddress(DataUtility.getString(request.getParameter("address"))); 

       dto.setState(DataUtility.getString(request.getParameter("state"))); 

       dto.setCity(DataUtility.getString(request.getParameter("city"))); 

       dto.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo"))); 

       log.debug("CollegeCtl Method populateDTO Ended"); 

       return dto; 
   } 

   /** 
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
    *      response) 
    */ 
   protected void doGet(HttpServletRequest request, 
           HttpServletResponse response) throws ServletException, IOException { 

       log.debug("CollegeCtl Method doGet Started"); 

       String op = DataUtility.getString(request.getParameter("operation")); 

       // get model 
       CollegeModelInt model = ModelFactory.getInstance().getCollegeModel(); 

       long id = DataUtility.getLong(request.getParameter("id")); 

       if (OP_SAVE.equalsIgnoreCase(op)) { 

           CollegeDTO dto = (CollegeDTO) populateDTO(request); 

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
               e.printStackTrace(); 
               log.error(e); 
               ServletUtility.handleException(e, request, response); 
               return; 
           } catch (DuplicateRecordException e) { 
               ServletUtility.setDto(dto, request); 
               ServletUtility.setErrorMessage("College Name already exists", 
                       request); 
           } 

       } else if (OP_DELETE.equalsIgnoreCase(op)) { 

           CollegeDTO dto = (CollegeDTO) populateDTO(request); 
           try { 
               model.delete(dto); 
               ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, 
                       response); 
               return; 

           } catch (ApplicationException e) { 
               log.error(e); 
               ServletUtility.handleException(e, request, response); 
               return; 
           } 

       } else if (OP_CANCEL.equalsIgnoreCase(op)) { 

           ServletUtility 
                   .redirect(ORSView.COLLEGE_LIST_CTL, request, response); 
           return; 

       } else { // View page 

           if (id > 0 || op != null) { 
               CollegeDTO dto; 
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

       ServletUtility.forward(ORSView.COLLEGE_VIEW, request, response); 

       log.debug("CollegeCtl Method doGet Ended"); 
   } 
@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    log.debug("CollegeCtl Method doPost Started"); 

    String op = DataUtility.getString(request.getParameter("operation")); 

    // get model 
    CollegeModelInt model = ModelFactory.getInstance().getCollegeModel(); 

    long id = DataUtility.getLong(request.getParameter("id")); 

    if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) { 

        CollegeDTO dto = (CollegeDTO) populateDTO(request); 

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
            e.printStackTrace(); 
            log.error(e); 
            ServletUtility.handleException(e, request, response); 
            return; 
        } catch (DuplicateRecordException e) { 
            ServletUtility.setDto(dto, request); 
            ServletUtility.setErrorMessage("College Name already exists", 
                    request); 
        } 

    } else if (OP_DELETE.equalsIgnoreCase(op)) { 

        CollegeDTO dto = (CollegeDTO) populateDTO(request); 
        try { 
            model.delete(dto); 
            ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, 
                    response); 
            return; 

        } catch (ApplicationException e) { 
            log.error(e); 
            ServletUtility.handleException(e, request, response); 
            return; 
        } 

    } else if (OP_CANCEL.equalsIgnoreCase(op)) { 

        ServletUtility 
                .redirect(ORSView.COLLEGE_LIST_CTL, request, response); 
        return; 

    } else { // View page 

        if (id > 0 || op != null) { 
            CollegeDTO dto; 
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

    ServletUtility.forward(ORSView.COLLEGE_VIEW, request, response); 

    log.debug("CollegeCtl Method doPost Ended"); 
}
   /* (non-Javadoc)
 * @see in.co.sunrays.proj3.controller.BaseCtl#getView()
 */
@Override 
   protected String getView() { 
       return ORSView.COLLEGE_VIEW; 
   } 

} 
