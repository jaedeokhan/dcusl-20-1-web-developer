### 2020 0609 TUE

### Today
1. 좋아하는 아이돌을 선택해서 선택한 아이돌의 사진을 출력하기!
2. Model 1 방식, Model 2 방식
3. Model 2 방식으로 Login 구현하기.
4. 


#### 1. 좋아하는 아이돌을 선택해서 선택한 아이돌의 사진을 출력하기.

#### 1.1.1 idolChoiceForm.jsp

> idolChoiceForm.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #idolChoiceArea {
      width : 400px;
      height : 150px;
      border : 1px solid red; 
      margin : auto; 
   }
   h1 {
      text-align : center;
   }
   fieldset {
      text-align : center;
      border : none;
   }
</style>
</head>
<body>

<section id="idolChoiceArea">
   <h1>좋아하는 아이돌을 선택하세요.</h1>
   <form action="idolChoicePrint.jsp" method="POST">
      <fieldset>
          <label for="idol1">아이돌 : </label><br>
	      <input type="checkbox" name="idol" id="idol1" value="tagi.jpg"/>서태지
	      <input type="checkbox" name="idol" id="idol2" value="choi.jpg"/>최민수
	      <input type="checkbox" name="idol" id="idol3" value="jeni.jpg"/>제니
	      <input type="checkbox" name="idol" id="idol4" value="park.jpg"/>박서준<br>
	      <input type="submit" value="선택"/>
      </fieldset>
   </form>
</section>
</body>
</html>
```

#### 1.1.2 idolChoicePrint.jsp
* choice에서 사진 파일 이름을 check한 value를 받으면, 어떻게 서태지와 박서준 이렇게 띄워져있는데도 데이터가 들어오는지 의아했는데, 생각해보면 배열이라는 구조에 서태지, 박서준이 0,1 인덱스를 각각 가지게 되고, choice[i]로 for문을 돌리면 해당하는 서태지, 박서준의 사진이 출력이 된다.

> idolChoicePrint.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String[] choice = request.getParameterValues("idol");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   table {
      width : 600px;
      margin : auto;
   }
   
</style>
</head>
<body>
   <table>
      <tr>
         <%
            if (choice != null && choice.length > 0){
            	for (int i = 0; i < choice.length; i++){
            		
         %>
         <td>
            <img src="image/<%=choice[i] %>"/>
         </td>
         <% 
            	}
            } else
         %>
      </tr>
   </table>
</body>
</html>
```

#### 2. Model 1 vs Model 2

#### 2.1 Model 1 방식
* Client 
* Server
* DB

Server에서 비지니스 로직 처리, 뷰생성을 같이 하면서 바로 DB와 연동을 한다.
모델 1 개발 방식은 비지니스 로직과 뷰 생성 부분이 합쳐져 있다.

* 문제점
1. <b>가장 큰 문제점은 비즈니스 로직과 뷰가 합쳐져 있으니 유지 보수가 가장 어렵다.</b>
2. 비즈니스 영역과 뷰가 한 페이지에 있으니, 개발자와 디자이너가  한 페이지를 가지고 동시에 사용하기 때문에, 디자이너가 틀을 만들어 주기 전까지는 제대로 개발을 못했다. => 생산성을 저하시킨다.
3. 코드의 재사용성이 저하된다.

#### 2.2 Model 2 방식 => MVC 패턴 - (Model, View, Controller)
* Client
* Controller(Servlet)
* Model(POJO-Plain Old Java Object)
* DB
* View(jsp) -> Controller에게 Forwarding 을 받는다.

MVC로 나누면서 유지 보수성이 향상한다.
디자이너와 개발자가 동시에 작업이 가능해서 작업의 효율, 생상성이 향산된다.
코드의 재사용성이 높아진다.

#### 3. Model 2 형태로 요청 처리하기.

#### 3.1 처음에는 DB를 먼저 설계를 해야한다.
* Member.sql로 member table 생성 

> member.sql

```sql
CREATE TABLE member (
   id VARCHAR2(12) PRIMARY KEY,
   passwd VARCHAR2(12) NOT NULL,
   name VARCHAR2(15) NOT NULL,
   zip VARCHAR2(5) NOT NULL,
   addr1 VARCHAR2(50) NOT NULL,
   addr2 VARCHAR2(50) NOT NULL,
   email VARCHAR2(30) NOT NULL,
   age NUMBER NOT NULL,
   country VARCHAR2(15) NOT NULL
)
```

#### 3.2 Application 쪽에서 DB에 담긴 데이터를 처리하기 위해 VO(Value Object)를 생성해준다.
id, passwd, name, zip, addr1, addr2, email, age, country 들을 캡슐화를 해주고, Getter, Setter를 생성해준다.

> vo/MemberVO.java

```java
package vo;

public class MemberVO {
	private String id;
	private String passwd;
	private String name;
	private String zip;
	private String addr1;
	private String addr2;
	private String email;
	private int age;
	private String country;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	} 	
}
```
 
#### 3.3 기존에 DB 연결에 사용한 클래스를 재사용한다. 

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
}
```

#### 3.4 Login id, pw 를 받는 Client를 만들어 준다.

> LoginForm.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <input type="text" name="id" id="id"><br>
      <label for="pw">비밀번호 : </label>
      <input type="passwd" name="passwd" id="passwd">
      <input type="submit" value="로그인">
      </fieldset>
   </form>
</section>

</body>
</html>
```

#### 3.5 LoginForm으로부터 id, pw를 받아서 처리하는 Login Servlet을 구현한다.
* LoginSerlvet 이라고 이름을 정의하고, LoginForm에서는 action을 Login으로 해주었기 때문에 LoginServlet을 생성할때 next를 눌러서 mapping을 /login 으로 설정하고, post 방식으로 요청을 받기에 doGet()은 항목에서 제외를 시켜준다.
* LoginServlet에서 id, pw를 체크해주는 비지니스 로직을 구현하는 것이 아니라,  Model로 처리를 하게 넘겨줘야 한다.
* svc/LoginService.java 클래스를 만들어 준다.
   * svc에서 connection을 다룬다.


> LoginServlet.java

```java
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
```

#### 3.5.2 LoginService.java 구현하기.

> LoginService.java

```java
package svc;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import vo.MemberVO;
public class LoginService {

	public MemberVO getLoginMember(String id, String pw) {
		
		return null;
	}
   
}

```



