### 2020 0521 THU 
### 학습 내용
1. table
2. html , servlet
3. DB

#### 1.1 table 사용하기.

> ex12.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Table Create</title>
<style>
   #test1 {
      color : powderblue;
   }

   .test2 {
      color : blue;
   }
</style>
</head>
<body>
   <h1>표 만들기</h1>
   <h2>1행 1열 표 만들기</h2>
   <table border="1">
      <tr>
<!--          <th></th> -->
         <td>입력</td>
      </tr>
   </table>
   
   <h2>1행 2열 표 만들기</h2>
   <table border="1">
      <tr>
         <td>1행 1열</td>
         <td>1행 2열</td>
      </tr>
   </table>
   
   <h2>2행 1열 표 만들기</h2>
   <table id="test1" border="1">
      <tr>
         <td>1행 1열</td>
      </tr>
      <tr>
         <td>2행 2열</td>
      </tr>
   </table>
   
   <h2>2행 2열 표 만들기</h2>
   <table class="test2" border="1">
      <tr>
         <td>1행 1열</td>
         <td>1행 2열</td>               
      </tr>
      <tr>
         <td>2행 1열</td>
         <td>2행 2열</td>
      </tr>
   </table>
</body>
</html>
```

#### 1.2 th, td 사용 (table head, table data)

> ex13.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form method="get" action="/Day0521THU/ServletEx3">
      <table border="1">
         <tr>
            <th>ID</th>
            <td>
               <input type="text" name="id">
            </td>
         </tr>
         <tr>
            <th>PW</th>
            <td>
               <input type="password" name="pw">
            </td>
         </tr>
         <tr>
            <td align="center" colspan="2">
               <input type="submit" value="전송">
               <input type="reset" value="취소">
            </td>
         </tr>
      </table>
   </form>
</body>
</html>
```

#### 1.3 rowspan, colspan

> ex14.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form method="get" action="/Day0521THU/ServletEx3">
      <table border="1">
         <tr>
            <td>
               	이름
            </td>
            <td>
               <input type="text" name="이름" size="50">
            </td>
         </tr>
         <tr>
            <td>E - mail</td>
            <td>
               <input type="text" name="이메일" size="50">
            </td>
         </tr>
         <tr>
            <td>
				자기소개
            </td>
            <td>
               <textarea name="content" cols="50" rows="10"></textarea>
            </td>
         </tr>
         <tr>
            <td align="left" colspan="2">
               <input type="reset" value="이름 확인">
            </td>
         </tr>
      </table>
   </form>   
</body>
</html>
```

#### 1.4 table Group => caption, colgroup, thead, tbody, tfoot

> ex15.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>표 만들기</h1>
   <h2>데이터 테이블</h2>
   <table border="1" width="100%">
      <caption>태블릿 PC와 스마트폰 판매현황</caption>
      <colgroup>
         <col width="20%">
         <col width="40%">
         <col width="40%">
      </colgroup>
      <thead>
         <tr>
            <th>구분</th>
            <th>태블릿 PC</th>
            <th>스마트폰</th>
         </tr>
      </thead>
      <tbody>
         <tr>
            <th>하반기 판매수</th>
            <td>3만대</td>
            <td>11만대</td>         
         </tr>
         <tr>
            <th>상반기 판매수</th>
            <td>2만대</td>
            <td>5만대</td>         
         </tr>
      </tbody>
      <tfoot>
         <tr>
            <th>총판매수</th>
            <td>5만대</td>
            <td>16만대</td>
         </tr>
      </tfoot>
   </table>
</body>
</html>
```

#### 1.5 input component => 라디오, 체크박스 연습
radio, checkbox

> ex17.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h3>라디오 , 체크박스 연습</h3><HR><HR>
   <form method="post" action="/Day0521THU/ServletEx4">
          좋아하는 색깔 <BR>
          <input type="radio" name="color" value="빨간색" checked>빨간색<BR>
          <input type="radio" name="color" value="파란색">파란색<BR>
          <input type="radio" name="color" value="노란색">노란색<BR>
          <br>
          좋아하는  과일 <br> 
          <input type="checkbox" name="fruit" value="사과">사과<BR>
          <input type="checkbox" name="fruit" value="오렌지">오렌지<BR>
          <input type="checkbox" name="fruit" value="바나나">바나나<BR>
          <input type="submit" value="내용 확인"><BR>         
   </form>
</body>
</html>
```

#### 1.6 ex18.html의 color, fruit, check 데이터를 ServeltEx3 에서 데이터 뿌리기

> ex18.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h3>* 리스트, 리스트 박스 연습 *</h3><hr><hr>
   <form method="post" action="/Day0521THU/ServletEx4">
      <h4>좋아하는 색깔</h4>
      <select name="color" size="1">
         <option value="빨간색"> 빨간색
         <option value="파란색"> 파란색
         <option value="노란색"> 노란색
      </select>
      <h4>좋아하는 과일</h4>      
      <select name="fruit" size="3" multiple>
      	 <option value="사과"> 사과
         <option value="배"> 배
         <option value="오렌지"> 오렌지
      </select>
      
     <h4>좋아하는 취미</h4>
     <input type="checkbox" name="check" value="운동">운동 
	 <input type="checkbox" name="check" value="독서">독서
	 <input type="checkbox" name="check" value="게임">게임
      
      <input type="submit" value="내용 확인"><br>
   </form>
</body>
</html>

```

> ServletEx3.java

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
 * Servlet implementation class ServletEx4
 */
@WebServlet("/ServletEx4")
public class ServletEx4 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String fruit;
		String color;
		String hobbyCheck[];
		String hobby = "";
		
		
		PrintWriter out;
		color = request.getParameter("color");
		fruit = request.getParameter("fruit");
		hobbyCheck = request.getParameterValues("check");
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hobbyCheck.length; i++) {
			sb.append(hobbyCheck[i]);
			// hobbyCheck 
			if (hobbyCheck.length > i + 1) {
				sb.append(", ");
			}
		}
		hobby = sb.toString();
		
		response.setContentType("text/html;"); 
		response.setCharacterEncoding("UTF-8");
		 
		out = response.getWriter();
		
		out.println("<html><head><title>리스트</title></head>");
		out.println("<body><center>");
		out.println("<h2>색깔, 과일, 취미</h2>");
		out.println("<table border='1'>"
				+ "<tr>"
				+ "<th>색깔</th>"
				+ "<td>" + color + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<th>과일</th>"
				+ "<td>" + fruit + "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<th>취미</th>"
				+ "<td>" + hobby + "</td>"
				+ "</tr>"
				+ "</table>");
		out.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
		
	}

}

```


