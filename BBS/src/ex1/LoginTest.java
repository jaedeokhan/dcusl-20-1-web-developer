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
			out.println("<form action='/BBS/LoginTest' method='post'>");
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
		
		protected void service(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException{
			   doGet(request, response);
			}

}
