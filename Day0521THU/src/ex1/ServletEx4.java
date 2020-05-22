package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEx4
 */
@WebServlet("/ServletEx4")
public class ServletEx4 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String fruit;
		String color;
		String hobbyCheck[];
		String hobby = "";
		
		
		PrintWriter out;
		color = request.getParameter("color");
		fruit = request.getParameter("fruit");
		hobbyCheck = request.getParameterValues("check");
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hobbyCheck.length; i++) {
			sb.append(hobbyCheck[i]);
			// hobbyCheck 
			if (hobbyCheck.length > i + 1) {
				sb.append(", ");
			}
		}
		hobby = sb.toString();
		
		response.setContentType("text/html;"); 
		response.setCharacterEncoding("UTF-8");
		 
		out = response.getWriter();
		
		out.println("<html><head><title>리스트</title></head>");
		out.println("<body><center>");
		out.println("<h2>색깔, 과일, 취미</h2>");
		out.println("<table border='1'>"
				+ "<tr>"
				+ "<th>색깔</th>"
				+ "<td>" + color + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<th>과일</th>"
				+ "<td>" + fruit + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<th>취미</th>"
				+ "<td>" + hobby + "</td>"
				+ "</tr>"
				+ "</table>");
		out.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
		
	}

}
