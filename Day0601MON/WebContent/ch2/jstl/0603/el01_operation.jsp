<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css">
<title>Insert title here</title>
</head>
<body>
<p><a href="el02.jsp">[다음]</a></p>
<h2>EL 연산자</h2>
<%
	// 데이터 정의
   pageContext.setAttribute("title", "EL 연산자!"); 
%>
<table>
   <tr><th>분류</th><th>연산자</th><th>EL 코드 = 실행 결과</th></tr>
   <tr><td>산술</td><td>+, -, *, /(div), %(mod)</td>
   	   <td>
   	      <pre>
   	         \${10 + 20} = ${10 + 20 }
   	         \${10 - 20} = ${10 - 20 }
   	         \${10 * 20} = ${10 * 20 }
   	         \${10 / 20} = ${10 / 20 }
   	         \${10 % 20} = ${10 % 20 }
   	         \${10 mod 20} = ${10 mod 20}
   	      </pre>  
   	   </td>
   </tr>
   <tr><td>관계</td><td>==(eq), !=(ne), &lt;(lt), &gt;(gt),<br>
           &lt;=(le), &gt;=(ge)</td>
       <td>
          <pre>
             \${10 == 11 } = ${10 == 11}
          </pre>
       </td>
   </tr>
      <tr><td>Empty 검사</td><td>empty</td>
       <td>
          <pre>
             \${empty title} = ${empty title}
             \${empty title2} = ${empty title2}
          </pre>
       </td>
   </tr>
      <tr><td>삼항연산자</td><td>a > b ? a : b</td>
       <td>
          <pre>
             \${a >b ? a : b} = ${15 > 10 ? 15 : 10}
          </pre>
       </td>
   </tr>
</table>
</body>
</html>