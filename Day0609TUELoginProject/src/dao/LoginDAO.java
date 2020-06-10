package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MemberVO;
public class LoginDAO {
	
	Connection con;
	private static LoginDAO instance;
	
	private LoginDAO() {
		
	}
	
	public static LoginDAO getInstance() {
		if (instance == null) {
			instance = new LoginDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public MemberVO selectLoginMember(String id, String passwd) {
		MemberVO loginMember = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE id = ? AND passwd = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 조건에 부합하는 내 코드가 있다면?
				loginMember = new MemberVO();
				loginMember.setAddr1(rs.getString("addr1"));
				loginMember.setAddr2(rs.getString("addr2"));
				loginMember.setAge(rs.getInt("age"));
				loginMember.setCountry(rs.getString("country"));
				loginMember.setEmail(rs.getString("email"));
				loginMember.setId(rs.getString("id"));
				loginMember.setName(rs.getString("name"));
				loginMember.setPasswd(rs.getString("passwd"));
				loginMember.setZip(rs.getString("zip"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			close(rs);
			close(pstmt);
		}
		return loginMember;
	}
	
}
