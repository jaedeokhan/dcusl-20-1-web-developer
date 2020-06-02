package ex2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ex2.DBAction;

/**
 * Servlet implementation class LoginServletEx
 */
@WebServlet("/LoginServletEx")
public class LoginServletEx extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginForm.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
	
			Member member = new OracleMemberDao().exist(request.getParameter("email"), request.getParameter("pw"));
			if (member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
//				session.setMaxInactiveInterval(10);
				
				
				response.sendRedirect("MemberListServlet");
				
				
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginFail.jsp");
				rd.forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
