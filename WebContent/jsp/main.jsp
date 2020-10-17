<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="pageElement/header.jsp"%>

<link rel="stylesheet" href="css/font-awesome.css" type="text/css">    

<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.entrance" var="entrance" />
<fmt:message bundle="${loc}" key="local.registration" var="registration" />

<body>
	<div class="container">
		<form action="Controller" method="post">
		
		<c:if test="${not empty requestScope.exceptionMessage}">
			<h1><c:out value="${requestScope.exceptionMessage}" /></h1>
			<c:out value="hello" />
		</c:if>
		
			<input type="hidden" name="command" value="entrance" />
			<h1><c:out value="${login}" /></h1>
			<div class="dws-input">
			<input type="text" name="login" value="" required/>
			</div>
		
			<c:out value="${password}" /><br/>
			<div class="dws-input">
			<input type="password" name="password" value="" required />
			</div>	
			<div class="button"><input type="submit" name="entrance" value="${entrance}" />	</div>
		</form>

		<a href="Controller?command=go_to_registration_page"><c:out value="${registration}" /></a>
	</div>
	
	<div class="services-photo">
		<a href=""><img src="images/coursesPhoto/course.jpg"></a>  
		<a href=""><img src="images/coursesPhoto/course.jpg"></a> 
		<a href=""><img src="images/coursesPhoto/course.jpg"></a> 
		<a href=""><img src="images/coursesPhoto/course.jpg"></a>      
	</div>	

</body>

<%@include file="pageElement/footer.jsp"%>