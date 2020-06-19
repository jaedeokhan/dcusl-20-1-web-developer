## 2020 0619 FRI 

### Today
1. dogShopping QtyDown 구현 => js function checkQty()를 정의해서 1보다 크면 감소시키기
2. 실질적인 Deploy 경로이다. 
* C:\Users\330-15\Desktop\20_1_WEB_Developer\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps
3. dogRegist 구현 => DefaultFileRenamePolicy() 이용
4. Part API 사용해서 upload 구현하기. = Servlet 제공 API
5. expression tag = <%= %> <% %> 이 것이다. 요 놈들을 EL, JSTL을 사용해서 변경해보기!
   * EL : Servlet 자체에서 제공되는 것 
   * JSTL 

### Error
1. dogRegist.jsp에서 form action으로dogRegist.dog 요청을 계속 보냈지만, 아무것도 받지 못한 이유는 DogFrontController에서 정확한 url로 받아주지 않았다.
   * requestURL = /MyProject/dogRegist.dog 
   * contextUrl =  /MyProject
   * contextUrl 만큼 requestURL을 subString하기에 /dogRegist.dog를 command 변수가 가지게 된다.
   * 그래서, / 를 명확하게 해주지 않아서 에러가 발생한다.

> 수정 전 코드

```java
else if (command.contentEquals("dogRegist.dog")) {}
```

> 수정 후 코드

```java
else if (command.contentEquals("/dogRegist.dog")) {}
```

#### 1. dogShopping QtyDown => javascript, checkQty function을 통해서 1보다 클때만 감소하게 설정!

#### 1.1 dogCartList.jsp

> dogCartList.jsp

```html
<a href="javascript:checkQty('<%= cartList.get(i).getId() %>',<%=cartList.get(i).getQty()%>)">
   <img src="image/down.jpg" class="upDownImage">0619
</a>   
```

```html
   function checkQty(id, qty){
	   if (qty > 1 ){
		  location.href = "dogCartQtyDown2.dog?id=" + id;   
	   }
   }
```

#### 1.2 js 에서 각각의 id의 dogCartQtyDown2.dog로 요청을 하면 처리한다.

> DogFrontController.java

```java
		else if (command.contentEquals("dogRegist.dog")) {
			// 모든 요소를 처리할 수 있게끔 다형성을 이용한다. 
			action = new DogRegistAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

#### 1.3 Action
* 해당하는 id 파라미터의 해당하는 것을 Qty를 다운시켜주기 위해서 Service단으로 넘긴다.

> DogCartQtyDown2Action.java

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyDown2Service;
import vo.ActionForward;

public class DogCartQtyDown2Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		DogCartQtyDown2Service dogCartQtyDown2Service =
				new DogCartQtyDown2Service();
		
		dogCartQtyDown2Service.downQty(request, id);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("dogCartList.dog");
		forward.setRedirect(true);
		
		return forward;
	}

}

```

#### 1.4 Service
* Action으로부터 request를 받아서 session 영역에 있는 cartList ArrayList 객체를 불러와서 id가 존재하는지 체크하고 해당 id에 Qty를 감소시켜준다.

> DogCartQtyDown2Service.java

```java
package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;

public class DogCartQtyDown2Service {

	public void downQty(HttpServletRequest request, String id) {
		
		HttpSession session = request.getSession();
		ArrayList<CartVO> cartList = (ArrayList<CartVO>)session.getAttribute("cartList");
		
		for (int i = 0; i < cartList.size(); i++) {
			if(Integer.parseInt(id)==cartList.get(i).getId()) {
				cartList.get(i).setQty(cartList.get(i).getQty()-1);
			}
		}
	}
}
```

#### 2. Deploy 주소!

#### 3. DogRegist 구현하기 => 이미지 업로드!

#### 3.1 dogRegist.jsp
* kind, price, image, country, height, weight, content 항목!


> dogRegist.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #registFormArea {
      width : 800px;
      margin : auto;
      border : 1px solid blue;
   }
   table {
      width : 780px;
      margin : auto;
   }
   .td_left{
      width : 200px;
      
   }
   .td_right{
      width : 580px;
   }
   h1 {
      text-align : center;
   }
</style>
<script>
   function checkPasswd(){
         // 1. form 태그로 접근하는 방법
	     //var passwd1 = document.myForm.passwd1;
         //alert(passwd1.value);
         
         // 2. id로 바로 접근하는 방법
         var passwd1 = document.getElementById("passwd1");
         var passwd2 = document.getElementById("passwd2");
         var errorMessageArea = document.getElementById("errorMessageArea");
         
         if (passwd1.value != passwd2.value){
        	 errorMessageArea.innerHTML = "<font color='red'>비밀번호가 일치하지 않습니다.</font>";
         }
         else {
        	 errorMessageArea.innerHTML = "";
         }
         
   };
   
   function checkId(){
       var id = document.getElementById("id");
       
       // 윈도우를 띄워서 요청 URL을 준다. 즉 Servlet을 호출한다.
       window.open("IdCheck?id=" + id.value, "window1", "width=400, height=300");
   };
   
   function findZip(){
	   window.open("zipFind.jsp", "window", "width=400, height=300");
   };
   
</script>
</head>
<body>

<section id="registFormArea">
   <h1>회원가입</h1>
   <form action="dogRegist.dog" method="POST" name="myForm" encType="multipart/form">
   <table>
      </tr>
            <tr>
         <td class="td_left">
            <label for="kind">품종 : </label>
         </td>
         <td class="td_right">
            <input type="password" name="kind" id="kind"/>
         </td>
      </tr>
      </tr>
            <tr>
         <td class="td_left">
            <label for="price">가격 : </label>
         </td>
         <td class="td_right">
            <input type="number" name="price" id="price"/>
         </td>
      </tr>
      </tr>
      <tr>
         <td class="td_left">
            <label for="addr1">이미지 : </label>
         </td>
         <td class="td_right">
            <input type="file" name="image" id="image"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="country">원산지 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="country" id="country"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="height">신장 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="height" id="height"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="weight">체중 : </label>
         </td>
         <td class="td_right">
            <input type="text" name="weight" id="weight"/>
         </td>
      </tr>
       <tr>
         <td class="td_left">
            <label for="content">상품설명 : </label>
         </td>
         <td class="td_right">
            <textarea row="20" cols="40" name="content" id="content">
            </textarea>
         </td>
      </tr>   
      <tr>
         <td colspan="2" style="text-align : center">
            <input type="submit" value="회원가입"/>
         </td>
      </tr>
      <div id="errorMessageArea"></div>           
   </table>
   </form>
</section>
</body>
</html>
```

#### 3.2 form을 보매녀 dogRegist.dog 요청을 FC에 받는다.

> DogFrontController.java

```java
		else if (command.contentEquals("dogRegist.dog")) {
			// 모든 요소를 처리할 수 있게끔 다형성을 이용한다. 
			action = new DogRegistAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

#### 3.3 FC => Action
* 파일 업로드를 위해 필요한 cos.jar 관한 변수를 설정한다.
   * realFolder : 파일이 업로드 될 서버상의 물리적인 경로
   * saveFolder : 파일이 업로드 될 상대 경로
   * encType : 인코딩 처리
   * maxSize = 5 * 1024 * 1024 => 5MB
* 애플리케이션 하나 당 할당되는 컨텍스트(환경설정) 정의
   * ServletContetx 의 context를 사용

> DogRegistAction.java

```java
package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.DogRegistService;
import vo.ActionForward;
import vo.DogVO;

public class DogRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// cos.jar을 이용해서 정의
		String realFolder = ""; // 파일이 업로드 될 서버상의 물리적인 경로 
		String saveFolder = "image"; // 파일이 업로드 될 상대경로
		String encType = "UTF-8"; // 파일이름의 한글 처리 => 한글이 안깨지게!
		int maxSize = 5 * 1024 * 1024; // 5MB
		
		// 애플리케이션 하나 당 할당되는 컨텍스트(환경설정)
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = 
				new MultipartRequest(request, realFolder, maxSize, encType,
						             new DefaultFileRenamePolicy());
		// RenamePolicy => 1.zip , 2.zip, 3.zip 이런 형식으로 이름을 배정한다.
		
		// Client에서 데이터를 받아온다.
		String kind = multi.getParameter("kind");
		int price = Integer.parseInt(multi.getParameter("price"));
		String image = multi.getParameter("image");
		String country = multi.getParameter("country");
		int height = Integer.parseInt(multi.getParameter("height"));
		int weight = Integer.parseInt(multi.getParameter("weigth"));
		String content = multi.getParameter("content");
		
		// Client의 데이터를 객체에 모두 담는다.
		DogVO dogVO = new DogVO();
		dogVO.setKind(kind);
		dogVO.setPrice(price);
		dogVO.setImage(image);
		dogVO.setCountry(country);
		dogVO.setHeight(height);
		dogVO.setWeight(weight);
		dogVO.setContent(content);
		
		DogRegistService dogRegistService = new DogRegistService();
		
		boolean registSuccess = dogRegistService.registDog(dogVO);
		ActionForward forward = null;
		
		if (registSuccess) {
		   forward = new ActionForward();
		   forward.setRedirect(true);
		   forward.setUrl("dogList.dog");
		}
		else {
		   response.setContentType("text/html; charset=UTF-8");
		   PrintWriter out = response.getWriter();
		   out.println("<script>");
		   out.println("alert('등록실패')");
		   out.println("history.back()");
		   out.println("</script>");
		}
		
		return forward;
	}

}
```

#### 3.4 Service
* DAO 에 insertDog를 요청해서 insertCount가 1 이상이면 registSuccess를 true를 하고, commit를 한다.

> DogRegistService.java

```java
package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
import vo.DogVO;

public class DogRegistService {

	public boolean registDog(DogVO dogVO) {
		
		boolean registSuccess = false;
		
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
		
		int insertCount = dogDAO.insertDog(dogVO);
		if (insertCount > 0) {
			commit(con);
			registSuccess = true;
		}
		else {
			rollback(con);
			
		}
		return registSuccess;
	}

}

```

#### 3.5 DogDAO
* INSERT INTO dog(테이블이름)  VALUES(테이블 값);

> DogDAO.insertDog(dogVO)

```java
	public int insertDog(DogVO dogVO) {
		
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO dog(id, kind, price, image,"
				  + " country, height, weight, content, readcount)"
		     	  + " VALUES(dog_seq.nextval,?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, dogVO.getKind());
		    pstmt.setInt(2, dogVO.getPrice());
		    pstmt.setString(3, dogVO.getImage());
		    pstmt.setString(4, dogVO.getCountry());
		    pstmt.setInt(5, dogVO.getHeight());
		    pstmt.setInt(6, dogVO.getWeight());
		    pstmt.setString(7, dogVO.getContent());
		    pstmt.setInt(8, 0);
		    
		    
			insertCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}
```

#### 4. Servlet에서 기본적으로 제공하는 upload 관련 API를 사용
* Part Interface

#### 4.1 partUploadForm

> partUploadForm.html

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
<form action="upload.do" method="POST" encType="multipart/form-data">
       설명 : <input type="text" name="description" /><br>
       첨부파일 : <input type="file" name="attachment" /><br>
    <input type="submit" value="파일업로드" />
</form>

</body>
</html>
```

#### 4.2 FileUploadServlet
* Part API

> FileUploadServlet.java

```java

```

#### 5. EL, JSTL 

#### 5.1 EL 사용의 장점은 변수 정의를 안해도 된다.

> writePro.jsp => basic

```java
<%
   ReplyVO replyVO = (ReplyVO)request.getAttribute("replyVO");
   int num = replyVO.getNum();
   int ref = replyVO.getRef();
   int re_step = replyVO.getRe_step();
   int re_level = replyVO.getRe_level();
%>
```

> writePro.jsp => EL 

```java

```

#### 5.2 list.jsp => EL 변환

> 

```html

```

> 

```html

```




