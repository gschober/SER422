<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>

	<title>Add User: Step 1</title>

	<%	
		String cpp="false";
		String cs="false";
		String java="false";
		String python="false";
		String ruby="false";
		String gs = "false";
	
		List<String> values = new ArrayList<String>();
		attributes = ((Map<String,String>)session.getAttribute("answers")); 
			
		if(!attributes.isEmpty()){
			 if(attributes.get("languages") != null){
			 	values = Arrays.asList(attributes.get("languages").split(","));			 				 	
			 	
			 	cpp = values.contains("CPP") ? "selected" : "";
			 	cs = values.contains("CSharp") ? "selected" : "";
			 	java = values.contains("Java") ? "selected" : "";
			 	python = values.contains("Python") ? "selected" : "";
			 	ruby = values.contains("Ruby") ? "selected" : "";
			 	gs = values.contains("GolfScript") ? "selected" : ""; 
			 }
		}
		

	
	%>


</head>


	<body>

	<h1>Lab 2:Step 2 </h1>
	<3><%= session.getAttribute("surveywarning")%>

	<form action="./controller" method="post" id="form1">
		<div><input type="hidden" name="action" value="survey"></input></div>
		<input type="hidden" name="surveyNameMulti" value="languages">
		<div>
		Select Your Favorite Programming Languages (Use Ctrl To Select Multiple)
		</div>
		<div>
		<select name="surveyAnswerMulti" multiple>
			<option value="CPP" <%=cpp%>>C++</option>
			<option value="CSharp" <%=cs%>>C#</option>
			<option value="Java" <%=java%>>Java</option>
			<option value="Python" <%=python%>>Python</option>
			<option value="Ruby" <%=ruby%>>Ruby</option>
			<option value="GolfScript" <%=gs%>>GolfScript</option>
		<select>
		</div>
	</form>
	
	<div>
	<a href="./controller?action=survey&submit=previous">Previous</a>
	<button type="submit" name="submit" value="next" form="form1">Next</button>
	</div>
<br>

<br>

</body>
</html>
