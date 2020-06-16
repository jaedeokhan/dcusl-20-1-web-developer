package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardUpdateFormService;
import vo.ActionForward;
import vo.BoardVO;

public class BoardUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardUpdateFormService boardUpdateFormService =
				new BoardUpdateFormService();
		
	
		BoardVO article =  boardUpdateFormService.getUpdateArticle(num);
		request.setAttribute("article", article);
//		request.setAttribute("pageNum", pageNum);

		ActionForward forward = new ActionForward();
		forward.setUrl("board/updateForm.jsp");
		
		return forward;
	}

}
