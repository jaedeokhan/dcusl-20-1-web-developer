<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
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
	<h3><a href="/Day0601MON/MemberAddServlet">신규 회원 추가하기</a></h2>
	<table class="centertable" border="1"> 
	<%
		for (Member member : members) {
	%>	
		<tr>
			<td>
			      <%= member.getNo() %>
			</td>
			<td>
			      <%= member.getName() %>
			   </a>
			</td>
			<td>
			   <%= member.getEmail() %>
			</td>
			<td>
			   <%= member.getPassword() %>
			</td>
			<td>
			      <%= member.getCreateDate()%>
			   </a>
			</td>
			<td>
			   <%= member.getModifiedDate() %>
			</td>
			<td>
			   <a href="/Day0601MON/MemberUpdateServlet?no=<%= member.getNo() %>">
			   [ 수정  ]
			   </a> 
			</td>
		</tr>
	<% 		
		}
	%>
	</table>
	</section>
	
	<footer>
	   <jsp:include page="Footer.jsp" />
	</footer>
</body>
</html>