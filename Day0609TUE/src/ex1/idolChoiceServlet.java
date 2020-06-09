package ex1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class idolChoiceServlet
 */
@WebServlet("/idolChoiceServlet")
public class idolChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public idolChoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8"); 
		
		String checks[] = request.getParameterValues("test");
		String check = "";
		
		
		for (int i = 0; i < checks.length; i++) {
			check += checks[i];
		}
		request.setAttribute("check", check);
		RequestDispatcher rd = request.getRequestDispatcher("/idolChoicePrint.jsp");
		rd.include(request, response);
	
	}

}
