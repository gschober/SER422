package ser422.lab2.gschober;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaskManager extends HttpServlet {
	

	public void init(ServletConfig config) throws ServletException{
		super.init(config);	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		StringBuilder sb = new StringBuilder("");
		String contentType = "text/plain";
		
		//Process Headers
		String acceptsHeader = req.getHeader("Accept");
		String[] acceptsFormats = acceptsHeader.split(",");
		
		for(String s : acceptsFormats){
			if(s.equals("text/html")){
				sb.append("<HTML><TITLE>Task Count</TITLE></Head>\n<body>\n<b>\n");
				contentType = "text/html";
				break;
			}
		}
		
		
		String name = req.getParameter("firstName");
		String description = req.getParameter("lastName");
		String completion = req.getParameter("completion");
		String workday = req.getParameter("workday");
		String custom = req.getParameter("custom");
		
		if(name != null){
			sb.append("Task: " + name + " was successfully added" + "<br>" + 
			"Task Count: " +  "<br>" +
			"<a href='/index'>Return</a>");
		}
		
		//Assign Response Headers
		res.setContentType(contentType);
		res.setStatus(res.SC_OK);
		
		//Write Out The Response
		if(contentType.equals("text/html")){
			sb.append("<b>\n</Body>\n</HTML>");
		}
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
	}
}
