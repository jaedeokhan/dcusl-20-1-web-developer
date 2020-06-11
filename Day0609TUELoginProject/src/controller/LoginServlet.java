package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 주소 표시줄에서 주소 바로 입력하는 방식은 get 방식이다.
    	// 요청에 넘어오는 쿠키 객체를 얻어오기.
    	Cookie[] cookieArray = request.getCookies();
    	
    	String id = "";
    	String passwd = "";
    	if (cookieArray != null) {
    		// 즉, 존재한다면!
    	    for (int i = 0; i < cookieArray.length; i++) {
				if(cookieArray[i].getName().contentEquals("id")) {
					id = cookieArray[i].getValue();
				}
				else if(cookieArray[i].getName().contentEquals("passwd")) {
					passwd = cookieArray[i].getValue();
				}
			}
    	}
		// Login 처리하기. => 비지니스 로직은 Controller 에서 구현하는 것이 아니고,  
		// 비지니스 로직은 Model 에서 구현한다.
		LoginService loginService = new LoginService();
		
		// 로그인에 성공하면 로그인에 성공한 사용자의 정보를 출력하고,
		// 실패하면, 일단은 로그인 실패 메세지를 출력! (다시 LoginForm으로 redirect한다.)
        MemberVO loginMember = loginService.getLoginMember(id, passwd);
        // 로그인에 성공하면 로그인에 성공한 회원의 정보를 MemberVO 객체타입으로 변환
        // 로그인에 실패하면  null을 반환할 것이다.
        
        // 뷰 페이지(JSP)로 포워딩을 해준다. 
        if(loginMember != null) {
        	String nextJSP = "/LoginSccuess.jsp";
          	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        	request.setAttribute("loginMember", loginMember);
          	dispatcher.forward(request, response);
        	
        	
        }else {
        	// 로그인에 실패한 경우
        	// js를 이용해서 로그인 실패 alert를 출력을 해주고, 버튼을 누르면 다시 redirect
        	response.setContentType("text/html; charset=UTF-8");
        	PrintWriter out = response.getWriter();
        	out.println("<script>");
        	out.println("alert('로그인이 실패했습니다.')");
        	// Clinet가 접속한 것 을 가지고 있다.
        	// 현재 방문한 주소의 이전 주소로 돌아간다.
        	out.println("history.back();");
        	out.println("</script>");
//        	response.sendRedirect("LoginFail.jsp");
        }
    	
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String cookieUse = request.getParameter("cookieUse");
		 
		if (cookieUse != null) {
			Cookie idCookie = new Cookie("id", id);
			// 쿠키 객체를 생성하면? 생성된 쿠키객체의 기본 생존기간은 -1로 설정된다.
			// 브라우저가 실행되는 동안에는 쿠키가 유지가 되지만, 브라우저가 종료된다면 쿠키가 자동으로 소멸된다.
			idCookie.setMaxAge(60 * 60 * 24); // 단위는 초!
			response.addCookie(idCookie);
			
			Cookie passwdCookie = new Cookie("passwd", passwd);
			passwdCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(passwdCookie);
			
		}
		
		// Login 처리하기. => 비지니스 로직은 Controller 에서 구현하는 것이 아니고,  
		// 비지니스 로직은 Model 에서 구현한다.
		LoginService loginService = new LoginService();
		
		// 로그인에 성공하면 로그인에 성공한 사용자의 정보를 출력하고,
		// 실패하면, 일단은 로그인 실패 메세지를 출력! (다시 LoginForm으로 redirect한다.)
        MemberVO loginMember = loginService.getLoginMember(id, passwd);
        // 로그인에 성공하면 로그인에 성공한 회원의 정보를 MemberVO 객체타입으로 변환
        // 로그인에 실패하면  null을 반환할 것이다.
        
        // 뷰 페이지(JSP)로 포워딩을 해준다. 
        if(loginMember != null) {
        	/*
        	String nextJSP = "/LoginSccuess.jsp";
          	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        	request.setAttribute("loginMember", loginMember);
          	dispatcher.forward(request, response);
        	*/
        	HttpSession session = request.getSession();
        	session.setAttribute("loginMember", loginMember);
        	response.sendRedirect("index.jsp");
        	
        }else {
        	// 로그인에 실패한 경우
        	// js를 이용해서 로그인 실패 alert를 출력을 해주고, 버튼을 누르면 다시 redirect
        	response.setContentType("text/html; charset=UTF-8");
        	PrintWriter out = response.getWriter();
        	out.println("<script>");
        	out.println("alert('로그인이 실패했습니다.')");
        	// Clinet가 접속한 것 을 가지고 있다.
        	// 현재 방문한 주소의 이전 주소로 돌아간다.
        	out.println("history.back();");
        	out.println("</script>");
//        	response.sendRedirect("LoginFail.jsp");
        }
		
	}

}
