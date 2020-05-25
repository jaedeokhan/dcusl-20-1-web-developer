

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
				out.println("<tr><td><a href='/Day0523SAT/MemberServletUpdate?id=" 
															  + rs.getString("id") +"'>" 
															  + rs.getString("id") + "</a></td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(4) + "</td></tr>");
			}
			out.println("</table><center>");
			out.println("<center><p><a href='/Day0523SAT/signup.html'>회원가입 페이지로 이동</a></p></center>");
//			out.println("<center><p><a href=''></a></p></center>");
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