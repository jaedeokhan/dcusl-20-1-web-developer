### 2020 0526 TUE 

### Today
1. html,css =>  header, nav, section, footer 구조 학습
2. jsp
   * 선언 태그: <%! %>
   * 설정 태그: <% %>
   * 출력 태그: <%= %>
   * JSP 내장 객체
   * EL(Expression Language) : jsp 페이지에서 데이터를 표현할 때 사용하는 언어
	* EL은 변수를 선언하기 위한 언어가 아니라 데이터를 표현하기 위한 언어이다. 때문에 변수나 객체를 선언해서 사용하지 않고 기존의 데이터를 가져와서 사용하는 방법이다.
3. JSTL(Jsp Standard Tag Library) vs EL(Expression Language)  
   * JSP 2.0버전에서 새로 추가된 스크립트 언어인 EL(Expression Language)은 <%= abc %>를 ${abc}로 간단하게 사용
   * JSTL의 Core에서 c를 이용해 <%= if%>문을 <c:if>, <%=for%>문을 <c:forEach>로 대체하여 사용


#### 1. html5 header, nav, section, footer 구조를 사용해서 기존에 회원가입의 form을 이용해서 사용하기.

> html5_form.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style> 
        header {
            background-color : lightgrey;
            height : 100px;
        }
        nav {
            background-color : #339999;
            color : white;
            width : 200px;
            height  :300px;
            float : left;
            margin-right : 20px;
        }
        section {
            /* width : 200px; */
            width : 100%;
            text-align : left;
            /* float : left; */
            padding : 10px;
        }
        footer {
            background-color : #FFCC00;
            height : 100px;
            clear : both;
        }
        header, nav, section, footer { text-align : center;}
        header, footer { line-height : 100px;}
        /* nav, section { line-height : 240px;} */
    </style>
</head>
<body>
    <h1>HTML5 레이아웃</h1>
    <header>
        <h2>HEADER 영역</h2>
    </header>
    <nav>
        <h2>NAV 영역</h2>
    </nav>
    <section>
        <p>SECTION 영역</p>
        <form action="/Day0525MON/MemberServletInsert" method="post">
            <fieldset>
               <legend>회원가입</legend>
               <p>아이디 : <input type="text" size="12" maxlength="8" name="id"></p>
               <p>비밀번호 : <input type="password" size="12" maxlength="8" name="pw"></p>
               <p>메일 수신 여부 : 
                  <input type="radio" value="y" name="receive">Y
                  <input type="radio" value="n" name="receive">N
               </p>
               <p>관심분야 : 
                 <input type="checkbox" value="HTML" name="chk">HTML
                 <input type="checkbox" value="CSS" name="chk">CSS
                 <input type="checkbox" value="JavaScript" name="chk">JavaScript
               </p>
               <input type="image" src="" alt="검색">
               <input type="submit" value="전송">
               <input type="reset" value="취소">
               <input type="button" value="확인">         
            </fieldset>
         </form>
    </section>
    <footer>
        <h2>FOTTER 영역</h2>
    </footer>
</body>
</html>
```

#### 2. JSP(Java Server Page) : 즉 Servlet 과 같다.
* Servlet 은 java 코드 안에서 html을 넣었다.
* JSP 는 html 구조에서 java 코드를 넣는 즉 스크립트 언어를 넣어서 사용하는 것이다.

> ex1.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    안녕하세요. JSP 페이지 입니다.
  
  <%= "\n안녕하세요\n" %>
  <%
  	out.println("안녕하세요");
  %>
</body>
</html>
```

#### 2.1 
* import tag 일단은 이렇게 생각! : <%@ %>
   * page
   * taglib
   * include 
* 선언태그 : <%! int a; %>
* 설정태그 : <% a = 10; out.println("출력"); %> 
* 출력태그 : <%= "양의 값 (두 번째)  %>
* 출력 태그가 설정 태그의 out.println() 과 같은 반복을 줄이기 위해 사용한다.
* JSP의 내장 객체 

> ex2.html

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
	// 선언태그
		int a;
		int b;
	%>
	
	<%
	// 설정태그
		a = 10;
		b = 110;
		if ( b / a > 10){
	%>
	<p>양의 값 (첫 번째)</p><br>
	<%--출력태그 --%>
		<%= a + " 양의 값 (두 번째 )" %><br>
		<% out.println("다시 양의 값(세 번째)"); %>
	
	<%
		} else {
			
	%>
	음의 값
	<%
		}
	%>
	
	<%
		
	%>
</body>
</html>

```

#### 2.2
* Presentation Logic
* Business Logic

#### 2.3 JSP 문법을 사용해서 2~9단 작성하기 + Calendar 객체를 사용해서 현재 연 월 일 시 분 초 출력해보기.
* Calendar calendar = Calenar.getInstance() 와 같은 경우는 싱글톤의 유형이다. getInstance()는 주로 싱글톤에서 사용한다.
* int days = calendar.get(Calendar.DAY_OF_WEEK); => switch 문으로 String형으로 데이터를 받아서 처리하기.

> ex3.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int mon = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		int date = calendar.get(Calendar.DATE);
		int days = calendar.get(Calendar.DAY_OF_WEEK); 
		String[] yoil = {"일", "월", "화", "수", "목", "금", "토"};
	%>
	<table border="5">
			<tr>
				<% 
					for (int i = 2; i <=9; i++){
				%>
					<td><%= i + "단" %></td>
				<%
					}
				%>
			</tr>
	<%
		for (int i=1; i <=9; i++){
	%>
			<tr>
	<% 
			for (int j=2; j<=9; j++){
	%>	
				<td>
					<%= 
					j + " * " + i + " = " + i * j
					%>
				</td>
	<%
			}
	%>
			</tr>
	<%
		}
	%>
	</table>
	   <p align="center">현재시간</p>
	   <p align="center">
		  <%= year + "년 " + mon + "월 " + day + "일 " 
	   	  + hour + "시 " + min + "분 " + sec + "초 " +
				  "오늘은 (" + yoil[days - 1] + ") 요일입니다." %>
	   </p>
	   <p align="center">
	      <%= date %>
	   </p>
	   
</body>
</html>
```





