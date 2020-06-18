## 2020 0618 THU

## Today
### 1. dogCartAdd => session을 사용해서 처리하기 
### 2. allCheck => 모두 선택, 해제 구현 => javascript  
### 3. dogCartRemove => session 불러와서 remove 구현
### 4. dogCartQtyUp 구현하기 
### 5. dogCartQtyDown 구현하기

## Error
### 1. if(cartList == null && cartList.size() == 0 ) 일때 현재 장바구니에 담긴 상품이 없다고 나와야하는데, 계쏙 다른게 나왔던 이유는 END 로 해서 cartList가 null 이면서 cartList 의 size가 0 이여야 하기에 || 로 처리해줘야 한다. 
### 2. dogCartList.jsp 에서 a href="" 쌍 따옴표를 제대로 닫아주지 못해서 에러가 발생!!

> 수정 전

```html
<a href="dogCartQtyDown.dog"?id=<%= cartList.get(i).getId() %>">
  <img src="image/down.jpg" class="upDownImage">
</a>
```

> 수정 후
```html
<a href="dogCartQtyDown.dog?id=<%= cartList.get(i).getId() %>">
   <img src="image/down.jpg" class="upDownImage">
</a>
```

#### 1. dogCartAdd 구현
OB
#### 1.1 dogView.jsp
* dogView.jsp에서 장바구니 담기를 누르면 애래의 dogCartAdd.dog 의 요청으로 FC에서 받아서 처리한다.

> dogView.jsp

```html
<a href="dogCartAdd.dog?id=<%= dogVO.getId()%>">장바구니담기</a>
```

#### 1.2 FC(DogFrontController) 
* dogView.jsp로부터 dogCartAdd.dog 요청을 받으면 DogCartAddwAction을 호출한다.

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

#### 1.3 Action
* 세션을 처리하기 위해서는, dogView.jsp 에서 넘어오는 id를 받아서 DogViewService에서 DAO로 getDogVO(id) 메소드를 통해서 해당 id의 dogVo 객체를 받아온다.
* DogCartAddService 라는 서비스를 만들어서 session에 추가할 dogVO와 session 객체를 사용하기 위해 request를 동시에 넘겨준다.
* Service에서 처리를 받고나면 forward로 dogCartList.dog로 넘겨준다.

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
		DogViewService dogViewService = new DogViewService();
		DogVO dogVO = dogViewService.getDogVO(id);
		
		DogCartAddService dogCartAddService = new DogCartAddService();
		
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

#### 1.4 Service
cartList에 각각의 CartVO를 담아서 세션에 담을 것이다.
   * 각각의 client가 server에 자신의 CartList가 생긴다.

1. 장바구니 담기 요청을 한 번 도 안했으면 장바구니에 한 개도 없을 수가 있다. 
   * null이면 새로 cartList를 하나 만들어서 세션에 공유한다.
2. 새로운 상품을 장바구니에 담는다.
   1. 장바구니에 담을 상품이 이미 장바구니에 담겨있는 경우!  => 수량만 증가시킴
   2. 장바구니에 담을 상품이 기존의 장바구니에 없는 경우 => CartVO(새로운 상품)을 하나 만들어서 CartList에 담는다.

> DogCartAddService.java

```java
package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;
import vo.DogVO;

public class DogCartAddService {

	public void addCart(HttpServletRequest request, DogVO dogVO) {
		
		// 장바구니는 세션에 담기 때문에 DB 작업은 필요하지않다.
		// 1. 현재 자신의 세션에 저장된 장바구니 목록을 가져온다.
		HttpSession session = request.getSession();
		
		// 각각의 사람들의 세션의 정보를 저장을 하기 위해서는 Arraylist를 사용해주는 것이 좋다.
		ArrayList<CartVO> cartList = (ArrayList<CartVO>)session.getAttribute("cartList");
		
		// cartList에 각각의 CartVO 를 담아서 세션에 담을 것이다.
		 
		// 아직 장바구니 요청을 한 번도 안했으면! => 장바구니 담을 영역을 만든다.
		if (cartList == null) {
			// 새롭게 담을 ArrayList를 만든다.
			cartList = new ArrayList<CartVO>();
			session.setAttribute("cartList", cartList);
		}
		
		// 2. 새로운 상품을 장바구니에 담기
		// 2-1. 이미 있는 경우
		
		// 2-2. 새로운 상품
		
		// newCart = ture 새로운 상품이라면!
		boolean newCart = true;
		// newCart = false 로 돌리는 경우는 기존의 상품이 세션(장바구니)있다면!
		for (int i = 0; i < cartList.size(); i++) {
			// 현재 담는거랑           == 기존에 담는거
			if(dogVO.getId() == cartList.get(i).getId()) {
				cartList.get(i).setQty(cartList.get(i).getQty() + 1);
				// 기존에 있는 상품이다.
				newCart = false;
			}
		}
		
		// 새 상품이라면!
		if (newCart) {
			CartVO cartVO = new CartVO();
			cartVO.setId(dogVO.getId());
			cartVO.setImage(dogVO.getImage());
			cartVO.setKind(dogVO.getKind());
			cartVO.setPrice(dogVO.getPrice());
			cartVO.setQty(1);
			cartList.add(cartVO);
		}
		
		
	}
	
	
	
}
```

#### 1.5 DogCarAddAction에서dogCartList forward.setUrl을 해주고, FC에 dogCartList.dog로 들어간다.

```java
		else if (command.contentEquals("/dogCartList.dog")) {
			// 모든 요소를 처리할 수 있게끔 다형성을 이용한다. 
			action = new DogCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```


#### 1.6 DogCartListAction : 위에서는 DogCartListAdd를 수행하고, 여기서는 출력을 처리한다. 
* ArrayList 형인 cartList를 받기 위해서 Service 단에 비지니스 로직을 request 를 넘겨서 session 영역을 사용할 수 있게 넘긴다.
* 전체 cartList의 totalMoney를 .jsp에서 연산하지 말고, Action에서 연산을 한다.

> DogCartListAction.java

```java
package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartListService;
import vo.ActionForward;
import vo.CartVO;

public class DogCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		ArrayList<CartVO> cartVO = 
		DogCartListService dogCartListService = new DogCartListService();
		
		// 장바구니 목록은 세션에 있으니 , session을 얻을려면 request 객체를 사용해야한다. 
		ArrayList<CartVO> cartList = dogCartListService.getCartList(request);
		
		// 장바구니 목록이 출력되고 하단에 총 금액 출력이 된다. => 무슨 상품 + 무슨 상품 = 총 금액 
		// 총 금액을 Action에서 구한다. => 연산하는 것은 될 수 있으면 jsp에서는 하지 않는다.
		int totalMoney = 0;
		for (int i = 0; i < cartList.size(); i++) {
			totalMoney += cartList.get(i).getPrice() * cartList.get(i).getQty();
		}
		
		request.setAttribute("cartList", cartList);
		request.setAttribute("totalMoney", totalMoney);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("dogCartList.jsp");
		
		return forward;
	}

}
```

#### 1.7 DogCartListService
* session에 cartList가 저장되어 있기에 가져온다. 

> DogCartListService.java

```java
package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;

public class DogCartListService {

	public ArrayList<CartVO> getCartList(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		ArrayList<CartVO> cartList = (ArrayList<CartVO>)session.getAttribute("cartList");	
	
		return cartList;
	}
}
```

#### 1.9 Service =>  Action => FC => dogCartList.jsp(forward)

#### 2. allCheck 
* checkbox 를 맨 위에 항목을 체크를 통해서 allCheck 설정/해제를 한다.
* document객체는 form의 name="cartForm"을 통해서 접근이 가능하다.

> dogCartList.jsp

```html
<script>
   function checkAll(){
	   var delete1 = document.cartForm.delete1;
	   var allCheck = document.cartForm.allCheck;
	   
	   // if 문으로 allCheck 체크박스가 선택되었으면 delete1의 모든 체크 박스를 선택!
	   // else 문으로 allCheck 체크박스가 해제되었으면 delete1의 모든 체크 박스 해제
	   if (delete1.length == undefined){ // 단일 객체이면!
		   delete1.checked = allCheck.checked;
	      
	   }
	   else{
		  for (i = 0; i < delete1.length; i++){
			  delete1[i].checked = allCheck.checked;
		  }
	   }
   };
</script>
```

```html
	       <tr class="text-title">
	          <td>번호</td>
	          <td>상품명 </td>
	          <td>이미지</td>
	          <td>가격</td>
	          <td>수량 </td>
	          <td><input type="checkbox" name="allCheck"
	           id="allCheck" onClick="checkAll()"></td>
	       </tr>
```

#### 3.  DogCartRemove
1. dogCartList.jsp 에서 삭제를 누르면 dogCartRemove.dog로 요청을 보낸다.
2. 다음과 같은 요청을 FC 에서 dogCartRemove.dog 를 처리한다.
3. dogRemobeAction 에서 dogCartList.jsp에서 사용하는 deleteIdArray를 배열을 통해서 받아서, Service 에 request와 deleteArray를 넘긴다.
4. Service 에서는 세션을 불러와서, for 문을 돌려서 해당 deleteIdArray에 해당하는 cartList를 지우면 된다.
5. Action 단에서, forward를 dogCartList.dog로 보낸다.

#### 3.1 dogCartList.jsp
* dogCartRemove.dog 를 누르면 요청을 post로 가게 만든다.

> dogCartList.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.CartVO" %>
<%@ page import="java.util.*" %> 
 
<%
   ArrayList<CartVO> cartList = (ArrayList<CartVO>)request.getAttribute("cartList");
   int totalMoney = (int)request.getAttribute("totalMoney");
   
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   function checkAll(){
	   var delete1 = document.cartForm.delete1;
	   var allCheck = document.cartForm.allCheck;
	   
	   // if 문으로 allCheck 체크박스가 선택되었으면 delete1의 모든 체크 박스를 선택!
	   // else 문으로 allCheck 체크박스가 해제되었으면 delete1의 모든 체크 박스 해제
	   if (delete1.length == undefined){ // 단일 객체이면!
		   delete1.checked = allCheck.checked;
	      
	   }
	   else{
		  for (i = 0; i < delete1.length; i++){
			  delete1[i].checked = allCheck.checked;
		  }
	   }
   };
</script>
<style>
   #cartListArea{
      width : 600px;
      margin : auto;
      border : 1px solid red;
   }
   h1{
      text-align : center;
   }
   table{
      width : 580px;
      margin : auto;
      text-align : center;
      border : 1px solid blue;
   }
   .productImage{
   	  width : 150px;
      height : 150px;
   }
   .text-title {
      font-weight : bold;
   }

</style>
</head>
<body>

   <section id="cartListArea">
<%
         if (cartList == null || cartList.size() == 0){
%>
	      <h1>현재 장바구니에 담긴 상품이 없습니다.</h1>
<%     
         }
         else{
%>
	   <form action="dogCartRemove.dog" method="post" name="cartForm">
	   <h1>장바구니 </h1>
	   <h1> <a href="dogList.dog">쇼핑 계속하기</a></h1>
	   <table border="1">
	       <tr>
	          <td colspan="6" style="text-align : right">
	             <input type="submit" value="삭제"/>
	          </td>
	       </tr>
	       <tr class="text-title">
	          <td>번호</td>
	          <td>상품명 </td>
	          <td>이미지</td>
	          <td>가격</td>
	          <td>수량 </td>
	          <td><input type="checkbox" name="allCheck"
	           id="allCheck" onClick="checkAll()"></td>
	       </tr>
	   
<% 
			int num = 1;
	        for (int i = 0; i < cartList.size(); i++){
%>		    
            <tr>
               <td><%= num++ %></td>
               <td><%= cartList.get(i).getKind() %></td>
               <td>
                  <img src="image/<%=cartList.get(i).getImage()%>" class="productImage">
               </td>
               <td><%= cartList.get(i).getPrice() %></td>
			   <td><%= cartList.get(i).getQty() %></td>
			   <td>
			       <input type="checkbox" name="delete1" 
			        id="delete1" value="<%=cartList.get(i).getId()%>">
			   </td>
		    </tr>
   <%	  
		  }
		%>
		    <tr class="tr-totalmoney">
		       <td colspan="5" style="text-align:right; font-size: xx-large;">
		          	총 금액 : <%= totalMoney %>원 <br>
		          	총 수량 : <%= cartList.size() %>
		       </td>
		    </tr>
<% 		   
	   }
   
%>
      </table>
      </form>
   </section>
</body>
</html>
```

#### 3.2 FC => dogCartRemove.dog 의 요청을 받아서 처리한다.

> DogFrontController.java

```java
		else if (command.contentEquals("/dogCartRemove.dog")) {
			// 모든 요소를 처리할 수 있게끔 다형성을 이용한다.
			action = new DogCartRemoveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

#### 3.3 Action
* delete id가 배열이 아닐수도 있는 경우는 1개일 경우여서 배여롤 받는다.
* session영역으로 비즈니스 로직을 처리해야 하기에 service에 request를 넘겨준다.

> DogCartRemoveAction.java

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartRemoveService;
import vo.ActionForward;

public class DogCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String[] deleteIdArray = request.getParameterValues("delete1");
		
		DogCartRemoveService dogCartRemoveService =
				new DogCartRemoveService();
		
		dogCartRemoveService.removeCartVO(request, deleteIdArray);
		
		
		
		ActionForward forward = new ActionForward();
		forward.setUrl("dogCartList.dog");
		forward.setRedirect(true);
		
		return forward;
	}

}
```

#### 3.3 DogCartRemoveService

> DogCartRemoveService.java

```java
package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;

public class DogCartRemoveService {

	public void removeCartVO(HttpServletRequest request, String[] deleteIdArray) {
		
		
		// CartList에서 지금 선택한 CartVO 목록을 제거하기.
		HttpSession session = request.getSession();
		ArrayList<CartVO> cartList = 
				(ArrayList<CartVO>)session.getAttribute("cartList");
		
		for (int i = 0; i < deleteIdArray.length; i++) {
			for (int j = 0; j < cartList.size(); j++) {
				if (Integer.parseInt(deleteIdArray[i]) == cartList.get(j).getId()) {
					cartList.remove(cartList.get(i));
				}
			}
		}
	}
}

```

#### 3.4 해당하는 id를 전부 지워주고, Service => Action => FC => dogCartList.dog

#### 4.  cartList.get(i).getQty() 의 수량의 up/down 을 이미지를 누르면 dogCartQtyUp.dog?id=로 처리하기.

> dogCartList.jsp

```html
<a href="dogCartQtyUp.dog?id=<%= cartList.get(i).getId()%>">
   <img src="image/up.jpg" class="upDownImage">
</a><br>
```

#### 4.1 FC 에서 dogCartQtyUp.dog 로 들어오는 모든 요청을 처리하기

> DogFrontController.java

```java
		else if (command.contentEquals("/dogCartQtyUp.dog")) {
			// 모든 요소를 처리할 수 있게끔 다형성을 이용한다. 
			action = new DogCartQtyUpAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

#### 4.2 Action
* dogCartList.jsp 에서 넘어오는 id 값을 받아서, 세션의 Qty를 올려주는 것을 처리하기 위해서 Service 단 == 비지니스 로직을 넘겨준다.
* forward 를 한다. => setUrl 설정을 해주고, setUrl 이 .jsp 이면 그대로 Redirect는 하진 않고, dogCatList.dog와 같이 새로운 url이면 Redirect를 한다.

> DogCartQtyUpService.java

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		DogCartQtyUpService dogCartQtyUpService  =
				new DogCartQtyUpService();
		
		dogCartQtyUpService.upQty(request, id);
		
		
		ActionForward forward = new ActionForward();
		forward.setUrl("dogCartList.dog");
		forward.setRedirect(true);
		
		return forward;
	}

}

```

#### 4.3 Service
* session을 이용한 비지니스 로직 처리를 위해서 먼저 session을 정의해준다.
* 기존의 세션 영역의 cartList를 각각의 id와 맞는지 비교해서 해당되는 id의 count를 올려준다.

> DogCartQtyCUpService.java

```java
package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;

public class DogCartQtyUpService {

	public void upQty(HttpServletRequest request, String id) {
		
		HttpSession session = request.getSession();
		ArrayList<CartVO> cartList = 
				(ArrayList<CartVO>)session.getAttribute("cartList");
		
		for (int i = 0; i < cartList.size(); i++) {
			if(Integer.parseInt(id) == cartList.get(i).getId()) {
				cartList.get(i).setQty(cartList.get(i).getQty() + 1);
			}
		}
	}
}
```

#### 4.4 Service => Action => FC = > dogCartList.dog  

#### 5. dogCartListDown 구현하기

#### 5.1 dogCartList.jsp

> dogCartList.jsp

```html
<a href="dogCartQtyDown.dog?id=<%= cartList.get(i).getId() %>">
   <img src="image/down.jpg" class="upDownImage">
</a>
```

#### 5.2 FC => dogCartQtDown.dog 요청을 처리한다.

> DogFrontController.java

```java
		else if (command.contentEquals("/dogCartQtyDown.dog")) {
			// 모든 요소를 처리할 수 있게끔 다형성을 이용한다. 
			action = new DogCartQtyDownAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

#### 5.3 Action

> DogCartQtyDownAction.java

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyDownService;
import vo.ActionForward;

public class DogCartQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		DogCartQtyDownService dogCartQtyDownService = 
				new DogCartQtyDownService();
		
		dogCartQtyDownService.downQty(request, id);
		
		ActionForward forward = new ActionForward();
		
		forward.setUrl("dogCartList.dog");
		forward.setRedirect(true);
		
		return forward;
	}
}
```

#### 5.4 Service

> DogCartQtyDownService.java

```java
package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.CartVO;

public class DogCartQtyDownService {

	public void downQty(HttpServletRequest request, String id) {
		
		HttpSession session = request.getSession();
		ArrayList<CartVO> cartList = 
				(ArrayList<CartVO>)session.getAttribute("cartList");
		
		for(int i = 0; i < cartList.size(); i++) {
			if (Integer.parseInt(id) == cartList.get(i).getId()) {
			    cartList.get(i).setQty(cartList.get(i).getQty() - 1);
			}
		}
	}
}
```

#### 5.5 Service => Action => FC => dogCartList.dog





