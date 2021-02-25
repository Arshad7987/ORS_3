package in.co.sunrays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.sunrays.proj3.util.ServletUtility;
/** 
 * Welcome  functionality Controller. Performs operation for 
 * Application Home Page
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */ 
@WebServlet(urlPatterns={"/WelcomeCtl"})
public class WelcomeCtl extends BaseCtl {
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("WelcomeCtl");
		ServletUtility.forward(getView(), request, response);
		
		
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.WELCOME_VIEW;
	}

}
