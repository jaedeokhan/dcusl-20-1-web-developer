### 2020 0528 THU 

### Today

1. 파일구조
![image](https://user-images.githubusercontent.com/45028904/83145411-b3c83a00-a12f-11ea-9110-7066a45dd940.png)


1. 회원가입 JSP 로 forward, include 를 사용해서 구현하기.
   * reqeust 와 같은 scope 는 session, page, 
   * SQL : ORDER BY 테이블[DESC/ASC]
      * SELECT * FROM 테이블 ORDER BY 칼럼 [ASC/DESC]
   * Collection ArrayList 사용하기.
      * ArrayList<Member> members = new ArrayList<Member>();
   * request 는 다음 페이지까지만 사용이 가능하다.
   * RequestDispatcher rd = reqeust.getRequestDispatcher("url");
   * rd.include(request, response); 위의 url로 include 한다.
   * Servlet의 역할을 점점 더 줄여나가면 MVC 패턴이 완성!

2. MemberAddServlet, MemberUpdateServlet, MemberDeleteServlet


#### 1. MemberListServlet Members 테이블에 전체 속성을 MMO 속성으로 ORDER BY를 ASC 로해서 Collection 에 모든 데이터를 담아서 Client에게 보내준다.
1. Connection, PreparedStatement, ResultSet을 설정해서 sql문 처리해주기.
2. Arraylist 에 각각의 member의 객체를 담아준다.
3. RequestDispatcher 를 사용해서 "/ch2/MemberList.jsp" 로 보내주기.
4. 다음과 같은 구문을 사용해서 include를 해준다.  => rd.include(request, response) 
5. MemberListServlet에서 members 라는 ArrayList 보내는 것을 받아서 MemberList.jsp 에서 getAttribute("members") 를 사용한다.

. Forwarding 과 Include 는 Servlet, JSP 둘 다 사용이 가능하다.
   * Header , Center, Footer 만들어주기.



> MemberListServlet.java

```java
package ex2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/MemberListServlet")
public class MemberListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM MEMBERS ORDER BY MMO ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<Member> members = new ArrayList<Member>();
			while(rs.next()) {
				members.add(new Member()
						.setNo(rs.getInt(1))              // MMO
						.setEmail(rs.getString(2)) 		  // EMAIL
						.setPassword(rs.getString(3))	  // PWD
						.setName(rs.getString(4))		  // MNAME
						.setCreateDate(rs.getDate(5))	  // CRE_DATE
						.setModifiedDate(rs.getDate(6))); // MOD_DATE
			}
			request.setAttribute("members", members);
			RequestDispatcher rd = request.getRequestDispatcher("/ch2/MemberList.jsp");
			rd.include(request, response);
			
		} catch(SQLException e) {e.printStackTrace();}
		
		finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
 			} catch(SQLException e) {e.printStackTrace();}
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}

```

> MemberList.jsp

```html
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
	<h3><a href="/Day0528THU/MemberAddServlet">신규 회원 추가하기</a></h2>
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
			   <a href="/Day0528THU/MemberUpdateServlet?no=<%= member.getNo() %>">
			   [ 수정  ]
			   </a> 
			</td>
			<td>
			   <a href="/Day0528THU/MemberDeleteServlet?no=<%= member.getNo() %>">
			   [ 삭제  ]
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
```

#### 1.1 수정 누르면 MemberUpdateServlet=no<%=member.getNo()%>으로 동적으로 받는 MMO 로 처리하기.

> MemberUpateServlet.java

```java
package ex2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
//		String sql = "UPDATE members SET EMAIL=? PWD=? MNAME=?"
//				+ " CRE_DATE=SYSDATE MOD_DATE=SYSDATE WHERE MMO=?";
//		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MMO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS"
					+ " WHERE MMO=" + request.getParameter("no"));
			
			if (rs.next()) {
				request.setAttribute("member", new Member().setNo(rs.getInt("MMO"))
					   .setEmail(rs.getString("EMAIL"))
					   .setPassword(rs.getString("PWD"))
					   .setName(rs.getString("MNAME"))
					   .setCreateDate(rs.getDate("CRE_DATE"))
				       .setModifiedDate(rs.getDate("MOD_DATE")));
				
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/ch2/MemberUpdateForm.jsp");
			rd.forward(request, response);
			
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, request.getParameter("email"));
//			stmt.setString(2, request.getParameter("password"));
//			stmt.setString(3, request.getParameter("name"));
			
			
		} catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "UPDATE members SET EMAIL=?, PWD=?, MNAME=?"
				+ " WHERE MMO=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("email"));
			pstmt.setString(2, request.getParameter("password"));
			pstmt.setString(3, request.getParameter("name"));
			pstmt.setString(4,  request.getParameter("no"));
			pstmt.executeUpdate();
			response.sendRedirect("MemberListServlet");
		
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

```

> MemberUpdateForm.jsp

```java
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
	Member member = (Member)request.getAttribute("member");
%>
	<jsp:include page="Header.jsp" />
	<h1>회원 수정</h1>
	<form action="/Day0528THU/MemberUpdateServlet" method="post">
	   번호 : <input type="text" name="no" value="<%=member.getNo() %>" readonly /><br>
	   이메일 :<input type="text" name="email" value="<%=member.getEmail() %>"><br>
	   암호 : <input type="password" name="password" value="<%=member.getPassword() %>"><br>
 	   이름 : <input type="text" name="name" value="<%=member.getName() %>"><br>
	   가입일 : <%=member.getCreateDate() %> <br>
	   수정일 : <%=member.getModifiedDate() %> <br>
	 	  <input type="submit" value="수정">
	 	  <input type="button" value="삭제" 
	 	  	onclick='location.href="/Day0528THU/MemberDeleteServlet?no=<%=member.getNo()%>"'>
		  <input type="button" value="취소" onclick='location.href="/Day0528THU/MemberListServlet"'>
	</form>
	<jsp:include page="Footer.jsp" />

</body>
</html>
```


#### 2. SQL ORDER BY 사용

```sql
# 오름차순 정렬 : 낮은 수 -> 높은 수
SQL> SELECT * FROM members ORDER BY MMO ASC;

# 내림차순 정렬 : 높은 수 -> 낮은 수
SQL> SELECT * FROM members ORDER BY MMO DESC;
```

#### 3.


#### 4.

