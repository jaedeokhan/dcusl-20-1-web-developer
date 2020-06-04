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
   
   <h2>c:if 태그</h2>
   <c:if test="${10 > 20}" var="false_test">
     if { 10 > 20 } = ${false_test};
   </c:if>
</body>
</html>