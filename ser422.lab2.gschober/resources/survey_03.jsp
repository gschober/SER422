<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<title>Add User: Step 1</title>

	<%	
		String option1="";
		String option2="";
		String option3="";
		String option4="";
		String option5="";
		String option6="";
		String option7="";
	
		values = new ArrayList<String>();
		attributes = ((Map<String,String>)session.getAttribute("answers")); 
			
		if(!attributes.isEmpty()){
			 if(attributes.get("days") != null){
			 	values = Arrays.asList(attributes.get("days").split(","));			 				 	
			 	
			 	option1 = values.contains("Monday") ? "selected" : "";
			 	option2 = values.contains("Tuesday") ? "selected" : "";
			 	option3 = values.contains("Wednesday") ? "selected" : "";
			 	option4 = values.contains("Thursday") ? "selected" : "";
			 	option5 = values.contains("Friday") ? "selected" : "";
			 	option6 = values.contains("Saturday") ? "selected" : "";
			 	option7 = values.contains("Sunday") ? "selected" : "";

			 }
		}	
	%>

</head>


	<body>

	<h1>Lab 2:Step 3 </h1>

	<form action="./controller" method="post" id="form1">
		<div><input type="hidden" name="action" value="survey"></input></div>
		<input type="hidden" name="surveyNameMulti" value="days">
		<div>
		Select Available To Meet (Use Ctrl To Select Multiple)
		</div>
		<div>
		<select name="surveyAnswerMulti" multiple>
			<option value="Monday" <%=option1%>>Monday</option>
			<option value="Tuesday" <%=option2%>>Tuesday</option>
			<option value="Wednesday" <%=option3%>>Wednesday</option>
			<option value="Thursday" <%=option4%>>Thursday</option>
			<option value="Friday" <%=option5%>>Friday</option>
			<option value="Saturday" <%=option6%>>Saturday</option>
			<option value="Sunday" <%=option7%>>Sunday</option>
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


