package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.IdCheckService;

/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/IdCheck")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		IdCheckService idCheckService = new IdCheckService();
		boolean idExist = idCheckService.existId(id);
		
		request.setAttribute("idExist", idExist);
		request.setAttribute("id", id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("idCheck.jsp");
		dispatcher.forward(request, response);
		
	}

}
