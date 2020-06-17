<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.DogVO" %>

<%
	DogVO dogVO = (DogVO) request.getAttribute("dogVO");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #dogViewArea {
      width : 800px;
      margin : auto;
   }
   #top {
      height : 150px;
      background : orange;
   }
   
   #main{
   	  margin-top : 10px;
   }
   #left_main{
      height : 300px;
      width : 300px;
      background : powderblue;
      margin-right : 10px;
      float : left;
   }
   #right_main{
   	  height : 300px;
   	  width : 490px;
   	  background : green;
   	  float : left;
   	  font-size : xx-Large;
   }
   #bottom{
   	  height : 150px;
   	  margin-top : 10px;
   	  background : gray;
   	  text-align : center;
   	  font-size : xx-Large;
   }
   h1 {
      text-align : center;
   }
   .bigProductImage{
      width : 300px;
      height : 300px;
   }
   
</style>
</head>
<body>

<section id="dogViewArea">
   <header id="top">
   	 <h1>개정보 : <%= dogVO.getKind() %></h1>
   </header>
   <section id="main">
      <section id="left_main">
         <img src="image/<%= dogVO.getImage()%>" class="bigProductImage">
      </section>
      <section id="right_main">
         
         <b>개가격 :</b><%= dogVO.getPrice()%><br>
         <b>출신지 :</b><%= dogVO.getCountry() %><br>
         <b>신장 : </b><%= dogVO.getHeight() %><br>
         <b>몸무게 : </b><%= dogVO.getWeight() %><br>
         <b>특징 : </b><%= dogVO.getContent() %><br>
         <b>조회수 : </b><%= dogVO.getReadCount() %>
      </section>
   </section>
   <div style="clear:both"></div>
   <footer id="bottom">
      <a href="dogList.dog">쇼핑계속하기</a>
      <a href="dogCartAdd.dog?id=<%= dogVO.getId()%>">장바구니담기</a>
   </footer>
</section>

</body>
</html>