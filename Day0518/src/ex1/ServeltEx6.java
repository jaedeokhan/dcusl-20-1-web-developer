package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServeltEx6")
public class ServeltEx6 extends HttpServlet {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public void init(ServletConfig config) throws ServletException {
		
		try {
			String dbURL = "jdbc:mysql://localhost:3306/news";
			String dbID = "root";
			String dbPW = "root";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			System.out.println("Connection Sccuess!!");
		}
		catch(Exception e) {
			
		}
		
	
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			try {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				String sql = "select * from news";
				pstmt = conn.prepareStatement(sql);
				pstmt.addBatch();
				rs = pstmt.executeQuery();
				
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
//				
//				ResultSetMetaData rsmd = rs.getMetaData();
//				int cols = rsmd.getColumnCount();
//				
//				out.println("<center><h2>" + cols + "</h2></center>");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	protected void destory() {
		try {
			// 객체가 없는데 닫을려고 하면 안되니까 rs, stmt, conn 이 있을때만 close() 해라. 
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
