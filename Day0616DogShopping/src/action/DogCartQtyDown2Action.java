package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyDown2Service;
import vo.ActionForward;

public class DogCartQtyDown2Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		DogCartQtyDown2Service dogCartQtyDown2Service =
				new DogCartQtyDown2Service();
		
		dogCartQtyDown2Service.downQty(request, id);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("dogCartList.dog");
		forward.setRedirect(true);
		
		return forward;
	}

}
