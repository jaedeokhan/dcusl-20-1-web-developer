package ex2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberAddServlet
 */
@WebServlet("/MemberAddServlet")
public class MemberAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/ch2/MemberForm.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		OracleMemberDao memberDao = new OracleMemberDao();
		memberDao.Insert(new Member().setEmail(request.getParameter("email"))
				.setName(request.getParameter("pw"))
				.setPassword(request.getParameter("name")));
		
		response.sendRedirect("MemberListServlet");
		
//		Member member = new OracleMemberDao()
//		.Insert(request.getParameter("name"), request.getParameter("email"), request.getParameter("pw"));


		//request.setCharacterEncoding("UTF-8");
		//Connection conn = DBAction.getInstance().getConnection();
		//PreparedStatement pstmt = null;
		//ResultSet rs = null;
		//String sql = "INSERT INTO members(MMO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) "
		//		+ "VALUES(SEQ_MMO.nextVal, ?, ?, ?, SYSDATE, SYSDATE)";
		//
		//try {
		//	pstmt = conn.prepareStatement(sql);
		//	pstmt.setString(1, request.getParameter("email"));
		//	pstmt.setString(2, request.getParameter("password"));
		//	pstmt.setString(3, request.getParameter("name"));
		//	pstmt.executeUpdate();
		//	response.sendRedirect("MemberListServlet");
		//	
		//} catch (SQLException e) {
		//	e.printStackTrace();
		//} finally {
		//	try {
		//		if (rs != null) rs.close();
		//		if (pstmt != null) pstmt.close();
		//		if (conn != null) conn.close();
		//	} catch(SQLException e) {
		//		e.printStackTrace();
		//	}
		//}
	}
	

}
