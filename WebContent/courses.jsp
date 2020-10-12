<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.go_to" var="go_to" />



<body>
	<c:out value="${courses}" />
	
	
	<c:forEach items="${requestScope.courses}" var="course">  
		<div class="container">
		
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="go_to_running_courses_page" />	
			<input type="hidden" name="courseID" value="${course.id}" />	
			<c:out value="${course.courseName}" /><br/>
			<c:out value="${course.description}" /><br/>
			<input type="submit" name="runningCourses" value="${go_to}" /> 
		</form>	
		
		</div>
	</c:forEach>
</body>

<%@include file="footer.jsp"%>