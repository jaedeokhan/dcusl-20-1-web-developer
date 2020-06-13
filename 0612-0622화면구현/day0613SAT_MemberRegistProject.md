### 2020 0613 SAT
### Today
1. 회원가입처리
   1. MemberRegistForm.jsp 회원가입 요청을  POST 방식으로 MemberRegistServlet으로 보낸다.
   2. MemberRegistServlet 에서는 비즈니스 로직 처리와 뷰 작업을 처리한다.
      * boolean registSuccess 로 memberRegistService 에 registMember(memberVO)를 요청해서 받는다. 
      * 회원가입이 성공하면, LoginForm.jsp 로 id를 가지고, redirect해준다.
      * 회원가입이 실패하면, script를 사용해서 history.back으로 다시 MemberResigtForm.jsp 로 가게 해준다.
   3. LoginForm.jsp에서는 MemberRegistService에서 Service, DAO 를 거쳐 비즈니스 작업을 받아서 boolean registSuccess가 True이면 회원 가입을 수행한 id가 LoginForm.jsp에서 바로 보이게 value=<%= id%> 처리 작업을 해주고, 만약 사용자가 회원가입 처리를 하지 않고 그냥 LoginForm.jsp에 오면 null이 남아 있기에 조건문으로 처리해서 id == null이면 공백으로 만들어준다.
   3. MemberRegistService 단에서는con을 생성해주고, 트랜잭션 처리 작업을 해준다.
   4. LoginDAO 에서는 Service 단에서 요청한 insertMember(memberVO) 메소드를 처리해서 INSERT 가 성공, 실패했는지 int 형으로 반환을 해준다.
   5. JdbcUtil에서는 Java에서 트랜잭션 autoCommit True이기에 False로 변경해주고 commit, rollback 메소드를 작성해준다. 
      * ORACLE에서는 DML을 사용하면 트랜잭션처리가 걸려서 세션끼리 공유하려면 COMMIT, ROLLBACK을 해줘야 한다.
   6. LoginDAO에서는 
   7. addr1 이 VARCHAR2(50) 이여서 초과해서 DB에 들어가지 않아서 더 크게 만들어준다.

#### 1. 회원가입 처리

#### 1.1 MemberResigtServlet.java :  memberReistForm.jsp에서 회원가입을 클릭하면 RegistServlet을 불러서 인자들을 받아서 비지니스 로직 처리와 forword 처리를 해준다.
* memberRegistService에 registMember(memberVO); 메소드를 사용해서 
* 회원 등록을 성공한다면, LoginForm.jsp 로 redirect가 되고, 회원 가입 성공한 id를 회원가입 목록에 보여준다.
* 회원 등록을 하지 않았고, LoginForm으로 오면 null이 보이기에 id 가 null일때 처리를 해준다.

> MemberResigtServlet.java

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
```

#### 1.2 LoginForm.jsp : 
* String id 를 받고, id가 null인지 아닌지 체크해서 사용자가 회원가입을 하고 바로 온거면 회원가입을 진행한 id를 바로 보여주고, id == null이면 "" 으로 맏들어서 보여준다.

> LoginForm.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  // Object로 반환되서 오니 String으로 다운 캐스팅해야한다.
   String id = (String)session.getAttribute("id");
   
   if (id == null){
	   id = "";
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #LoginFormArea {
      width : 300px;
      height : 150px;
      border : 3px double green;
      margin : auto;
   }
   h1 {
      text-align : center;
   }
   fieldset {
      text-align : center;
      border : none;
   }
   
   input[type="submit"]{
      margin-top : 5px;
   }
</style>
</head>
<body>
<section id="LoginFormArea">
   <form action="Login" method="POST">
      <fieldset>
      <h1>로그인</h1>
      <label for="id">아이디 : </label>
      <input type="text" name="id" id="id" value=<%= id %>><br>
      <label for="passwd">비밀번호 : </label>
      <input type="password" name="passwd" id="passwd">
      <label for="cookieUse">쿠키사용 : </label>
      <input type="checkbox" name="cookieUse" id="cookieUse" value="on">
      <input type="submit" value="로그인">
      </fieldset>
   </form>
</section>

</body>
</html>
```

#### 1.3 MemberRegistService.java : 

> MemberRegistService.java

```java
package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.LoginDAO;
import vo.MemberVO;

public class MemberRegistService {

	public boolean registMember(MemberVO memberVO) {
			
		boolean registSuccess = false;
		Connection con = getConnection();
		LoginDAO loginDAO = LoginDAO.getInstance();
		loginDAO.setConnection(con);
		
		// DAO 의 DB 작업의 이름은 DML 을 사용한다.
		int InsertCount = loginDAO.insertMember(memberVO);
		
		if(InsertCount > 0) {
			commit(con);
			registSuccess = true;
		}
		else {
			rollback(con);
		}
		
		close(con);
			
		return registSuccess;
	}
	
}

```


#### 1.4 LoginDAO.java : 

> LoginDAO.java

```java
	public int insertMember(MemberVO memberVO) {
		
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member(id, passwd, name, zip, addr1, addr2, email, age, country)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPasswd());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getZip());
			pstmt.setString(5, memberVO.getAddr1());
			pstmt.setString(6, memberVO.getAddr2());
			pstmt.setString(7, memberVO.getEmail());
			pstmt.setInt(8, memberVO.getAge());
			pstmt.setString(9, memberVO.getCountry());
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			close(pstmt);
		}
		
		return insertCount;
	}

```

#### 1.5 JdbcUtil.java : 트랜잭션 처리를 사용하기 위해 commit, rollback을 작성한다.
* commit, rollback 메소드를 작성해준다. 
* oracle 에서는 INSERT, DELETE 작업을 하면 자동으로 트랜잭션 작업이 걸린다.
   * 즉, 작업이 자기 세션의 메모리상에서만 이루어진다.
   * 그래서, commit(), rollback()을 해야한다.
* Oracle에서는 Connection 객체의 기본 속성은 autoCommit이 False이다.
* Java에서는 Connection 객체의 기본 속성은 autoCommit이 True이다.
    * 기본적으로 트랜잭션에 걸리지 않는다. 
    * 수동으로 autoCommit을 False로 변경을 해야한다!

> JdbcUtil.java

```java
package db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	static {
		// static 요것은 클래스를 읽자마자 바로 실행되는 영역이다.
		// 즉, 클래스를 읽자마자 oracle drive를 바로 읽어들이려고 하는 것이다.
		// forName() 메소드는 괄호안에 있는 클래스를 메모리에 로딩하는 것이다.
		// forName() 처리를 하면 반드시 예외처리를 해줘야한다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		// db 연결 작업을 해주는 것.
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
											  "java",
											  "java");
			System.out.println("Connection Success!!");
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con) {
		// 여기서 예외처리를 해주자.
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(Statement stmt) {
		// 여기서 예외처리를 해주자.
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void close(ResultSet rs) {
		// 여기서 예외처리를 해주자.
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 트랜잭션 처리 부분
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
   
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
```


