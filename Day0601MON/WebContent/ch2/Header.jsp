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
</head>
<body>
<% 
	Member member = (Member)session.getAttribute("member");
%>

   <div style="background-color:#00008b; text-align : center;
   		color:#ffffff; height:20px; padding:5px; ">
   		MemberSystem Header Page
   		<!--  
   		<c:choose>
   			 <c:when test="${member != null && member.Email != null}">
	                    	 
	         </c:when>
	         <c:otherwise>
	                 	 
             </c:otherwise>
   		</c:choose>
   		-->
	    <%  // Login Sccuess
	    	if (member != null && member.getEmail() != null){
	    	%>
	    	   <span style="float:right;">
	    	      <%= member.getName()%>
	    	      <a style="color:white;" href="/Day0601MON/LogoutServlet">로그아웃</a>
	    	   </span>
	    	<% 
	    	// Login Fail
	    	}else {
	    %>		
	    	   <script type="text/javascript">
	    	      location.href = "/Day0601MON/ch2/LoginForm.jsp"
	    	   </script>
	    <% 	
	    	}
	    	
	    %>
   </div>

</body>
</html>