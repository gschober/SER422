<h1>Confirm Deletion Of User</h1>
<br>
<br>
<a href="./usercontroller?action=cancel&
		firstname=<%=request.getParameter("firstname")%>&
		lastname=<%=request.getParameter("lastname")%>&
		days=<%=request.getParameter("days")%>&
		languages=<%=request.getParameter("languages")%>&
		sourceeditor=<%=request.getParameter("sourceeditor")%>">Cancel</a>
<a href="./usercontroller?action=confirm">Confirm</a>