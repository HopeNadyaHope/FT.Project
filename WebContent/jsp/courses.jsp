<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.go_to" var="go_to" />



<body>
	<c:out value="${courses}" />
		
	<c:forEach items="${requestScope.courses}" var="course">  
		<div class="container">
		
		<form action="Controller" method="get">	
			<c:out value="${course.courseName}" /><br/>
			<c:out value="${course.description}" /><br/>			
			<a href="Controller?command=go_to_running_courses_page&courseID=${course.id}"><c:out value="${go_to}" /></a>			
		</form>	
		
		</div>
	</c:forEach>
</body>

<%@include file="pageElement/footer.jsp"%>