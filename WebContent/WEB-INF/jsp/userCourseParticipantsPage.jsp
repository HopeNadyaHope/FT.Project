<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../../header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.student" var="student" />
<fmt:message bundle="${loc}" key="local.passing" var="passing" />

<fmt:message bundle="${loc}" key="local.rating" var="rating" />
<fmt:message bundle="${loc}" key="local.review" var="review" />

<body>

<jsp:useBean id="runningCourse" class="by.epam.lobanok.entity.RunningCourse" scope="request" />
	<c:out value="${runningCourse.course.courseName}" /></br>
	<c:out value="${runningCourse.course.description}" /></br>
	<c:out value="${runningCourse.teacher.name} ${runningCourse.teacher.surname}" /></br>
	<c:out value="${runningCourse.passing}" /></br>
	
	
	<table border = "1" width = "100%">
         <tr>
           	<th><c:out value="${student}" /></th>
           	<th><c:out value="${rating}" />
           	<th><c:out value="${review}" />
           	<th>result </th>
         </tr>

	<c:forEach items="${requestScope.courseParticipants}" var="courseParticipants">  
		<tr>
			<td><c:out value="${courseParticipants.student.name} ${courseParticipants.student.surname}" /></td>
			<c:if test="${courseParticipants.result eq null }">
				<td>Выставить оценку</td>
				<td>Выставить ревью</td>
			</c:if>
			<c:if test="${courseParticipants.result ne null }">
				<td><c:out value="${courseParticipants.result.rating}" /></td>
				<td><c:out value="${courseParticipants.result.review}" /></td>
			</c:if>
			
  		</tr>  
	</c:forEach>
	</table>
</body>

<%@include file="../../footer.jsp"%>