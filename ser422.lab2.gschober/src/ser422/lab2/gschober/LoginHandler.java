package ser422.lab2.gschober;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandler implements ActionHandler{

	public String handleAction(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		
		String returnPage = "loginFailure"; 
		String firstName = ((String[]) request.getParameterValues("firstName"))[0];
		String lastName = ((String[]) request.getParameterValues("lastName"))[0];
		String password = ((String[]) request.getParameterValues("password"))[0];
		
		User user = LoginService.login(new LoginCredentials("","",password));
		if(user != null){
			session.setAttribute("firstname",firstName);
			session.setAttribute("lastname",lastName);
			
			returnPage = "loginSuccessful";
		}
		
		return returnPage;
	}
}
