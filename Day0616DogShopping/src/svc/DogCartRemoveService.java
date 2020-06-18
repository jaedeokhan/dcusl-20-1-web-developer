package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;

public class DogCartRemoveService {

	public void removeCartVO(HttpServletRequest request, String[] deleteIdArray) {
		
		
		// CartList에서 지금 선택한 CartVO 목록을 제거하기.
		HttpSession session = request.getSession();
		ArrayList<CartVO> cartList = 
				(ArrayList<CartVO>)session.getAttribute("cartList");
		
		for (int i = 0; i < deleteIdArray.length; i++) {
			for (int j = 0; j < cartList.size(); j++) {
				if (Integer.parseInt(deleteIdArray[i]) == cartList.get(j).getId()) {
					cartList.remove(cartList.get(i));
				}
			}
		}
	}
}
