<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    안녕하세요. JSP 페이지 입니다.
  
  <%= "\n안녕하세요\n" %>
  <%
  	out.println("안녕하세요");
  %>
</body>
</html>