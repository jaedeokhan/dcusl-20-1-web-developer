<%@page import="vo.DogVO"%>
<%@page import="dao.DogDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   ArrayList<DogVO> dogList = (ArrayList<DogVO>)request.getAttribute("dogList");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #dog_table{
      text-align : center;
   }
</style>
</head>
<body>

<table id="dog_table" border="1"> 
<%
   for (int i = 0; i < dogList.size(); i++){
	   DogVO dog = (DogVO)dogList.get(i);
%>
	   <tr>
	      <td><%= dog.getId()%></td>
	      <td><%= dog.getKind() %></td>
	      <td><%= dog.getPrice() %></td>
	      <td><img src="image/<%= dog.getImage() %>"></td>
	      <td><%= dog.getCountry() %></td>
	      <td><%= dog.getHeight() %></td>
	      <td><%= dog.getWeight() %></td>
	      <td><%= dog.getContent() %></td>
	      <td><%= dog.getReadCount() %></td>
	   </tr>
<%
   }
%>
</table>
</body>
</html>