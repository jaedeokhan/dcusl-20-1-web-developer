package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartAddService;
import svc.DogViewService;
import vo.ActionForward;
import vo.DogVO;

public class DogCartAddwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		// 장바구니 담기 요청을 하기 위해서는 다른 정보들도 필요하다.
		// 기존에 사용한 Service로 dogVO를 받아온다.
		DogViewService dogViewService = new DogViewService();
		DogVO dogVO = dogViewService.getDogVO(id);
		
		DogCartAddService dogCartAddService = new DogCartAddService();
		
		// 장바구니에 담으면 장바구니에 담은 목록들이 보인다.
		dogCartAddService.addCart(request, dogVO);
		
		ActionForward forward = new ActionForward();
		
		// 완전하게 새로운 URL로 보낼때에는 Redirect로 한다.
		forward.setRedirect(true);
		forward.setUrl("dogCartList.dog");
		
		return forward;
	}

}
