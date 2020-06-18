package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;

public class DogCartQtyDownService {

	public void downQty(HttpServletRequest request, String id) {
		
		HttpSession session = request.getSession();
		ArrayList<CartVO> cartList = 
				(ArrayList<CartVO>)session.getAttribute("cartList");
		
		for(int i = 0; i < cartList.size(); i++) {
			if (Integer.parseInt(id) == cartList.get(i).getId()) {
			    cartList.get(i).setQty(cartList.get(i).getQty() - 1);
			}
		}
	}
}
