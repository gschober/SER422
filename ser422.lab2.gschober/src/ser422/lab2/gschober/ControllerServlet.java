package ser422.lab2.gschober;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ControllerServlet  extends HttpServlet {

	private static String errorPage = "/error.jsp";
    private static String successPage = "/welcome";
    
	private static Map<String, ActionHandler> actionHandlers = new HashMap<String, ActionHandler>();
	private static Map<String, String> pageViews = new HashMap<String, String>();
	
	private String inputFile = null;
	
	public void init(ServletConfig config) throws ServletException{
		
		String inputFile = config.getInitParameter("personfile");
		if (inputFile == null || inputFile.length() == 0) {
			throw new ServletException();
		}		
		
		actionHandlers.put("login",new LoginHandler());
		actionHandlers.put("survey",new SurveyHandler());
		
		
		pageViews.put("survey","/survey.jsp");
		pageViews.put("surveysaved", "/surveyComplete.jsp");
		pageViews.put("surveyerror", errorPage);
		pageViews.put("loginSuccessful",successPage);
		pageViews.put("loginFailure",errorPage);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		HttpSession session = request.getSession();
		String forwardPage = errorPage;
		
		String action = request.getParameter("action");
		
		Map<String, String[]> params = request.getParameterMap();
	http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=2963451
		session.setAttribute("inputFile", inputFile);
		
		if(action != null && action.length() > 0){
			ActionHandler handler = actionHandlers.get(action);
		
			if(handler != null){
				String result = handler.handleAction(request,response);
				
				if(result != null && result.length() > 0){
					forwardPage = pageViews.get(result);					
				}
					
				if(forwardPage == null || forwardPage.length() == 0 ){
					forwardPage = errorPage;
				}
			}
		}
		
		request.getRequestDispatcher(forwardPage).forward(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doAction(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doAction(request, response);	
	}	
}
