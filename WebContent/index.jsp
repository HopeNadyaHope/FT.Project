<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Education</title>
</head>
<body>
 	<c:set var = "lastCommand" scope = "session" value = "go_to_main_page"/>
 	<c:redirect url = "/main.jsp"/>	
</body>
</html>