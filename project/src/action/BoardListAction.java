package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import svc.BoardListService;
import vo.ActionForward;
import vo.BoardVO;
import vo.PageVO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			//데이터들을 PageVO로 담아서 보내기 위해서는 지역 변수가 아니라, 전역 변수로 밖으로 빼준다.
			int pageCount = 0;
			int startPage = 0;
			int endPage = 0;
		 	int pageSize = 10;
		 
			String pageNum = request.getParameter("pageNum");
		    if (pageNum == null) {
		        pageNum = "1";
		    }
			
		    // 하단에 페이지 번호를 가지고 연산하는 부분이 있다.
		    // 연산이 제대로 될 수 있도록 정수로 변환한다.
		    int currentPage = Integer.parseInt(pageNum);
		    
		    // 해당 페이지에 첫 번째로 출력되는 글의 레코드 번호를 구하는 부분 
		    // 현재 페이지가 1이라면
		    // (1 - 1) * 10 + 1 =====> 1번 레코드 가져온다.
		    // (2 - 1) * 10 + 1 =====> 11번 레코드 가져온다.
		    // (3 - 1) * 10 + 1 =====> 21번 레코드 가져온다.
		    int startRow = (currentPage - 1) * pageSize + 1;
		 
		    // 전체 글의 개수를 저장할 변수 => board 테이블 자체에 글이 몇개인지?
		    int count = 0;
		    // 해당 페이지에 첫 번째로 출력되는 글의 글번호
		    int number=0;

		    // 해당 페이지에 출력되는 글정보들을 저장할 컬렉션
		    List<BoardVO> articleList = null;
		    BoardListService boardListService = 
		    		new BoardListService();
		    
		    count = boardListService.getArticleCount();
		    
		    // 번호 == 글이 하나라도 있으면!
		    if (count > 0) {
		        articleList = boardListService.getArticles(startRow, pageSize);
		    }
			// 페이지에 첫 번째 번호를 구하는 방법
			// 1페이지, 총 글의 개수 : 123개라면
			// 123 - (1 - 1) * 10 ===> 123이다.
			// 2페이지, 총 글의 개수 : 130개라면
			// 130 - (2 - 1) * 10 ===> 120
			number=count-(currentPage-1)*pageSize;
			
			//########################################################################
	        if(count > 0) {
				// 총 페이지 개수를 구하는 것이다.
		        // 글이 123개, pageSize 10 으로 나누면 => 12
		        // count % pageSize ==> 3 ==>     + 1
		        // pageCount = 13개이다.
		    	pageCount = count / pageSize + 
		        		( count % pageSize == 0 ? 0 : 1);
				 
		        // 해당 페이지 그룹에서 첫 번재로 시작되는 페이지번호
		        startPage = (int)(currentPage - 1/10)*10+1;
				
		        int pageBlock=10;
		        
		        // 해당 페이지 그룹의 마지막 페이지 번호
				//              21      +   10 - 1
		        endPage = startPage + pageBlock-1;
		        
		        // 마지막 페이지 그룹의 경우 
		        if (endPage > pageCount) endPage = pageCount;
	        }
	       
	    // 클래스를 공유해서 하나의 속성값으로 보내는 것이 더 효율적이다.
	    PageVO pageVO = new PageVO();
	    pageVO.setCount(count);
	    pageVO.setNumber(number);
	    pageVO.setCurrentPage(currentPage);
	    pageVO.setPageCount(pageCount);
	    pageVO.setStartPage(startPage);
	    pageVO.setEndPage(endPage);
	    
	    request.setAttribute("pageVO", pageVO);
	    request.setAttribute("articleList", articleList);
	    
	    ActionForward forward = new ActionForward();
	    forward.setUrl("board/list.jsp");
	   
		return forward;
	}

}
