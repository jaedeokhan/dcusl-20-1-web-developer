<%@page import="java.sql.Array"%>
<%@page import="vo.DogVO"%>
<%@page import="dao.DogDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   ArrayList<DogVO> dogList = (ArrayList<DogVO>)request.getAttribute("dogList");
   ArrayList<String> todayImageList = (ArrayList<String>)request.getAttribute("todayImageList");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #dog_table{
      width : 600px;
      margin : auto;
   }
   h1 {
   	  text-align : center;
   }
   .productImage{
      width : 150px;
      height : 150px;
   }
   .todayImage{
      width : 100px;
      height: 100px;
   }
</style>
</head>
<body>
<%
   if (dogList == null || dogList.size() == 0){
%>
	<h1>등록된 개의 정보가 없습니다.</h1>
<%
   }
   else {
%>
	<h1>강아지 상품 목록</h1>
	<table id="dog_table" border="1"> 
    <tr>  
<% 
	   for (int i = 0; i < dogList.size(); i++){
%>		 
			<td>
			   <a href="dogView.dog?id=<%=dogList.get(i).getId()%>">
			      <img src="image/<%= dogList.get(i).getImage() %>" class="productImage">
			   </a><br>
			   상품명: <%= dogList.get(i).getKind() %><br>
			   가격: <%= dogList.get(i).getPrice() %><br>

			</td>
		<%
		  if (i != 0 && (i + 1) % 4 == 0){
		%>
		</tr>
		<%
		  }
		%>
<% 		   
	   }
   }
%>
      </table>
   <%
      if (todayImageList != null && todayImageList.size() > 0){
    	  
   %>
   <section id="todayImageArea">
   <h1>오늘 본 상품 리스트</h1>
      <table id="dog_table" border="1">
         <tr>
             <%
               for (int i = 0; i < todayImageList.size(); i++){
             %>
             <td>
                <img src="image/<%= todayImageList.get(i) %>" class="todayImage">
             </td>
             <%
               }
             %>
         </tr>
      </table>
   </section>
   <%
      }
   %>
</body>
</html>