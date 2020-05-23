

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
