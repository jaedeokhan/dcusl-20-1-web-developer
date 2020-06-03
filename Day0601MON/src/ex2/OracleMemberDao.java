package ex2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OracleMemberDao {

	public Member exist(String email, String pw) throws Exception{
		
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MNAME, EMAIL FROM MEMBERS WHERE EMAIL=? AND PWD=?";		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  email);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			Member member = null;
			
			if (rs.next()) {
				return member = new Member()
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"));
			}
			else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Member> selectList() throws Exception{
		
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM MEMBERS ORDER BY MMO ASC";
		ArrayList<Member> members = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			members = new ArrayList<Member>();
			
			while(rs.next()) {
				members.add(new Member()
				.setNo(rs.getInt(1))              // MMO
				.setEmail(rs.getString(2)) 		  // EMAIL
				.setPassword(rs.getString(3))	  // PWD
				.setName(rs.getString(4))		  // MNAME
				.setCreateDate(rs.getDate(5))	  // CRE_DATE
				.setModifiedDate(rs.getDate(6))); // MOD_DATE
			}
			
			return members;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		
		return null;
	}

		

	public void Insert(Member member) {
		
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO members(MMO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) "
				+ "VALUES(SEQ_MMO.nextVal, ?, ?, ?, SYSDATE, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
