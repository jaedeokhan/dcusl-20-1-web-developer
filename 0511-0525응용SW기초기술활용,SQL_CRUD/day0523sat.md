### 2020 0523 SAT
### Today
1. SingupEx 회원가입 구현해서 DB에 INSERT 하기.
2. Singleton pattern : 하나의 커넥션 객체를 사용하도록 하는 것 => 무분별한 객체의 사용을 막는 것이다.
   * Calender class 
3. Connection Pool(DBCP) : Singleton을 확장한 개념
   * 많은 사용자가 접속하는 사이트의 경우에는 Connection 객체를 미리 생성을 해서 Pool이라는 곳에 미리 쓸만큼만 만들어 놓고, Pooling에서 꺼내서 쓰고,  Pooling에게 다시 재활용을 하는 것이다.
4.
5.


### 참고
* String sql = ""; => 나중에는 Mapper를 통해서 관리를 한다.
* Statement 와 PreparedStatement 의 차이는? Statement 를 상속 받은것은 PreparedStatement이다. PreparedStatement가 보통 편해서 많이 사용한다고 한다.
   *  pstmt 는 조금 더 객체를 편하게 참조할 수 있게 해준다.
* deploy => .jar(java 압축 파일)  , .war (web의 압축 파일)

 
#### 1. signup.html에서 id, pw, email, job 4가지의 form을 SingUpEx.java 에서 getParameter를 통해서 받아서 INSERT, SELECT 처리하기.

> singup.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/Day0522FRI/SingUpEx" method="post">
   <fieldset>
      <legend>회원가입</legend>
      <p>아이디 : <input type="text" size="12" maxlength="8" name="id"></p>
      <p>비밀번호 : <input type="password" size="12" maxlength="8" name="pw"></p>
      <p>메일 수신 여부 :
         <input type="radio" value="y" name="receive">Y
         <input type="radio" value="n" name="receive">N
      </p>
      <p>관심분야 :
        <input type="checkbox" value="HTML" name="chk">HTML
        <input type="checkbox" value="CSS" name="chk">CSS
        <input type="checkbox" value="JavaScript" name="chk">JavaScript
      </p>
      <input type="image" src="" alt="검색">
      <input type="submit" value="전송">
      <input type="reset" value="취소">
      <input type="button" value="확인">
   </fieldset>
</form>
</body>
</html>
```

> SingUpEx.java

```java
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SingUpEx
 */
@WebServlet("/SingUpEx")
public class SingUpEx extends HttpServlet {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public void init(ServletConfig config) throws ServletException {

		try {
			String dbURL = "jdbc:mysql://localhost:3306/bbs?characterEncoding=UTF-8&serverTimezone=UTC";
			String dbID = "root";
			String dbPW = "root";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			System.out.println("Connection Success!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =  response.getWriter();
		
		String msg = "";
		String id = "";
		String pw = "";
		String YesNo = "";
		String programming = "";
		String programmings[];
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		YesNo = request.getParameter("receive");
		programmings = request.getParameterValues("chk");
		
		for (int i = 0; i < programmings.length; i++) {
			// HTML CSS JavaScript
			programming +=  programmings[i];
			if (programmings.length > i + 1) {
				programming += " ";
			}
		}
		
		String sql = "INSERT INTO signup VALUES ('" + id + 
											 "', '" + pw + 
											 "', '" + YesNo + 
											 "', '" + programming + "')";
		try {
			stmt = conn.createStatement();
			// 삼항 연산자를 사용해서 바로 처리하기.
			msg = stmt.executeUpdate(sql) > 0 ? "회원가입 성공!" : "회원가입 실패!";
			out.println("<center><h2>" + msg + "</h2></center>");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		String select = "SELECT * FROM signup";
		
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(select);
			
			out.println("<center><h2>현재 회원가입한 목록</h2><center>");
			out.println("<center><table border='1'>");
			out.println("<tr><th>ID</th>"
					      + "<th>PW</th>"
					      + "<th>Email</th>"
					      + "<th>Jobs</th></tr>");
			
			while (rs.next()) {
				
				out.println("<tr><td>" + rs.getString(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(4) + "</td></tr>");
			}
			out.println("</table><center>");
			out.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void destory() {
		try {
			// 객체가 없는데 닫을려고 하면 안되니까 rs, stmt, conn 이 있을때만 close() 해라. 
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
```

#### 2. Java Singleton pattern
Singleton : 하나의 커넥션 객체를 사용하도록 하는 것 => 무분별한 객체 사용을 막는 것이다.
하나의 conn만을 생성해서 사용을 하기 위해서 작성을 한다.

Abstract class 는 new 해서 사용을 할 수 없다. 그래서 get instance를 사용한다.
getInstance를 통해서 객체 생성을 하는 것이 singleton의 특징이다.


> DBAction.java

```java
import java.sql.Connection;
import java.sql.DriverManager;

public class DBAction {
	
	// Singleton : 하나의 커넥션 객체를 사용하도록 하는 것 => 무분별한 객체 사용을 막는 것이다.
	// 하나의 conn만을 생성해서 사용을 하기 위해서 작성을 한다.
	// 지금의 목적은 connection 객체를 사용하는 것이다!! => 반복되는 객체를 클래스화! 밑에 생성자!
	private static DBAction instance = new DBAction();
	private Connection conn;
	
	// Constructor
	public DBAction() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/bbs?characterEncoding=UTF-8&serverTimezone=UTC";
			String dbID = "root";
			String dbPW = "root";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			System.out.println("Connection Success!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Abstract class 는 new 해서 사용을 할 수 없다. 그래서 get instance를 사용한다.
	// getInstance를 통해서 객체 생성을 하는 것이 singleton의 특징이다.
	public static DBAction getInstance() {
		if (instance == null) {
			instance = new DBAction();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return conn;	
	}
}
```

#### 2.2 SignUpEx.java 에서 두 가지만 하면 코드의 중복과 객체의 무분별한 사용을 줄일 수 있다.

> SignUpEx.java

```java
DBAction db = DBAction.getInstance();
conn = db.getConnection();
```

#### 3. Connection Pool
tomcat 의 container를 사용하는 방법! => tomcat에게 conn 설정을 준다.
tomcat container == engine, WAS(Web Application Server) 등 여러 용어로 사용을 한다.

tomcat configuration

1. Servers => Tomcat v8.5 Server at localhost => context.html, server.html 
   * 두 가지 중 하나를 사용해서 설정을 한다.
2. context.xml 에서 <Context> </Context> 태그 안에서 작성을 한다.
   * Data Source 라고 한다.
3. DBAction.java 에 initialContext 추가
   * JNDI(Java Naming Directory Interface) : 자바 네이밍 서비스 => 이름으로 원격지의(RMI : Remote) 객체를 찾는 것이다.  
4. 

> context.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--><!-- The contents of this file will be loaded for each web application --><Context>

    <!-- Default set of monitored resources. If one of these changes, the    -->
    <!-- web application will be reloaded.                                   -->
    <WatchedResource>WEB-INF/web.xml</WatchedResource>
    <WatchedResource>${catalina.base}/conf/web.xml</WatchedResource>

    <!-- Uncomment this to disable session persistence across Tomcat restarts -->
    <!--
    <Manager pathname="" />
    -->
    <!-- 추가 한 내용!! -->
    <Resource name="jdbc/mysql" auth="Container" type="javax.sql.DataSource"
       username="root"
       password="root"
       driverClassName="com.mysql.jdbc.Driver"
       url="jdbc:mysql://localhost:3306/bbs"
       closeMethod="close"/>

</Context>
```

> DBAction.java

```java
import java.sql.Connection;

import javax.naming.InitialContext;

public class DBAction {
	
	// Singleton : 하나의 커넥션 객체를 사용하도록 하는 것 => 무분별한 객체 사용을 막는 것이다.
	// 하나의 conn만을 생성해서 사용을 하기 위해서 작성을 한다.
	// 지금의 목적은 connection 객체를 사용하는 것이다!! => 반복되는 객체를 클래스화! 밑에 생성자!
	private static DBAction instance = new DBAction();
	private Connection conn;
	
	// Constructor
	public DBAction() {
//		try {
//			String dbURL = "jdbc:mysql://localhost:3306/bbs";
//			String dbID = "root";
//			String dbPW = "root";
//			
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
//			System.out.println("Connection Success!!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			InitialContext initctx = new InitialContext();
			ctx = (Context)initctx.lookup("java:/comp/env");
			ds = (DataSource)ctx.lookup("jdbc/mysql");

		} catch (NamingException e) {
			// TODO: handle exception
		}
	}
	
	// Abstract class 는 new 해서 사용을 할 수 없다. 그래서 get instance를 사용한다.
	// getInstance를 통해서 객체 생성을 하는 것이 singleton의 특징이다.
	public static DBAction getInstance() {
		if (instance == null) {
			instance = new DBAction();
		}
		return instance;
	}
	
	public Connection getConnection() {
		try {	
	  		conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;	
	}
}
```

#### 4. signup.html 에서 form data를 SignUpEx.java에 전달하고 ISNERT 구문을 실행하고, 성공/실패 여부 반환하고 MemberServletList.java의 하이퍼 링크를 제공해서 List에서 DB 회원 가입의 목록을 SELECT한다.

> MemberServeltList.java

```java


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServletList
 */
@WebServlet("/MemberServletList")
public class MemberServletList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from signup";
			rs = stmt.executeQuery(sql);
			
			out.println("<center><h2>현재 회원가입한 목록</h2><center>");
			out.println("<center><table border='1'>");
			out.println("<tr><th>ID</th>"
					      + "<th>PW</th>"
					      + "<th>Email</th>"
					      + "<th>Jobs</th></tr>");
			
			while (rs.next()) {
				
				out.println("<tr><td>" + rs.getString(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(4) + "</td></tr>");
			}
			out.println("</table><center>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 객체가 없는데 닫을려고 하면 안되니까 rs, stmt, conn 이 있을때만 close() 해라. 
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
```

#### 5. MemberServletUpdate.java 구현하기.
* MemberServiceList에서 각각의 id를 클릭하면 id가 get으로 넘어가게 설정하기
* MemberServiceUpdate에서는 id가 넘어오는것을 request.getParaemeter("id")를 받아서 사용하기.
* rs.next() 를 지금은 하나의 값들을 출력하는 것이기에 if문을 사용해서 체크를 해준다.

> MemberServletUpdate.java

* doGET() 메소드에서 회원 정보 수정 form을 생성해서 처리하고, 마지막으로 저장을 하면 다시 MemberServletUpdate 로 metthod='post' 방식으로 doPost() 로 보내서 각각의 id에 해당하는 pw, mail, job을 수정을 한다. 


```java
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServletUpdate
 */
@WebServlet("/MemberServletUpdate")
public class MemberServletUpdate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 회원정보 수정하기.
		response.setContentType("text/html; charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();		
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String id = "";
		

		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM signup WHERE id='" + request.getParameter("id") + "'";
			rs = stmt.executeQuery(sql);
			// Member 객체 생성
			Member test;
			
			if (rs.next()) { 
				test = new Member(rs.getString(1), rs.getString(2),
						rs.getString(3),rs.getString(4));
				out.println("<html><head><title>회원정보</title></head>");
				out.println("<body><h1>회원정보</h1>");
				out.println("<form action='/Day0523SAT/MemberServletUpdate' method='post'>");
				out.println("아이디 : <input type='text' name='id' value='"
						+ test.getId() + "' readonly><br>");
				out.println("암호 : <input type='password' name='pw'" + 
						" value='" + test.getPw() + "'><br>");
				out.println("이메일 : <input type='text' name='mail'" + 
						" value='" + test.getMail() + "'><br>");
				out.println("직업 : <input type='text' name='jobs'" + 
						" value='" + test.getProgramming() + "'><br>");
				out.println("<input type='submit' value='저장'>");
				out.println("<input type='button' value='삭제'" + 
						" onclick='location.href=\"/Day0523SAT/MemberServletDelete?id="
						+ test.getId() + "\"'>");
				out.println("<input type='button' value='취소'" + 
						" onclick='location.href=\"/Day0523SAT/MemberServletList\"'>");
				out.println("</form");
				out.println("</body></html>");
				out.close();
			}
//			
//			if (rs.next()) {
//				out.println("<center><table border='1'>");
//				out.println("<tr><th>ID</th>"
//					      + "<th>PW</th>"
//					      + "<th>Email</th>"
//					      + "<th>Jobs</th></tr>");
//				out.println("<tr><td>" + rs.getString(1) + "</td>");
//				out.println("<td>" + rs.getString(2) + "</td>");
//				out.println("<td>" + rs.getString(3) + "</td>");
//				out.println("<td>" + rs.getString(4) + "</td></tr>");
//				out.println("</center></table>");
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
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
		
		response.setContentType("text/html; charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();		
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String msg = "";
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String mail = request.getParameter("mail");
		String job = request.getParameter("jobs");
		
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE signup SET pw='" + pw + "',"
										+ " mail='" + mail + "', "
										+ " programming='" + job + "'"
										+ " WHERE id='" + id + "'";
			msg = stmt.executeUpdate(sql) > 0 ? "회원수정 성공!" : "회원수정 실패!";
			out.println("<center><h2>" + msg + "</h2></center>");
			out.println("<center><p><a href='/Day0523SAT/MemberServletList'>회원수정 홈페이지로 다시 가고 싶다면?</a></p></center>");
		}
	    catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		out.close();
		

	}

}

```

#### 5.1 객체화하기 위한 코딩!
* DB 데이터에 바로 접근하지 말고, 객체를 만들어서 사용하자.
* Member

> Member.java

```java
public class Member {
	
	private String id;
	private String pw;
	private String mail;
	private String programming;

	public Member(String id, String pw, String mail, String programming){
	   this.id = id;
     	   this.pw = pw;
	   this.mail = mail;
	   this.programming = programming;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getProgramming() {
		return programming;
	}
	public void setProgramming(String programming) {
		this.programming = programming;
	}
	
}
```

#### 5.2 MemberServletDelete.java 구현하기.

> MemberServletDelete.java 

* MemverServletUpdate.java에서 form에서 삭제를 누르면 MemberServletDelete?id= 뒤에 오는 인자를 통해서 DELETE 구문에서 WHERE 조건에 반영한다.


```java


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MemberServletDelete")
public class MemberServletDelete extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원정보 수정하기.
		response.setContentType("text/html; charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();		
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String msg = "";
		String id = request.getParameter("id");
		
		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM signup WHERE id='" + id + "'";
			msg = stmt.executeUpdate(sql) > 0 ? "회원삭제 성공!" : "회원삭제 실패!";
			out.println("<center><h2>" + msg + "</h2></center>");
			out.println("<center><p><a href='/Day0523SAT/MemberServletList'>회원수정 홈페이지로 다시 가고 싶다면?</a></p></center>");
		}
	    catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```

#### 5.3 Statement 와 PreparedStatement의 차이는?
* Statement 

* PreparedStatement 

> Statement

```java
Statement stmt = null;

try {
	stmt = conn.createStatement();
	String sql = "UPDATE signup SET pw='" + pw + "',"
								+ " mail='" + mail + "', "
								+ " programming='" + job + "'"
								+ " WHERE id='" + id + "'";
	msg = stmt.executeUpdate(sql) > 0 ? "회원수정 성공!" : "회원수정 실패!";

```

> PrepareStatement

```java
PreparedStatement stmt = null;

String sql = "UPDATE signup SET pw=?, mail=?, programming=? WHERE id=?";

	try {
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, pw);
		stmt.setString(2, mail);
		stmt.setString(3, job);
		stmt.setString(4, id);
		
		msg = stmt.executeUpdate() > 0 ? "회원수정 성공!" : "회원수정 실패!";
```



