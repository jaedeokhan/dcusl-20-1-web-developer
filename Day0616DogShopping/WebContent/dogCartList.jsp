<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.CartVO" %>
<%@ page import="java.util.*" %> 
 
<%
   ArrayList<CartVO> cartList = (ArrayList<CartVO>)request.getAttribute("cartList");
   int totalMoney = (int)request.getAttribute("totalMoney");
   
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   function checkAll(){
	   var delete1 = document.cartForm.delete1;
	   var allCheck = document.cartForm.allCheck;
	   
	   // if 문으로 allCheck 체크박스가 선택되었으면 delete1의 모든 체크 박스를 선택!
	   // else 문으로 allCheck 체크박스가 해제되었으면 delete1의 모든 체크 박스 해제
	   if (delete1.length == undefined){ // 단일 객체이면!
		   delete1.checked = allCheck.checked;
	      
	   }
	   else{
		  for (i = 0; i < delete1.length; i++){
			  delete1[i].checked = allCheck.checked;
		  }
	   }
   };
   
   function checkQty(id, qty){
	   if (qty > 1 ){
		  location.href = "dogCartQtyDown2.dog?id=" + id;   
	   }
   }
   
</script>
<style>
   #cartListArea{
      width : 600px;
      margin : auto;
      border : 1px solid red;
   }
   h1{
      text-align : center;
   }
   table{
      width : 580px;
      margin : auto;
      text-align : center;
      border : 1px solid blue;
   }
   .productImage{
   	  width : 150px;
      height : 150px;
   }
   .text-title {
      font-weight : bold;
   }
   .upDownImage{
      width : 15px;
      height : 15px;
   }
</style>
</head>
<body>

   <section id="cartListArea">
<%
         if (cartList == null || cartList.size() == 0){
%>
	      <h1>현재 장바구니에 담긴 상품이 없습니다.</h1>
<%     
         }
         else{
%>
	   <form action="dogCartRemove.dog" method="post" name="cartForm">
	   <h1>장바구니 </h1>
	   <h1> <a href="dogList.dog">쇼핑 계속하기</a></h1>
	   <table border="1">
	       <tr>
	          <td colspan="6" style="text-align : right">
	             <input type="submit" value="삭제"/>
	          </td>
	       </tr>
	       <tr class="text-title">
	          <td>번호</td>
	          <td>상품명 </td>
	          <td>이미지</td>
	          <td>가격</td>
	          <td>수량 </td>
	          <td><input type="checkbox" name="allCheck"
	           id="allCheck" onClick="checkAll()"></td>
	       </tr>
	   
<% 
			int num = 1;
	        for (int i = 0; i < cartList.size(); i++){
%>		    
            <tr>
               <td><%= num++ %></td>
               <td><%= cartList.get(i).getKind() %></td>
               <td>
                  <img src="image/<%=cartList.get(i).getImage()%>" class="productImage">
               </td>
               <td><%= cartList.get(i).getPrice() %></td>
			   <td>
			       <a href="dogCartQtyUp.dog?id=<%= cartList.get(i).getId()%>">
			          <img src="image/up.jpg" class="upDownImage">
			       </a><br>
			          <%= cartList.get(i).getQty() %><br>
			       <a href="javascript:checkQty('<%= cartList.get(i).getId() %>',<%=cartList.get(i).getQty()%>)">
				      <img src="image/down.jpg" class="upDownImage">0619
			       </a>   
			       <a href="dogCartQtyDown.dog?id=<%= cartList.get(i).getId() %>">
				      <img src="image/down.jpg" class="upDownImage">
			       </a>
			   </td>
			   <td>
			       <input type="checkbox" name="delete1" 
			        id="delete1" value="<%=cartList.get(i).getId()%>">
			   </td>
		    </tr>
   <%	  
		  }
		%>
		    <tr class="tr-totalmoney">
		       <td colspan="5" style="text-align:right; font-size: xx-large;">
		          	총 금액 : <%= totalMoney %>원 <br>
		          	총 수량 : <%= cartList.size() %>
		       </td>
		    </tr>
<% 		   
	   }
   
%>
      </table>
      </form>
   </section>
</body>
</html>