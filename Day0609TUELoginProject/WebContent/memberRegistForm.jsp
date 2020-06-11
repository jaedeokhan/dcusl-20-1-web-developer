<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO" %>

<!DOCTYPE html>
<html>
<style>
   #registFormArea {
      width : 800px;
      margin : auto;
      border : 1px solid blue;
   }
   table {
      width : 780px;
      margin : auto;
   }
   .td_left{
      width : 200px;
      
   }
   .td_right{
      width : 580px;
   }
   h1 {
      text-align : center;
   }
</style>
<script>
   function checkPasswd(){
         // 1. form 태그로 접근하는 방법
	     //var passwd1 = document.myForm.passwd1;
         //alert(passwd1.value);
         
         // 2. id로 바로 접근하는 방법
         var passwd1 = document.getElementById("passwd1");
         var passwd2 = document.getElementById("passwd2");
         var errorMessageArea = document.getElementById("errorMessageArea");
         
         if (passwd1.value != passwd2.value){
        	 errorMessageArea.innerHTML = "<font color='red'>비밀번호가 일치하지 않습니다.</font>";
         }
         else {
        	 errorMessageArea.innerHTML = "";
         }
         
   };
   
   function checkId(){
       var id = document.getElementById("id");
       
       // 윈도우를 띄워서 요청 URL을 준다. 즉 Servlet을 호출한다.
       window.open("idCheck?id=" + id.value, "window1", "width=400, height=300");
   };
   
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section id="registFormArea">
   <h1>회원가입</h1>
   <form action="memberRegist" method="POST" name="myForm">
   <table>
      <tr>
         <td class="td_left">
            <label for="id">아이디 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="id" id="id"/>
            <input type="button" value="아이디중복체크" onclick="checkId()"/>
         </td>
      </tr>
      <tr>
         <td class="td_left">
            <label for="passwd1">비밀번호 : </label>
         </td>
         <td class="td_right">
            <input type="password" name="passwd1" id="passwd1"/>
         </td>
      </tr>
      <tr>
         <td class="td_left">
            <label for="passwd2">비밀번호 확인: </label>
         </td>
         <td class="td_right">
            <input type="password" name="passwd2" id="passwd2" onblur="checkPasswd()"/>
         </td>
      </tr>
      <div id="errorMessageArea"></div>
      </tr>
            <tr>
         <td class="td_left">
            <label for="name">이름 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="name" id="name"/>
         </td>
      </tr>
      </tr>
            <tr>
         <td class="td_left">
            <label for="zip">우편번호 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="zip" id="zip" readonly="readonly"/>
            <input type="button" value="우편번호찾기"/>
         </td>
      </tr>
      </tr>
      <tr>
         <td class="td_left">
            <label for="addr1">기본주소 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="addr1" id="addr1" readonly="readonly"/>
            <input type="button" value="우편번호찾기"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="addr1">상세주소 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="addr2" id="addr2" readonly="readonly"/>
            <input type="button" value="우편번호찾기"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="email">이메일 : </label>
         </td>
         <td class="td_right">
            <input type="email" name="email" id="email"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="age">나이 : </label>
         </td>
         <td class="td_right">
            <input type="number" name="age" id="age" min="1" max="110"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="country">출신지 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="country" id="country"/>
         </td>
      </tr>   
      <tr>
         <td colspan="2">
            <input type="submit" value="회원가입"/>
         </td>
      </tr>           
   </table>
   </form>
</section>
</body>
</html>








