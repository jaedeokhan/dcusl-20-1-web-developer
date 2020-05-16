package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.MemberVO;

import static db.JdbcUtil.*;

public class MemberDAO {
	// Java DAO는 싱글톤 패턴을 사용한다.
	// singleton 패턴
	// 해당 클래스의 기능을 다른 클래스에서 사용할 때 첫번째로만 사용할 때만 객체를 생성하고, 그 다음 부터는
	// 이미 생성된 객체를 공유해서 사용하게 하는 패턴
	// 왜? 이렇게 하는가? => 메모리 사용을 절약하기 위해서 하는 것이다.
	// 주로 메소드 위주로 정의된 클래스에서 사용한다. 기능만 다른 클래스에서 호출해서 사용하면 되는데 굳이 객체 생성할 필요가 없다!
	Connection con;
	
	private static MemberDAO memberDAO;
	
	
	private MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			// getInstance() 메소드가 처음으로 호출됬으면!
			// 처음에 사용할때만 객체를 하나 올리고, 한 개 이후부터는 만들지 않는다.
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public String selectExistId(String id) {
		String dbId = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM member1 WHERE id='" + id + "'";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			if (rs.next()) {
				// select 구문에 의해서 반환된 레코드가 하나라도 있으면
				dbId = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		return dbId;
	}

	public int insertMember(MemberVO newMemberVO) {
		int insertCount = 0;
		
		String sql = "INSERT INTO member1(id, passwd, name, email, addr, age, gender, tell) "
				+ "VALUES('" + newMemberVO.getId() 
				+ "','" + newMemberVO.getPasswd() 
				+ "','" + newMemberVO.getName() 
				+ "','" + newMemberVO.getEmail()
				+ "','" + newMemberVO.getAddr() 
				+ "'," + newMemberVO.getAge()
				+ ",'" + newMemberVO.getGender() 
				+ "','" + newMemberVO.getTell() + "')";

		Statement stmt = null;
		
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			stmt = con.createStatement();
			insertCount = stmt.executeUpdate(sql);
			// insertCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
		} catch (SQLException e) {
			// TODO: handle exception
			// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
		// 작업한 리소르를 모두 해제해주기.
			close(stmt);
		}
		return insertCount;
	}

	public ArrayList<MemberVO> selectMemberList() {
		ArrayList<MemberVO> memberList = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM member1";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			if (rs.next()) {
				memberList = new ArrayList<MemberVO>();
				MemberVO memberVO = null;
				do {
					// 테이블에 한 라인당 객체를 만든다.
					// memberVO 객체의 속성 값으로 모두 다 할당하고, 최종적으로 arrayList에 추가
					memberVO = new MemberVO();
					memberVO.setAddr(rs.getString("addr"));
					memberVO.setAge(rs.getInt("age"));
					memberVO.setEmail(rs.getString("email"));
					memberVO.setGender(rs.getString("gender"));
					memberVO.setId(rs.getNString("id"));
					memberVO.setName(rs.getString("name"));
					memberVO.setPasswd(rs.getString("passwd"));
					memberVO.setTell(rs.getString("tell"));
					
					memberList.add(memberVO);
				}while(rs.next());
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}		
		return memberList;
	}

	public MemberVO selectMemberVO(String memberViewId) {

		MemberVO memberVO = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member1 WHERE id='" + memberViewId + "'";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			if (rs.next()) {
				// select 구문에 의해서 반환된 레코드가 하나라도 있으면
				memberVO = new MemberVO();
				memberVO.setAddr(rs.getString("addr"));
				memberVO.setAge(rs.getInt("age"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setGender(rs.getString("gender"));
				memberVO.setId(rs.getNString("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPasswd(rs.getString("passwd"));
				memberVO.setTell(rs.getString("tell"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		
		return memberVO;
	}

	public int updateMember(MemberVO newMemberVO) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		
		String sql = "UPDATE member1 SET "
				+ "passwd = '" + newMemberVO.getPasswd() 
				+ "', name = '" + newMemberVO.getName() 
				+ "', email = '" + newMemberVO.getEmail()
				+ "', addr = '" + newMemberVO.getAddr() 
				+ "', age = " + newMemberVO.getAge()
				+ ", gender = '" + newMemberVO.getGender() 
				+ "', tell = '" + newMemberVO.getTell() + "'"
				+ " WHERE id = '" + newMemberVO.getId() + "'";

		Statement stmt = null;
		
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			stmt = con.createStatement();
			updateCount = stmt.executeUpdate(sql);
			// updateCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
		} catch (SQLException e) {
			// TODO: handle exception
			// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
		// 작업한 리소르를 모두 해제해주기.
			close(stmt);
		}
		return updateCount;
	}

	public int deleteMember(String removeMemberId) {
		// TODO Auto-generated method stub
		
		int deleteCount = 0;
		
		String sql = "DELETE member1 " 
				+ "WHERE id = '" + removeMemberId + "'";

		Statement stmt = null;
		
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			stmt = con.createStatement();
			deleteCount = stmt.executeUpdate(sql);
			// deleteCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
		} catch (SQLException e) {
			// TODO: handle exception
			// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
		// 작업한 리소르를 모두 해제해주기.
			close(stmt);
		}
		return deleteCount;
	}
	

	public ArrayList<MemberVO> selectMemberByName(String memberSearchName) {
		// TODO Auto-generated method stub
		
		ArrayList<MemberVO> memberList = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM member1 " + 
					 "WHERE name = '" + memberSearchName + "'";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			if (rs.next()) {
				memberList = new ArrayList<MemberVO>();
				MemberVO memberVO = null;
				do {
					// 테이블에 한 라인당 객체를 만든다.
					// memberVO 객체의 속성 값으로 모두 다 할당하고, 최종적으로 arrayList에 추가
					memberVO = new MemberVO();
					memberVO.setAddr(rs.getString("addr"));
					memberVO.setAge(rs.getInt("age"));
					memberVO.setEmail(rs.getString("email"));
					memberVO.setGender(rs.getString("gender"));
					memberVO.setId(rs.getNString("id"));
					memberVO.setName(rs.getString("name"));
					memberVO.setPasswd(rs.getString("passwd"));
					memberVO.setTell(rs.getString("tell"));
					
					memberList.add(memberVO);
				}while(rs.next());
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}		
		return memberList;
	}


	
}


