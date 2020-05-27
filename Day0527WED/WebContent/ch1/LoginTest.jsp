<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex1.Member"%>
<%@ page import="ex1.DBAction" %>
<%@ page import="java.sql.SQLException"%> 
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String msg = "";

	if (id != null && pw != null){
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM test WHERE id =? AND PW=?");
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		ResultSet rs = pstmt.executeQuery();
		try {
			if(rs.next()){
				%>
					<center><h2><%= id %> 님 로그인중.</h2></center>
			<%
			}
			else {
				%>
					<center><h2><%= id %> 없거나, 암호틀림</h2></center>
					<center><a href="/Day0527WED/ch1/LoginTest.jsp">로그인 페이지</a></center>
				<%
			}
		}
		catch(SQLException e){ e.printStackTrace();}
		finally {
			try {
				// 객체가 없는데 닫을려고 하면 안되니까 rs, stmt, conn 이 있을때만 close() 해라. 
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}else {
%>

<body>
	<h1>로그인</h1>
	<form action="/Day0527WED/ch1/LoginTest.jsp" method="post">
	   <label>id :
	   <input type="text" name="id"><br>
	   </label>
	   <label>pw : 
	   <input type="password" name="pw"><br>
	   </label>
	   <input type="submit" value="로그인">
	   <input type="reset" value="취소">
	</form>

<% } %>	
</body>
</html>