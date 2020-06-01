<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex2.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Member member = (Member)request.getAttribute("member");
%>
	<jsp:include page="Header.jsp" />
	<h1>회원 수정</h1>
	<form action="/Day0601MON/MemberUpdateServlet" method="post">
	   번호 : <input type="text" name="no" value="<%=member.getNo() %>" readonly /><br>
	   이메일 :<input type="text" name="email" value="<%=member.getEmail() %>"><br>
	   암호 : <input type="password" name="password" value="<%=member.getPassword() %>"><br>
 	   이름 : <input type="text" name="name" value="<%=member.getName() %>"><br>
	   가입일 : <%=member.getCreateDate() %> <br>
	   수정일 : <%=member.getModifiedDate() %> <br>
	 	  <input type="submit" value="수정">
	 	  <input type="button" value="삭제" 
	 	  	onclick='location.href="/Day0601MON/MemberDeleteServlet?no=<%=member.getNo()%>"'>
		  <input type="button" value="취소" onclick='location.href="/Day0601MON/MemberListServlet"'>
	</form>
	<jsp:include page="Footer.jsp" />

</body>
</html>