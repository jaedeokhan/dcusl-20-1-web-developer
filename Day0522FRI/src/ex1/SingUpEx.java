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
















