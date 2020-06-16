package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardVO;


public class BoardUpdateProService {

	public boolean modifyArticle(BoardVO article) throws Exception{
		
		boolean modifySuccess = false;
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCount = boardDAO.updateArticle(article);
		
		if (updateCount > 0) {
			commit(con);
			// 요 놈!!
			modifySuccess = true;
		}
		else {
			rollback(con);
		}
		close(con);
		
		return modifySuccess;
	}
	
	
}
