package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		DogCartQtyUpService dogCartQtyUpService  =
				new DogCartQtyUpService();
		
		dogCartQtyUpService.upQty(request, id);
		
		
		ActionForward forward = new ActionForward();
		forward.setUrl("dogCartList.dog");
		forward.setRedirect(true);
		
		return forward;
	}

}
