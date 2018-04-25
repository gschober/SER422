<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lab 2-GSchober: Login Page</title>

</head>
<body>
<% String cookieFirstName = "userFirstName"; %>
<% String cookieLastName = "userLastName"; %>
<%@include file="./getCookie.jsp" %>


	<form action="./controller" method="post">
		<input type="hidden" name="action" value="login"/>
		<div>
		First Name:
		<input type='text' name='firstName' value="<%= userFirstName %>" >
		</div>
		<div>
		Last Name:
		<input type='text' name='lastName' value="<%= userLastName %>">
		</div>
		<div>
		Password:
		<input type="password" name="password" value="">
		<div>
		<button type='submit'>Login</button>
		</div>
	</form>
</body>
</html>