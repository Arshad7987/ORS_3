package in.co.sunrays.proj3.controller;

import in.co.sunrays.proj3.dto.BaseDTO; 
import in.co.sunrays.proj3.dto.RoleDTO; 
import in.co.sunrays.proj3.dto.UserDTO; 
import in.co.sunrays.proj3.exception.ApplicationException; 
import in.co.sunrays.proj3.exception.DuplicateRecordException; 
import in.co.sunrays.proj3.model.ModelFactory;
import in.co.sunrays.proj3.model.UserModelHibImpl;
import in.co.sunrays.proj3.model.UserModelInt; 
import in.co.sunrays.proj3.util.DataUtility; 
import in.co.sunrays.proj3.util.DataValidator; 
import in.co.sunrays.proj3.util.PropertyReader; 
import in.co.sunrays.proj3.util.ServletUtility; 
 
import java.io.IOException; 
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
import org.apache.log4j.Logger; 
 
/** 
 * User registration functionality Controller. Performs operation for User 
 * Registration 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
@WebServlet(name="UserRegistrationCtl",urlPatterns={"/UserRegistrationCtl"})
public class UserRegistrationCtl extends BaseCtl { 
 
    public static final String OP_SIGN_UP = "SignUp"; 
 
    private static Logger log = Logger.getLogger(UserRegistrationCtl.class); 
 
    @Override 
    protected boolean validate(HttpServletRequest request) { 
 
        log.debug("UserRegistrationCtl Method validate Started"); 
 
        boolean pass = true; 
 
        String login = request.getParameter("login"); 
        String dob = request.getParameter("dob"); 
 
        if (DataValidator.isNull(request.getParameter("firstName"))) { 
            request.setAttribute("firstName", 
                    PropertyReader.getValue("error.require", "First Name")); 
            pass = false; 
        }else if (!DataValidator.isFname(request.getParameter("firstName"))) { 
            request.setAttribute("firstName", 
                    PropertyReader.getValue("error.name", "First Name")); 
            pass = false; 
        }  
        if (DataValidator.isNull(request.getParameter("lastName"))) { 
            request.setAttribute("lastName", 
                    PropertyReader.getValue("error.require", "Last Name")); 
            pass = false; 
        }else if (!DataValidator.isLname(request.getParameter("lastName"))) { 
            request.setAttribute("lastName", 
                    PropertyReader.getValue("error.name", "Last Name")); 
            pass = false; 
        } 
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
        }else if (!DataValidator.isPassword(request.getParameter("password"))) { 
            request.setAttribute("password", 
                    PropertyReader.getValue("error.password", "Password")); 
            pass = false; 
        } 
        if (DataValidator.isNull(request.getParameter("confirmPassword"))) { 
            request.setAttribute("confirmPassword", PropertyReader.getValue( 
                    "error.require", "Confirm Password")); 
            pass = false; 
        } 
        if (DataValidator.isNull(request.getParameter("gender"))) { 
            request.setAttribute("gender", 
                    PropertyReader.getValue("error.require", "Gender")); 
            pass = false; 
        }if (DataValidator.isNull(request.getParameter("mobileNo"))) { 
            request.setAttribute("mobileNo", 
                    PropertyReader.getValue("error.require", "Mobile Number")); 
            pass = false; 
        } else if (!DataValidator.isMobileNo(request.getParameter("mobileNo"))) { 
            request.setAttribute("mobileNo", 
                    PropertyReader.getValue("error.mobile", "Mobile Number")); 
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
        if (!request.getParameter("password").equals( 
                request.getParameter("confirmPassword")) 
                && !"".equals(request.getParameter("confirmPassword"))) { 
            ServletUtility.setErrorMessage( 
                    "Confirm  Password  should not be matched.", request); 
 
            pass = false; 
        } 
 
        log.debug("UserRegistrationCtl Method validate Ended"); 
 
        return pass; 
    } 
 
    @Override 
    protected BaseDTO populateDTO(HttpServletRequest request) { 
 
        log.debug("UserRegistrationCtl Method populateDTO Started"); 
 
        UserDTO dto = new UserDTO(); 
 
        dto.setId(DataUtility.getLong(request.getParameter("id"))); 
 
        dto.setRoleId(RoleDTO.STUDENT); 
 
        dto.setFirstName(DataUtility.getString(request 
                .getParameter("firstName"))); 
 
        dto.setLastName(DataUtility.getString(request.getParameter("lastName"))); 
 
        dto.setLogin(DataUtility.getString(request.getParameter("login"))); 
 
        dto.setPassword(DataUtility.getString(request.getParameter("password"))); 
 
        dto.setConfirmPassword(DataUtility.getString(request 
                .getParameter("confirmPassword"))); 
 
        dto.setGender(DataUtility.getString(request.getParameter("gender"))); 
        dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
        dto.setDob(DataUtility.getDate(request.getParameter("dob"))); 
 
        log.debug("UserRegistrationCtl Method populateDTO Ended"); 
 populateDateTime(dto, request);
        return dto; 
    } 
 
    @Override 
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException { 
 
        log.debug("UserRegistrationCtl Method doGet Started"); 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
 System.out.println("ok");
        // get model 
        UserModelInt model = ModelFactory.getInstance().getUserModel(); 
 
        if (OP_SIGN_UP.equalsIgnoreCase(op)) { 
 
            UserDTO dto = (UserDTO) populateDTO(request); 
 
            try { 
                long pk = model.registerUser(dto); 
                dto.setId(pk); 
                request.getSession().setAttribute("userDto", dto); 
                ServletUtility.redirect(ORSView.LOGIN_CTL, request, response); 
                return; 
            } catch (ApplicationException e) { 
                log.error(e); 
                ServletUtility.handleException(e, request, response); 
                return; 
            } catch (DuplicateRecordException e) { 
                log.error(e); 
                ServletUtility.setDto(dto, request); 
                ServletUtility.setErrorMessage("Login id already exists", 
                        request); 
                ServletUtility.forward(ORSView.USER_REGISTRATION_VIEW, request, 
                        response); 
            } 
 
        } else { 
            ServletUtility.forward(ORSView.USER_REGISTRATION_VIEW, request, 
                    response); 
        } 
 
        log.debug("UserRegistrationCtl Method doGet Ended"); 
    } 
 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String op=request.getParameter("operation");
System.out.println("registration dopost ");
UserDTO dto=(UserDTO)populateDTO(request);
if(OP_SIGN_UP.equalsIgnoreCase(op)){
	
	UserModelHibImpl model=new UserModelHibImpl();
	//System.out.println("pk="+model.add(bean));
	try {
	long pk=model.registerUser(dto);
	dto.setId(pk);
	ServletUtility.setDto(dto, request);
	ServletUtility.setSuccessMessage("Registration Successful", request);
		ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);
	} catch (ApplicationException e) {
		
		System.out.println("Application Exception aa gyi="+e);
	} catch (DuplicateRecordException e) {
		ServletUtility.setDto(dto, request);
		ServletUtility.setErrorMessage("User is allready Exist", request);
		
		e.printStackTrace();
	}
	
}
 ServletUtility.forward(getView(), request, response);
 }
    @Override 
    protected String getView() { 
        return ORSView.USER_REGISTRATION_VIEW; 
    } 
 
}
