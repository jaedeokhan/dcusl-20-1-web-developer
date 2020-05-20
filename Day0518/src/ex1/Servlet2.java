package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	/*
	 * 학습 목표
	 * request 객체 method 사용 & response 객체 method 사용
	 * request.getParameter(String name)
	 * request.getParameterNames()
	 * request.getParameterValues(String name)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET 방식보다는 보안성이 있는 POST 방식을 사용한다.
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// Client에서도 마찬가지로 utf-8 설정
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		out.println("<html><head><title>폼 연습 서블릿</title></head>");
		out.println("<body><center>");
		// Servlet은 자바코드에서 html 문서를 넣는다. JSP는 반대이다.
		out.println(" id : " + id + "<br>" );
		out.println(" pw : " + pw + "<br>" );
		out.println("</center></body></html>");
		out.close();
		
	}

}
