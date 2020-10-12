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

	<c:out value="${courseName}" />
	<c:out value="${courseDescription}" />
	<c:out value="${teacher}" />
	<c:out value="${passing}" />
	
	<table border = "1" width = "100%">
         <tr>
             <th><c:out value="${rating}" /></th>
           	<th><c:out value="${review}" /></th>
         </tr>
         
    
	<jsp:useBean id="result" class="by.epam.lobanok.entity.Result" scope="request" />
	<c:if test="${result eq null}">
	 	<td><c:out value="учите" /></td>
	 	<td><c:out value="разбирайтесь" /></td>
	</c:if>
	
	<c:if test="${result ne null}">
		<tr>
			<td><c:out value="${result.rating}" /></td>
			<td><c:out value="${result.review}" /></td>
  		</tr> 
  	</c:if> 
	</table>
</body>

<%@include file="../../footer.jsp"%>