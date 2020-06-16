<%@ page contentType = "text/html;charset=UTF-8" %>
<%@ page import = "dao.DogDAO" %>
<%@ page import = "vo.BoardVO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import ="vo.PageVO" %>
<%@ include file="/view/color.jsp"%>

<%
   SimpleDateFormat sdf = 
   				new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%
   PageVO pageVO = (PageVO)request.getAttribute("pageVO");
   int count = pageVO.getCount();
   int number = pageVO.getNumber();
   int currentPage = pageVO.getCurrentPage();
   int endPage = pageVO.getEndPage();
   int startPage = pageVO.getStartPage();
   int pageCount = pageVO.getPageCount();
   List<BoardVO> articleList = (List<BoardVO>)request.getAttribute("articleList");
   
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="<%=bodyback_c%>">
<center><b>글목록(전체 글:<%=count%>)</b>
<table width="700">
<tr>
    <td align="right" bgcolor="<%=value_c%>">
    <a href="writeForm.jsp">글쓰기</a>
    </td>
</table>

<%
	if (count == 0) {
%>
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
    <td align="center">
    게시판에 저장된 글이 없습니다.
    </td>
</table>

<%
	} else {
%>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="<%=value_c%>"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
<%
	for (int i = 0 ; i < articleList.size() ; i++) {
          BoardVO article = (BoardVO)articleList.get(i);
%>
   <tr height="30">
    <td align="center"  width="50" > <%=number--%></td>
    <td  width="250" >
	<%
		  // 들여쓰기 이미지의 넓이를 저장하는 변수 
	      int wid=0; 
	      if(article.getRe_level()>0){
	      // wid == level 1이면 공백은 5
	      // wid == level 2이면 공백은 10
	        wid=5*(article.getRe_level());
	%>
	  <img src="board/images/level.gif" width="<%=wid%>" height="16">
	  <img src="board/images/re.gif">
	<%}else{%>
	  <img src="board/images/level.gif" width="<%=wid%>" height="16">
	<%}%>
           
      <a href="boardContent.bo?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
           <%=article.getSubject()%></a> 
          <% if(article.getReadcount()>=20){%>
         <img src="images/hot.gif" border="0"  height="16"><%}%> 
         </td>
    <td align="center"  width="100"> 
       <a href="mailto:<%=article.getEmail()%>">
       <%=article.getWriter()%></a></td>
    <td align="center"  width="150">
    <%= sdf.format(article.getReg_date())%></td>
    <td align="center"  width="50"><%=article.getReadcount()%>
    </td>
    <td align="center" width="100" ><%=article.getIp()%></td>
  </tr>
     <%}%>
</table>
<%}%>

<%
 // 페이징 처리하는 부분이다. 
    if (count > 0) {

        
        // 첫 번째 페이지 그룹이 아니면, [이전] 링크를 걸어준다.
        if (startPage > 10) {
        // 이전 그룹의 첫 페이지로 이동한다.	
        	%>
        <a href="boardlist.jsp?pageNum=<%= startPage - 10 %>">[이전]</a>
<%      }
        // startPage부터 endPage 까지 찍는 것이다. 
        for (int i = startPage ; i <= endPage ; i++) {  %>
        <a href="boardlist.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
        }
        // 마지막 페이지 그룹이 아니면... 즉 다음 그룹이 있다
        // startPage + 10 이라면 ==? 1 + 10 => 11 로 간다. 다음 페이지로 간다.
        if (endPage < pageCount) {  %>
        <a href="boardlist.jsp?pageNum=<%= startPage + 10 %>">[다음]</a>
<%
        }
    }
%>
</center>
</body>
</html>