### 2020 0610 WED 

### Today
1. Model2 방식 장점
   * 유지 보수성 향상 => 모듈화를 해서 자유롭게 수정이 가능하다.
   * 생산성 향상 => view, Business Logic 이 분리되서 동시 작업이 가능하다.
2. Cookiees and Session
3.

#### 1. LoginProject 구현
1. LoginForm.jsp : 사용자의 id, pw를 받아서 LoginServlet에서 처리하기 위해 Login으로 POST 방식으로 id, pw를 보낸다.
2. LoginServlet.java : Servlet에서 비지니스 로직을 구현하는 것이 아니라, Controller에서 Model에게 넘겨준다. 
   * LoginService loginService = loginService.getLoginMember(id, passwd); 를 넘겨서 처리하게 한다.
   * loginMember != null 이 아니면 회원 로그인 성공
   * loginMember == null 이면 회원 로그인 실패
3. LoginService.java : 트랜잭션 처리 때문에 Connection 객체는 Service 단에서 다루어져야 한다.
   * 왜냐하면, 상대방에게 돈을 입금을 한다고 한다면 나의 계좌에서 5000원을 빼서, 효재 계좌에 5000이 들어가야 하기에 이 두 작업은 끊김이 없어야 한다. 그래서 Service단에서 끊김없이 처리를 해줘야 한다.
   * 데이터베이스 작업은 DAO에서 다루기 위해 singleton으로 만들어 준다.
4. LoginDao.java : singleton으로 사용을 한다. 무분별한 객체의 사용을 방지하기 위해서 메모리의 사용을 절약하기 위해서 하나의 객체로 사용하도록 하는 패턴이다.


#### 1.1 LoginForm.jsp

#### 1.2 LoginServlet.java

#### 1.3 LoginService.java

#### 1.4 LoginDAO.java
* LoginDAO를 캡슐화한다.
* private으로 생성자를 호출한다. 외부에서 함부로 생성할 수 없게한다.
* instance가 기존에 만들어지지 않았으면 하나를 만든다.
* LoginService.java 에서 loginService.setConnection(con); 으로 넘어오는 con을 사용할 수 있게 메소드를 만든다.
* Service에서는 비지니스 로직 관련 처리 이름을 사용하면 되고, DAO 에서는 DB에 마는 이름을 준다.
* selectLoginMember(String id, String passwd); : 여기서 close(con)을 해주지 않는 이유는 트랜잭션 처리를 한다고 생각하면 같은 트랜잭션안에서 작업을 처리해야 하기에 service단으로 와서 입금, 출금읆 모두 수행을 하고 close(con)을 해주는 것이다.
* LoginDAO => LoginService => LoginServlet으로 loginMember 객체를 가져오는 것이다.
* 

> LoginDAO.java

```java

```

#### 1.5 LoginServlet.java 에서 Login sccuess, fail 구분하기.
1. 뷰 페이지에서 사용할 수 있도록 데이터를 공유한다. => 1)속성을 사용 2) 초기화 파라미터 사용방식(프레임워크 설정시 사용)
   1. 속성을 생성하는 방법
      1. 속성 생성(공유)
         * 영역객체.setAttribute(String attrName, Object attrValue);
   2. 속성을 사용하는 방법
      1. 속성 사용(얻어오기)
         * Object 영역객체.getAttribute(String attrName);

1. 영역 종류
   1. page 영역 : 동일 페이지 내에서만 공유된 속성값에 접근(사용)할 수 있다.
      * 페이지가 바꿔지면 페이지의 데이터에 접근할 수 없다.
   2. request 영역 : 하나의 영역을 처리하는 영역에서 접근이 가능하다. 
      * 사용자의 요청이 넘어왔을때 사용자의 응답 화면이 뜰때까지 거치는 모든 과정이 request이다.
   3. session 영역 : 사용자 한명(웹브라우저) 당 공유되는 영역, request영역보다 조금 더 넓은 영역이다.
   4. application 영역 : /Day0609TUELoginProject/ 아래로 요청하는 페이지에서는 모두 공유된다. 껐다가 켜도 된다!
      * tomcat : server에 공유되는 영역

1. Servlet 에서의 포워딩 방식
   1. dispatch 방식
      * URL(주소) 변환이 서버 상에서 이루어지고, Client의 주소 표시줄에 변화가 없다. 즉, request가 변경되지 않는다.
   2. redirect 방식
      * URL(주소)변환이 .jsp로 이루어져서 request 영역의 사용으로는 안되고, session 영역을 사용해야 데이터를 넘겨 받을 수 있다.
      

> LoginServlet.java

```java
package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
		String passwd = request.getParameter("passwd");
		
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

}
```

#### 1.5.1 dispatch 방식, ForwardMethodServlet에서 dispatch 방식을 사용해서 forwardTo.jsp 에 request로 데이터를 보낸다.
* dispatch 방식으로 데이터를 보내서 forwardTo.jsp 에 접근을 해도 URL에서는 Servlet의 주소를 유지한다. 즉 request를 유지한다.



> ForwardMethodServlet.java

```java
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ForwardMethodServlet
 */
@WebServlet("/ForwardMethodServlet")
public class ForwardMethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ForwardMethodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. dispatch 방식
		// 이 방법은 request가 유지가 된다.
//		RequestDispatcher dispatcher = 
//				request.getRequestDispatcher("forwardTo.jsp");
//		request.setAttribute("requestScope", "requestValue");
//		dispatcher.forward(request, response);
		
		// 2. redirect 방식
		// 페이지 전환이 클라이언트에서 이루어진다.
		HttpSession session = request.getSession();
		session.setAttribute("sessionScope", "sessionValue");
		response.sendRedirect("forwardTo.jsp ");
		
	}

}

```

#### 1.5.2 redirect 방식, .jsp로 변환이 되고, 주소가 변경이 된다. 
* 페이지 전환이 클라이언트에서 이루어진다. 주소 표시줄에 주소가 .jsp로 변환이 된다.
* session 은 request보다 더 큰 페이지 영역이다.
* Servlet에서 .jsp로 변환이 되기에 session 페이지 영역을 사용해 줘야 한다.


#### 2. Cookiess : 클라이언트에 정보를 저장하는 단위

#### 2.1

#### 2.2 Session 

#### 3.  
