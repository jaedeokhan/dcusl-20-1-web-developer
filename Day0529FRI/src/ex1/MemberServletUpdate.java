package ex1;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
			String sql = "SELECT * FROM test WHERE id='" + request.getParameter("id") + "'";
			rs = stmt.executeQuery(sql);
			// Member 객체 생성
			Member test;
			
			if (rs.next()) { 
				test = new Member(rs.getString(1), rs.getString(2),
						rs.getString(3),rs.getString(4));
				out.println("<html><head><title>회원정보</title></head>");
				out.println("<body><h1>회원정보</h1>");
				out.println("<form action='/Day0525MON/MemberServletUpdate' method='post'>");
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
						" onclick='location.href=\"/Day0525MON/MemberServletDelete?id="
						+ test.getId() + "\"'>");
				out.println("<input type='button' value='취소'" + 
						" onclick='location.href=\"/Day0525MON/MemberServletList\"'>");
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
		ResultSet rs = null;
		String msg = "";
		PreparedStatement stmt = null;
//		Statement stmt = null;
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String mail = request.getParameter("mail");
		String job = request.getParameter("jobs");
//		
//		try {
//			stmt = conn.createStatement();
//			String sql = "UPDATE signup SET pw='" + pw + "',"
//										+ " mail='" + mail + "', "
//										+ " programming='" + job + "'"
//										+ " WHERE id='" + id + "'";
//			msg = stmt.executeUpdate(sql) > 0 ? "회원수정 성공!" : "회원수정 실패!";
//			out.println("<center><h2>" + msg + "</h2></center>");
//			out.println("<center><p><a href='/Day0523SAT/MemberServletList'>회원수정 홈페이지로 다시 가고 싶다면?</a></p></center>");
//		}
		
		// PreparedStatement를 사용하는 방법!
		String sql = "UPDATE test SET pw=?, mail=?, jobs=? WHERE id=?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pw);
			stmt.setString(2, mail);
			stmt.setString(3, job);
			stmt.setString(4, id);
			
			msg = stmt.executeUpdate() > 0 ? "회원수정 성공!" : "회원수정 실패!";
			out.println("<center><h2>" + msg + "</h2></center>");
			out.println("<center><p><a href='/Day0525MON/MemberServletList'>회원수정 홈페이지로 다시 가고 싶다면?</a></p></center>");
			
		}
	    catch (SQLException e) {
	    	e.printStackTrace();
	    	System.out.println("[SQL Error : " + e.getMessage() + "]");
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
