package ser422.lab2.gschober;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ser422.lab2.gschober.User;

public class Welcome extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    		PrintWriter out = null;
	    /*try {
		   response.setContentType("text/html");
		   HttpSession session = request.getSession(false); // do not create if it does not exist!

		   out = response.getWriter();
		   out.write("<html>\r\n");
		   out.write("<head>\r\n");
		   out.write("<title>SampleWebApplication: Logged in</title>\r\n");
		   out.write("</head>\r\n");
		   out.write("<body>\r\n");
		   //request.getRequestDispatcher("/header.html").include(request, response);
		   if (session != null) {		      
		     ser422.lab2.gschober.User user = (User) session.getAttribute("user");
		      if (user != null) {
		    	  	out.write("\r\n");
		    	  	out.write("<h1>Sample Web Application: Logged in</h1>\r\n");
		    	  	out.write("<br/>\r\n");
		    	  	out.write("<br/>\r\n");
		    	  	out.write("Welcome " + user.getFirstName() + " " +user.getLastName());
		    	  	out.write(", you have successfully logged in!\r\n");
		    	  	out.write("Click <a href=\"controller?action=list\">here</a> to order some books!\r\n");
		    	  	out.write("\r\n");
		      } else {
		    	  	out.write("I have lost the User!!!");
		    	  	session.invalidate();
		      }
		   } else { 
			   out.write("No valid conversation exists between client and server");
		   }
		} finally {
		     out.write("</body>\r\n");
		     out.write("</html>\r\n");

		     if (out != null) {
			 out.close();
			 out = null;
		     }
		}*/
    	
		Cookie firstName = new Cookie("userFirstName",request.getParameter("firstName"));
		Cookie lastName = new Cookie("userLastName",request.getParameter("lastName"));
		
		firstName.setMaxAge(60*60*24*7);
		lastName.setMaxAge(60*60*24*7);
		
		response.addCookie(firstName);
		response.addCookie(lastName);
    	
		
    	request.getRequestDispatcher("success.jsp").forward(request,response);
    }
	
}
