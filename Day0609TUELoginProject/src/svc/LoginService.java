package svc;
import static db.JdbcUtil.*;

// 트랜잭션 처리 때문에 Connection 객체는 Service 에서 다루어 준다.
// 자바에서 트랜잭션은 Connection 단위로 이루어진다.
import java.sql.Connection;
import dao.LoginDAO;
import vo.MemberVO;
public class LoginService {

	// Service에는 비지니스 로직에 맞는 이름을 준다.
	public MemberVO getLoginMember(String id, String passwd) {
		
		Connection con  = getConnection();
		// SQL 구문을 전송하는  작업은 DAO(Data access object) 클래스에서 처리를 한다.
		LoginDAO loginDAO  = LoginDAO.getInstance();
		loginDAO.setConnection(con);
		
		// LoginDAO 에서는 즉, DB 작업이니 DB관련 이름을 준다.
		// 자바에서 트랜잭셔능로 처리를 할려면 두 개의 커넥션 작업이 동시에 이루어져야 한다. 그래서 닫는 것은 
		// service 단에서 닫아야 한다.
		MemberVO loginMember = loginDAO.selectLoginMember(id, passwd);
		close(con);
		
		return loginMember;
	}
   
}
