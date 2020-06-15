<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  // Object로 반환되서 오니 String으로 다운 캐스팅해야한다.
   String id = (String)session.getAttribute("id");
   
   if (id == null){
	   id = "";
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #LoginFormArea {
      width : 300px;
      height : 150px;
      border : 3px double green;
      margin : auto;
   }
   h1 {
      text-align : center;
   }
   fieldset {
      text-align : center;
      border : none;
   }
   
   input[type="submit"]{
      margin-top : 5px;
   }
</style>
</head>
<body>
<section id="LoginFormArea">
   <form action="Login" method="POST">
      <fieldset>
      <h1>로그인</h1>
      <label for="id">아이디 : </label>
      <input type="text" name="id" id="id" value=<%= id %>><br>
      <label for="passwd">비밀번호 : </label>
      <input type="password" name="passwd" id="passwd">
      <label for="cookieUse">쿠키사용 : </label>
      <input type="checkbox" name="cookieUse" id="cookieUse" value="on">
      <input type="submit" value="로그인">
      </fieldset>
   </form>
</section>

</body>
</html>