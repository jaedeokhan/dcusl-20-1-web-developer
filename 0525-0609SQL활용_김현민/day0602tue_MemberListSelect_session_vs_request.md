### 2020 0602 TUE 

### Today
1. MemberUpdateServer 
2. el => HashMap, List
3. js => alert, confirm

#### 1. session.setAttribute("meber", member); 와 같이 session을 set을 해주는 경우에는 처음에 로그인할때 생성을 해준다고 생각!!
* MemberListServlet.java 에서는 리스트를 출력을 해주기 위해서는 request.setAttribute("members", members); 로 넘겨줘야 한다. 왜냐하면 session 으로는 로그인을 할때 생성을 해주기 때문에 request를 사용한다.
* MemberList.jsp 에서도 마찬가지로 RequsetDispathcer rd  = request.getRequestcher("/ch2/MemberList.jsp"); && rd.inlclude(request, response); include륾 다음과 같은 .jsp 파일에 해주면 jsp에서 파일을 읽을때는 마찬가지로 request.setAttribute("member")로 받는다!!


#### 1.1 LoginForm.jsp에서 post 방식으로 LoginServletEx.java로 email, pw를 보낸다.(로그인)

> LoginForm.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="/Day0601MON/LoginServletEx" method="post">
	   <label>이메일 :
	   <input type="text" name="email"><br>
	   </label>
	   <label>암호 : 
	   <input type="text" name="pw"><br>
	   </label>
	   <input type="submit" value="로그인">
	   <input type="reset" value="취소">
	</form>
</body>
</html>

```

#### 1.2 LoginServletEx.java 에서는 Member member = new OracleMemberDao().exist(request.getParameter("email"), request.getParameter("pw")); 로 email, pw를 인자로 날려줘서 DAO 에서 데이터베이스 SQL 구문을 처리한다. 그리고 Member에 해당하는 MNAME, EMAIL을 담아서 return 해준다.

> LoginServletEx.java 

```java
package ex2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ex2.DBAction;

/**
 * Servlet implementation class LoginServletEx
 */
@WebServlet("/LoginServletEx")
public class LoginServletEx extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginForm.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
	
			Member member = new OracleMemberDao().exist(request.getParameter("email"), request.getParameter("pw"));
			if (member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
//				session.setMaxInactiveInterval(10);
				
				
				response.sendRedirect("MemberListServlet");
				
				
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginFail.jsp");
				rd.forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
```

#### 1.3 LoginServletEx.java 에서 바로 redirect로 MemberListServlet.java 로 간다. 여기서는 OracleMemberDao memberDao = new OracleMemberDao();로 객체를 만들어 주고, List<Member> members = memberDao.selectList(); OracleMemberDao에서 selectList 메소드를 바로 members가 받는다.

> MemberListServlet.java

```java
package ex2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/MemberListServlet")
public class MemberListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			OracleMemberDao memberDao = new OracleMemberDao();
			// List는 ArrayList의 부모 객체이다. 그래서 다른 것들을 다형성을 이용해서 사용이 가능하다.
			List<Member> members = memberDao.selectList();
			request.setAttribute("members", members);
			
			response.setCharacterEncoding("UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/ch2/MemberList.jsp");
			rd.include(request, response);
		}
		catch(Exception e) {e.printStackTrace();}
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
```

> OracleMemberDao.java

```java
package ex2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OracleMemberDao {

	public Member exist(String email, String pw) throws Exception{
		
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MNAME, EMAIL FROM MEMBERS WHERE EMAIL=? AND PWD=?";		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  email);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			Member member = null;
			
			if (rs.next()) {
				return member = new Member()
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"));
			}
			else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Member> selectList() throws Exception{
		
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM MEMBERS ORDER BY MMO ASC";
		ArrayList<Member> members = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			members = new ArrayList<Member>();
			
			while(rs.next()) {
				members.add(new Member()
				.setNo(rs.getInt(1))              // MMO
				.setEmail(rs.getString(2)) 		  // EMAIL
				.setPassword(rs.getString(3))	  // PWD
				.setName(rs.getString(4))		  // MNAME
				.setCreateDate(rs.getDate(5))	  // CRE_DATE
				.setModifiedDate(rs.getDate(6))); // MOD_DATE
			}
			
			return members;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		
		return null;
	}
	
}
```

#### 1.4 MemberListServlet.java는 MemberList.jsp로 include한다. MemberList.jsp는 Header, Footer.jsp 를 include한다. 

> MemberList.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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
   	  text-align : center;
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
	<h3><a href="/Day0601MON/MemberAddServlet">신규 회원 추가하기</a></h3>
	<table class="centertable" border="1"> 

		<tr><th>MMO</th><th>EMAIL</th><th>PWD</th><th>MNAME</th><th>CRE_DATE</th><th>MOD_DATE</th><th>수정</th></tr>
	<%
		for (Member member : members) {
	%>	
		<tr>
			<td>
			      <%= member.getNo() %>
			</td>
			<td>
			      <%= member.getName() %>
			</td>
			<td>
			   <%= member.getEmail() %>
			</td>
			<td>
			   <%= member.getPassword() %>
			</td>
			<td>
			      <%= member.getCreateDate()%>
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
```



#### 2.

> el02.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList"%>
<%@ page import="ex2.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
   <p><a href="el01.jsp">[이전]</a><a href="el03.jsp">[다음]</a></p>
   <h2>EL - 값 꺼내기</h2>
   <%
     // 데이터 준비
     pageContext.setAttribute("scores", new int[]{90, 80, 70, 100});
   
   	 List<String> nameList = new LinkedList<String>();
   	 nameList.add("홍길동");
   	 nameList.add("임꺽정");
   	 nameList.add("일지매");
   	 pageContext.setAttribute("nameList", nameList);
   	 
   	 Map<String, String> map = new HashMap<String, String>();
   	 map.put("s01", "홍길동");
	 map.put("s02", "임꺽정");
	 map.put("s03", "일지매");
	 pageContext.setAttribute("map", map);
	 
	 pageContext.setAttribute("member", new Member().setNo(100).setName("홍길동").setEmail("hong@test.com"));
   %>
	<table>
   <tr><th>대상</th><th>EL 코드</th><th>설명</th></tr>
   <tr>
   <td>배열 </td><td>\${myArray[1] }</td>
      <td>배열에서 해당 인덱스의 값을 꺼낸다.<br>
      <pre>
      [자바코드]
      pageContext.setAttribute("scores", new int[] {90,80,70,100});
      [실행결과]
      \${scores[2]} = ${scores[2]}
      </pre>
   </td>   
   </tr>
   <tr>
      <td>리스트</td><td>\${myList[2]}</td>
      <td> List 객체에서 인덱스로 지정된 항목의 값을 꺼낸다.
         <pre>
            [자바코드]
            List nameList = new LinkedList();
            nameList.add("홍길동");
            nameList.add("임꺽정");
            nameList.add("일지매");
            pageContext.setAttribute("nameList", nameList);
            
            [실행결과]
         \${nameList[1]} = ${nameList[1]}
         </pre>
      
      </td>
      </tr>
   <tr>
      <td>맵</td><td>\${myMap.keyName}</td>
      <td>Map 객체에서 키에 해당하는 값을 꺼낸다.
      <pre>
         [자바코드]
         Map map = new HashMap();
         map.put("s01", "홍길동");
         map.put("s02", "임꺽정");
         map.put("s03", "일지매");
         pageContext.setAttribute("map", map);
         
         [실행 결과]
         \${map.s02} = ${map.s02}
      </pre>
      </td>
      </tr>
      <tr>
         <td>자바빈</td><td>\${myObj.propName}</td>
         <td>자바 객체에서 프로페티의 값을 꺼낸다.
            <pre>
            [자바코드]
            pageContext.setAttritub("member",
               new Member()
               .setNo(100)
               .setName("홍길동")
               .setEmail("hong@test.com"));
            
            [실행결과]   
            \${member.email} = ${member.email}         
            </pre>
            
         </td>
         </tr>
      </table>
	
	
</body>
</html>
```

#### 3. 




