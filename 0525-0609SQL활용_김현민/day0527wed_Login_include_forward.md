### 2020 0527 WED

### Today
1. JSP
   * include 
   * forward


#### 1. LoginTest.jsp 작성하기.

> LoginTest.jsp

```html
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
```


#### 2. CREATE TABLE MEMBERS
* MEMBERS 테이블을 하나 생성하기.
* <b>MMO 는 MNO 즉 Member Number인데 오타이다.</b>

```sql
SQL> CREATE TABLE MEMBERS(
     MMO NUMBER PRIMARY KEY,
     EMAIL VARCHAR2(40) NOT NULL,
     PWD VARCHAR2(100) NOT NULL,
     MNAME VARCHAR2(50) NOT NULL,
     CRE_DATE DATE DEFAULT SYSDATE NOT NULL,
     MOD_DATE DATE NOT NULL);
```

#### 2.1 현재 id가 가지고 있는 테이블의 목록을 출력하는 방법

```sql
# 현재 아이디가 가지고 있는 테이블의 목록을 출력하는 방법
SQL > SELECT table_name FROM tabs;
```
#### 2.2 CREATE sequence 
http://www.gurubee.net/lecture/1037

* 유일한 값을 생성해주는 오라클 객체이다.
* 시퀀스를 생성하면 기본키와 같이 순차적으로 증가하는 컬러믕ㄹ 자동적으로 생성 할 수 있다.
* 메모리에 Cache되었을 때 시퀀스값의 액세스 효울이 증가한다.
* 시퀀스는 테이블과는 독립적으로 저장되고 생성한다.

```sql
# 시퀀스 생성
SQL> CREATE sequence seq_MMO start with 1 increment BY 1 NOCACHE;

# 하나의 데이터 일단 삽입
SQL> INSERT INTO MEMBERS(MMO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) VALUES(SEQ_MMO.nextVal, '1', '1', '홍길동', SYSDATE, SYSDATE);
```

#### 3. Memeber Java Bean 작성하기.
아래와 같은 방법으로 하는 이유는 나중에 객체를 생성하면,
Member member = new Member();
member.setNo("셋 할값").set~ 이렇게 사용하기 위해서 작성하는 것이다.
 

```java
public Member setNo(int no) {
   this.no = no;
   return this;
}
```

> Member.java

```java
package ex2;

import java.util.Date;

public class Member {
	private int no;
	private String name;
	private String email;
	private String password;
	private Date createDate;
	private Date modifiedDate;
	public int getNo() {
		return no;
	}	
	
	
	
	public Member setNo(int no) {
		this.no = no;
		return this;
	}
	public String getName() {
		return name;
	}
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Member setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}

}
```

#### 4. Forward와 Include 
* Forward 는 페이지를 전환시키는 주 역할인 메소드이다.
* Include는 동적인 페이지를 하나의 페이지로 병합하는 역할을 하는 것이다.
* <%@ page, include, taglib %> 3 가지 방법이 있다. 
* RequestDispatcher rd = request.getRequestDispatcher(path);
   * forward(request, response);
   * include(request, response);
      * request, response를자원을  가지고 다니고, 자원의 전달을 통해서 사용이 편리하다. sendredirect와의 차이점이다.

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
	<form action="/Day0527WED/LoginServletEx" method="post">
	   <label>이메일 :
	   <input type="text" name="email"><br>
	   </label>
	   <label>암호 : 
	   <input type="password" name="pw"><br>
	   </label>
	   <input type="submit" value="로그인">
	   <input type="reset" value="취소">
	</form>
</body>
</html>
```


> LoginServletEx.java

* Member.java 인 JavaBeans를 사용해본다.

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

import ex1.DBAction;

/**
 * Servlet implementation class LoginServletEx
 */
@WebServlet("/LoginServletEx")
public class LoginServletEx extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인터페이스
		RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginForm.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM members WHERE EMAIL=? AND PWD=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("email"));
			pstmt.setString(2, request.getParameter("pw"));
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				response.setContentType("text/html; charset=utf-8");
				member = new Member();
				member.setEmail(rs.getString("EMAIL"));
				member.setName(rs.getString("MNAME"));
				
				request.setAttribute("member", member);
				RequestDispatcher rd =
						request.getRequestDispatcher("/ch2/Login.jsp");
						rd.include(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginFail.jsp");
				rd.forward(request, response);
				
			}
		} catch(SQLException e) {
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
		
	}

}

```

#### 4.1
아래에서 "member" 는 key , member 는 value로 Java의 Map과 유사하다고 생각하면 된다.
* rd.include 로 Login.jsp로 다음 라인을 병합을 하는 것이다.

```java
request.setAttribute("member", member);
RequestDispatcher rd = request.getRequestDispatcher("/ch2/Login.jsp");
rd.include(request, response);
```

> Login.jsp

```html
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
	<%=	member.getName() + "님 로그인 중입니다."%>
	<h1>로그인 성공</h1>
	<p><a href="/Day0527WED/ch2/LoginForm.jsp">로그인 페이지!</a></p>

</body>
</html>
```

#### 4.2 rs.next()가 없으면 즉 DB를 SELECT해서 email, pw가 존재하지 않을때는 LoginFail.jsp 로 forwarding을 수행한다. 
RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginFail.jsp");
rd.forward(request, response);

> LoginFail.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center><h2>없거나, 암호틀림</h2></center>
	<p><a href="/Day0527WED/ch2/LoginForm.jsp">로그인 페이지!</a></p>
</body>								 
</html>
```


