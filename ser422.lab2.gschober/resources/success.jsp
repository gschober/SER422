<%@ page import="java.util.*,ser422.lab2.gschober.XMLReader" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>

	<title>Lab 2: Welcome User</title>

</head>


	<body>

	<%=session.getAttribute("validationMessage")%>

	<h1>Lab 2: Welcome <%= session.getAttribute("firstname") %> <%= session.getAttribute("lastname")%> </h1>
<table>


<%
String filename = "C://Users/Zerker/personfile.xml";
	Map<String,String> user = new HashMap<String,String>();
	Map<String,Map<String,String>> surveys = XMLReader.XMLToSurveyAnswers(filename); 

	for(Map<String,String> value : surveys.values()){
		user = value;
%>
<tr>
	<td>
		<a href="./usercontroller?action=display
		&firstname=<%=user.get("firstname")%>
		&lastname=<%=user.get("lastname")%>
		&days=<%=user.get("days")%>
		&languages=<%=user.get("languages")%>
		&sourceeditor=<%=user.get("sourceeditor")%>"><%=user.get("firstname")%> <%=user.get("lastname")%></a>


	</td>
</tr>
<% }%>
</table>

	<form action="./controller" method="post">
		<input type="hidden" name="action" value="survey"/>
		
		<button type='submit'>Enter Info</button>
		
	</form>
<br>

<br>

</body>
</html>
