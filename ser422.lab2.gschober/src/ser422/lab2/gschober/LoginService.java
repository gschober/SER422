package ser422.lab2.gschober;

public class LoginService {

	public static User login(LoginCredentials loginCredentials){
		
		if(loginCredentials == null || !loginCredentials.getPassword().equalsIgnoreCase("ser422")){
			return null;
		}		

		User loggedUser = new User();
		loggedUser.setFirstName(loginCredentials.getFirstName());
		loggedUser.setLastName(loginCredentials.getLastName());
		
		return loggedUser;
	}
	
}
