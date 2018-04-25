<%

	int value = (int)session.getAttribute("pagenumber");

	switch(value){
		case 0:
		case 1: %><%@include file="./survey_01.jsp"%><%
		break;
		case 2: %><%@include file="./survey_02.jsp"%><%
		break;
		case 3: %><%@include file="./survey_03.jsp"%><%
		break;
		case 4: %><%@include file="./survey_04.jsp"%><%
		break;
		case 5: %><%@include file="./survey_05.jsp"%><%
		break;
	}

%>