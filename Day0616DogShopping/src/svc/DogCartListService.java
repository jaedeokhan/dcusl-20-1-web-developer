package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;

public class DogCartListService {

	public ArrayList<CartVO> getCartList(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		ArrayList<CartVO> cartList = (ArrayList<CartVO>)session.getAttribute("cartList");	
	
		return cartList;
	}
}
