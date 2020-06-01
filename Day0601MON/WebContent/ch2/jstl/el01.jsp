<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL(Expression Language)</title>
<style type="text/css"> 
   body {
   	font-size : small;
   }
   table{
  	border : thin solid gray;
  	border-collapse : collpase;
   }
   
   td, th {
   	border : thin dotted gray;
   	padding : 2px;
   	text-align : center;
   }
   
   th {
   	background-color : lightgray;
   }
   
   pre {
   	font-size : 90%;
   }
   
</style>
</head>
<body>
    <h2>EL - 리터럴 표현식</h2>
	<table border="1">
	  <tr>
	  	<th>데이터 형</th>
	  	<th>EL 코드</th>
	  	<th>실행 결과</th>
	  </tr>
	  <tr>
	  	<td>문자열</td>
	  	<td>\${"test"}</td>
	  	<td>${"test"}</td>
	  </tr>
	  <tr>
	  	<td>문자열</td>
	  	<td>\${'test'}</td>
	  	<td>${'test'}</td>
	  </tr>
	  <tr>
	  	<td>정수</td>
	  	<td>\${20}</td>
	  	<td>${20}</td>
	  </tr>
	  <tr>
	  	<td>부동 소수점</td>
	  	<td>\${3.14}</td>
	  	<td>${3.14}</td>
	  </tr>
	  <tr>
	  	<td>Boolean</td>
	  	<td>\${true}</td>
	  	<td>${true}</td>
	  </tr>
	  	<tr>
	  	<td>Null</td>
	  	<td>\${null}</td>
	  	<td>${null}</td>
	  </tr>
	</table>
	<p><a href="el02.jsp">[다음]</a></p>
</body>
</html>