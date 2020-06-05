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
	<jsp:include page="Header.jsp" />
	<h1>회원 수정</h1>
	<form action="/Day0601MON/MemberUpdateServlet" method="post">
	   번호 : <input type="text" name="no" value="${member.no}" readonly /><br>
	   이메일 :<input type="text" name="email" value="${member.email}"><br>
	   암호 : <input type="password" name="pw" value="${member.password}"><br>
 	   이름 : <input type="text" name="name" value="${member.name}"><br>
	   가입일 : ${member.createDate} <br>
	   수정일 : ${member.modifiedDate} <br>
	 	  <input type="submit" value="수정">
	 	  <input type="button" value="삭제" 
	 	  	onclick='location.href="/Day0601MON/MemberDeleteServlet?no=${member.no}"'>
		  <input type="button" value="취소" onclick='location.href="/Day0601MON/MemberListServlet"'>
	</form>
	<jsp:include page="Footer.jsp" />

</body>
</html>