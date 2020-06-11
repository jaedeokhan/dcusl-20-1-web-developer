package svc;
import static db.JdbcUtil.*;
import java.sql.Connection;
import vo.MemberVO;
import dao.LoginDAO;

public class IdCheckService {

	public boolean existId(String id) {
		
		Connection con  = getConnection();
		// SQL 구문을 전송하는  작업은 DAO(Data access object) 클래스에서 처리를 한다.
		LoginDAO loginDAO  = LoginDAO.getInstance();
		loginDAO.setConnection(con);
		
		// DAO는 이름을 SELECT와 같이 DB 명령어를 사용한다.
		String dbId = loginDAO.selectId(id);
		boolean idExist = false;
		if (dbId != null) {
			idExist = true;
		}
		
		
		close(con);
		
		return idExist;
	}

	
	
}
