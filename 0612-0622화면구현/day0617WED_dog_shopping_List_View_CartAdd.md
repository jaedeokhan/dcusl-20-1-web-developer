## 2020 0617 WED 

## Today
1. dogList의 목록을 출력하기   
2. dogList View 처리하기 
3. dog 오늘 본 상품 처리하기 =>  Cookie 사용 
4. dog ReadCount 처리하기 => DogView 단에서 처리해준다. 
5. dog CartAdd => 장바구니 추가!

## error
1. missing syntax => SELECT * FROM WHERE id=? : WHERE 뒤에 id라는 속성을 줘야하는데 주지 않았다.
2. dogView.do?id=1 으로 Parameter 가 넘어가고, DB 작업이 성공했지만, dogView.jsp 로 forward를 이루어 지지 않았다. => Action단에서 dogVO 속성을 옳게 넘겼지만, dogView.jsp에서 getAttribute("DogVO")를 해서 제대로 얻어오지 못했다. 
3. dogList.jsp 에서 h1 태그가 아래에 작성했는데, 계속해서 위에 있는 이유는 <table>을 </table> 제대로 닫아주지 않아서!!



#### 1. 어dogList.jsp에서 어제 한것과는 조금 다르게 읽어 들이기.
* if (i != 0 && ( i + 1 ) % 4 ==0) :  1가 0아니고, 4의 배수가 i로 나머지가 떨어진다면, 다음 <tr>로 보낸다.

> dogList.jsp

```html
<%@page import="vo.DogVO"%>
<%@page import="dao.DogDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   ArrayList<DogVO> dogList = (ArrayList<DogVO>)request.getAttribute("dogList");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #dog_table{
      width : 600px;
      margin : auto;
   }
   h1 {
   	  text-align : center;
   }
   .productImage{
      width : 150px;
      height : 150px;
   }
</style>
</head>
<body>
<%
   if (dogList == null || dogList.size() == 0){
%>
	<h1>등록된 개의 정보가 없습니다.</h1>
<%
   }
   else {
%>
	<h1>강아지 상품 목록</h1>
	<table id="dog_table" border="1"> 
    <tr>  
<% 
	   for (int i = 0; i < dogList.size(); i++){
%>		 
			<td>
			   <img src="image/<%= dogList.get(i).getImage() %>" class="productImage"><br>
			   상품명: <%= dogList.get(i).getKind() %><br>
			   가격: <%= dogList.get(i).getPrice() %>
			</td>
		<%
		  if (i != 0 && (i + 1) % 4 == 0){
		%>
		</tr>
		<%
		  }
		%>
<% 		   
	   }
   }
%>
</table>
</body>
</html>
```

#### 2. DogContent View! 
1.dogList.jsp 에서 각각의 사진을 누르면 a 링크의 각각의 dogView.dog?id=<%=dogList.get(i).getId()% > 로 요청을 한다.
2. 

#### 2.1 dogList.jsp 

> dogList.jsp

```html
<%@page import="vo.DogVO"%>
<%@page import="dao.DogDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   ArrayList<DogVO> dogList = (ArrayList<DogVO>)request.getAttribute("dogList");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #dog_table{
      width : 600px;
      margin : auto;
   }
   h1 {
   	  text-align : center;
   }
   .productImage{
      width : 150px;
      height : 150px;
   }
</style>
</head>
<body>
<%
   if (dogList == null || dogList.size() == 0){
%>
	<h1>등록된 개의 정보가 없습니다.</h1>
<%
   }
   else {
%>
	<h1>강아지 상품 목록</h1>
	<table id="dog_table" border="1"> 
    <tr>  
<% 
	   for (int i = 0; i < dogList.size(); i++){
%>		 
			<td>
			   <a href="dogView.dog?id=<%=dogList.get(i).getId()%>">
			      <img src="image/<%= dogList.get(i).getImage() %>" class="productImage">
			   </a><br>
			   상품명: <%= dogList.get(i).getKind() %><br>
			   가격: <%= dogList.get(i).getPrice() %><br>

			</td>
		<%
		  if (i != 0 && (i + 1) % 4 == 0){
		%>
		</tr>
		<%
		  }
		%>
<% 		   
	   }
   }
%>
</table>
</body>
</html>
```

#### 2.2 FC(Front Controller) 에서는 dogList.jsp에서 각각의 이미지를 누르면 dogView.dog?id 요청을 받는다.

> DogFrontController.java

```java
		else if (command.contentEquals("/dogView.dog")) {
				// 모든 요소를 처리할 수 있게끔 다형성을 이용한다. 
				action = new DogViewAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
			}
```
 
#### 2.3 Action 
* dogList.jsp 에서 넘어오는 id를 파라미터로 받아서 Service, DAO 단으로 넘긴다.
* 속성 전달과, 포워드 처리를 해준다.

> DogViewAction.java

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogViewService;
import vo.ActionForward;
import vo.DogVO;

public class DogViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		DogViewService dogViewService =
				new DogViewService();
		
		DogVO dogVO = dogViewService.getDogVO(id);
		
		
		// JSP 에서 사용할려면 공유를 해야한다.
		request.setAttribute("dogVO", dogVO);
		ActionForward forward = new ActionForward();
		forward.setUrl("dogView.jsp");
		
		return forward;
	}

}
```


#### 2.4 Service
* Connection을 관리를 해준다.
* DAO 를 통해서 DB에 요청을 한다. 

> DogService.java 

```java
package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
import vo.DogVO;

public class DogViewService {

	public DogVO getDogVO(String id) {
		
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
				
		DogVO dogVO = dogDAO.selectDogVO(id);
		
		close(con);
		
		return dogVO;
	}
}
```

#### 2.5 DAO => selectDogVO(String id)
* SELECT * FROM dog WHERE = id : id에 맞는 dog 테이블의 모든 속성을 불러와서 DogVO에 담아서 return 해준다.

> DogDAO.java

```java
	public DogVO selectDogVO(String id) {
	
		DogVO dogVO = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM dog WHERE=?";
		
		
		try {
		    pstmt = con.prepareStatement(sql);
		    pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dogVO = new DogVO();
				dogVO.setId(rs.getInt("id"));
				dogVO.setContent(rs.getString("content"));
				dogVO.setCountry(rs.getString("country"));
				dogVO.setHeight(rs.getInt("height"));
				dogVO.setImage(rs.getString("image"));
				dogVO.setKind(rs.getString("kind"));
				dogVO.setPrice(rs.getInt("price"));
				dogVO.setReadCount(rs.getInt("readcount"));
				dogVO.setWeight(rs.getInt("weight"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return dogVO;
	}
```

#### 2.6 dogView.jsp => symentic 웹 방식으로 사용한다.

> dogView.jsp 

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.DogVO" %>

<%
	DogVO dogVO = (DogVO) request.getAttribute("dogVO");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #dogViewArea {
      width : 800px;
      margin : auto;
   }
   #top {
      height : 150px;
      background : orange;
   }
   
   #main{
   	  margin-top : 10px;
   }
   #left_main{
      height : 300px;
      width : 300px;
      background : powderblue;
      margin-right : 10px;
      float : left;
   }
   #right_main{
   	  height : 300px;
   	  width : 490px;
   	  background : green;
   	  float : left;
   	  font-size : xx-Large;
   }
   #bottom{
   	  height : 150px;
   	  margin-top : 10px;
   	  background : gray;
   	  text-align : center;
   	  font-size : xx-Large;
   }
   h1 {
      text-align : center;
   }
   .bigProductImage{
      width : 300px;
      height : 300px;
   }
</style>
</head>
<body>

<section id="dogViewArea">
   <header id="top">
   	 <h1>개정보 : <%= dogVO.getKind() %></h1>
   </header>
   <section id="main">
      <section id="left_main">
         <img src="image/<%= dogVO.getImage()%>" class="bigProductImage">
      </section>
      <section id="right_main">
         
         <b>개가격 :</b><%= dogVO.getPrice()%><br>
         <b>출신지 :</b><%= dogVO.getCountry() %><br>
         <b>신장 : </b><%= dogVO.getHeight() %><br>
         <b>몸무게 : </b><%= dogVO.getWeight() %><br>
         <b>특징 : </b><%= dogVO.getContent() %>
      </section>
   </section>
   <div style="clear:both"></div>
   <footer id="bottom">
      <a href="dogList.dog">쇼핑계속하기</a>
      <a href="dogBaguni.dog">바구니담기</a>
   </footer>
</section>

</body>
</html>
```


#### 3. dog Baguni => 장바구니 처리하기
* 한 번 본 상품을 장바구니에 담기!! 
* Cookie 를 사용해서 처리한다ㅣ

#### 3.1 DogViewAction Cookie 설정하기
* DogViewAction에서 DogView와 같이 사진을 눌러서 봐야 한 번 상품을 본것이기에 ㅁCookie 생성 구문과, 유지시간을 설정해준다.
* Cookie todayImage = new Cookie("today" + id, dogVO.getImage()); => 첫 번째 인자는 이름, 두 번째 인자는 값
   * "today" + id 는 중복되지 않게 만들어주기 위해 뒤에id를 붙여서 만들어준다.
   * dogVO.getImage()를 불러와서 저장한다.

> DogViewAction.java

```java
		// 오늘 본 상품 정보를 담는 액션을 DogViewAction에서 처리한다.
		// 쿠키가 중복이 되지 않게 해준다.
		Cookie todayImage = new Cookie("today" + id, dogVO.getImage());
		// 쿠키의 default 유지 시간은 -1
		todayImage.setMaxAge(60 * 60 * 24);
		response.addCookie(todayImage);
```

#### 3.2 목록보기 요청을 들어올 때, 쿠키를 사용을 해야한다.
* client에서 넘오는 cookie는 한 두개가 아니라서, 배열로 얻어온다.
* startWith("시작하는이름") 를 사용해서 찾고자 하는 이름으로 시작하는 것을 찾는다.
* DogViewAction에서 상세보기를 하고, 이전 화면으로 돌아가면 DogListAction에서는 cookie 를 요청 받아서 처리하고 다시 속성을 넘겨준다.

> DogListAction.java

```java
		ArrayList<String> todayImageList = new ArrayList<String>();
		Cookie[] cookieArray = request.getCookies();
		
		if (cookieArray != null) {
			for (int i = 0; i < cookieArray.length; i++) {
				if (cookieArray[i].getName().startsWith("today")) {
					todayImageList.add(cookieArray[i].getValue());
				}
			}
		}
		
		
		request.setAttribute("todayImageList", todayImageList);
```

#### 3.3 dogList.jsp 에서 todayImageList를 setAttribute를 하고 조건문으로 출력해준다.
* 상단의 for문에는 list 를 출력
* 하단의 for문에서는 cookie list를 출력

```java
<%@page import="java.sql.Array"%>
<%@page import="vo.DogVO"%>
<%@page import="dao.DogDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   ArrayList<DogVO> dogList = (ArrayList<DogVO>)request.getAttribute("dogList");
   ArrayList<String> todayImageList = (ArrayList<String>)request.getAttribute("todayImageList");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   #dog_table{
      width : 600px;
      margin : auto;
   }
   h1 {
   	  text-align : center;
   }
   .productImage{
      width : 150px;
      height : 150px;
   }
   .todayImage{
      width : 100px;
      height: 100px;
   }
</style>
</head>
<body>
<%
   if (dogList == null || dogList.size() == 0){
%>
	<h1>등록된 개의 정보가 없습니다.</h1>
<%
   }
   else {
%>
	<h1>강아지 상품 목록</h1>
	<table id="dog_table" border="1"> 
    <tr>  
<% 
	   for (int i = 0; i < dogList.size(); i++){
%>		 
			<td>
			   <a href="dogView.dog?id=<%=dogList.get(i).getId()%>">
			      <img src="image/<%= dogList.get(i).getImage() %>" class="productImage">
			   </a><br>
			   상품명: <%= dogList.get(i).getKind() %><br>
			   가격: <%= dogList.get(i).getPrice() %><br>

			</td>
		<%
		  if (i != 0 && (i + 1) % 4 == 0){
		%>
		</tr>
		<%
		  }
		%>
<% 		   
	   }
   }
%>
      </table>
   <%
      if (todayImageList != null && todayImageList.size() > 0){
    	  
   %>
   <section id="todayImageArea">
   <h1>오늘 본 상품 리스트</h1>
      <table id="dog_table" border="1">
         <tr>
             <%
               for (int i = 0; i < todayImageList.size(); i++){
             %>
             <td>
                <img src="image/<%= todayImageList.get(i) %>" class="todayImage">
             </td>
             <%
               }
             %>
         </tr>
      </table>
   </section>
   <%
      }
   %>
</body>
</html>
```

#### 4. 조회수 증가 처리 => readcount += 1

#### 4.1.1 DogView단에서 DogViewService 단에서 DAO에게 updateReadCount(id) 메소드를 통해서 받는다.

> DogViewService.java

```java
package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
import vo.DogVO;

public class DogViewService {

	public DogVO getDogVO(String id) {
		
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
				
		// 조회수 증가시키는 작업
		int updateCount = dogDAO.updateReadCount(id);
		
		if (updateCount > 0) {
			commit(con);
		}
		else {
			rollback(con);
		}
		
		// 강아지 ArrayList 얻어오는 작업
		DogVO dogVO = dogDAO.selectDogVO(id);
		
		close(con);
		
		return dogVO;
	}
}
```

#### 4.1.2 DogDAO 에서 readcount 즉 조회수를 올리는 UPDATE문을 사용한다.
* UPDATE dog SET readcount = readcount + 1 WEHRE id = ? 

> DogDAO.updateReadCount(String id)

```java
	public int updateReadCount(String id) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE dog SET readcount = readcount + 1 WHERE id=?";
		
		try {
		    pstmt = con.prepareStatement(sql);
		    pstmt.setInt(1, Integer.parseInt(id));
			updateCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
```

#### 5. dogCartAdd 구현하기 => 장바구니!!

#### 5.1 CartVO 를 만들어서 객체로 데이터를 저장해서 사용!

> CartVO.java

```java
package vo;

public class CartVO {
   private int id;
   private String image;
   private String kind;
   private int price;
   private int qty;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getKind() {
	return kind;
}
public void setKind(String kind) {
	this.kind = kind;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
   
}
```

#### 5.2 dogView.jsp 에서 장바구니 담기 요청을 클릭하면 FC 에서 처리한다.

> dogView.jsp

```html
<a href="dogCartAdd.dog?id=<%= dogVO.getId()%>">장바구니담기</a>
```

> DogFrontController.java

```java
		else if (command.contentEquals("/dogCartAdd.dog")) {
			// 모든 요소를 처리할 수 있게끔 다형성을 이용한다. 
			action = new DogCartAddwAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

#### 5.3 Action
* 기존에 사용한 DogViewService를 통해서 DAO => DB 로부터 해당 id의 정보를 가져온다.
* DogCartAddAction에서 addCart(request, dogVO)메소드를 사용해서 해당 세션의 ArrayList를 받아온다.

> DogCartAddAction.java

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartAddService;
import svc.DogViewService;
import vo.ActionForward;
import vo.DogVO;

public class DogCartAddwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		// 장바구니 담기 요청을 하기 위해서는 다른 정보들도 필요하다.
		// 기존에 사용한 Service로 dogVO를 받아온다.
		DogViewService dogViewService = 
				new DogViewService();
		DogVO dogVO = dogViewService.getDogVO(id);
		
		DogCartAddService dogCartAddService = 
				new DogCartAddService();
		
		// 장바구니에 담으면 장바구니에 담은 목록들이 보인다.
		dogCartAddService.addCart(request, dogVO);
		
		ActionForward forward = new ActionForward();
		
		// 완전하게 새로운 URL로 보낼때에는 Redirect로 한다.
		forward.setRedirect(true);
		forward.setUrl("dogCartList.dog");
		
		return forward;
	}

}

```

#### 5.4 Service
* 서버에 사용자마다 자기 세션이 생긴다.
* 서블릿에서 자기 세션의 참조를 얻어 올려면 request 영역에 getSession을 사용해야한다.
* 이미 장바구니에 정보가 담겨 있을 수도 있으니, 기존의 장바구니 상품을 확인하는게 먼저다.
* 

> DogCartAddService.java

```java

```


