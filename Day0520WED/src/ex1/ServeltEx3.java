package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServeltEx3")
public class ServeltEx3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String check[];
		String checks = "";
		String fruit;
		String content;
		
		PrintWriter out;
		check = request.getParameterValues("check");
		fruit = request.getParameter("fruit");
		content = request.getParameter("content");
		java.util.Enumeration data = request.getParameterNames();
		// data에 ParameterNames가 존재한다면
//		while (data.hasMoreElements()) {
//			// 다음 요소로 이동
//			data.nextElement();
//			
//		} 						// 2
		for (int i = 0; i < check.length; i++) {
			checks += check[i]; // 독서, 게임 checks = checks + check[i];
			          // 2 > i + 1
					  // 2 > 1 
			   		  // 2 > 2
			if (check.length > i + 1) {
				checks += ", "; // checks = checks + ", " 
			}
		}
		// StringBuilder로 바꿔보기.
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < check.length; i++) {
//			sb.append(check[i]);
//			if (check.length > i + 1) {
//				sb.append(",");
//			}
//		}
		
		response.setContentType("text/html;");
		response.setCharacterEncoding("UTF-8");
		out = response.getWriter();
		while (data.hasMoreElements()) {
			out.println(data.nextElement());
		}
		
		out.println("<html><head><title>폼 연습 서블릿</title></head>");
		out.println("<body><center>");
		out.println("취미 : " + checks + "<br>");
		out.println("선택 : " + fruit + "<br>");
		out.println("내용 : " + content + "<br>");
		out.println("</body></html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
