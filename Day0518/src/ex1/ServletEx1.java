package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEx1
 */
@WebServlet("/ServletEx1")
public class ServletEx1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 어떤 언어, 문자셋을 설정해주는 것(setContentType("text/html; charset=utf-8"))
		response.setContentType("text/html; charset=utf-8");
		// 위의 방법으로 Encoding을 설정하기도 하지만, 아래와 같이 따로줘도 된다.
//		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("Hello Servelt!");
		out.println("<html>"+"<body>"+"<h1>서블릿 간단 작성 예제입니다. </h1> "+"</body>"+"</html>");
// 외부장치에 사용에 있어서는 open을 해준다 즉 사용을 한다면 항상 자원을 close()를 해줘야 한다.
		out.close();
		
	}

}
