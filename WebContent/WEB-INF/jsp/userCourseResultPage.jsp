<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../../header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />
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
             <th><c:out value="${rating}" /></th>
           	<th><c:out value="${review}" /></th>
         </tr>
       
    
	<jsp:useBean id="courseResult" class="by.epam.lobanok.entity.Result" scope="request" />
	<c:if test="${courseResult eq null}">
	 	<td><c:out value="учите" /></td>
	 	<td><c:out value="разбирайтесь" /></td>
	</c:if>
	
	<c:if test="${courseResult ne null}">
		<tr>
			<td><c:out value="${courseResult.rating}" /></td>
			<td><c:out value="${courseResult.review}" /></td>
  		</tr> 
  	</c:if> 
  	</table> 

</body>

<%@include file="../../footer.jsp"%>