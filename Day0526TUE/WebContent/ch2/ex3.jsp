<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int mon = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		int date = calendar.get(Calendar.DATE);
		int days = calendar.get(Calendar.DAY_OF_WEEK); 
		String[] yoil = {"일", "월", "화", "수", "목", "금", "토"};
	%>
	<table border="5">
			<tr>
				<% 
					for (int i = 2; i <=9; i++){
				%>
					<td><%= i + "단" %></td>
				<%
					}
				%>
			</tr>
	<%
		for (int i=1; i <=9; i++){
	%>
			<tr>
	<% 
			for (int j=2; j<=9; j++){
	%>	
				<td>
					<%= 
					j + " * " + i + " = " + i * j
					%>
				</td>
	<%
			}
	%>
			</tr>
	<%
		}
	%>
	</table>
	   <p align="center">현재시간</p>
	   <p align="center">
		  <%= year + "년 " + mon + "월 " + day + "일 " 
	   	  + hour + "시 " + min + "분 " + sec + "초 " +
				  "오늘은 (" + yoil[days - 1] + ") 요일입니다." %>
	   </p>
	   <p align="center">
	      <%= date %>
	   </p>
	   
</body>
</html>








