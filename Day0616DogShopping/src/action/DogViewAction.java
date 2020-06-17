package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogViewService;
import vo.ActionForward;
import vo.DogVO;

public class DogViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		DogViewService dogViewService =
				new DogViewService();
	
		
		DogVO dogVO = dogViewService.getDogVO(id);
		
		// 오늘 본 상품 정보를 담는 액션을 DogViewAction에서 처리한다.
		// 쿠키가 중복이 되지 않게 해준다.
		Cookie todayImage = new Cookie("today" + id, dogVO.getImage());
		// 쿠키의 default 유지 시간은 -1
		todayImage.setMaxAge(60 * 60 * 24);
		response.addCookie(todayImage);
		
		// JSP 에서 사용할려면 공유를 해야한다.
		request.setAttribute("dogVO", dogVO);
		ActionForward forward = new ActionForward();
		forward.setUrl("dogView.jsp");
		
		return forward;
	}

}
