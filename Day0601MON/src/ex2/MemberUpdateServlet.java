package ex2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			request.setCharacterEncoding("UTF-8");
			OracleMemberDao memberDao = new OracleMemberDao();
			Member member = memberDao.UpdateSelect(request.getParameter("no"));
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/ch2/MemberUpdateForm.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {e.printStackTrace();}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		memberDao.Insert(new Member().setEmail(request.getParameter("email"))
//				.setName(request.getParameter("pw"))
//				.setPassword(request.getParameter("name")));
		
		try {
		
			OracleMemberDao memberDao = new OracleMemberDao();
			memberDao.Update(new Member().setNo(Integer.parseInt(request.getParameter("no")))
					.setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("pw"))
					.setName(request.getParameter("name")));
			
			response.sendRedirect("MemberListServlet");
		}
		catch (Exception e) {e.printStackTrace();}
		
	}

}
