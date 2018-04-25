<%@ page import="java.util.*" %>



<html>
<head>

	<title>Add User: Step 1</title>


</head>


	<body>

	<% String cookieFirstName = "userFirstName"; %>
	<% String cookieLastName = "userLastName"; %>
	<%@include file="./getCookie.jsp" %>
	<%	
		Map<String,String> attributes = ((Map<String,String>)session.getAttribute("answers")); 
		String firstname = userFirstName;
		String lastname = userLastName;
		
		if(!attributes.isEmpty()){
			firstname = attributes.get("firstname");
			lastname = attributes.get("lastname");
		}	
	%>


	<h1>Lab 2:Step 1 </h1>
	<% 	String message = "";
		String warning = (String)session.getAttribute("validationMessage");
	
		if(warning != null && warning.length() > 0)
		message = warning;
	%>
	<br>
	<h3 style="color:red"><%=message%><h3>
	<br>

	<form action="./controller" method="post">
		<div><input type="hidden" name="action" value="survey"></input></div>
		 <input type="hidden" name="surveyName01" value="firstname">
		<div>First Name:<input type="text" name="surveyAnswer01" value="<%=firstname%>"></input></div>
		<input type="hidden" name="surveyName02" value="lastname">
		<div>Last Name: <input type="text" name="surveyAnswer02" value="<%=lastname%>"></input></div>
		<div><button type="submit" name="submit" value="next">Next</button></div>
	</form>
<br>

<br>
test
<%=session.getAttribute("answers")%>

</body>
</html>
