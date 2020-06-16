package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardRemoveProService;
import vo.ActionForward;

public class BoardRemoveProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int num = Integer.parseInt(request.getParameter("num"));
	    String pageNum = request.getParameter("pageNum");
	    String passwd = request.getParameter("passwd");
	    
	    
	    BoardRemoveProService boardRemoveProService 
	    		= new BoardRemoveProService();
	   

	    boolean removeSuccess = boardRemoveProService.removeArticle(num, passwd);
	    
		ActionForward forward = null;
		if (removeSuccess) {
			forward = new ActionForward();
			// 글 등록이 성공하고, 글 목록 보기로 바로 날린다.
			forward.setUrl("boardList.bo?pageNum=" + pageNum);
			forward.setRedirect(true);
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("<history.back();>");
			out.println("</script>");
		}
		
		return forward;
	}

}
