package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.ReplyVO;

public class BoardWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 1. 요청처리
	
	    int num=0,ref=1,re_step=0,re_level=0; // 해당 글을 답변글로 처리하는데 필요한 값들! 
	    // 사용자가 글 내용을 보고 답변하기 요청을 했을때 넘어온다. 
	  
	    if(request.getParameter("num")!=null){
			num=Integer.parseInt(request.getParameter("num"));
			ref=Integer.parseInt(request.getParameter("ref"));
			re_step=Integer.parseInt(request.getParameter("re_step"));
			re_level=Integer.parseInt(request.getParameter("re_level"));
		}
	
		ReplyVO replyVO = new ReplyVO();
	    replyVO.setNum(num);
	    replyVO.setRe_level(re_level);
	    replyVO.setRe_step(re_step);
	    replyVO.setRef(ref);
	    // 저장한 객체의 데이터들을 set 해준다.
	    request.setAttribute("replyVO", replyVO);
	    
	    // WriteForm.jsp 로 forward를 해준다.
	    ActionForward forward = new ActionForward();
	    forward.setUrl("board/writeForm.jsp");
	    
	    
		
	return forward;
	}

}
