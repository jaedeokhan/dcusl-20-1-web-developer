<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.*" %>
<%@ page import="ex2.Member" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
   h2, h3 {
   	text-align : center;
   }

   .centertable {
   	  margin : 0px auto;
   	  text-align : center;
   }

</style>
</head>
<body>
   <header>
      <jsp:include page="Header.jsp" />
   </header>
	<section>
	<%
		ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
	    
	%>
	<h2>회원목록</h2>
	<h3><a href="/Day0601MON/MemberAddServlet">신규 회원 추가하기</a></h3>
	<table class="centertable" border="1"> 
		<tr><th>MMO</th><th>EMAIL</th><th>PWD</th><th>MNAME</th><th>CRE_DATE</th><th>MOD_DATE</th><th>수정</th></tr>
		<c:forEach var="member" items="${members}">
			<tr>
				<td>
				      ${member.no}
				</td>
				<td>
				      ${member.name}
				</td>
				<td>
				      ${member.email}
				</td>
				<td>
				      ${member.password}
				</td>
				<td>
				      ${member.createDate}
				</td>
				<td>
				      ${member.modifiedDate}
				</td>
				<td>
				   <a href="/Day0601MON/MemberUpdateServlet?no=${member.no}">
				   [ 수정  ]
				   </a> 
				</td>
			</tr>
	    </c:forEach>
	</table>
	</section>
	
	<footer>
	   <jsp:include page="Footer.jsp" />
	</footer>
</body>
</html>