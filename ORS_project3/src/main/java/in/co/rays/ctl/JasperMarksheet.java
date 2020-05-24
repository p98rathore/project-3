package in.co.rays.ctl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;

import in.co.rays.util.HibDataSource;
import in.co.rays.util.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@WebServlet(name= "JasperMarksheet" ,urlPatterns={"/ctl/JasperMarksheet"})
public class JasperMarksheet extends BaseCtl {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Connection con = null;
		JasperReport jr = null;
		JasperDesign jd = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.proj3.bundle.system");
			String database = rb.getString("DATABASE");
			if ("Hibernate".equals(database)) {
				Session session = HibDataSource.getSession();
				SessionFactoryImplementor factoryImplementor = (SessionFactoryImplementor) session.getSessionFactory();
				ConnectionProvider connectionProvider = factoryImplementor.getConnectionProvider();
				try {
					con =  connectionProvider.getConnection();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if ("JDBC".equals(database)) {
				con =JDBCDataSource.getConnection();
			}
			Map map = new HashMap();
			String path = getServletContext().getRealPath("/WEB-INF/");
			jd = JRXmlLoader.load(path+"/mark.jrxml");
			jr= JasperCompileManager.compileReport(jd);
			byte[] bytestrem = JasperRunManager.runReportToPdf(jr, map,con);
			OutputStream os = resp.getOutputStream();
			resp.setContentType("application/pdf");
			resp.setContentLength(bytestrem.length);
			os.write(bytestrem, 0, bytestrem.length);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return null;
	}


}
