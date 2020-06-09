### 2020 0601 MON

### Today
1. Business Logic
2. Tag Library 


#### 1. JSP Standard Tag Library Download 
* Jakarata apache page에서 taglibs 로 들어간다.
http://tomcat.apache.org/taglibs/

#### 1.1 Standard 1.1v download
http://archive.apache.org/dist/jakarta/taglibs/standard/

#### 1.2 download 후 lib에 jstl, standard 두 개를 WEB-INF -> lib 즉 ojdbc6.jar 파일이 있는 곳에 같이 담기!
* jstl,jar
* standard.jar

#### 2. LoginServletEx 를 model1 => model1.5 => model2 와 같이 향상하기!
* Controller 와 View를 구분하기
   * View를 contact해주는 역할이 중요!
* DAO(DB Access Object)

> LoginServletEx.java

DAO에게 Handling을 수행해서 코드를 분리해준다.
* Controller 에서는 redirect와 view에게 contact를 해준다!!

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
		
		RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginForm.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
	
			Member member = new OracleMemberDao().exist(request.getParameter("email"), request.getParameter("pw"));
			if (member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
//				session.setMaxInactiveInterval(10);
				response.sendRedirect("MemberListServlet");
				
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/ch2/LoginFail.jsp");
				rd.forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
```

#### 2.1 DAO에서는 사용자의 요청 분석을 처리한다.

> OracleMemberDao.java

```java
package ex2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleMemberDao {

	public Member exist(String email, String pw) throws Exception{
		
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MNAME, EMAIL FROM MEMBERS WHERE EMAIL=? AND PWD=?";		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  email);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Member()
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"));
			}
			else {
				return null;
			}
			
		} catch (SQLException e) {
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
		return null;
	}
	
}
```

#### 2.2 JSTL 을 이용해서 View 구현하기.
* EL - 리터럴 표현식

|  데이터형  |  EL 코드  | 실행 결과 |
|:----------:|:---------:|:---------:|
|   문자열   | ${"test"} |    test   |
|   문자열   | ${'tset'} |   tests   |
|    정수    |   ${20}   |     20    |
| 부동소수점 |  ${3.14}  |    3.14   |
|   Boolean  |  ${true}  |    true   |
|    Null    |  ${null}  |           |

#### 2.2.1 EL 문법 활용
* .jsp 파일 맨 위에 <%@ page isElIgnored=false %> EL 표현식을 사용한다고 정의한다.

> el01.jsp

기본 EL 리터럴 표현식 사용법!

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL(Expression Language)</title>
<style type="text/css"> 
   body {
   	font-size : small;
   }
   table{
  	border : thin solid gray;
  	border-collapse : collpase;
   }
   
   td, th {
   	border : thin dotted gray;
   	padding : 2px;
   	text-align : center;
   }
   
   th {
   	background-color : lightgray;
   }
   
   pre {
   	font-size : 90%;
   }
   
</style>
</head>
<body>
    <h2>EL - 리터럴 표현식</h2>
	<table border="1">
	  <tr>
	  	<th>데이터 형</th>
	  	<th>EL 코드</th>
	  	<th>실행 결과</th>
	  </tr>
	  <tr>
	  	<td>문자열</td>
	  	<td>\${"test"}</td>
	  	<td>${"test"}</td>
	  </tr>
	  <tr>
	  	<td>문자열</td>
	  	<td>\${'test'}</td>
	  	<td>${'test'}</td>
	  </tr>
	  <tr>
	  	<td>정수</td>
	  	<td>\${20}</td>
	  	<td>${20}</td>
	  </tr>
	  <tr>
	  	<td>부동 소수점</td>
	  	<td>\${3.14}</td>
	  	<td>${3.14}</td>
	  </tr>
	  <tr>
	  	<td>Boolean</td>
	  	<td>\${true}</td>
	  	<td>${true}</td>
	  </tr>
	  	<tr>
	  	<td>Null</td>
	  	<td>\${null}</td>
	  	<td>${null}</td>
	  </tr>
	</table>
	<p><a href="el02.jsp">[다음]</a></p>
</body>
</html>
```

#### 2.2.2 EL 문법 => 배열
* Mission : external css file 생성하기.


> el02.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList"%>
<%@ page import="ex2.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
   <p><a href="el01.jsp">[이전]</a><a href="el03.jsp">[다음]</a></p>
   <h2>EL - 값 꺼내기</h2>
   <table>
      <tr><td>1</td><th>2</th><th>3</th></tr>
      <tr></tr>
      <tr></tr>  
   </table>

</body>
</html>
```

> css/style.css

```css
@charset "UTF-8";

body {
	font-size : small;
}
table{
	border : thin solid gray;
	border-collapse : collpase;
}

td, th {
	border : thin dotted gray;
	padding : 2px;
	text-align : center;
}

th {
	background-color : lightgray;
}

pre {
	font-size : 90%;
}
```

#### 3. Java Script
* Internal
* External
* Inline 

> js01.html

* document.write();
* document.getElementById("test").innerHTML = "Hello GoodBye!";

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
   document.write("<center><h2>Hello JavaScript<h2></center>")
</script>
</head>
<body style="color : #ff8800; background-color : #000042">
<p id="test">Hello Good!</p>
<script>
	document.getElementById("test").innerHTML = "Hello GoodBye!";
</script>
JAVA Script는 Live Script 로서 다이나믹한 홈페이지를 만들기 위한 도구이다.
서버의 간섭이 없이 클라이언트에 반응하는 도구.
Live Script 에 Java의 문법을 적용한 도구. 엄격히 말하자면 ECMA 스크립트라고 해야함.
넷스케이프의 Java Script 에 대항하려고 MS에서 Jscript를 만들어냄
웹이 발전할 수록 더욱 중요한 가치를 지님

특징
1. Script 언어이다.
2. 간단한 Application 이다.
3. 사용자 event 처리 가능
4. Object-Based Programming(객체기반) 언어이다. (cf. 자바는 OOP 객체지향언어)

장점
1. 신속한 개발
2. 배우기 쉽다.
3. 플랫폼에 독립적이다.
4. 시스템에 부하가 적다.

단점
1. 내장 Method의 한계 - 늘릴 수가 없다. 즉 기능 추가를 더 할 수 가 없다.
2. Code의 보안성 - 이를 위해서 .js 로 밖에 꺼내 놓는다.
3. debugging 및 개발 등의 부재

함수
함수에는 항상 괄호가 없다.
(ex : F(x)= 처럼..x에 값을 넣으려면 f가 기능을 하여 어떤 값을 출력하게 된다.
1. build-in function - 메뉴얼에 이미 만들어져있는 함수
2. user-defined function - 사용자가 필요에 의해 만드는 함수

실습 > 가장 많이 쓰는 Build-in 함수는

</body>
</html>
```

#### 3.1 Java Script 의 alert 메소드 사용
* alert()

> js02.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("당신의 pc는 지금 공격당하고 있습니다.");
	alert("당신의 pc는 지금 공격당하고 있습니다.");
	alert("당신의 pc는 지금 공격당하고 있습니다.");
</script>
</head>
<body>
	1. alert 무언가를 말할때!
</body>
</html>
```


#### 4.



