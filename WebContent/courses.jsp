<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />


<body>
	<c:out value="${courses}" />
	
	<table border = "1" width = "100%">
         <tr>
            <th><c:out value="${courseName}" /></th>
           	<th><c:out value="${courseDescription}" /></th>
         </tr>
    
	<c:forEach items="${requestScope.courses}" var="course">  
		<tr>
			<td><c:out value="${course.courseName}" /></td>
			<td><c:out value="${course.description}" /></td>
  		</tr>  
	</c:forEach>
	</table>
</body>

<%@include file="footer.jsp"%>