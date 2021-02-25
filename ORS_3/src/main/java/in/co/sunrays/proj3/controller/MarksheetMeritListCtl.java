package in.co.sunrays.proj3.controller;


import in.co.sunrays.proj3.dto.BaseDTO; 
import in.co.sunrays.proj3.dto.MarksheetDTO; 
import in.co.sunrays.proj3.exception.ApplicationException; 
import in.co.sunrays.proj3.model.MarksheetModelInt; 
import in.co.sunrays.proj3.model.ModelFactory; 
import in.co.sunrays.proj3.util.DataUtility; 
import in.co.sunrays.proj3.util.PropertyReader; 
import in.co.sunrays.proj3.util.ServletUtility; 
 
import java.io.IOException; 
import java.util.List; 
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
import org.apache.log4j.Logger; 
 
/** 
 * Marksheet Merit List functionality Controller. Performs operation for 
 * Marksheet Merit List 
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 */
@WebServlet(urlPatterns="/ctl/MarksheetMeritListCtl")
public class MarksheetMeritListCtl extends BaseCtl { 
 
    public static final String OP_BACK = "Back"; 
 
    private static Logger log = Logger.getLogger(MarksheetMeritListCtl.class); 
 
    @Override 
    protected BaseDTO populateDTO(HttpServletRequest request) { 
        MarksheetDTO dto = new MarksheetDTO(); 
 
        return dto; 
    } 
 
    @Override 
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException { 
 
        log.debug("MarksheetMeritListCtl doGet Start"); 
 
        List list = null; 
 
        int pageNo = DataUtility.getInt(request.getParameter("pageNo")); 
        int pageSize = DataUtility.getInt(request.getParameter("pageSize")); 
 
        pageNo = (pageNo == 0) ? 1 : pageNo; 
 
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader 
                .getValue("page.size")) : pageSize; 
 
        MarksheetDTO dto = (MarksheetDTO) populateDTO(request); 
 
        String op = DataUtility.getString(request.getParameter("operation")); 
 
        MarksheetModelInt model = ModelFactory.getInstance() 
                .getMarksheetModel(); 
 
        try { 
 
            if (OP_BACK.equalsIgnoreCase(op)) { 
                ServletUtility.redirect(ORSView.WELCOME_CTL, request, response); 
                return; 
            } 
 
            list = model.getMeritList(pageNo, pageSize); 
            ServletUtility.setList(list, request); 
            if (list == null || list.size() == 0) { 
                ServletUtility.setErrorMessage("No record found ", request); 
            } 
            ServletUtility.setList(list, request); 
 
            ServletUtility.setPageNo(pageNo, request); 
            ServletUtility.setPageSize(pageSize, request); 
            ServletUtility.forward(ORSView.MARKSHEET_MERIT_LIST_VIEW, request, 
                    response); 
 
        } catch (ApplicationException e) { 
            log.error(e); 
            ServletUtility.handleException(e, request, response); 
            return; 
        } 
 
        log.debug("MarksheetMeritListCtl doGet End"); 
    } 
 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 log.debug("MarksheetMeritListCtl doPost Start");
	 String op = DataUtility.getString(request.getParameter("operation"));
	 if(OP_BACK.equalsIgnoreCase(op)){
		 
		 ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
	 return;
	 }
	 log.debug("MarksheetMeritListCtl doPost End");
}
    @Override 
    protected String getView() { 
        return ORSView.MARKSHEET_MERIT_LIST_VIEW; 
    } 
 
} 

