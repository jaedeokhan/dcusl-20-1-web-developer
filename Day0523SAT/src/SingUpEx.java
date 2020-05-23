

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
	
//	public void init(ServletConfig config) throws ServletException {
//
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBAction db = DBAction.getInstance();
		conn = db.getConnection();
		
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
			out.println("<center><p><a href='/Day0523SAT/MemberServletList'>회원가입 목록보기 Click!!</a></p></center>");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		out.close();
		
		String select = "SELECT * FROM signup";
		
//		
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(select);
//			
//			out.println("<center><h2>현재 회원가입한 목록</h2><center>");
//			out.println("<center><table border='1'>");
//			out.println("<tr><th>ID</th>"
//					      + "<th>PW</th>"
//					      + "<th>Email</th>"
//					      + "<th>Jobs</th></tr>");
//			
//			while (rs.next()) {
//				
//				out.println("<tr><td>" + rs.getString(1) + "</td>");
//				out.println("<td>" + rs.getString(2) + "</td>");
//				out.println("<td>" + rs.getString(3) + "</td>");
//				out.println("<td>" + rs.getString(4) + "</td></tr>");
//			}
//			out.println("</table><center>");
//			
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		out.close();
		
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
















