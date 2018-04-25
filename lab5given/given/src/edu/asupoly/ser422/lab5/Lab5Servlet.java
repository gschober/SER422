/*
 * Lab5Servlet.java
 *
 * Copyright:  2008 Kevin A. Gary All Rights Reserved
 *
 */
package edu.asupoly.ser422.lab5;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import javafx.util.Pair;
import sun.net.www.protocol.http.HttpURLConnection;

@SuppressWarnings("serial")
public class Lab5Servlet extends HttpServlet {

	private String __mapUrl = null;
	private String __calcUrl = null;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);

		Properties props = new Properties();

		try {
			InputStream propFile = this.getClass().getClassLoader().getResourceAsStream("lab5url.properties");
			props.load(propFile);
			propFile.close();
		}catch (IOException ie) {
			ie.printStackTrace();
			//throw new Exception("Could not open property file");
		}
		
		__mapUrl = "http://localhost:" + props.getProperty("map.url") +"/lab5Map/lab5Map?";
		__calcUrl = "http://localhost:" + (String)props.getProperty("calc.url") +"/lab5Calc/lab5Calc?";
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		StringBuffer pageBuf = new StringBuffer();
		
		Object object;
		JSONObject jObject;
		
		String year = req.getParameter("year");
		String subject = req.getParameter("subject");
		
		double gradeValue = 0;
		
		boolean isValid = true;
		
		String letterGrade = "";
		
		if (year != null && !year.trim().isEmpty()) {
			pageBuf.append("<br/>Year: " + year);
		}
		if (subject != null && !subject.trim().isEmpty()) {
			pageBuf.append("<br/>Subject: " + subject);
		}
		
		String response = "";
		
		try {		
			URL calc = new URL(__calcUrl+"subject="+subject+"&year="+year);
			//URL calc = new URL("http://localhost:8081/lab5Calc/lab5Calc?subject=History&year=1");
			response = sendGet(calc);
		} catch (Exception e) {
			pageBuf.append("\n\t<br/>Code 503: Service Unavailable");
			isValid = false;
		}
		
		//Parse the JSON
		try{
			object = new JSONParser().parse(response);
			jObject = (JSONObject) object;			
			
			if(response.contains("error")) {				
				String message = (String)jObject.get("error");				
				pageBuf.append("\n\t<br/>" + message);
				isValid = false;
			}
			
			else{
				 gradeValue = (double)jObject.get("grade");
				 pageBuf.append(gradeValue);
			}
			
		}catch(Exception e){
			pageBuf.append("\n\t<br/> Something Went Wrong Parsing The JSON" + response);
		}		
		

		
		if(isValid){
			try{
				response = sendGet(new URL(__mapUrl+"grade="+gradeValue));
			}catch(Exception e){
				pageBuf.append("\n\t<br/>Code 503: Service Unavaileble");
				isValid = false;
			}
		}
		
		if(isValid){
			try{
				object = new JSONParser().parse(response);
				jObject = (JSONObject) object;			
				
				if(response.contains("error")) {				
					String message = (String)jObject.get("error");				
					pageBuf.append("\n\t<br/>" + message);
					isValid = false;
				}
				
				else{
					letterGrade  = (String)jObject.get("grade");
				}
				
			}catch(Exception e){
				pageBuf.append("\n\t<br/> Something Went Wrong Parsing The JSON 2");
				isValid = false;
			}			
		}
		
		if(isValid){
			pageBuf.append("\n\t<br/>Grade: " + gradeValue);
			pageBuf.append("\n\t<br/>Letter: " + letterGrade); 
		}
		
		// some generic setup - our content type and output stream
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		out.println(pageBuf.toString());
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
	
	public String sendGet(URL url) throws IOException{		
		
		StringBuilder result = new StringBuilder();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");	
		
	    BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}	
}
