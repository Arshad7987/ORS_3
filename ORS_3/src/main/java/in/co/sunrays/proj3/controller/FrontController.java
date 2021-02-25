package in.co.sunrays.proj3.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.sunrays.proj3.util.ServletUtility;

/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller. It prevents any user to access
 * application without login.
 * 
 * @author Adapterpattern
 * @version 1.0 Copyright (c) Adapterpattern
 */
@WebFilter(filterName = "FrontCtl", urlPatterns = { "/ctl/*", "/doc/*" })
public class FrontController implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession(true);
		String ro=(String) session.getAttribute("role");
	

		if (session.getAttribute("userd") == null || session.getAttribute("role") == null)

		{
			request.setAttribute("error", "Your session has been expired. Please re-Login");

			String str = request.getRequestURI();
			//session=request.getSession();
			
			request.setAttribute("ro", ro);
		/*	System.out.println(ro);*/
			
			request.setAttribute("uri", str);
			//System.out.println("---------------------------mm0-----------"+str);
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
			return;
			
		} else {
			// System.out.println("No ");
			chain.doFilter(req, res);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
