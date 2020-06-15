package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberVO;
import vo.ZipCodeVO;
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

	public String selectId(String id) {
		// TODO Auto-generated method stub
		String dbId = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbId = rs.getString("id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			close(rs);
			close(pstmt);
		}
		
		return dbId;
	}

	public ArrayList<ZipCodeVO> selectZipCodeList(String ro) {
		
		ArrayList<ZipCodeVO> zipCodeList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM zipcode WHERE ro LIKE ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + ro + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 조건에 부합하는 내 코드가 있다면?
//				   private int num;
//				   private String zip;
//				   private String dou;
//				   private String si;
//				   private String ro;
//				   private String bunzi;
				zipCodeList = new ArrayList<ZipCodeVO>();
				ZipCodeVO zipCodeVO = null;
				
				do {
					zipCodeVO = new ZipCodeVO();
					zipCodeVO.setBunzi(rs.getString("bunzi"));
					zipCodeVO.setDou(rs.getString("do"));
					zipCodeVO.setNum(rs.getInt("num"));
					zipCodeVO.setRo(rs.getString("ro"));
					zipCodeVO.setSi(rs.getString("si"));
					zipCodeVO.setZip(rs.getString("zip"));
					zipCodeList.add(zipCodeVO);
				} while(rs.next()); // 레코드가 있을동안 실행한다.
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			close(rs);
			close(pstmt);
		}
				
		return zipCodeList;
	}

	public int insertMember(MemberVO memberVO) {
		
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member(id, passwd, name, zip, addr1, addr2, email, age, country)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPasswd());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getZip());
			pstmt.setString(5, memberVO.getAddr1());
			pstmt.setString(6, memberVO.getAddr2());
			pstmt.setString(7, memberVO.getEmail());
			pstmt.setInt(8, memberVO.getAge());
			pstmt.setString(9, memberVO.getCountry());
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	
	
}
