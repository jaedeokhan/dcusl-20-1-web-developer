package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.LoginService;
import vo.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("passwd");
		
		// Login 처리하기. => 비지니스 로직은 Controller 에서 구현하는 것이 아니고,  
		// 비지니스 로직은 Model 에서 구현한다.
		LoginService loginService = new LoginService();
		
		// 로그인에 성공하면 로그인에 성공한 사용자의 정보를 출력하고,
		// 실패하면, 일단은 로그인 실패 메세지를 출력! (다시 LoginForm으로 redirect한다.)
        MemberVO loginMember = loginService.getLoginMember(id, pw);
        // 로그인에 성공하면 로그인에 성공한 회원의 정보를 MemberVO 객체타입으로 변환
        // 로그인에 실패하면  null을 반환할 것이다.
        
        // 뷰 페이지(JSP)로 포워딩을 해준다. 
        if(loginMember != null) {
        	// 로그인에 성공한 경우
        	response.sendRedirect("LoginSccuess.jsp");
        	
        }else {
        	// 로그인에 실패한 경우
        	response.sendRedirect("LoginFail.jsp");
        }
		
	}

}
