<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../../header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />
<fmt:message bundle="${loc}" key="local.passing" var="passing" />

<fmt:message bundle="${loc}" key="local.go_to" var="go_to" />


<body>
	<table border = "1" width = "100%">
         <tr>
            <th><c:out value="${courseName}" /></th>
           	<th><c:out value="${courseDescription}" /></th>
           	<th><c:out value="${teacher}" />
           	<th><c:out value="${passing}" />
           	<th>result </th>
         </tr>
    
   <c:if test="${requestScope.runningCourses eq null}">
   <h1>NOTHING</h1> 
   </c:if>
    
	<c:forEach items="${requestScope.runningCourses}" var="runningCourse">  
		<tr>
			<td><c:out value="${runningCourse.course.courseName}" /></td>
			<td><c:out value="${runningCourse.course.description}" /></td>
			<td><c:out value="${runningCourse.teacher.name}  ${runningCourse.teacher.surname}" /></td>
			<td><c:out value="${runningCourse.passing}" /></td>
			<td>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="go_to_user_course_result_page" />	
					<input type="hidden" name="runningCourseID" value="${runningCourse.id}" />
					
					<c:set var = "runningCourse" scope = "request" value = "${runningCourse}"/>	
					
					<input type="submit" name="runningCourses" value="${go_to}" /> 
				</form>	
			</td>
  		</tr>  
	</c:forEach>
	</table>
</body>

<%@include file="../../footer.jsp"%>