<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/former.css">
<link rel="stylesheet" href="css/form-style.css">


<meta http-equiv="Content-Type"
	content="text/html; charset=utf-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.ru" var="ru_button" />

<fmt:message bundle="${loc}" key="local.mainPage" var="mainPage" />
<fmt:message bundle="${loc}" key="local.about" var="about" />
<fmt:message bundle="${loc}" key="local.courses" var="courses" />

<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.entrance" var="entrance" />
<fmt:message bundle="${loc}" key="local.registration" var="registration" />
</head>

<header>
	<div class="header">
		<div class="section-inner">
			<div class="logo">	<c:out value="${message}" /></div>
			<div class = "localization">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="localization" />	
					<input type="hidden" name="local" value="ru" />	
					<input type="submit" value="${ru_button}" />
				</form>
	
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="localization" />	
					<input type="hidden" name="local" value="en" />	
					<input type="submit" value="${en_button}" />
				</form>
			</div>			
		</div>	
	</div>

	<!-- nav -->
	<nav class="menu">
		<div class="section-inner">
			<ul>
				<li><a href="Controller?command=go_to_main_page"><c:out value="${mainPage}" /></a></li>
				<li><a href="Controller?command=go_to_courses_page"><c:out value="${courses}" /></a></li>
				<li><a href="Controller?command=go_to_about_page"><c:out value="${about}" /></a></li>
				<li><a href="#"></a></li>
				<li><a href="#"></a></li>
		
			</ul>
		</div>
	</nav><!--/nav-->
</header><!--/header-->

<body>
	<c:out value="${about}" />
</body>

<!-- footer --->
<div class="footer">
	<div class="section-inner">
		<p>Подвал сайта Footer</p>
	</div>
</div><!--/footer-->

</html>