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
		
//		OracleMemberDao memberDao = new OracleMemberDao();
//		memberDao.UpdateSelect();
		
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
//		String sql = "UPDATE members SET EMAIL=? PWD=? MNAME=?"
//				+ " CRE_DATE=SYSDATE MOD_DATE=SYSDATE WHERE MMO=?";
//		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MMO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS"
					+ " WHERE MMO=" + request.getParameter("no"));
			
			if (rs.next()) {
				request.setAttribute("member", new Member().setNo(rs.getInt("MMO"))
					   .setEmail(rs.getString("EMAIL"))
					   .setPassword(rs.getString("PWD"))
					   .setName(rs.getString("MNAME"))
					   .setCreateDate(rs.getDate("CRE_DATE"))
				       .setModifiedDate(rs.getDate("MOD_DATE")));
				
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/ch2/MemberUpdateForm.jsp");
			rd.forward(request, response);
			
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, request.getParameter("email"));
//			stmt.setString(2, request.getParameter("password"));
//			stmt.setString(3, request.getParameter("name"));
			
			
		} catch (Exception e) {e.printStackTrace();}
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "UPDATE members SET EMAIL=?, PWD=?, MNAME=?"
				+ " WHERE MMO=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("email"));
			pstmt.setString(2, request.getParameter("password"));
			pstmt.setString(3, request.getParameter("name"));
			pstmt.setString(4,  request.getParameter("no"));
			pstmt.executeUpdate();
			response.sendRedirect("MemberListServlet");
		
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
