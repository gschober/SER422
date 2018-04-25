
package edu.asupoly.ser422.lab5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONObject;


@SuppressWarnings("serial")
public class Lab5Map extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("grade");
		
		double grade = Double.parseDouble(value);
		
		String letterGrade = mapToLetterGrade(grade);
		
		JSONObject object = new JSONObject();
		object.put("grade",letterGrade);
		
		String returnValue = object.toJSONString();
		
		response.setContentLength(returnValue.length());
		
		response.getOutputStream().write(returnValue.getBytes());
		response.getOutputStream().flush();
		response.getOutputStream().close();		
	}
	
	public final String mapToLetterGrade(double grade) {
		if (grade >= 98.0) return "A+";
		if (grade >= 93.0) return "A";
		if (grade >= 90.0) return "A-";
		if (grade >= 88.0) return "B+";
		if (grade >= 83.0) return "B";
		if (grade >= 80.0) return "B-";
		if (grade >= 77.0) return "C+";
		if (grade >= 70.0) return "C";
		if (grade >= 60.0) return "D";
		if (grade < 0.0) return "I";
		return "E";
	}
}