<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   function onloadEvent(obj){
	   if (obj.value == ""){
		   alert(obj.name + " 을 입력해주세요.");
	   }
   } 
</script>
</head>
<body>
	<h1>로그인</h1>
	<form action="/Day0601MON/LoginServletEx" method="post">
	   <label>이메일 :
	   <input type="text" name="email" onblur="onloadEvent(this)"><br>
	   </label>
	   <label>암호 : 
	   <input type="text" name="pw" onblur="onloadEvent(this)"><br>
	   </label>
	   <input type="submit" value="로그인">
	   <input type="reset" value="취소">
	</form>
</body>
</html>
