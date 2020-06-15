package action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardVO;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		BoardVO article = new BoardVO();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(request.getParameter("ref")));
		
		article.setWriter(request.getParameter("writer"));
		article.setContent(request.getParameter("content"));
		article.setEmail(request.getParameter("eamil"));
		article.setIp(request.getRemoteAddr());
		article.setPasswd(request.getParameter("passwd"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setSubject(request.getParameter("subject"));
		
		BoardWriteProService boardWriteProService = 
				new BoardWriteProService();
		boolean writeSuccess  = boardWriteProService.writeAritcle(article);
		
		ActionForward forward = null;
		if (writeSuccess) {
			forward = new ActionForward();
			// 글 등록이 성공하고, 글 목록 보기로 바로 날린다.
			forward.setUrl("boardList.bo");
			forward.setRedirect(true);
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("<history.back();>");
			out.println("</script>");
		}
		return forward;
	}

}
