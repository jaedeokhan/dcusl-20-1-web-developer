package action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BoardDAO;
import svc.BoardUpdateProService;
import vo.ActionForward;
import vo.BoardVO;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardVO article = new BoardVO();
		// 답변글이나, 답변글이 아니냐 지금 그게 필요한거다!!
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setContent(request.getParameter("content"));
		article.setEmail(request.getParameter("eamil"));
		article.setIp(request.getRemoteAddr());
		article.setPasswd(request.getParameter("passwd"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setSubject(request.getParameter("subject"));
		
		String pageNum = request.getParameter("pageNum");
		
		BoardUpdateProService boardUpdateProService = 
				new BoardUpdateProService();
		
	    boolean modifySuccess = boardUpdateProService.modifyArticle(article);
	    
		ActionForward forward = null;
		if (modifySuccess) {
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
