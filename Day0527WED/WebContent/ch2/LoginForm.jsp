<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="/Day0527WED/LoginServletEx" method="post">
	   <label>이메일 :
	   <input type="text" name="email"><br>
	   </label>
	   <label>암호 : 
	   <input type="password" name="pw"><br>
	   </label>
	   <input type="submit" value="로그인">
	   <input type="reset" value="취소">
	</form>
</body>
</html>