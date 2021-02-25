package in.co.sunrays.proj3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.BaseDTO;
import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.util.DataUtility;
import in.co.sunrays.proj3.util.DataValidator;
import in.co.sunrays.proj3.util.PropertyReader;
import in.co.sunrays.proj3.util.ServletUtility;
/** 
 * Forget Password  functionality Controller. Performs operation for send password on user email id 
 * of user 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
@WebServlet(urlPatterns="/ForgetPasswordCtl")
public class ForgetPasswordCtl extends BaseCtl {
	private static Logger log=Logger.getLogger(ForgetPasswordCtl.class);
	
@Override
protected boolean validate(HttpServletRequest request) {
boolean pass=true;
if(DataValidator.isNull(request.getParameter("emailId"))){
	request.setAttribute("emailId", PropertyReader.getValue("error.require","Email id"));
	pass=false;
}else if(!DataValidator.isEmail(request.getParameter("emailId"))){
	request.setAttribute("emailId", PropertyReader.getValue("error.email","Email id"));
pass=false;
}
	return pass;
}	
@Override
protected BaseDTO populateDTO(HttpServletRequest request) {
UserDTO dto=new UserDTO();
dto.setLogin(DataUtility.getString(request.getParameter("emailId")));
	return dto;
}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	log.debug("forget password ctl doGet start");
	
	
	ServletUtility.forward(getView(), request, response);
	log.debug("forget password ctl doGet ended");
	}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
log.debug("fogetPassword doPost Start");
String op=DataUtility.getString(request.getParameter("operation"));
if(OP_RESET.equalsIgnoreCase(op)){
	ServletUtility.redirect(ORSView.FORGET_PASSWORD_CTL, request, response);
	return;
}

if(OP_SEND.equalsIgnoreCase(op)){
UserDTO dto=(UserDTO)populateDTO(request);
try {
	ModelFactory.getInstance().getUserModel().forgetPassword(dto.getLogin());
	ServletUtility.setSuccessMessage("Your password has been sent on your email address", request);
	ServletUtility.setDto(dto, request);
} catch (ApplicationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (RecordNotFoundException e) {
	ServletUtility.setErrorMessage("Email id is not exists", request);
	ServletUtility.setDto(dto, request);
	e.printStackTrace();
}
}
ServletUtility.forward(getView(), request, response);

log.debug("fogetPassword doPost ended");
}
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FORGET_PASSWORD_VIEW;
	}

}
