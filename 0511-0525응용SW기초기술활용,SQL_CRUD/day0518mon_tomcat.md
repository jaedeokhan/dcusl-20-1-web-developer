### 2020 0518 MON

#### 1. Client 와 Server
* request
   * GET 방식 : url을 사용해서 서버에서 정보를 가져오는 방식(게시판 보기)
   * POST 방식 : 사용자로부터 정보를 받아, 서버에 입력하거나 수정하는 방식
* response 
* Static Page
* Dynamic Page
* 1 Tier, 2 Tier, 3 Tier
* WAS(Web Aplication Server)
* html, css, javascript 


#### 2. Tomcat install 

#### 2.1 Tomcat 홈페이지 이동=>  https://tomcat.apache.org/download-80.cgi 
9 version 으로 선택

#### 2.2 Tomcat installer 를 이용해서 download 하기.
![image](https://user-images.githubusercontent.com/45028904/82199174-4d296c00-9938-11ea-9bd2-b81fcdd9b6bd.png)

#### 2.3 user, password는 일단 pass

#### 2.4 Activate, Shutdown

#### 3. Dynamic Web Project Create 
Maven 은 나중에 한다. Maven은 센터에서 원격으로부터 내려받아서 라이브러리를 설치하는 것이다.
* Java Resource

#### 3.1 Java에Tomcat 8v 추가하기.
![image](https://user-images.githubusercontent.com/45028904/82209612-810c8d80-9948-11ea-8cd8-a1ee8b897ec4.png)

#### 3.1.1 Brower에서 선택해서 폴더 고르기
![image](https://user-images.githubusercontent.com/45028904/82209625-88cc3200-9948-11ea-9e9f-1ec4dcc95d0d.png)

#### 3.2 Project에서 Properties
웹 서버를 이클립스에 구동한 것이다.
![image](https://user-images.githubusercontent.com/45028904/82209874-ff692f80-9948-11ea-84c6-bd4a7ed2b4f4.png)

#### 3.3 Servel 가동하기
서버를 가동하면 Several ports (8005, 8080, 8009) required by Tomcat v8.5 Server at localhost are already in use. 
즉 was 를 위한 포트가 이미 사용중이여서 그렇다.

#### 3.3.1 문재해결방법

```cmd
# 연결되있는 포트번호 확인
> netstat -ano -p tcp

# 현재 8080을 사용중이 포트 pid를 직접 taskkill을 해준다.
> taskkill /f /pid PID

# 해당 프로세스가 종료되었는지 다시 포트를 확인한다.
> netstat -ano -p tcp
```

#### 4. Servlet 을 사용하기.
Servlet이란?
Servlet과 JSP의 개념
기능의 차이는 없고 역할의 차이만 있다. (하는 일은 동일)

Servlet이란?
웹 기반의 요청에 대한 동적인 처리가 가능한 Server Side에서 돌아가는 Java ProgramJava 코드 안에 HTML 코드 ( 하나의 클래스)
웹 개발을 위해 만든 표준

JSP란?
Java 언어를 기반으로 하는 Server Side 스크립트 언어
HTML 코드 안에 Java 코드
Servlet를 보완하고 기술을 확장한 스크립트 방식 표준
   * Servlet 의 모든 기능 + 추가적인 기능

Servlet과 JSP의 차이
Servlet 
Java 코드안에 HTML 코드 (하나의 클래스)
data processing(Controller)에 좋다.
즉 DB와의 통신, Business Logic 호출, 데이터를 읽고 확인하는 작업 등에 유용하다.
Servlet이 수정된 경우 Java 코드를 컴파일(.class 파일 생성)한 후 동적인 페이지를 처리하기 때문에 전체 코드를 업데이트하고 다시 컴파일한 후 재배포하는 작업이 필요하다.(개발 생산성 저하)

JSP
HTML 코드 안에 Java 코드
presentatation(View)에 좋다.
즉 요청 결과를 나타내는 HTML 작성하는데 유용하다.
JSP가 수정된 경우 재배포할 필요가 없이 WAS가 알아서 처리한다.(쉬운 배포)



#### 4.1 Servlet 생성하기.
![image](https://user-images.githubusercontent.com/45028904/82212992-6e955280-994e-11ea-9e8e-85aecfce02c6.png)

#### 4.1.1 URL mapping default
![image](https://user-images.githubusercontent.com/45028904/82213233-caf87200-994e-11ea-8ee1-85e3c66bb64e.png)

#### 4.1.2 서블릿 파일 하나 만들어서 사용해보기.
아래 Servlet 파일의 default이다.

> HelloServlet.java

```java
package ex1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
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





