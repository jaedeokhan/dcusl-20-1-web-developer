<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.*" %>
<%@ page import="ex2.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member"); 
	%>
	${member.name}님 로그인 중입니다.
	<h1>로그인 성공</h1>
	<p><a href="/Day0528THU/ch2/LoginForm.jsp">로그인 페이지!</a></p>

</body>
</html>