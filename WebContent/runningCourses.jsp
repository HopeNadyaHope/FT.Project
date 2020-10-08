<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />
<fmt:message bundle="${loc}" key="local.passing" var="passing" />


<body>
	<table border = "1" width = "100%">
         <tr>
            <th><c:out value="${courseName}" /></th>
           	<th><c:out value="${courseDescription}" /></th>
           	<th><c:out value="${teacher}" />
           	<th><c:out value="${passing}" />
         </tr>
    
	<c:forEach items="${requestScope.runningCourses}" var="runningCourse">  
		<tr>
			<td><c:out value="${runningCourse.course.courseName}" /></td>
			<td><c:out value="${runningCourse.course.description}" /></td>
			<td><c:out value="${runningCourse.teacher.name}  ${runningCourse.teacher.surname}" /></td>
			<td><c:out value="${runningCourse.passing}" /></td>
  		</tr>  
	</c:forEach>
	</table>
</body>

<%@include file="footer.jsp"%>