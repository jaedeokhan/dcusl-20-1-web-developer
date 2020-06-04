<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <p><a href="ex01_scope.jsp">[ 이전 ]</a><a href="ex03.jsp">[ 다음 ]</a></p>
   <h2>c:remove 태그</h2>
   <h3>보관소에 저장된 값을 제거하는 방법</h3>
   <% pageContext.setAttribute("username1", "한재덕"); %>
   1) ${username1}<br>
   <c:remove var="username1"/>
   2) ${username1}<br>
   
</body>
</html>