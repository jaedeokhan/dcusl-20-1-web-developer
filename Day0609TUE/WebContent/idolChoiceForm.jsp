<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #idolChoiceArea {
      width : 400px;
      height : 150px;
      border : 1px solid red; 
      margin : auto; 
   }
   h1 {
      text-align : center;
   }
   fieldset {
      text-align : center;
      border : none;
   }
</style>
</head>
<body>

<section id="idolChoiceArea">
   <h1>좋아하는 아이돌을 선택하세요.</h1>
   <form action="idolChoicePrint.jsp" method="POST">
      <fieldset>
          <label for="idol1">아이돌 : </label><br>
	      <input type="checkbox" name="idol" id="idol1" value="tagi.jpg"/>서태지
	      <input type="checkbox" name="idol" id="idol2" value="choi.jpg"/>최민수
	      <input type="checkbox" name="idol" id="idol3" value="jeni.jpg"/>제니
	      <input type="checkbox" name="idol" id="idol4" value="park.jpg"/>박서준<br>
	      <input type="submit" value="선택"/>
      </fieldset>
   </form>
</section>
</body>
</html>