<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String[] choice = request.getParameterValues("idol");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   table {
      width : 600px;
      margin : auto;
   }
   
</style>
</head>
<body>
   <table>
      <tr>
         <%
            if (choice != null && choice.length > 0){
            	for (int i = 0; i < choice.length; i++){
            		
         %>
         <td>
            <img src="image/<%=choice[i] %>"/>
         </td>
         <% 
            	}
            } else
         %>
      </tr>
   </table>
</body>
</html>