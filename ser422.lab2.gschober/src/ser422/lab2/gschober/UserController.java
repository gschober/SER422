package ser422.lab2.gschober;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserController  extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		String filename = (String)session.getAttribute("inputFile");
		
		String action = request.getParameter("action");
		String forwardPage = "/displayUser.jsp";
				
		if(action != null && action.length() > 0){
			
			if(action.equals("display")){
				forwardPage = "/displayUser.jsp";
			}
			
			if(action.equals("delete")){
				forwardPage = "/confirmation.jsp";
			}
			
			if(action.equals("cancel")){
				forwardPage ="/displayUser.jsp";
			}
			
			if(action.equals("confirm")){
				XMLReader.DeleteUser(filename, (String)request.getParameter("firstname")+(String)request.getParameter("lastname"));
				forwardPage = "/success.jsp";
			}
			
			if(action.equals("exit")){
				forwardPage ="/success.jsp";
			}
		}
		
		request.getRequestDispatcher(forwardPage).forward(request,response);
	}
}
