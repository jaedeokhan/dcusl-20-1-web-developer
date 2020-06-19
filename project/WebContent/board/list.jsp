<%@ page contentType = "text/html;charset=UTF-8" %>
<%@ page import = "dao.BoardDAO" %>
<%@ page import = "vo.BoardVO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import ="vo.PageVO" %>
<%@ include file="/view/color.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   SimpleDateFormat sdf = 
   				new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
/*
   PageVO pageVO = (PageVO)request.getAttribute("pageVO");
   int count = pageVO.getCount();
   int number = pageVO.getNumber();
   int currentPage = pageVO.getCurrentPage();
   int endPage = pageVO.getEndPage();
   int startPage = pageVO.getStartPage();
   int pageCount = pageVO.getPageCount();
   List<BoardVO> articleList = (List<BoardVO>)request.getAttribute("articleList");
*/ 
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="<%=bodyback_c%>">
<center><b>글목록(전체 글:${pageVO.count})</b>
<table width="700">
<tr>
    <td align="right" bgcolor="<%=value_c%>">
    <a href="writeForm.jsp">글쓰기</a>
    </td>
</table>

<c:if test="${empty articleList}">
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
    <td align="center">
    게시판에 저장된 글이 없습니다.
    </td>
</table>
</c:if>
<c:if test="${not empty articleList}">
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="<%=value_c%>"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
<c:set var="number" value="${pageVO.number}"></c:set>
<c:forEach var="article" items="${articleList}">
   <tr height="30">
    <td align="center"  width="50" >${number}
  
    </td>
    <td  width="250" >
    <c:set var="wid" value="${0}"></c:set>
    <c:if test="${article.re_level > 0}">
       <c:set var="wid" value="${ 5 * article.re_level }"></c:set>
	  <img src="board/images/level.gif" width="${pageVO.wid}" height="16">
	  <img src="board/images/re.gif">
	</c:if>
	<c:if test="${article.re_level == 0}">
	  <img src="board/images/level.gif" width="${pageVO.wid}" height="16">
	</c:if>
	
           
      <a href="boardContent.bo?num=${article.num }&pageNum=${pageVO.currentPage}">
           ${article.subject }</a> 
          <c:if test="${article.readcount > 20}">
         <img src="images/hot.gif" border="0"  height="16"> 
          </c:if>
         </td>
    <td align="center"  width="100"> 
       <a href="mailto:${article.email}">
       ${article.write}</a></td>
    <td align="center"  width="150">
    ${article.reg_date}</td>
    <td align="center"  width="50">${article.readcount}
    </td>
    <td align="center" width="100" >${article.ip}</td>
  </tr>
</c:forEach>
</table>
</c:if>

<c:if test="${pageVO.count > 0 }">
<c:if test="${pageVO.startPage > 10 }">
        <a href="boardlist.jsp?pageNum=${pageVO.startPage - 10}">[이전]</a>
</c:if>
        // startPage부터 endPage 까지 찍는 것이다. 
        <c:forEach var= "i" begin="${pageVO.startPage}" end="${pageVO.endPage}">
        <a href="boardlist.jsp?pageNum=${i}">[${i}]</a>
        </c:forEach>
        }
         
        <c:if test="${pageVO.endPage < pageVO.pageCount }">
        <a href="boardlist.jsp?pageNum=${pageVO.startPage + 10}">[다음]</a>
</c:if>
</c:if>

</center>
</body>
</html>