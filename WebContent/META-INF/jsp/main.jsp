<%@ page language="java" import="by.epam.lobanok.entity.User" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
			User userX = (User) request.getAttribute("user");
			if(userX == null){
				userX = new User();
			}
			out.println(userX.getName());
			out.println("<br />");
			out.println(request.getParameter("login"));
	%>
	<br />
	
	<jsp:useBean id="user" class="by.epam.lobanok.entity.User" scope="request" />
	<jsp:getProperty property="surname" name="user"/>
	<form action="index.jsp" method="post">
		<input type="submit" value="Go to index page"/>
	</form>
</body>
</html>