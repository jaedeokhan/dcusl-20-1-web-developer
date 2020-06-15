package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.LoginDAO;
import vo.MemberVO;

public class MemberRegistService {

	public boolean registMember(MemberVO memberVO) {
			
		boolean registSuccess = false;
		Connection con = getConnection();
		LoginDAO loginDAO = LoginDAO.getInstance();
		loginDAO.setConnection(con);
		
		// DAO 의 DB 작업의 이름은 DML 을 사용한다.
		int InsertCount = loginDAO.insertMember(memberVO);
		
		if(InsertCount > 0) {
			commit(con);
			registSuccess = true;
		}
		else {
			rollback(con);
		}
		
		close(con);
			
		return registSuccess;
	}
	
}
