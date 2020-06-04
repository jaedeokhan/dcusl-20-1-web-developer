package ex2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/MemberListServlet")
public class MemberListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			OracleMemberDao memberDao = new OracleMemberDao();
			// List는 ArrayList의 부모 객체이다. 그래서 다른 것들을 다형성을 이용해서 사용이 가능하다.
			List<Member> members = memberDao.selectList();
			request.setAttribute("members", members);
			response.setCharacterEncoding("UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/ch2/MemberList.jsp");
			rd.include(request, response);
		}
		catch(Exception e) {e.printStackTrace();}
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
