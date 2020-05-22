### 2020 0522 FRI
### Today
새로운 프로젝트를 만들면, Tomcat8, MYsql || Oracle 을 추가해주기.

1. Oracle, MySQL 두 개를 한 번에 같은 프로젝트에서 사용을 할려고 했는데, 기존에 있는 Oracle DB 때문인지 무엇인지 제대로 모르겠지만, 다른 프로젝트를 만들어서 WEB-INF > lib 안에 mysql-jdbc-connector-bin.jar 파일을 넣고 동일한 ServeltEx6.java를 작성하니 정상적으로 작동이 된다.
2. Login 페이지 구현해서 DB에 저장=> id, pw 저장
3. SignUp 페이지 구현해서 DB에 저장 => id, pw, mail, program(multiple value)
4. Servlet의 상대 경로 매핑 

#### 1. MySQL jdbc connector 사용해서 ServletEx6.java에서 DB의 데이터를 긁어오기.
Oracle, MySQL을 변경을 할려면 안의 dbURL, Driver 만 설정을 해주면 정상적으로 된다.

> ServeltEx6.java

```java
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEx6
 */
@WebServlet("/ServletEx6")
public class ServletEx6 extends HttpServlet {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public void init(ServletConfig config) throws ServletException {

		try {
			String dbURL = "jdbc:mysql://localhost:3306/news?characterEncoding=UTF-8&serverTimezone=UTC";
			String dbID = "root";
			String dbPW = "root";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			System.out.println("Connection Success!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// service 
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			stmt = conn.createStatement();
			String sql = "select * from new";
			rs = stmt.executeQuery(sql);
			
			out.println("<center><table border='1'>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>");
				out.println("<b>" + rs.getString(1) + "</b>");
				out.println("</td>");
				
				out.println("<td>");
				out.println("<b>" + rs.getString(2) + "</b>");
				out.println("</td>");
				
				out.println("<td>");
				out.println("<b>" + rs.getString(3) + "</b>");
				out.println("</td>");
				
				out.println("<td>");
				out.println("<b>" + rs.getString(4) + "</b>");
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table></center>");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			
			out.println("<center><h2>" + cols + "</h2></center>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 자원 정리
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

#### 1.1 LoginTest => form을 post 방식으로 id, pw 받아서 DB에 insert 하기.
* stmt.executeUpdate(sql); : INSERT를 할 때 사용을 한다. 
  * 세 가지의 정수를 return으로 받는 함수이다. 즉 성공 여부!
  * 0  : CREATE TABLE 을 만들 때 결과이다.
  * 1  : Sccuess
  * -1 : Fail
* executeUpdate를 삼항 연산자로 0보다 크면 회원 가입 성공, 0보다 미만이면 회원 가입 실패로 삼항 연산자를 사용해서 메시지를 String msg에 담아준다.
jdbc 활용하기 => 다른 곳에 안보이는 곳에 만들어 놓고 사용하기. 아니면 컨테이너에 놔두기.

> LoginTest.java

* 기존에 service를 지워줘야지 처음에 init()을 실행하고, DB 연결을 하고 doGet을 사용하고, form 에서 post 방식으로 넘겨야지 정상적으로 작동이 된다.
* form action에서 경로 유의하기!

```java
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTest
 */
@WebServlet("/LoginTest")
public class LoginTest extends HttpServlet {

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원 로그인</title></head>");
			out.println("<body><h1>회원 로그인</h1>");
			out.println("<form action='/Day0522FRI/LoginTest' method='post'>");
			out.println("아이디 : <input type='text' name='id'><br>");
			out.println("암호 : <input type='password' name='pw'><br>");
			out.println("<input type='submit' value='추가'>");
			out.println("<input type='reset' value='취소'>");
			out.println("</form>");
			out.println("</body></html>");
			out.close();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String sql = "INSERT INTO member values ('" + id + "', '" + pw + "')";

			String msg = null;

			try {
				stmt = conn.createStatement();
				// 삼항 연산자를 사용해서 바로 처리하기.
				msg = stmt.executeUpdate(sql) > 0 ? "회원가입 성공!" : "회원가입 실패!";
				out.println(msg);
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

#### 1.2 sinup.html에서 id, pw, mail, programm 4 개의 데이터를 받아서 SignUpEx.java 에서 데이터를 MySQL DB에 INSERT 수행하기.
* 조건
   * program 선택은 checkbox 로 multiple이 가능하다.
   * 그래서, 배열로 받아서 String인 변수에 넣어줘야한다.
   * DB TABLE 생성 => id, pw, mail, program
   * SQL Query 작성
   * 회원 가입 성공/실패 여부 출력해주기.

* 참고
   * html에서 name="" 속성의 값은 request.getParameter()를 통해서 받을 수 있다.
   * multiple의 값은 request.getParameterValues()를 통해서 배열로 받는다.
   * executeUpdate() 를 삼항 연산자를 통해서 0 이상이면 성공 반환, 아니면 실패 반환

> signup.html

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
      <p>아이디 : <input type="text" size="12" maxlengtt="8" name="id"></p>
      <p>비밀번호 : <input type="password" size="12" maxlengtt="8" name="pw"></p>
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
			out.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
```

#### 1.3 Servlet 이 src/ex1 에 있어도 다음과 같이 어노테이션을 해주면 @WebServlet("/SignUpEx")  <b><i>/프로젝트명/SignUpEx</i></b> 이렇게 접근이 가능하다. 


