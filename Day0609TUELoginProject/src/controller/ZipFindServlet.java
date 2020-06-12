package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ZipFindService;
import vo.ZipCodeVO;

/**
 * Servlet implementation class ZipFindServlet
 */
@WebServlet("/zipFind")
public class ZipFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipFindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String ro = request.getParameter("ro");
			
			ZipFindService zipFindService = new ZipFindService();
			
			// ro : 도로명을 zipFindSevice의  getZipcodeList 메소드로 넘겨준다.
			ArrayList<ZipCodeVO> zipCodeList = zipFindService.getZipcodeList(ro);
			
			request.setAttribute("zipCodeList", zipCodeList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("zipFind.jsp");
			dispatcher.forward(request, response);
	}
}