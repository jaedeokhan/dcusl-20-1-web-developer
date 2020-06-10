package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ForwardMethodServlet
 */
@WebServlet("/ForwardMethodServlet")
public class ForwardMethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ForwardMethodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. dispatch 방식
		// 이 방법은 request가 유지가 된다.
//		RequestDispatcher dispatcher = 
//				request.getRequestDispatcher("forwardTo.jsp");
//		request.setAttribute("requestScope", "requestValue");
//		dispatcher.forward(request, response);
		
		// 2. redirect 방식
		// 페이지 전환이 클라이언트에서 이루어진다.
		HttpSession session = request.getSession();
		session.setAttribute("sessionScope", "sessionValue");
		response.sendRedirect("forwardTo.jsp ");
		
	}

}
