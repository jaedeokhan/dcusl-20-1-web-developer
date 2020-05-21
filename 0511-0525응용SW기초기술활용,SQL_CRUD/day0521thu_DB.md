### 2020 0521 THU 
1. Servlet 의 Life Cycle Method => 객체가 생성, 소멸될 때 ( 즉, 살아 있는 동안의) 사용 가능한 메소드들이다.
   * init()
   * service()
      * doGet()
      * doPost()
   * destory()

#### 1.1 Oracle Home Page 에서 11.2.0.4 JDBC Driver .jar 다운 받아서 프로젝트의 webContent -> WEB-INF -> lib 안에ojdbc6.jar 나두기 
https://www.oracle.com/database/technologies/jdbcdriver-ucp-downloads.html

> ServeltEx5.java

```java
package ex1;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet("/ServletEx5")
public class ServletEx5 extends HttpServlet {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public void init(ServletConfig config) throws ServletException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
```

#### 1.2  위의 방법말고 만들어 놓은 DB로 사용하기.

> ServeltEx5.java

```java
package ex1;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet("/ServletEx5")
public class ServletEx5 extends HttpServlet {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	/*
	 * 교수님이 사용하는 방법
	 * public void init(ServletConfig config) throws ServletException { String
	 * driver = "oracle.jdbc.driver.OracleDriver"; String url =
	 * "jdbc:oracle:thin:@localhost:1521:xe"; try { Class.forName(driver); conn =
	 * DriverManager.getConnection(url, "hr", "hr"); } catch(Exception e) {
	 * e.printStackTrace(); } }
	 */
	public void init(ServletConfig config) throws ServletException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
											  "java",
											  "java");
			System.out.println("Connection Success!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
```
나중에는 DBCP 라는 DB Connection Pool이라는 tomcat에 conn을 놔두고 사용한다.

#### 1.3 Life Cycle Method 3가지의 순서는 init => service => destory
* init() : Class.forName(driver) 실행 후 conn 연결
* service() : sql 쿼리 처리
* destory() : 자원 회수

> ServletEx5.java

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


@WebServlet("/ServletEx5")
public class ServletEx5 extends HttpServlet {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	/*
	 * 교수님이 사용하는 방법
	 * public void init(ServletConfig config) throws ServletException { String
	 * driver = "oracle.jdbc.driver.OracleDriver"; String url =
	 * "jdbc:oracle:thin:@localhost:1521:xe"; try { Class.forName(driver); conn =
	 * DriverManager.getConnection(url, "hr", "hr"); } catch(Exception e) {
	 * e.printStackTrace(); } }
	 */
	public void init(ServletConfig config) throws ServletException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,
					  "java",
					  "java");
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
			String sql = "select * from member";
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




