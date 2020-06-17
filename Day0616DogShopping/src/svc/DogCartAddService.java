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
		ArrayList<CartVO> cart = (ArrayList<CartVO>)session.getAttribute("cartList");
		
		 
		
		
	}
	
	
	
}
