<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <p href="el01_operation.jsp"><a>[이전]</a><a>[다음]</a></p>
   <h2>c:out 태그</h2>
   1) <c:out value="안녕하세요!"/><br>
   <%= "안녕하세요!" %>
   <h3>값 설정 방식</h3>
   <c:set var="username1" value="홍길동"/>
   <c:set var="username2">임꺽정</c:set>
   <c:set var="test">test</c:set>
   1) ${username1}<br>
   2) ${username2}<br>
   3) ${test}<br>
</body>
</html>