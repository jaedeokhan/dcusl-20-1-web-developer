### 2020 0529 FRI

### Today
1. MemberLogin => Update, Delete 마무리하기.
2. scope
   * Session : Session 객체가 살아있는 동안 정보를 모든 페이지 유지가 가능하다.
      * 세션을 만료하는 방법!
         * 시간설정
         * 메소드로!
   * Cookiee
   * Page
   * request : forwarding 되었을때 데이터를 다음 페이지까지 정보를 유지가 가능(활용)

#### 1. Update, Delete
저번 시간 강의 자료

#### 2. Session, Cookiee 
* Session 은 서버에서 객체로 저장한다.
   * Session이 종료전에 자신이 다시 사용하면 시간이 회복되서 사용을 할 수 있다.
* Cookiee 는 클라이언트에서 로그인 정보와 같은 정보를 물리적으로 저장을 한다.

> LoginServelt.java
로그인 할 때 즉 객체를 생성할때 생성을 생성해주기!


```java
package ex2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ex2.DBAction;

/**
 * Servlet implementation class LoginServletEx
 */
@WebServlet("/LoginServletEx")
public class LoginServletEx extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인터페이스
		RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginForm.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM members WHERE EMAIL=? AND PWD=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("email"));
			pstmt.setString(2, request.getParameter("pw"));
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				response.setContentType("text/html; charset=utf-8");
				member = new Member();
				member.setEmail(rs.getString("EMAIL"));
				member.setName(rs.getString("MNAME"));
				if (member != null) {
					response.setContentType("text/html; charset=utf-8"); 
//				request.setAttribute("member", member);
//				RequestDispatcher rd =
//						request.getRequestDispatcher("/ch2/Login.jsp");
//						rd.include(request, response);
					HttpSession session = request.getSession();
					session.setAttribute("member", member);
//					session.setMaxInactiveInterval(10);
					response.sendRedirect("MemberListServlet");
				}
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginFail.jsp");
				rd.forward(request, response);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}

```

#### 2.1 LogoutServlet에서 session을 invalidate() 메소드로 할당해제하고 sendRedirect 해주기.

> LogoutServlet.java

```java
package ex2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("LoginServletEx");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}

```

#### 2.2 

> 

```java

```

#### 3. MVC 이해하기
* Controller => 요청 처리 및 제어 담당
* Model => Business Logic 은 즉 실데이터를 다루는 DB 작업!
* View => 모델이 처리한 결과 데이터를 가지고 화면 생성
* 대형/장기 프로젝트 경우에는 MVC로는 나중에 유지보수성은 좋으나, 처음에 개발 비용이 많이 든다.
![image](https://user-images.githubusercontent.com/45028904/83254988-9a37f880-a1ea-11ea-838b-3fcd39fb15a3.png)

#### 3.1 MVC 아키텍처의 특징
1. 유지 보수가 쉽도록, 중복 코드의 작성을 최소하하고 기존 코드의 재상용을 높임
   * 역할의 세분화
   * 의존성 최소화



#### 3.2



#### 3.3




#### 4.


#### 
