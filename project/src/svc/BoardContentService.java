package svc;
import static db.JdbcUtil.*;


import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardContentService {

	public BoardVO getArticle(int num) throws Exception{
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);	    
		
        BoardVO article = boardDAO.selectArticle(num);
        
        close(con);
        
		return article;
	}
	
	
	
	
}
