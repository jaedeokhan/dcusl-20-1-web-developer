<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO" %>
<%
   MemberVO loginMember = (MemberVO)session.getAttribute("loginMember");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%
      if(loginMember == null){ 	  
   %>
      <a href="LoginForm.jsp">로그인</a>
      <a href="memberRegistForm.jsp">회원가입</a>
   <%
      }
      else{
   %>
      <a href="Logout">로그아웃</a>
   <%
      }
   %>
</body>
</html>