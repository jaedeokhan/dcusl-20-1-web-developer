### 2020 0605 FRI

### Today
1. MemberDeleteServlet
2. JSTL - choose, forEach,  forTokens
3. JS 

#### 1. MemberDeleteServlet를 OracleMemberDao에서 Delete 메소드를 사용해서 DB 처리하기.

#### 1.1.1 MemberDeleteServlet

> MemberDeleteServlet.java

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
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/MemberDeleteServlet")
public class MemberDeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OracleMemberDao memberDao = new OracleMemberDao();
		memberDao.Delete(request.getParameter("no"));
		response.sendRedirect("MemberListServlet");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

```

#### 1.1.2 OracleMemberDao

> OracleMemberDao.java

```java
	public void Delete(String no) {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM members WHERE MMO=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  no);
			pstmt.executeUpdate(); 
		
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
```

#### 1.1.3 response.sendRedirect("MemberListServlet");
* Delete 를 수행하고 나서 Redirect를 한다.

#### 2. JS => choose, forEach, forTokens 사용하는 방법

#### 2.1.1 <c:choose var="" value=""/>
* if 문과 같은 choose 를 사용한다.

> ex01_choose.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <p><a href="ex02_foreach.jsp">[ 다음  ]</a></p>
   <h2>c:choose 태그</h2>
      <c:set var="userid" value="admin"/>
      <c:choose>
         <c:when test="${userid == 'hong'}">
                     홍길동님 반갑습니다.
         </c:when>
         <c:when test="${userid == 'leem' }">
                     임꺽정님 반갑습니다.
         </c:when>
         <c:when test="${userid == 'admin' }">
                     관리자님 반갑습니다.
         </c:when>   
         <c:otherwise>
                    등록되지 않은 사용자입니다.
         </c:otherwise>
      </c:choose> 
</body>
</html>
```

#### 2.1.2 <c:forEach var="" items="${ }"/>
* ArrayList Collection을 사용해서 forEach 구문을 사용

> ex02_foreach.html

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ex2.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <p><a href="ex01_choose.jsp">[ 이전  ]</a><a href="ex03.jsp">[ 다음 ]</a></p>
   <h2>반복문 - 배열 (forEach)</h2>
   <%
      pageContext.setAttribute("nameList", new String[]{"홍길동", "임꺽정", "일지매"});
   %>
   <ul>
      <c:forEach var="name" items="${nameList}">
         <li>${name}</li>
      </c:forEach>
   </ul>
   <h3>속성 사용</h3>
   <ul>
      <c:forEach var="name" items="${nameList}" begin="0" end="1">
         <li>${name}</li>
      </c:forEach>
   </ul>
   <h3>반복문 - ArrayList 객체</h3>
   <%
      ArrayList<String> nameList3 = new ArrayList<String>();
      nameList3.add("홍길동");
      nameList3.add("임꺾정");
      nameList3.add("일지매");
      nameList3.add("음악대장");
      nameList3.add("주먹대장");
      pageContext.setAttribute("nameList3", nameList3);
   %>
   <ul>
      <c:forEach var="name" items="${nameList3}">
         <li>${name}</li>
      </c:forEach>
   </ul>
</body>
</html>
```

#### 2.1.3 <c:forTokens var="변수이름" items="${값}" delims="구분자"/>
* & 를 기준으로 Tokens 화 한다.

> ex03_fortokens.html

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ex2.Member" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

   <p><a href="ex02_foreach.jsp">[ 이전 ]</a></p>
   <%
      pageContext.setAttribute("tokens", "v1=20&v2=30&op=+");
   %>
   <ul>
      <c:forTokens var="item" items="${tokens}" delims="&">
         <li>${item}</li>
      </c:forTokens>
   </ul>
</body>
</html>
```

#### 3. JS

#### 3.1.1 setTimeout => 시간이 지나면 끝나게 하는 방법이다.

> ex01_settimeout.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script>
      setTimeout(function(){
    	  alert("3초가 지났습니다.");
      }, 3000);
   </script>
</head>
<body>

</body>
</html>
```

#### 3.1.2 setInterval => 내부적인 실행을 반복 set

> ex02_interval.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script>
      // 1초마다 함수를 실행
      var intervalID = setInterval(function(){
    	  alert('<p>' + new Date() + '</p>');
      }, 1000);
      
      // 5초 후 함수를 실행
      setTimeout(function(){
    	  clearInterval(intervalID);
      }, 5000);
   </script>
</head>
<body>

</body>
</html>
```

#### 3.1.4 Eval => 문자열을 실제 함수화한다.
* willEval 안에 들어있는 문장을 ; 기준으로 실행을 한다.


> ex03_Eval.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script>
      var willEval = '';
      willEval += 'var number = 10;';
      willEval += 'alert(number)';
      
      eval(willEval);
      
   </script>
</head>
<body>

</body>
</html>
```

#### 3.2 window 객체 사용

#### 3.2.1 var child 변수에 window.open 객체를 넣어주고 사용하기.

> ex05_child.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script>
      var child = window.open('', '', 'width=300, height=200');
      child.document.write("<h1>from parent windows</h1>");
      child.moveTo(650, 300);
   </script>
</head>
<body>

</body>
</html>
```

#### 3.2.2 window resize

> ex07_widnow_resize.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script>
      var child = window.open('', '', 'width=300, height=200');
      var width = screen.width;
      var height = screen.height;
      child.moveTo(0, 0);
      child.resizeTo(width, height);
      setInterval(function(){
    	 child.resizeBy(-100, -100);
    	 child.moveBy(10, 10);
      }, 1000);
   </script>
</head>
<body>

</body>
</html>
```

#### 3.3 tagDisplay(), tagClear(), tagRefresh()
* tagDisplay() : 현재 작성중인 문장을 가지고 새로운 winodw.open하기.
* tagClear() : 기본의 내용을 지우기.
* tagRefresh() : 페이지를 다시 reload하기.

> ex08.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css"> 
body
{ 
scrollbar-face-color:#dedede; 
scrollbar-shadow-color:gray; 
scrollbar-highlight-color:#ffffff;
scrollbar-3dlight-color:#dedede; 
scrollbar-darkshadow-color:#ffffff; 
scrollbar-track-color:#f0f0f0; 
scrollbar-arrow-color:#dedede
} 
input{background-color:#000000; color:white; border:0; height:18;font-family:돋움; font-size:12px; cursor: hand;}
</style> 
<script language="javascript">
	
	function tagDisplay(){
	   var source = frm.editor.value;
	   win = window.open("http://localhost:8081/Day0601MON/ch2/js/0605/ex08.html", "", "width=300, height=300");
	   win.document.write(source);
	};
	
	function tagClear(){
		frm.editor.value = "";
	}
	
	function tagRefresh(){
		location.reload();
	}
</script>
</head>
<body topmargin=50>

<center>
   <form name="frm">
      <input type="button" value="웹브라우저로 보기" onclick="tagDisplay()"/>
      <input type="button" value="새 문서" onclick="tagClear()"/>
   	  <input type="reset" value="새HTML페이지" onclick="tagRefresh()"/>
</center>
  
<p>
   <textarea rows="20" cols="60" name="editor" style="padding:5px; border:2 sliver solid;">
&lt;html&gt;

&lt;head&gt;
&lt;title&gt; &lt;/title&gt;
&lt;/head&gt;
	
&lt;body&gt;
&lt;/body&gt;
	
&lt;/html&gt; 
   </textarea>
   </form> 
</body>
</html>
```
