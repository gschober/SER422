<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<title>Add User: Step 1</title>

	<%
		String editor1="";
		String editor2="";
		String editor3="";
		String editor4="";
		
	
		values = new ArrayList<String>();
		attributes = ((Map<String,String>)session.getAttribute("answers")); 
			
		if(!attributes.isEmpty()){
			 if(attributes.get("sourceeditor") != null){
			 	values = Arrays.asList(attributes.get("sourceeditor").split(","));			 				 	
			 	
			 	editor2 = values.contains("Sublime") ? "selected" : "";
			 	editor3 = values.contains("IntelliJ") ? "selected" : "";
			 	editor4 = values.contains("Brackets") ? "selected" : "";
			 }
		}	
	%>

</head>


	<body>

	<h1>Lab 2:Step 4 </h1>

	<form action="./controller" method="post" id="form1">
		<div><input type="hidden" name="action" value="survey"></input></div>
		<input type="hidden" name="surveyName01" value="sourceeditor">
		<div>
		Select Favorite Source Editor
		</div>
		<div>
		<select name="surveyAnswer01">
			<option value="Sublime" <%=editor2%>>Sublime</option>
			<option value="Intellij" <%=editor3%>>IntelliJ</option>
			<option value="Brackets" <%=editor4%>>Brackets</option>
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
