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
	Member member = (Member)session.getAttribute("member");
%>

   <div style="background-color:#00008b; text-align : center;
   		color:#ffffff; height:20px; padding:5px; ">
   		MemberSystem Header Page
	    <%  // Login Sccuess
	    	if (member != null && member.getEmail() != null){
	    	%>
	    	   <span style="float:right;">
	    	      <%= member.getName()%>
	    	      <a style="color:white;" href="/Day0528THU/LogoutServlet">로그아웃</a>
	    	   </span>
	    	<% 
	    	// Login Fail
	    	}else {
	    %>		
	    	   <script type="text/javascript">
	    	      location.href = "/Day0528THU/ch2/LoginForm.jsp"
	    	   </script>
	    <% 	
	    	}
	    	
	    %>
   </div>

</body>
</html>