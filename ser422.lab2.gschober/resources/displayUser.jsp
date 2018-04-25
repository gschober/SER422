<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
</head>

<body>
	<h1>User Information</h1>
	<table>
	<tr><th>First Name</th><th>Last Name</th><th>Available To Meet</th><th>Languages</th><th>Source Editor</th><tr>
	<tr>
		<td><%=request.getParameter("firstname")%></td>
		<td><%=request.getParameter("lastname")%></td>
		<td><%=request.getParameter("days")%></td>
		<td><%=request.getParameter("languages")%></td>
		<td><%=request.getParameter("sourceeditor")%></td>
	</table>
	<a href="./usercontroller?action=exit">Back</a>
	<a href="./usercontroller?action=delete&
		firstname=<%=request.getParameter("firstname")%>&
		lastname=<%=request.getParameter("lastname")%>&
		days=<%=request.getParameter("days")%>&
		languages=<%=request.getParameter("languages")%>&
		sourceeditor=<%=request.getParameter("sourceeditor")%>">Delete</a>
</body>
</html>