package in.co.sunrays.proj3.controller;

import in.co.sunrays.proj3.dto.BaseDTO; 
 
import in.co.sunrays.proj3.dto.RoleDTO; 
import in.co.sunrays.proj3.exception.ApplicationException; 
import in.co.sunrays.proj3.exception.DuplicateRecordException; 
import in.co.sunrays.proj3.model.ModelFactory; 
import in.co.sunrays.proj3.model.RoleModelInt; 
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
 * Role functionality Controller. Performs operation for add, update and 
 * get Role 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
@WebServlet(name="/ctl/RoleCtl",urlPatterns={"/ctl/RoleCtl"})
public class RoleCtl extends BaseCtl { 
 
    private static final long serialVersionUID = 1L; 
 
    private static Logger log = Logger.getLogger(RoleCtl.class); 
 
    @Override 
    protected boolean validate(HttpServletRequest request) { 
 
        log.debug("RoleCtl Method validate Started"); 
 
        boolean pass = true; 
 
        if (DataValidator.isNull(request.getParameter("name"))) { 
            request.setAttribute("name", 
                    PropertyReader.getValue("error.require", "Name")); 
            pass = false; 
        } else if (!DataValidator.isFname(request.getParameter("name"))) { 
            request.setAttribute("name", 
                    PropertyReader.getValue("error.name", "Name")); 
            pass = false; 
        } 
 
        if (DataValidator.isNull(request.getParameter("description"))) { 
            request.setAttribute("description", 
                    PropertyReader.getValue("error.require", "Description")); 
            pass = false; 
        }else if (!DataValidator.isFname(request.getParameter("description"))) { 
            request.setAttribute("description", 
                    PropertyReader.getValue("error.name", "Description")); 
            pass = false; 
        }  
 
        log.debug("RoleCtl Method validate Ended"); 
 
        return pass; 
    } 
 
    @Override 
    protected BaseDTO populateDTO(HttpServletRequest request) { 
 
        log.debug("RoleCtl Method populateDTO Started"); 
 
        RoleDTO dto = new RoleDTO(); 
 
        dto.setId(DataUtility.getLong(request.getParameter("id"))); 
 
        dto.setName(DataUtility.getString(request.getParameter("name"))); 
 
        dto.setDescription(DataUtility.getString(request 
                .getParameter("description"))); 
 
        log.debug("RoleCtl Method populateDTO Ended");
        populateDateTime(dto, request);
 
        return dto; 
    } 
 
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
     *      response) 
     */ 
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException { 
        log.debug("RoleCtl Method doGet Started"); 
 
        System.out.println("In do get"); 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
 
        // get model 
        RoleModelInt model = ModelFactory.getInstance().getRoleModel(); 
 
        long id = DataUtility.getLong(request.getParameter("id")); 
 
        if (OP_SAVE.equalsIgnoreCase(op)) { 
 
            RoleDTO dto = (RoleDTO) populateDTO(request); 
 
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
                ServletUtility.setErrorMessage("Role already exists", request); 
            } 
 
        } else if (OP_DELETE.equalsIgnoreCase(op)) { 
 
            RoleDTO dto = (RoleDTO) populateDTO(request); 
            try { 
                model.delete(dto); 
                ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, 
                        response); 
                return; 
            } catch (ApplicationException e) { 
                log.error(e); 
                ServletUtility.handleException(e, request, response); 
                return; 
            } 
 
        } else if (OP_CANCEL.equalsIgnoreCase(op)) { 
 
            ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response); 
            return; 
 
        } else { // View page 
 
            if (id > 0 || op != null) { 
                RoleDTO dto; 
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
 
        ServletUtility.forward(ORSView.ROLE_VIEW, request, response); 
 
        log.debug("RoleCtl Method doGet Ended"); 
    } 
 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     log.debug("RoleCtl Method doPost Started"); 
     
     System.out.println("role dopost"); 

     String op = DataUtility.getString(request.getParameter("operation")); 
     System.out.println("reset="+op);
     // get model 
     RoleModelInt model = ModelFactory.getInstance().getRoleModel(); 

     long id = DataUtility.getLong(request.getParameter("id")); 
 if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) { 

         RoleDTO dto = (RoleDTO) populateDTO(request); 

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
             ServletUtility.setErrorMessage("Role already exists", request); 
         } 

     }else if(OP_RESET.equalsIgnoreCase(op)){
    		System.out.println("reset="+op);
    		ServletUtility.redirect(ORSView.ROLE_CTL, request, response);	
    			return;
    		}else if(OP_CANCEL.equalsIgnoreCase(op)){
        		
        		ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);	
        			return;
        		}	 
	 ServletUtility.forward(getView(), request, response);
     log.debug("RoleCtl Method doPost Ended");
}
    @Override 
    protected String getView() { 
        return ORSView.ROLE_VIEW; 
    } 
 
}