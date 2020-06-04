### 2020 0604 THU 

### Today
1. MemberListUpdate.java , MemberDeleteServlet
2. JSTL -> page scope
3. 

#### 1. MemberListUpdate를 처리하기!
1. MemberListServlet 에서 [수정] 을 누르면 MemberListUpdate?no<%= member.getNo() %> 로 각각의 no에 해당하는 Servlet으로 처리가 된다. 
2. doGet(); 메소드에서는 request.getParameter("no")로 MMO를 받아서 OracleMemberDao에 UpdateSelect를 들어간다.
3. UpdateSelect에서는 넘어오는 no == MMO 를 SELECT 문의 조건문으로 사용해서 모든 데이터를 읽어서 불러온다. 
4. request.setAttribute("member", member); 로 member 객체를 "member"라는 이름으로 RequestDispatcher 에게 rd.forward(request, response)를 해준다.
5. MemberUpdateForm.jsp 로 forwoar가 되면 member 객체를 받은 곳에서 수정할 목록들을 출력해주고, form 태그를 통해서 다시 post 방식으로 doPost()로 던져주면 Update를 수행하게 만든다.
6. 정보를 입력하면, doPost()로 넘어와서 OracleMemberDao 의 참조 변수인 memberDao에 Update(new Member => no, email, pw, name) 수정한 데이터를 request로 받아서 Update 메소드에 던져준다.
7. OracleMemberDao 의 Update 메소드에서는 PreparedStatement 에서 placeholder 방식을 사용해서 member.get 메소드를 통해서 각각의 파라미터를 설정한다.
8. Update 메소드의 기능을 수행하고, response.sendRedirect("MemberListServlet") 로 Redirect를 해준다. 
9. MemberListServlet에서 전체 목록(수정된 목록도 포함)을 출력한다.

#### 1.1 MemberUpdateServlet.java

> MemberUpdateServlet.java

```java
package ex2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			request.setCharacterEncoding("UTF-8");
			OracleMemberDao memberDao = new OracleMemberDao();
			Member member = memberDao.UpdateSelect(request.getParameter("no"));
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/ch2/MemberUpdateForm.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {e.printStackTrace();}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		memberDao.Insert(new Member().setEmail(request.getParameter("email"))
//				.setName(request.getParameter("pw"))
//				.setPassword(request.getParameter("name")));
		
		try {
		
			OracleMemberDao memberDao = new OracleMemberDao();
			memberDao.Update(new Member().setNo(Integer.parseInt(request.getParameter("no")))
					.setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("pw"))
					.setName(request.getParameter("name")));
			
			response.sendRedirect("MemberListServlet");
		}
		catch (Exception e) {e.printStackTrace();}
		
	}

}
```

#### 1.1.1 OracleMemberDao  에서 오늘 추가한 부분은 UpdateSelect, Update

> OracleMemberDao.java

```java
package ex2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

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
			Member member = null;
			
			if (rs.next()) {
				return member = new Member()
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

	public List<Member> selectList() throws Exception{
		
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM MEMBERS ORDER BY MMO ASC";
		ArrayList<Member> members = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			members = new ArrayList<Member>();
			
			while(rs.next()) {
				members.add(new Member()
				.setNo(rs.getInt(1))              // MMO
				.setEmail(rs.getString(2)) 		  // EMAIL
				.setPassword(rs.getString(3))	  // PWD
				.setName(rs.getString(4))		  // MNAME
				.setCreateDate(rs.getDate(5))	  // CRE_DATE
				.setModifiedDate(rs.getDate(6))); // MOD_DATE
			}
			
			return members;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		
		return null;
	}

		

	public void Insert(Member member) {
		
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO members(MMO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) "
				+ "VALUES(SEQ_MMO.nextVal, ?, ?, ?, SYSDATE, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.executeUpdate();
			
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
	}

	public Member UpdateSelect(String no) {
		
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Member member = null;
//		String sql = "UPDATE members SET EMAIL=? PWD=? MNAME=?"
//				+ " CRE_DATE=SYSDATE MOD_DATE=SYSDATE WHERE MMO=?";
//		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MMO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS"
					+ " WHERE MMO=" + no);
			
			if (rs.next()) {
				member = new Member();
				return new Member().setNo(rs.getInt("MMO"))
					   .setEmail(rs.getString("EMAIL"))
					   .setPassword(rs.getString("PWD"))
					   .setName(rs.getString("MNAME"))
					   .setCreateDate(rs.getDate("CRE_DATE"))
				       .setModifiedDate(rs.getDate("MOD_DATE"));
				
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			
		} catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public void Update(Member member) {
		
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "UPDATE members SET EMAIL=?, PWD=?, MNAME=?"
				+ " WHERE MMO=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setInt(4,  member.getNo());
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
		
	}
	
}
```

#### 1.1.2 MemberUpdateForm.jsp

> MemberUpdateForm.jsp

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
<%
	Member member = (Member)request.getAttribute("member");
%>
	<jsp:include page="Header.jsp" />
	<h1>회원 수정</h1>
	<form action="/Day0601MON/MemberUpdateServlet" method="post">
	   번호 : <input type="text" name="no" value="<%=member.getNo() %>" readonly /><br>
	   이메일 :<input type="text" name="email" value="<%=member.getEmail() %>"><br>
	   암호 : <input type="password" name="pw" value="<%=member.getPassword() %>"><br>
 	   이름 : <input type="text" name="name" value="<%=member.getName() %>"><br>
	   가입일 : <%=member.getCreateDate() %> <br>
	   수정일 : <%=member.getModifiedDate() %> <br>
	 	  <input type="submit" value="수정">
	 	  <input type="button" value="삭제" 
	 	  	onclick='location.href="/Day0601MON/MemberDeleteServlet?no=<%=member.getNo()%>"'>
		  <input type="button" value="취소" onclick='location.href="/Day0601MON/MemberListServlet"'>
	</form>
	<jsp:include page="Footer.jsp" />

</body>
</html>
```

#### 2. JSTL -> Page scope

#### 2.1 scope vs request
1. default => scope
2. scope="request"



> ex01_scope.jsp

1. 기존에는 객체를 사용할려면 아래와 같이 형변환을 해서 사용을 해줘야 했다.
   * MyMember member2 = (MyMember)pageContext.getAtribute("member");
2. 이러한 불편한 점을 pageContext.setAttribute("member", member); 와 같이 간결하게 설정!
   * 사용 방법 : ${member.name}

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Page</title>
</head>
<body>
   <c:set var="username1" value="홍길동"/>
   <c:set var="username2">임꺽정</c:set>
   1) ${username1}<br>
   2) ${username2}<br>
   <h3>기본 보관소 - page</h3>
   3) ${pageScope.username1}<br>
   4) ${requestScope.username}<br>
   <h3>보관소 지정 - scope 속성</h3>
   <c:set var="username3" scope="request">일지매</c:set>
   5) ${pageScope.username3}<br>
   6) ${requestScope.username3}<br>
   <h3>기존의 값 덮어씀</h3>
   <% pageContext.setAttribute("username4", "똘이장군"); %>
   7) 기존 값=${username4}<br>
   <c:set var="username4" value="주먹대장"/>
   8) 덮어쓴 값=${username4}<br>
   <h3>객체 프로퍼티 값 변경</h3>
   <%!
   public class MyMember {
	   int no;
	   String name;
	   public int getNo(){
		   return no;
	   }
	   public void setNo(int no){
		   this.no = no;
	   }
	   public String getName(){
		   return name;
	   }
	   public void setName(String name){
		   this.name = name;
	   }
   }
   %>
   
   <%
      MyMember member = new MyMember();
      member.setNo(100);
      member.setName("홍길동");
      pageContext.setAttribute("member", member);
      
   %>
   9) ${member.name}<br>
   <c:set target="${member}" property="name" value="임꺽정"/>
   10) ${member.name}<br>
   <!-- 기존에는 MyMember member2 = (MyMember)pageContext.getAtribute("member"); -->
   
   
   
</body>
</html>
```

#### 2.1.1 

> ex02_remove.jsp

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
   <p><a href="ex01_scope.jsp">[ 이전 ]</a><a href="ex03.jsp">[ 다음 ]</a></p>
   <h2>c:remove 태그</h2>
   <h3>보관소에 저장된 값을 제거하는 방법</h3>
   <% pageContext.setAttribute("username1", "한재덕"); %>
   1) ${username1}<br>
   <c:remove var="username1"/>
   2) ${username1}<br>
   
</body>
</html>
```

#### 2.1.2

> ex03_condition.jsp

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
   <p><a href="ex01_scope.jsp">[ 이전 ]</a><a href="ex03.jsp">[ 다음 ]</a></p>

   <h2>c:if 태그</h2>
   <c:if test="${10 > 20}" var="false_test">
     if { 10 > 20 } = ${false_test};
   </c:if>
</body>
</html>
```

#### 3. JS

#### 3.1.1  if-else

> ex01_condition.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var date = new Date();
   var hour = date.getHours();
   // 조건문
   if (hour < 12){
	   alert("오전입니다.");
   }
   else {
	   alert("오후입니다.");
   }
   
</script>
</head>
<body>

</body>
</html>
```

#### 3.1.2 if_nest if

> ex02_if_nest.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var date =  new Date();
   var hour = date.getHours();
   
   if (hour < 11){
	   alert("아침 먹을 시간입니다.");
   }
   else {
	   if (hour < 15){
		   alert("점심 먹을 시간입니다.");
	   }
	   else {
		   alert("저녁 먹을 시간입니다.");
	   }
   }
</script>
</head>
<body>

</body>
</html>
```

#### 3.1.3 if_else if

> ex03_if_elseif.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var date = new Date();
   var hour = date.getHours();
   
   if (hour < 11){
	 alert("아침 먹을 시간");   
   }
   else if (hour < 15){
	   alert("점심 먹을 시간");
   }
   else {
	   alert("저녁 먹을 시간");
   }
   
</script>
</head>
<body>

</body>
</html>
```

#### 3.1.4 prompt

> ex04_prompt.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var score = prompt("학점을 입력 : ", '학점');
   if ( score >= 4.0 && score <= 4.5){
	   alert("A+학점");
   }
   else {
	   alert("재수강");	   
   }
</script>
</head>
<body>

</body>
</html>
```

#### 3.1.5 input number switch

> ex05_input_number.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var input = Number(prompt('숫자를 입력 : ', '숫자'));
   
   switch (input % 2){
      case 0:
    	  alert("짞수");
          break;
      case 1:
    	  alert("홀수");
    	  break;
      default:
    	  alert("숫자가 아닙니다.");
          break;
   }
</script>
</head>

<body>

</body>
</html>
```

#### 3.1.6 array

> ex06_array.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var array = [273, 32, 103, 57, 52];
   
   for (i = 0; i < array.length; i++){
	   alert(array[i]);
   }

</script>
</head>
<body>

</body>
</html>
```

#### 3.1.7 array push

> ex07_array2.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var array = [0, 1];
   var array_test = [];
   
   // 배열 요소 추가
   array.push(2);
   array.push(3);
   array.push(4);
   
   alert(array);
   
   for (i = 0; i < 5; i++){
	   array_test[i] = array_test.push(i * 2);
   }
   
   alert(array_test);
   
</script>
</head>
<body>

</body>
</html>
```

#### 3.1.8 string array

> ex08_string_array.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var str = "abc";
   alert(str.length);
   for (i = 0; i < str.length; i++){
	   alert(str[i]);
   }
</script>
</head>
<body>

</body>
</html>
```

#### 3.1.9 향상된 for문

> ex08_string_array.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var str = "abc";
   alert(str.length);
   for (i = 0; i < str.length; i++){
	   alert(str[i]);
   }
   
   var fruit = ['사과', '포도', '딸기'];
   
   for (var i in fruit){
	   alert(fruit[i]);
   }

   
   var cnt = 0 ;
   while(cnt < str.length){
	   alert(str[cnt++]);
   }
  
   cnt = 0;
   do {
	   alert(str[cnt++]);
   } while(cnt < str.length);
   
</script>
</head>
<body>

</body>
</html>
```

#### 3.2 Anonymous function

> ex09_annoymous.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   function function_test() {
	   alert("함수A");
	   
   };
   
   function_test();
   
   var test = function(){
	   alert("함수B");
   };
   
   test();
   
   
</script>
</head>
<body>

</body>
</html>
```

#### 3.2.1 call back function 

> ex10_callback.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   // 함수 선언
   function callTenTimes(callback){
	   // 10회 반복
	   for (var i = 0; i < 10; i++){
		   callback();
	   }
   }
   // 변수 선언
   var callback = function(){
	  alert("함수 호출");  
   };
   // 함수 호출
   callTenTimes(callback);
</script>
</head>
<body>

</body>
</html>
```

#### 3.2.2 call other function

> ex12_other_function.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   // 함수 선언
   function returnFunction(){
	   return function(){
	        alert("test");
	   };
   }
   // 함수 호출
   returnFunction()();
   var test = returnFunction();
   test();
   
</script>
</head>
<body>

</body>
</html>
```

#### 3.2.3 


 
