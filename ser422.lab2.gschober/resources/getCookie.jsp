<%
String userFirstName = "";
String userLastName = "";

Cookie[] c = request.getCookies();
if (c != null) {
	for (Cookie i : c) {
		if (i.getName().equals(cookieFirstName)) {
			userFirstName = i.getValue();
		}
		
		if (i.getName().equals(cookieLastName)) {
			userLastName = i.getValue();
		}
	}
}
%>