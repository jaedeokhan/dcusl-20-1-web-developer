### 2020 0519 TUE 
1. Servlet이란?
   * web.xml : Servlet이 어디 있는지 매핑을 해준다. 컨테이너가 있는지 확인하고 매핑을 한 Servlet을 찾는다. 나중에 너무 많아져서 복잡해진다.
      * web.xml이 Servlet 에서 JSP로 전환이 된 이유이기도 하다.
   * Sevlet은 View 역할을 하지 않고, 비즈니스 로직을 처리한다.   
2. JSP
   * 프레젠테이션 로직
3. Framework 
   * 가이드 라인이라고 볼 수 있다.
   * MVC pattern(Model, View, Controller)
4. Servlet과 JSP의 사용의 구분
5. reload 문제 => 보통 server를 재가동하면 해결이 된다.
6. 개체 직렬화 : 데이터를 쪼개서 물리적인 신호로 바꿔서 보내는 것이다.
   * 유익한 점 : 나의 PC에서 사용하는 데이터를 원격지에 전송을 하고 싶다면?
      * Java에서 String은 객체 직렬화 인터페이스를 구현하고 있어서, 사용 가능
      * 객체가 충돌하지 않도록, 사용해주는 것이다.
7. request 즉 사용자의 요청의 종류
   * Get  : 따로 정보를 받지는 않고, 서버에 있는 것을 그대로 전달을 해준다.
   * Post : 사용자의 정보를 받아서 정보를 수정하는 것이다. (Ex:로그인)
      * Get 방식보다는 조금 더 보안적으로 요청을 하는 것이다.


#### 1. Servlet이란?
@WebServlet("/ServletEx1") : Servlet을 사용하기 위해서 선언해준다.

Servelt은 순환 주기로 각각의 메소드를 체크한다.

> ServeltEx1.java 

```java
package ex1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEx1
 */
@WebServlet("/ServletEx1")
public class ServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEx1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

```

#### 1.1 Standard Stream = 표준 (IO 클래스)
PrintStream 의 부모는 OutStream 이다.

#### 1.2 PrinterWriter => 원격지에서 입출력을 사용하기 위한 것이다.
PrinterWriter out = response.getWriter(); 요 놈을 이용해서 개체 직렬화를 사용해서 출력을 하는 것이다.

> Servelt1.java

```java
package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEx1
 */
@WebServlet("/ServletEx1")
public class ServletEx1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 어떤 언어, 문자셋을 설정해주는 것(setContentType("text/html; charset=utf-8"))
		response.setContentType("text/html; charset=utf-8");
		// 위의 방법으로 Encoding을 설정하기도 하지만, 아래와 같이 따로줘도 된다.
//		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("Hello Servelt!");
		out.println("<html>"+"<body>"+"<h1>서블릿 간단 작성 예제입니다. </h1> "+"</body>"+"</html>");
// 외부장치에 사용에 있어서는 open을 해준다 즉 사용을 한다면 항상 자원을 close()를 해줘야 한다.
		out.close();
		
	}

}
```

#### 2. HTML
// form method 의 default는 get 방식이다.
get 방식은 검색 엔진에서 ?=파라미터 값들이 나열되면서 보이는 것이다.

> htmlEx1.html

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>HTML 20200519</title>
	</head>

	<body>
		<form action="/Day0518/ServletEx1">
			<input type="id"/>
		    <input type="password"/>
			<input type="submit" value="전송"/>
		</form>
		
		<span><a href="ServletEx1">여기를 클릭하면 /Day0518/ServletEx1로 go</a></span>
		
		<form action="/Day0518/HelloServlet" method="get">
			<input type="submit" name="id" value="전송"/>
		</form>
		
		<form action="/Day0518/HelloServlet" method="post">
			<input type="submit" value="전송"/>
		</form>
	</body>
</html>
```



#### 3. request
학습 목표 : 오늘은 id, password 
request 객체 method 사용 & response 객체 method 사용
request.getParameter(String name)
request.getParameterNames()
request.getParameterValues(String name)

Servlet2에서는 데이터를 받아서 그려준다.

> Servlet2.java

```java
package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	/*
	 * 학습 목표
	 * request 객체 method 사용 & response 객체 method 사용
	 * request.getParameter(String name)
	 * request.getParameterNames()
	 * request.getParameterValues(String name)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET 방식보다는 보안성이 있는 POST 방식을 사용한다.
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// Client에서도 마찬가지로 utf-8 설정
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		out.println("<html><head><title>폼 연습 서블릿</title></head>");
		out.println("<body><center>");
		// Servlet은 자바코드에서 html 문서를 넣는다. JSP는 반대이다.
		out.println(" id : " + id + "<br>" );
		out.println(" pw : " + pw + "<br>" );
		out.println("</center></body></html>");
		out.close();
		
	}

}
```

#### 3.1 htmlEx2.html에서는 action 으로 Servlet2 로 POST 방식으로 요청을 한다.

> htmlEx2.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/Day0518/Servlet2" method="POST">
	  <input type="text" name="id"/><br>
	  <input type="text" name="pw"/>
	  <input type="submit" value="전송"> 
	</form>
</body>
</html>
```


