package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
import vo.DogVO;

public class DogViewService {

	public DogVO getDogVO(String id) {
		
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
				
		// 조회수 증가시키는 작업
		int updateCount = dogDAO.updateReadCount(id);
		
		if (updateCount > 0) {
			commit(con);
		}
		else {
			rollback(con);
		}
		
		// 강아지 ArrayList 얻어오는 작업
		DogVO dogVO = dogDAO.selectDogVO(id);
		
		close(con);
		
		return dogVO;
	}
}
