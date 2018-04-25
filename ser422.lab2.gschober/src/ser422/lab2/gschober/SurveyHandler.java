package ser422.lab2.gschober;

import java.awt.List;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class SurveyHandler implements ActionHandler{

	public String handleAction(HttpServletRequest request, HttpServletResponse response) throws IOException{
	
	
		Map<String,String> surveyAnswers = new HashMap<String,String>();
		
		HttpSession session = request.getSession(true);
		

		String filename = (String)session.getAttribute("inputFile");
		
		int surveyLength = 5;
		
		//Add in a session null error handling
				
		String username = (String)session.getAttribute("firstName")+(String)session.getAttribute("lastname");
		surveyAnswers = (Map<String,String>)session.getAttribute("answers");
		
		//Get the survey object. This will hold the array containing all the jsp files
		//This will be used to increment through all the questions.
		
		if(surveyAnswers == null){
			surveyAnswers = XMLReader.XMLToSurveyAnswers(filename).get(username);
			
			//iF there are no answers on the file for this user
			if(surveyAnswers == null){
				surveyAnswers = new HashMap<String,String>();
				//Write to the file 
			}
			
			session.setAttribute("answers",surveyAnswers);
		}
		
		Integer pageNumber = (Integer)session.getAttribute("pagenumber");
		String pageAction = request.getParameter("submit");
		
		if(pageNumber == null){
			pageAction = "next";
			pageNumber = 0;
		}
		
		//This really doesn't account for expandablity. A better way would have been to pass in a list
		//of key-value pairs or create a custom class to handle parameter names and values. 
		//But since we only have one question that uses two fields this is used for ease and my sanity.
		else if( pageNumber > 0 && pageNumber <= surveyLength){
			String surveyName01 = request.getParameter("surveyName01");
			String surveyAnswer01 = request.getParameter("surveyAnswer01");
			
			String surveyName02 = request.getParameter("surveyName02");
			String surveyAnswer02 = request.getParameter("surveyAnswer02");
			
			String surveyNameMulti = request.getParameter("surveyNameMulti");
			String[] surveyAnswerMulti = request.getParameterValues("surveyAnswerMulti");
			
			if(surveyAnswer01 != null && !surveyAnswer01.isEmpty()){
				surveyAnswers.put(surveyName01, surveyAnswer01);
			}
			
			if(surveyAnswer02 != null && !surveyAnswer02.isEmpty()){
				surveyAnswers.put(surveyName02, surveyAnswer02);
			}
			
			if(surveyAnswerMulti != null && surveyAnswerMulti.length > 0){
			
				String selection = surveyAnswerMulti[0];
				
				for(int i=1; i<surveyAnswerMulti.length; i++){
					selection += "," + surveyAnswerMulti[i];
				}
				
				surveyAnswers.put(surveyNameMulti,selection);
			}
		}
		
		else{
			//Send back an error message
			session.invalidate();
			return "surveyerror";
		}
		
		if(pageAction != null){
			if(pageAction.equals("previous") && pageNumber > 0){
				pageNumber--;
			}
			
			else if(pageAction.equals("next")){
				if(pageNumber >= surveyLength){
					return "surveyConfirm";
				}
				else{
					pageNumber++;
				}
					
			}
			
			else if(pageAction.equals("cancel")){
				session.setAttribute("pagenumber",0);
				return "loginSuccessful";				
			}
			
			else if(pageAction.equals("edit")){
				pageNumber = 1;
			}
			
			else if(pageAction.equals("confirm")){
				
				String validationMessage = "";
				boolean isValid = true;
				
				if(request.getParameter("firstname") == null || !isAlpha((String)request.getParameter("firstname"))){
					validationMessage += "<br>First name is not valid";
					isValid = false;
				}
				
				if(request.getParameter("lastname") == null || !isAlpha((String)request.getParameter("lastname"))){
					validationMessage += "<br>Last name is not valid";
					isValid = false;
				}
				
				if(request.getParameter("days") == null || ((String)request.getParameter("days")).length() == 0){
					validationMessage += "<br>Meeting Times Not Select. Select Available Meeting Times";
					isValid = false;
				}
				
				if(request.getParameter("languages") == null || ((String)request.getParameter("languages")).length() == 0){
					validationMessage += "<br>Languages Not Select. Select Available Langauges";
					isValid = false;
				}
				
				if(request.getParameter("sourceeditor") == null || ((String)request.getParameter("sourceeditor")).length() == 0){
					validationMessage += "<br>Source Editor Not Selected. Select Source Editor";
					isValid = false;
				}
				
				session.setAttribute("validationMessage", validationMessage);
				
				if(!isValid){
					session.setAttribute("pagenumber",0);
					return "survey";
				}
				
				else if(isValid){
					
					XMLWriter.NewEntryToXML(filename, surveyAnswers);
					
					Cookie firstName = new Cookie("userFirstName",request.getParameter("firstname"));
					Cookie lastName = new Cookie("userLastName",request.getParameter("lastname"));
					
					firstName.setMaxAge(60*60*24*7);
					lastName.setMaxAge(60*60*24*7);
					
					response.addCookie(firstName);
					response.addCookie(lastName);
					
					session.setAttribute("pagenumber",0);
					
					
					return "loginSuccessful";
				}
			}
		}
		
		session.setAttribute("pagenumber",pageNumber);
		return "survey";
		//return Survey.getPageView(pageNumber);		
	}
	
	public boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	}
}
