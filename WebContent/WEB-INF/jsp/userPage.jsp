<%@ page language="java"  import="by.epam.lobanok.entity.User" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../../jsp/pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.age" var="age" />
<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.email" var="email" />

<fmt:message bundle="${loc}" key="local.edit" var="edit" />

<fmt:message bundle="${loc}" key="local.welcome" var="welcome" />

<body>
	<c:out value="${welcome}" /><br />	
	<jsp:useBean id="user" class="by.epam.lobanok.entity.User" scope="session" />
	
	<div class="photo">
		<c:if test="${user.sex eq 'жен'}">	
           <img src="images/userPhoto/female.jpg">   
    	</c:if>    	
    	<c:if test="${user.sex eq 'муж'}">	
           <img src="images/userPhoto/men.jpg">   
    	</c:if>
    </div>
    
	<br />	
	<c:out value="${name} : " /><c:out value="${user.name}" /><br />
	<c:out value="${surname} :" /><c:out value="${user.surname}" /><br />
	<c:out value="${age} :" /><c:out value="${user.age}" /><br />
	<c:out value="${sex} :" /><c:out value="${user.sex}" /><br />
	<c:out value="${role} : " /><c:out value="${user.role}" /><br />
	<c:out value="${email} : " />	<c:out value="${user.email}" /><br />
	
	<a href="Controller?command=go_to_edit_profile"><c:out value="${edit}" /></a>	
</body>

<%@include file="../../jsp/pageElement/footer.jsp"%>