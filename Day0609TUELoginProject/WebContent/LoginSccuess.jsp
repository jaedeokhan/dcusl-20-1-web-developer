<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO" %>
<%
   MemberVO loginMember = (MemberVO)request.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>로그인에 성공한 회원의 정보</h1>
이름  : <%= loginMember.getName() %><br>
아이디 :  <%= loginMember.getId() %><br>
주소  :  <%= loginMember.getAddr1() %><br>
이메일  : <%= loginMember.getEmail() %>
</body>
</html>