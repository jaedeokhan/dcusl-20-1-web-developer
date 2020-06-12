<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO" %>
<%
	boolean idExist = (boolean)request.getAttribute("idExist");
    String id = (String)request.getAttribute("id");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   function winClose(){
	   // 아이디 중복 체크한 값을 가지고 간다.
	   opener.document.myForm.id.value = "<%=id%>";
	   window.close();
	   // self.close();
	   // close();
   };
</script>
</head>
<body>
    <%
    // 해당 아이디가 DB에서 이미 다른 회원이 사용중이라면?
       if( idExist ){
    %>
       <h1><%= id %> 해당 아이디는 사용할 수 없습니다.</h1>
       <form action="IdCheck">
          <label for="id">아이디 : </label>
          <input type="text" name="id" id="id"/><br>
          <input type="submit" value="아이디중복체크"/>
       </form>
    <%
    // 다른 회원들이 해당 아이디를 사용하지 않는다면?
       }else{
    %>
      <h1><%= id %> 해당 아이디는 사용할 수 있습니다.</h1>
      <a href="javascript:winClose()">닫기</a>
    <%
       }
    %>
</body>
</html>