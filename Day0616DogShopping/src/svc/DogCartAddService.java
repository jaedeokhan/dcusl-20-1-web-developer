package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;
import vo.DogVO;

public class DogCartAddService {

	public void addCart(HttpServletRequest request, DogVO dogVO) {
		
		// 장바구니는 세션에 담기 때문에 DB 작업은 필요하지않다.
		// 1. 현재 자신의 세션에 저장된 장바구니 목록을 가져온다.
		HttpSession session = request.getSession();
		
		// 각각의 사람들의 세션의 정보를 저장을 하기 위해서는 Arraylist를 사용해주는 것이 좋다.
		ArrayList<CartVO> cartList = (ArrayList<CartVO>)session.getAttribute("cartList");
		
		// cartList에 각각의 CartVO 를 담아서 세션에 담을 것이다.
		 
		// 아직 장바구니 요청을 한 번도 안했으면! => 장바구니 담을 영역을 만든다.
		if (cartList == null) {
			// 새롭게 담을 ArrayList를 만든다.
			cartList = new ArrayList<CartVO>();
			session.setAttribute("cartList", cartList);
		}
		
		// 2. 새로운 상품을 장바구니에 담기
		// 2-1. 이미 있는 경우
		
		// 2-2. 새로운 상품
		
		// newCart = ture 새로운 상품이라면!
		boolean newCart = true;
		// newCart = false 로 돌리는 경우는 기존의 상품이 세션(장바구니)있다면!
		for (int i = 0; i < cartList.size(); i++) {
			// 현재 담는거랑           == 기존에 담는거
			if(dogVO.getId() == cartList.get(i).getId()) {
				cartList.get(i).setQty(cartList.get(i).getQty() + 1);
				// 기존에 있는 상품이다.
				newCart = false;
			}
		}
		
		// 새 상품이라면!
		if (newCart) {
			CartVO cartVO = new CartVO();
			cartVO.setId(dogVO.getId());
			cartVO.setImage(dogVO.getImage());
			cartVO.setKind(dogVO.getKind());
			cartVO.setPrice(dogVO.getPrice());
			cartVO.setQty(1);
			cartList.add(cartVO);
		}
		
		
	}
	
	
	
}
