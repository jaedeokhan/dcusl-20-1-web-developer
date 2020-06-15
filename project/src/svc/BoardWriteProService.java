package svc;

import java.sql.Connection;
import static db.JdbcUtil.*;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardWriteProService {

	public boolean writeAritcle(BoardVO article) throws Exception{
		
		boolean writeSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int insertCount = boardDAO.insertArticle(article);
		if (insertCount > 0) {
			commit(con);
			writeSuccess = true;
		}
		else {
			rollback(con);
		}
		close(con);
		return writeSuccess;
	}
}
