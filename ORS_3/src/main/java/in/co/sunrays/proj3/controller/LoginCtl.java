package in.co.sunrays.proj3.controller;


import in.co.sunrays.proj3.dto.BaseDTO; 
import in.co.sunrays.proj3.dto.RoleDTO; 
import in.co.sunrays.proj3.dto.UserDTO; 
import in.co.sunrays.proj3.exception.ApplicationException; 
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.RoleModelInt;

import in.co.sunrays.proj3.model.UserModelInt; 
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
import javax.servlet.http.HttpSession; 

import org.apache.log4j.Logger; 

/** 
* Login functionality Controller. Performs operation for Login 
*  
*  
* @author SUNRAYS Technologies 
* @version 1.0 
* @Copyright (c) SUNRAYS Technologies 
*/ 
@WebServlet(name="LoginCtl",urlPatterns={"/LoginCtl"})
public class LoginCtl extends BaseCtl { 

   private static final long serialVersionUID = 1L; 
   public static final String OP_REGISTER = "Register"; 
   public static final String OP_SIGN_IN = "SignIn"; 
   public static final String OP_SIGN_UP = "SignUp"; 
   public static final String OP_LOG_OUT = "logout"; 
   private static Logger log = Logger.getLogger(LoginCtl.class); 

   @Override 
   protected boolean validate(HttpServletRequest request) { 

       log.debug("LoginCtl Method validate Started"); 

       boolean pass = true; 

       String op = request.getParameter("operation"); 
       if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) { 
           return pass; 
       } 

       String login = request.getParameter("login"); 

       if (DataValidator.isNull(login)) { 
           request.setAttribute("login", 
                   PropertyReader.getValue("error.require", "Login Id")); 
           pass = false; 
       } else if (!DataValidator.isEmail(login)) { 
           request.setAttribute("login", 
                   PropertyReader.getValue("error.email", "Login ")); 
           pass = false; 
       } 
       if (DataValidator.isNull(request.getParameter("password"))) { 
           request.setAttribute("password", 
                   PropertyReader.getValue("error.require", "Password")); 
           pass = false; 
       } 

       log.debug("LoginCtl Method validate Ended"); 

       return pass; 
   } 

   @Override 
   protected BaseDTO populateDTO(HttpServletRequest request) { 

       log.debug("LoginCtl Method populateDTO Started"); 

       UserDTO dto = new UserDTO(); 

       dto.setId(DataUtility.getLong(request.getParameter("id"))); 
       dto.setLogin(DataUtility.getString(request.getParameter("login"))); 
       dto.setPassword(DataUtility.getString(request.getParameter("password"))); 

       log.debug("LoginCtl Method populateDTO Ended"); 

       return dto; 
   } 

   /** 
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
    *      response) 
    */ 
   protected void doGet(HttpServletRequest request, 
           HttpServletResponse response) throws ServletException, IOException { 
       HttpSession session = request.getSession(true); 
       log.debug(" Method doGet Started"); 

       String op = DataUtility.getString(request.getParameter("operation")); 

       // get model 
       UserModelInt model = ModelFactory.getInstance().getUserModel(); 
       RoleModelInt role = ModelFactory.getInstance().getRoleModel(); 

       long id = DataUtility.getLong(request.getParameter("id")); 

     if (OP_LOG_OUT.equals(op)) { 

           session = request.getSession(); 

           session.invalidate(); 
     ServletUtility.setSuccessMessage("You have logged out successfully", request);
     ServletUtility.forward(ORSView.LOGIN_VIEW, request, response); 
     return;
           
       } else if (OP_SIGN_UP.equalsIgnoreCase(op)) { 

           ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, 
                   response); 
           return; 

       } 

       else { // View page 

           if (id > 0 || op != null) { 
               UserDTO userDTO; 
               try { 
                   userDTO = model.findByPK(id); 
                   ServletUtility.setDto(userDTO, request); 
               } catch (ApplicationException e) { 
                   log.error(e); 
                   ServletUtility.handleException(e, request, response); 
                   return; 
               } 
           } 
       } 
if(session.getAttribute("user")!=null){
	ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
	return;
}
       ServletUtility.forward(ORSView.LOGIN_VIEW, request, response); 

       log.debug("UserCtl Method doGet Ended"); 
   } 
@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session=request.getSession(true);
	UserDTO dto=(UserDTO)populateDTO(request);
	String uri=request.getParameter("uri");
String op=	request.getParameter("operation");
	if(OP_SIGN_IN.equalsIgnoreCase(op)){
	//UserModelHibImpl usermodel=new UserModelHibImpl();
	UserModelInt usermodel = ModelFactory.getInstance().getUserModel(); 
	RoleModelInt rolemodel=ModelFactory.getInstance().getRoleModel();
	try {
		UserDTO dto2=usermodel.authenticate(dto.getLogin(), dto.getPassword());
	if(dto2!=null){
		session.setAttribute("user", dto2);
		
	session.setAttribute("role", rolemodel.findByPK(dto2.getRoleId()).getName());
	if(uri!=null){
		ServletUtility.redirect(uri, request, response);
		return;
	}
	ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
	}else{
		
		ServletUtility.setDto(dto, request);
		ServletUtility.setErrorMessage("Login Id or Password is wrong", request);
		ServletUtility.forward(getView(), request, response);
	}
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}else if(OP_RESET.equalsIgnoreCase(op)){
		
		ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);	
			return;
		}
	

}
   @Override 
   protected String getView() { 
       return ORSView.LOGIN_VIEW; 
   } 

}