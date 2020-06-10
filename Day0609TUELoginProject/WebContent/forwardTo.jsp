<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%
    String requestValue = (String)request.getAttribute("requestScope");
 	String sessionValue = (String)session.getAttribute("sessionScope");
 %>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   requestScope = <%= requestValue %><br>
   sessionValue = <%= sessionValue %>
</body>
</html>