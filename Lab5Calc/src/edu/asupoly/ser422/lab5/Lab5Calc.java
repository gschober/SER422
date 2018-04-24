package edu.asupoly.ser422.lab5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class Lab5Calc extends HttpServlet {

	// These are the possible queries
	private static String queryAll = "SELECT grade FROM Enrolled";
	private static String queryYear = "SELECT grade from Enrolled JOIN Student ON (Enrolled.sid=Student.id) WHERE year=";
	private static String querySubject = "SELECT grade from Enrolled JOIN Course ON (Enrolled.crsid=Course.id) WHERE subject='";
	private static String queryYearSubject = "SELECT grade from (Student JOIN Enrolled ON (Student.id=Enrolled.sid)) JOIN Course ON (Enrolled.crsid=Course.id) WHERE year=";

	private String __jdbcUrl    = null;
	private String __jdbcUser   = null;
	private String __jdbcPasswd = null;
	private String __jdbcDriver = null;

	public void init(ServletConfig config) throws ServletException{
		super.init(config);

		Properties props = new Properties();

		try {
			InputStream propFile = this.getClass().getClassLoader().getResourceAsStream("lab5db.properties");
			props.load(propFile);
			propFile.close();
		}catch (IOException ie) {
			ie.printStackTrace();
			//throw new Exception("Could not open property file");
		}

		__jdbcUrl    = props.getProperty("jdbc.url");
		__jdbcUser   = props.getProperty("jdbc.user");
		__jdbcPasswd = props.getProperty("jdbc.passwd");
		__jdbcDriver = props.getProperty("jdbc.driver");

		try {
			Class.forName(__jdbcDriver); // ensure the driver is loaded
		}catch (ClassNotFoundException cnfe) {
			System.out.println("*** Cannot find the JDBC driver");
			cnfe.printStackTrace();
			//throw new Exception("Cannot initialize service from property file");
		}
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject = request.getParameter("subject");
		String year = request.getParameter("year");

		double grade = calculateGrade(year,subject);
				
		JSONObject object = new JSONObject();
		object.put("grade",new Double(grade));
		
		String value = object.toJSONString();
		
		response.setContentLength(value.length());
		
		response.getOutputStream().write(value.getBytes());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	// This is what you need to implement in a subclass!
	public double calculateGrade(String year, String subject) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		double grade = 0.0;
		try {
			// Create the connection anew every time
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);

			stmt = conn.createStatement();
			
			int iyear = 0;
			String query;
			if (year != null && !year.trim().isEmpty()) {
				iyear = Integer.parseInt(year);
			}
			if (iyear <=4 && iyear >= 1 && subject != null && !subject.trim().isEmpty()) {
				query = queryYearSubject + year + " AND subject='" + subject + "'";
			} else if (iyear <=4 && iyear >= 1) {
				query = queryYear + year;
			} else if (subject != null && !subject.trim().isEmpty()) {
				query = querySubject + subject + "'";
			} else {
				query = queryAll;
			}
			System.out.println("Executing query " + query);
			
			rs = stmt.executeQuery(query);
			int count = 0;
			double gradesum = -1.0;
			while (rs.next()) {
				gradesum += rs.getDouble(1);
				count++;
			}
			if (count > 0) {
				grade = gradesum / count;
			}
		}
		catch (SQLException se) {
			System.out.println("*** Uh-oh! Database Exception");
			se.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("*** Some other exception was thrown");
			e.printStackTrace();
		}
		finally {  // why nest all of these try/finally blocks?
			try {
				if (rs != null) { rs.close(); }
			} catch (Throwable t1) {
				t1.printStackTrace();
			}
			try {
				if (stmt != null) { stmt.close(); }
			} catch (Throwable t2) {
				t2.printStackTrace();
			}
			try {
				if (conn != null) { conn.close(); }
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
		// Note that error cases will return this as well which is not good
		return grade;
	}

	
	
}