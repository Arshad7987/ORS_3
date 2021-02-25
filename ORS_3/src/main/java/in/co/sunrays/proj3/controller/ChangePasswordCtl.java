package in.co.sunrays.proj3.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.UserModelInt;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
/** 
* Change Password functionality Controller. Performs operation for change password
*  
* @author SUNRAYS Technologies 
* @version 1.0 
* @Copyright (c) SUNRAYS Technologies 
*/ 
@WebServlet(urlPatterns="/ctl/ChangePasswordCtl")
public class ChangePasswordCtl extends BaseCtl {

	private static Logger log=Logger.getLogger(ChangePasswordCtl.class);
	
	 public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
	 /* (non-Javadoc)
	 * @see in.co.sunrays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	    protected boolean validate(HttpServletRequest request) {
	 
	        log.debug("ChangePasswordCtl Method validate Started");
	 
	        boolean pass = true;
	 
	        String op = request.getParameter("operation");
	 
	        if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
	 
	            return pass;
	        }
	        if (DataValidator.isNull(request.getParameter("oldPassword"))) {
	            request.setAttribute("oldPassword",
	                    PropertyReader.getValue("error.require", "Old Password"));
	            pass = false;
	        }else if(!DataValidator.isPassword(request.getParameter("oldPassword"))){
	        	request.setAttribute("oldPassword",
	                    PropertyReader.getValue("error.password", "Old Password"));
	            pass = false;
	        	
	        }
	        if (DataValidator.isNull(request.getParameter("newPassword"))) {
	            request.setAttribute("newPassword",
	                    PropertyReader.getValue("error.require", "New Password"));
	            pass = false;
	        }else if(!DataValidator.isPassword(request.getParameter("newPassword"))&&(request.getParameter("newPassword").length()>=6)){
	        	request.setAttribute("newPassword",
	                    PropertyReader.getValue("error.password", "New Password"));
	            pass = false;
	        	
	        }
	        if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
	            request.setAttribute("confirmPassword", PropertyReader.getValue(
	                    "error.require", "Confirm Password"));
	            pass = false;
	        }
	        if (!request.getParameter("newPassword").equals(
	                request.getParameter("confirmPassword"))
	                && !"".equals(request.getParameter("confirmPassword"))) {
	            ServletUtility.setErrorMessage(
	                    "New and Confirm passwords are not matched", request);
	 
	            pass = false;
	        }
	        
	        log.debug("ChangePasswordCtl Method validate Ended");
	 
	        return pass;
	    }
	 /* (non-Javadoc)
	 * @see in.co.sunrays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	protected BaseDTO populateDTO(HttpServletRequest request) {
	        log.debug("ChangePasswordCtl Method populateDTO Started");
	 
	        UserDTO dto = new UserDTO();
	 
	        dto.setPassword(DataUtility.getString(request
	                .getParameter("oldPassword")));
	 
	        dto.setConfirmPassword(DataUtility.getString(request
	                .getParameter("confirmPassword")));
	 
	        log.debug("ChangePasswordCtl Method populateDTO Ended");
	 populateDateTime(dto, request);
	        return dto;
	    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("Change Password ctl doGet start ");
	
	
		
		ServletUtility.forward(getView(), request, response);
		log.debug("Change Password ctl doGet start ");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("Change Password ctl doPost start ");
		
		 HttpSession session = request.getSession(true);
		 
	        log.debug("ChangePasswordCtl Method doGet Started");
	 
	        String op = DataUtility.getString(request.getParameter("operation"));
	 
	        // get model
	        UserModelInt model = ModelFactory.getInstance().getUserModel();
	        
	        UserDTO dto = (UserDTO) populateDTO(request);
	 
	        UserDTO userdto = (UserDTO) session.getAttribute("user");
	 
	        String newPassword = (String) request.getParameter("newPassword");
	 
	        long id = userdto.getId();
	 
	        if (OP_SAVE.equalsIgnoreCase(op)) {
	 
	                try {
	                boolean flag = model.changePassword(id, dto.getPassword(),
	                            newPassword);
	                    if (flag == true) {
	                    dto = model.findByLogin(userdto.getLogin());
	                    session.setAttribute("user", dto);
	                        ServletUtility.setDto(dto, request);
	                        ServletUtility.setSuccessMessage(
	                            "Password has been changed Successfully", request);
	                    }
	                } catch (ApplicationException e) {
	                    log.error(e);
	                    ServletUtility.handleException(e, request, response);
	                    return;
	 
	                } catch (RecordNotFoundException e) {
	                ServletUtility.setErrorMessage("Old Password is wrong",
	                            request);
	                }
	 
	        } else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
	            ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
	            return;
	 
	        }
	 
	        ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
		
		log.debug("Change Password ctl doPost start ");
	
	}
	
	/* (non-Javadoc)
	 * @see in.co.sunrays.proj3.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
