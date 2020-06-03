<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex2.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h1>회원 등록</h1>
	<form action="/Day0601MON/MemberAddServlet" method="post">
	   이름 : <input type="text" name="name"><br>
	   이메일 :<input type="text" name="email"><br>
	   암호 : <input type="password" name="pw">
	 	  <input type="submit" value="추가">
	 	  <input type="reset" value="취소">
	</form>
	<jsp:include page="Footer.jsp" />

</body>
</html>