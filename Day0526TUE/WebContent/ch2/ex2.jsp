<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
	// 선언태그
		int a;
		int b;
	%>
	
	<%
	// 설정태그
		a = 10;
		b = 110;
		if ( b / a > 10){
	%>
	<p>양의 값 (첫 번째)</p><br>
	<%--출력태그 --%>
		<%= a + " 양의 값 (두 번째 )" %><br>
		<% out.println("다시 양의 값(세 번째)"); %>
	
	<%
		} else {
			
	%>
	음의 값
	<%
		}
	%>
	
	<%
		
	%>
</body>
</html>


















