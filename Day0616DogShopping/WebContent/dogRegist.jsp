<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</script>
</head>
<body>

<section id="registFormArea">
   <h1>회원가입</h1>
   <form action="dogRegist.dog" method="POST" name="myForm" encType="multipart/form-data">
   <table>
      <tr>
         <td class="td_left">
            <label for="kind">품종 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="kind" id="kind"/>
         </td>
      </tr>
            <tr>
         <td class="td_left">
            <label for="price">가격 : </label>
         </td>
         <td class="td_right">
            <input type="number" name="price" id="price"/>
         </td>
      </tr>
      <tr>
         <td class="td_left">
            <label for="image">이미지 : </label>
         </td>
         <td class="td_right">
            <input type="file" name="image" id="image"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="country">원산지 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="country" id="country"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="height">신장 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="height" id="height"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="weight">체중 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="weight" id="weight"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="content">상품설명 : </label>
         </td>
         <td class="td_right">
            <textarea rows="20" cols="40" name="content" id="content">
            </textarea>
         </td>
      </tr>   
      <tr>
         <td colspan="2" style="text-align : center">
            <input type="submit" value="회원가입"/>
         </td>
      </tr>
      <div id="errorMessageArea"></div>           
   </table>
   </form>
</section>
</body>
</html>