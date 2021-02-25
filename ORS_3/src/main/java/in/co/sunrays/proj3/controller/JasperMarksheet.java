package in.co.sunrays.proj3.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;

import in.co.sunrays.proj3.util.HibDataSource;
import in.co.sunrays.proj3.util.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
     /**
	 * it's use for open jasper report
	 */
@WebServlet(name="JasperMarksheet",urlPatterns={"/ctl/JasperMarksheet"})
public class JasperMarksheet extends BaseCtl{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	     /**
		 *    getting the jasper iReport in pdf form 
		 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn=null;
		JasperReport jr=null;
		JasperDesign jd=null;
		try {
			ResourceBundle rb=ResourceBundle.getBundle("bundle.system");
			String database=rb.getString("DATABASE");
            if("Hibernate".equalsIgnoreCase(database)){
				Session session = HibDataSource.getSession();
				SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
				ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
				try {
				    conn = connectionProvider.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
			if("JDBC".equalsIgnoreCase(database)){
			conn=JDBCDataSource.getConnection();
			}
			Map map = new HashMap();
		    String path=getServletContext().getRealPath("/WEB-INF/");
		    jd=JRXmlLoader.load(path+"/Marksheet.jrxml");
		    jr=JasperCompileManager.compileReport(jd);
		    
		    byte[] byteStream=JasperRunManager.runReportToPdf(jr,map,conn);
		    java.io.OutputStream  os=response.getOutputStream();
		    response.setContentType("application/pdf");
		    response.setContentLength(byteStream.length);
		    os.write(byteStream,0, byteStream.length);
		} catch (Exception e) {
			e.printStackTrace();
 		}
	}
	
	
	@Override
	protected String getView() {
		return null;
	}
}