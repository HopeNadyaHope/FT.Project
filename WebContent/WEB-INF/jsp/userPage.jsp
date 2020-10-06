<%@ page language="java"  import="by.epam.lobanok.entity.User" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../../header.jsp"%>

<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.age" var="age" />
<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.position" var="position" />
<fmt:message bundle="${loc}" key="local.email" var="email" />

<fmt:message bundle="${loc}" key="local.welcome" var="welcome" />

<body>
	<c:out value="${welcome}" /><br />	
	<jsp:useBean id="user" class="by.epam.lobanok.entity.User" scope="session" />
	
	<div class="photo">
		<c:if test="${user.sex eq 'female'}">	
           <img src="images/userPhoto/female.jpg">   
    	</c:if>    	
    	<c:if test="${user.sex eq 'male'}">	
           <img src="images/userPhoto/men.jpg">   
    	</c:if>
    </div>
    
	<br />	
	<c:out value="${name} : " /><c:out value="${user.name}" /><br />
	<c:out value="${surname} :" /><c:out value="${user.surname}" /><br />
	<c:out value="${age} :" /><c:out value="${user.age}" /><br />
	<c:out value="${sex} :" /><c:out value="${user.sex}" /><br />
	<c:out value="${position} : " /><c:out value="${user.role}" /><br />
	<c:out value="${email} : " />	<c:out value="${user.email}" /><br />
</body>

<%@include file="../../footer.jsp"%>