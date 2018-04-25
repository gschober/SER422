<%@ page import="java.util.*" %>



<html>
<head>	
	
	<%
		String firstnamefinal ="";
		String lastnamefinal="";
		String daysfinal="";
		String languagesfinal ="";
		String sourceeditorfinal = "";
		
		attributes = ((Map<String,String>)session.getAttribute("answers"));
		if(!attributes.isEmpty()){
			firstnamefinal = attributes.get("firstname");
			lastnamefinal = attributes.get("lastname");
			daysfinal = attributes.get("days");
			languagesfinal = attributes.get("languages");
			sourceeditorfinal = attributes.get("sourceeditor");			
		}	
	%>

	
	<title>Finalize User Entry</title>
	
<head>
<body>

	<h1>Finalize Entry</h1>
	<div>First Name: <%= firstnamefinal %></div>
	<div>Last Nam: <%=lastnamefinal%></div>
	<div>Available To Meet: <%=daysfinal%></div>
	<div>Favorite Programming Languages: <%=languagesfinal%></div>
	<div>Favorite Source Editor: <%=sourceeditorfinal%></div>
	<div>
		<a href="./controller?action=survey&submit=cancel">Cancel</a>
	</div>
		<a href="./controller?action=survey&submit=edit">Edit</a>
		<a href="./controller?action=survey&submit=confirm
		&firstname=<%=firstnamefinal%>
		&lastname=<%=lastnamefinal%>
		&days=<%=daysfinal%>
		&languages=<%=languagesfinal%>
		&sourceeditor=<%=sourceeditorfinal%>">Confirm</a>
	</div>
		
</body>

