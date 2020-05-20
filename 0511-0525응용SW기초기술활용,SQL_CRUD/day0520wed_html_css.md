### 2020 0520 WED 
* html
   * input
      * id
      * name
   * select
      * option
   * textarea
* Servlet : request, response UTF-8 설정



#### 1. ServletEX3.java와 htmlEx3.html을 사용해서 form table 을 이용하기.

#### 1.1 htmlEx3.html에서 데이터를 받아서 출력하기.
component 선정 => radio, checkbox 

> ServletEX3.java

```java
package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServeltEx3")
public class ServeltEx3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String check[];
		String checks = "";
		String fruit;
		String content;
		
		PrintWriter out;
		check = request.getParameterValues("check");
		fruit = request.getParameter("fruit");
		content = request.getParameter("content");
		java.util.Enumeration data = request.getParameterNames();
		// data에 ParameterNames가 존재한다면
//		while (data.hasMoreElements()) {
//			// 다음 요소로 이동
//			data.nextElement();
//			
//		}
		for (int i = 0; i < check.length; i++) {
			checks += check[i];
			if (check.length > i + 1) {
				checks += ", ";
			}
		}
		// StringBuilder로 바꿔보기.
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < check.length; i++) {
//			sb.append(check[i]);
//			if (check.length > i + 1) {
//				sb.append(",");
//			}
//		}
		
		response.setContentType("text/html;");
		response.setCharacterEncoding("UTF-8");
		out = response.getWriter();
		while (data.hasMoreElements()) {
			out.println(data.nextElement());
		}
		
		out.println("<html><head><title>폼 연습 서블릿</title></head>");
		out.println("<body><center>");
		out.println("취미 : " + checks + "<br>");
		out.println("선택 : " + fruit + "<br>");
		out.println("내용 : " + content + "<br>");
		out.println("</body></html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```

#### 1.1.1 Java 의 String 종류
* String
* StringBuffer
* StringTokenizer : 위의 while 구문과 비슷하다. 
* 1개 더 있다.

#### 1.2 html => form, table, input, select, textarea 여러 태그 사용하기.

> htmlEx3.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Test</title>
</head>
<body>
   <center>
      <form action="/Day0512WED/ServletEx3" method="post">
	     <table border="2">
	        <tr>
	           <th>취미</th>
	           	<td> 
				   <!-- 배열로 가져올 것이다. -->
	           	  <input type="checkbox" name="check" value="운동">운동 
	           	  <input type="checkbox" name="check" value="독서">독서
	           	  <input type="checkbox" name="check" value="게임">게임
	           </td>
	        </tr>
	           <th>직업</th>
	           <td>
	              <select name="fruit">
	                 <option value="사과">사과</option>
	                 <option value="딸기">딸기</option>	 
	                 <option value="배">배</option>	                              
	              </select>
	           </td>
	        </tr>
	        <tr>
	           <th>소개</th>
	           <td>
	              <textarea name="content" cols="30" rows="10"></textarea>
	           </td>
	        </tr>
	        <tr>
	           <td colspan="2" align="center"> 
	              <input type="submit" value="OK">
	              <input type="reset" value="cancel">
	           </td>
	        </tr>
	     </table>
      </form>
   </center>
</body>
</html>
```

#### 2. html 연습

#### 2.1 block 요소인 h1, p , br 같은 태그들은 사용하면 자동 줄바꿈이 일어나는 블록 형식이다.

> ex1.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>블록 요소</h1>
   <h2>블록 요소 성질</h2>
   <p>앞뒤로 줄 바꿈이 생깁니다.</p>
   <p>줄바꿈이 <br> 생깁니다.</p>
</body>
</html>
```

#### 2.2 div, span a, strong 은 줄 바꿈이 일어나지 않는 인라인 형태이다.

> ex2.html
 
```html

```

#### 2.3 h1-h6 인 Heading 태그를 사용해본다.

> ex3.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>반응형 웹사이트 제작 1</h1>
	<h2>반응형 웹사이트 제작 2</h2>
	<h3>반응형 웹사이트 제작 2</h3>
	<h4>반응형 웹사이트 제작 2</h4>
	<h5>반응형 웹사이트 제작 2</h5>
	<h6>반응형 웹사이트 제작 2</h6>
</body>
</html>
```

#### 2.4 


#### 3. css Selector
1. 공통 선택자(Universal Selector) : * 로 표현
2. 타입 선택자(Type Selector)
3. ID 선택자 (ID Selector)
4. Class 선택자 (Class Selector)

> ex11.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

div {
   color : blue;
}

#gray_test {
	color : red;
}

.class_test {
	color : yellow;
}
</style>
</head>
<body>
   -공통 선택자(Universal Selector)는 * 로 표현되는 선택자입니다.
   <div>
      <p>Test</p>
   </div>
   
   <div id="gray_test">
      <p>id change</p>
   </div>
   
   <div class="class_test">
      <p>Class Change</p>
   </div>
</body>
</html>
```
