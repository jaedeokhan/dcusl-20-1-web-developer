<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.ZipCodeVO" %>
<%@ page import="java.util.*" %>
<%
	ArrayList<ZipCodeVO> zipCodeList 
			= (ArrayList<ZipCodeVO>)request.getAttribute("zipCodeList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   function winClose(zip, addr1){
	   opener.document.myForm.zip.value = zip;
	   opener.document.myForm.addr1.value = addr1;
	   window.close()
   }
</script>
</head>
<body>
   <%
      if(zipCodeList != null && zipCodeList.size() > 0) {
   %>
	   <%
	      for(int i = 0; i < zipCodeList.size(); i++){
	    	  String addr1 = zipCodeList.get(i).getDou() +
	    			  " " + zipCodeList.get(i).getSi() +
	    			  " " + zipCodeList.get(i).getBunzi() +
	    			  " " + zipCodeList.get(i).getRo();
	   %>
	  <table>
	     <tr>
	        <td><a href="javascript:winClose('<%=zipCodeList.get(i).getZip()%>','<%=addr1%>')"><%= zipCodeList.get(i).getZip()%></a></td>
	        <td><%= addr1 %></td>
	     </tr>
	   <%
      	}	
	   %>
	  </table>   
	
   
   <%
   	  }
      else{
   %>
      <h1>검색할 도로명을 입력하세요.</h1>
      <form action="zipFind">
         <label for="ro">도로명 : </label>
         <input type="text" name="ro" id="ro" value="하양"><br>
         <input type="submit" value="우편번호검색">
      </form>
   <%
      }
   %>
</body>
</html>