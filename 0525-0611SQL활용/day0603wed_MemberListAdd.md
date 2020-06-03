### 2020 0603 WED

### Today
1. MemberListAdd.java 
2. EL 

#### 1. MemberListAdd 를 OracleMemberDao로 Controller의 역할만 하게 설정하기!

> MemberListAdd.java

```java
package ex2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberAddServlet
 */
@WebServlet("/MemberAddServlet")
public class MemberAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/ch2/MemberForm.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		OracleMemberDao memberDao = new OracleMemberDao();
		memberDao.Insert(new Member().setEmail(request.getParameter("email"))
				.setName(request.getParameter("pw"))
				.setPassword(request.getParameter("name")));
		
		response.sendRedirect("MemberListServlet");
		}

}
```

#### 1.1.1 MemberForm.jsp 에서 name과 경로를 확실하게 파악하기!

> MemberForm.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex2.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<h1>회원 등록</h1>
	<form action="/Day0601MON/MemberAddServlet" method="post">
	   이름 : <input type="text" name="name"><br>
	   이메일 :<input type="text" name="email"><br>
	   암호 : <input type="password" name="pw">
	 	  <input type="submit" value="추가">
	 	  <input type="reset" value="취소">
	</form>
	<jsp:include page="Footer.jsp" />

</body>
</html>
```

#### 2. EL 연산자 사용하는 방법



#### 3. JS 

> ex01_onchange.html

* onchange="onchangeEvent(this)" 

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script> 
   function onchangeEvent(obj){
	   alert("onchage 실행되었습니다. " + obj.value)
   }
</script>
</head>
<body>
   <select onchange="onchangeEvent(this)">
      <option value="base">기본</option>
      <option value="apple">사과</option>
      <option value="orange">오렌지</option>
   </select>
</body>
</html>
```

#### 3.1.1 onloadEvent
child node, parent node 요런 식으로 node 라고도 한다. 

> ex02_onload.html

* docment.form.n.focus(); => form.n 에 onload 한다.
* onblur : focus 가 벗어날때!!

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
   function onloadEvent(obj) {
	   alert("onloadEvent 호출");
	   
   }
</script>
</head>
<body onload="document.form.n.focus();">
   <form action="#" name="form">
      <input type="text" name="n" onblur="onloadEvent(this)"/>
   </form>

</body>
</html>
```

#### 3.1.2



