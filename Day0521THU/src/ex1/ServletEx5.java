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
