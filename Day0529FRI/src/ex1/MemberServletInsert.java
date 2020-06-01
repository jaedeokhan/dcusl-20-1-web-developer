package ex1;


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
 * Servlet implementation class SingUpEx
 */
@WebServlet("/MemberServletInsert")
public class MemberServletInsert extends HttpServlet {
	
//	public void init(ServletConfig config) throws ServletException {
//
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		
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
		
		String sql = "INSERT INTO test VALUES ('" + id + 
											 "', '" + pw + 
											 "', '" + YesNo + 
											 "', '" + programming + "')";
		try {
			stmt = conn.createStatement();
			// 삼항 연산자를 사용해서 바로 처리하기.
			msg = stmt.executeUpdate(sql) > 0 ? "회원가입 성공!" : "회원가입 실패!";
//			out.println("<center><h2>" + msg + "</h2></center>");
			
			response.sendRedirect("/Day0529FRI/ch1/test.jsp");
//			out.println("<center><p><a href='/Day0525MON/MemberServletList'>회원가입 목록보기 Click!!</a></p></center>");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	  }	
	}

}
















