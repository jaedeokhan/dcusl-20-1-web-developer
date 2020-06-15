package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberRegistService;
import vo.MemberVO;

/**
 * Servlet implementation class MemberRegistServlet
 */
@WebServlet("/memberRegist")
public class MemberRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd2");
		String name = request.getParameter("name");
		String zip = request.getParameter("zip");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		String country  = request.getParameter("country");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setAddr1(addr1);
		memberVO.setAddr2(addr2);
		memberVO.setAge(age);
		memberVO.setCountry(country);
		memberVO.setEmail(email);
		memberVO.setId(id);
		memberVO.setName(name);
		memberVO.setPasswd(passwd);
		memberVO.setZip(zip);
		
		MemberRegistService memberRegistService = new MemberRegistService();

		boolean registSuccess = memberRegistService.registMember(memberVO);
		
		// registScuess가 True 일 경우!
		if(registSuccess) {
			HttpSession session = request.getSession();
			// 등록에 성공한 id를 session으로 넘겨준다.
			session.setAttribute("id", id);
			response.sendRedirect("LoginForm.jsp");
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back"); // history.go(-1) : 
										 // history.forward() == history.go(1)
			out.println("</script>");
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("");

	}

}
