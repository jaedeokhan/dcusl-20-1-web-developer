package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartListService;
import vo.ActionForward;
import vo.CartVO;

public class DogCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		ArrayList<CartVO> cartVO = 
		DogCartListService dogCartListService = new DogCartListService();
		
		// 장바구니 목록은 세션에 있으니 , session을 얻을려면 request 객체를 사용해야한다. 
		ArrayList<CartVO> cartList = dogCartListService.getCartList(request);
		
		// 장바구니 목록이 출력되고 하단에 총 금액 출력이 된다. => 무슨 상품 + 무슨 상품 = 총 금액 
		// 총 금액을 Action에서 구한다. => 연산하는 것은 될 수 있으면 jsp에서는 하지 않는다.
		int totalMoney = 0;
		for (int i = 0; i < cartList.size(); i++) {
			totalMoney += cartList.get(i).getPrice() * cartList.get(i).getQty();
		}
		
		request.setAttribute("cartList", cartList);
		request.setAttribute("totalMoney", totalMoney);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("dogCartList.jsp");
		
		return forward;
	}

}
