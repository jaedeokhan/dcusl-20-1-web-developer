<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ex2.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <p><a href="ex01_choose.jsp">[ 이전  ]</a><a href="ex03.jsp">[ 다음 ]</a></p>
   <h2>반복문 - 배열 (forEach)</h2>
   <%
      pageContext.setAttribute("nameList", new String[]{"홍길동", "임꺽정", "일지매"});
   %>
   <ul>
      <c:forEach var="name" items="${nameList}">
         <li>${name}</li>
      </c:forEach>
   </ul>
   <h3>속성 사용</h3>
   <ul>
      <c:forEach var="name" items="${nameList}" begin="0" end="1">
         <li>${name}</li>
      </c:forEach>
   </ul>
   <h3>반복문 - ArrayList 객체</h3>
   <%
      ArrayList<String> nameList3 = new ArrayList<String>();
      nameList3.add("홍길동");
      nameList3.add("임꺾정");
      nameList3.add("일지매");
      nameList3.add("음악대장");
      nameList3.add("주먹대장");
      pageContext.setAttribute("nameList3", nameList3);
   %>
   <ul>
      <c:forEach var="name" items="${nameList3}">
         <li>${name}</li>
      </c:forEach>
   </ul>
</body>
</html>