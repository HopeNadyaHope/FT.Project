<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Регистрация</title>

<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/former.css">
<link rel="stylesheet" href="css/form-style.css">


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.ru" var="ru_button" />

<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.age" var="age" />

<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.male" var="male" />
<fmt:message bundle="${loc}" key="local.female" var="female" />

<fmt:message bundle="${loc}" key="local.email" var="email" />

<fmt:message bundle="${loc}" key="local.position" var="position" />
<fmt:message bundle="${loc}" key="local.student" var="student" />
<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />

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
	
	<div class="container">
	<div class="form-data">
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration" />
		
		<div class="row">
      		<div class="col-25"><c:out value="${login}" /></div>
			<div class="col-75"><input type="text" name="login" value="" required/></div>
		</div>
				
		<div class="row">
			<div class="col-25"><c:out value="${password}" /></div>
			<div class="col-75"><input type="password" name="password" value="" required/></div>
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${name}" /></div>
			<div class="col-75"><input type="text" name="name" value="" required/></div>
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${surname}" /></div>
			<div class="col-75"><input type="text" name="surname" value="" required/></div>
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${age}" /></div>
			<div class="col-75"><input type="number" min="14" max="100" step="1" 
			name="age" value="" placeholder="14-100" required/></div>	
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${sex}" /></div>
			<input type="radio" name="sex" value="male"><c:out value="${male}" />
			<input type="radio" name="sex" value="female"><c:out value="${female}" /><br />		
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${email}" /></div>
			<div class="col-75"><input type="text" name="email" value="" 
			required pattern="^[.a-z0-9_-]+@[a-z0-9-]+\.[a-zA-Z]{2,6}$" placeholder="example@email.com"/></div>	
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${position}" /></div>
			<input type="radio" name="role" value="студент"><c:out value="${student}" />
			<input type="radio" name="role" value="преподаватель"><c:out value="${teacher}" />			
		</div>
			
		<div class="row">
			<input type="submit" name="redistration" value="${registration}" />
		</div>
	</form>
	</div>		
	</div>
</body>

<!-- footer --->
<div class="footer">
	<div class="section-inner">
		<p>Подвал сайта Footer</p>
	</div>
</div><!--/footer-->

</html>