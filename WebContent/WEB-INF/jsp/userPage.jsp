<%@ page language="java"  import="by.epam.lobanok.entity.User" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.ru" var="ru_button" />

<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.age" var="age" />
<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.position" var="position" />
<fmt:message bundle="${loc}" key="local.email" var="email" />

<fmt:message bundle="${loc}" key="local.welcome" var="welcome" />
</head>

<body>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="localization" />	
		<input type="hidden" name="local" value="ru" />	
		<input type="submit" value="${ru_button}" /><br />
	</form>
	
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="localization" />	
		<input type="hidden" name="local" value="en" />	
		<input type="submit" value="${en_button}" /><br />
	</form>
	
	<c:out value="${welcome}" /><br />
	
	<jsp:useBean id="user" class="by.epam.lobanok.entity.User" scope="session" />
	
	<c:out value="${name} : " /><c:out value="${user.name}" /><br />
	<c:out value="${surname} :" /><c:out value="${user.surname}" /><br />
	<c:out value="${age} :" /><c:out value="${user.age}" /><br />
	<c:out value="${sex} :" /><c:out value="${user.sex}" /><br />
	<c:out value="${position} : " /><c:out value="${user.role}" /><br />
	<c:out value="${email} : " />	<c:out value="${user.email}" /><br />
</body>

</html>