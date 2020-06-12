### 2020 0612 FRI 
### Today
1. 회원가입에서 아이디 중복 체크 처리
2. LoginDAO.java에서 String dbId = null 이 아닌 "" 으로 주면, IdCheckService에서는 String dbId = loginDAO.selectId(id); 로 dbId가 DAO에서 DB 연산할 값을 받기에 만약, DB에 존재하지 않는 값이여서 "" 로 return을 해주면 IdCheckService.java에서  if (dbId != null) 이 아니면 idExist = true; 를 주기에 dbId가 null이 아닌 "" 이 넘어오면 조건문에 들어가지 못하고 그대로 idExist = False 로 return을 해준다. 그래서 idCheckService.java 에서 boolean idExist에서 아이디가 현재 사용하지 않는 아이디인것에도 불구하고 현재 사용할 수 없다고 idCheck.jsp에 넘기게 되는 것이다. 
3. memberRegistForm.jsp 에서 우편번호 찾기를 누르면 findZip() function을 발생시켜서 findZip.jsp 로 가서 미리 만들어 놓은 ZipcodeVO의 데이터를 ArrayList로 불러와서 현재는 zipCodeList가 존재하지 않아서 else 문으로 빠져서 검색할 도로명을 검색하고, zipFindSerlvet으로 get 방식으로 우편번호를 전달한다.
4. 



#### 1. idCheck.jsp 에서 winClose() fucntion을 누르면 opener.document.myForm.id.vlaue 에 접근을 해서 현재 idCheck.jsp 의 id를(DB에 존재하지 않아서 사용가능한 아이디) memberRegistForm.jsp에 바로 전달을 해주는 작업! 
* a href="javascript:winClose() 을 하면? => 페이지의 변환을 주는 것이 아니라, 원하는 function만 수행을 하는 것이다.
* opener.document.myForm.id.value = "<% =id%>"; 이것을 사용하면 idCheck.jsp의 id를 가져와서 opener를 사용해서 memberRegistForm에 id의 값을 idCheck.jsp의 id 중복체크를 해서 사용이 가능한 아이디로 바꿔주는 작업을 한다.

> idCheck.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO" %>
<%
	boolean idExist = (boolean)request.getAttribute("idExist");
    String id = (String)request.getAttribute("id");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   function winClose(){
	   // 아이디 중복 체크한 값을 가지고 간다.
	   opener.document.myForm.id.value = "<%=id%>";
	   window.close();
	   // self.close();
	   // close();
   };
</script>
</head>
<body>
    <%
    // 해당 아이디가 DB에서 이미 다른 회원이 사용중이라면?
       if( idExist ){
    %>
       <h1><%= id %> 해당 아이디는 사용할 수 없습니다.</h1>
       <form action="IdCheck">
          <label for="id">아이디 : </label>
          <input type="text" name="id" id="id"/><br>
          <input type="submit" value="아이디중복체크"/>
       </form>
    <%
    // 다른 회원들이 해당 아이디를 사용하지 않는다면?
       }else{
    %>
      <h1><%= id %> 해당 아이디는 사용할 수 있습니다.</h1>
      <a href="javascript:winClose()">닫기</a>
    <%
       }
    %>
</body>
</html>
```

#### 2. LoginDAO.jsp 에서 String dbId = null 아닌 String dbId = "" 을 넣으면 DAO에서는 DB에 값이 존재하지 않으면 "" 을 넘기기에 Service 단에서 제대로 작동을 하지 못한다.

> LoginDAO.java

```java
	public String selectId(String id) {
		// TODO Auto-generated method stub
		String dbId = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbId = rs.getString("id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			close(rs);
			close(pstmt);
		}
		
		return dbId;
	}
```

#### 3. memberRegistForm.jsp => findZip() function => zipFind.jsp => ZipFindServlet

#### 3.1 ZipCodeVO.java에서 각각의 변수를 캡슐화, Getter, Setter 작성

> ZipCodeVO.java

```java
package vo;

public class ZipCodeVO {
   private int num;
   private String zip;
   private String dou;
   private String si;
   private String ro;
   private String bunzi;
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public String getZip() {
	return zip;
}
public void setZip(String zip) {
	this.zip = zip;
}
public String getDou() {
	return dou;
}
public void setDou(String dou) {
	this.dou = dou;
}
public String getSi() {
	return si;
}
public void setSi(String si) {
	this.si = si;
}
public String getRo() {
	return ro;
}
public void setRo(String ro) {
	this.ro = ro;
}
public String getBunzi() {
	return bunzi;
}
public void setBunzi(String bunzi) {
	this.bunzi = bunzi;
}
   
 }
```

#### 3.2 memberRegistForm.jsp :  fuction zipFind() 을 추가

> memberRegistForm.jsp

```html
   function findZip(){
	   window.open("zipFind.jsp", "window", "width=400, height=300");
   };
```

#### 3.3 zipFind.jsp : ArrayList<ZipCodeVO> zipCodeList를 받아서 zipCodeList가 존재하지 않으면, else문으로 빠져서 도로명을 검색하고 ZipFindServlet.java로 데이터를 전달한다.

> zipFind.jsp 

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.ZipCodeVO" %>
<%@ page import="java.util.*" %>
<%
	ArrayList<ZipCodeVO> zipCodeList = (ArrayList<ZipCodeVO>)request.getAttribute("zipCodeList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%
      if(zipCodeList != null && zipCodeList.size() > 0) {
   %>
	   <%
	      for(int i = 0; i < zipCodeList.size(); i++){
	    	  String addr1 = zipCodeList.get(i).getDou() +
	    			  " " + zipCodeList.get(i).getSi() +
	    			  " " + zipCodeList.get(i).getBunzi();
	   %>
	  <table>
	     <tr>
	        <td><%= zipCodeList.get(i).getZip() %></td>
	        <td></td>
	     </tr>
	   <%
      	}	
	   %>
	  </table>   
	
   
   <%
   	  }
      else{
   %>
      <h1>검색할 도로명을 입력하세요.</h1>
      <form action="zipFind">
         <label for="ro">도로명 : </label>
         <input type="text" name="ro" id="ro"><br>
         <input type="submit" value="우편번호검색">
      </form>
   <%
      }
   %>
</body>
</html>
```

#### 3.4 ZipFindServlet.java : zipFind.jsp에서 보낸 도로명 검색 요청을 Servlet에서는 Service에 비지니스 로직과 DB 커넥션 관리를 하고, Service에서는 DAO 디비 작업을 요청한다.
* servlet 즉 controller에서는 비지니스 로직으로 처리를 하고, .jsp 로 forward를 해준다.

> ZipFindServlet.java

```java
package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ZipFindService;
import vo.ZipCodeVO;

/**
 * Servlet implementation class ZipFindServlet
 */
@WebServlet("/zipFind")
public class ZipFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipFindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String ro = request.getParameter("ro");
			
			ZipFindService zipFindService = new ZipFindService();
			
			// ro : 도로명을 zipFindSevice의  getZipcodeList 메소드로 넘겨준다.
			ArrayList<ZipCodeVO> zipCodeList = zipFindService.getZipcodeList(ro);
			
			request.setAttribute("zipCodeList", zipCodeList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("zipFind.jsp");
			dispatcher.forward(request, response);
	}
}
```

#### 3.5 ZipFindService.java : ArrayList<ZipCodeVO> zipCodeList = loginDAO.selectZipCodeList(ro);  구문처럼 ZipFindService에서 loginDAO에게 DB작업을 요청한 것이다.
* Service단에서 DB 작업을 DAO에게 넘겨주고, selectZiPCodeList(ro); 메소드를 사용해서 ArrayList<ZipCodeVO> zipCodeList 가 메소드에게 값을 받는다.
* Service단에서 con을 처리를 해준다. 왜냐하면 트랜잭션 처리 때문에!

> ZipFindService.java

```java
package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.LoginDAO;
import vo.ZipCodeVO;


public class ZipFindService {

	public ArrayList<ZipCodeVO> getZipcodeList(String ro) {
		
		Connection con = getConnection();
		LoginDAO loginDAO = LoginDAO.getInstance();
		loginDAO.setConnection(con);
		
		ArrayList<ZipCodeVO> zipCodeList = loginDAO.selectZipCodeList(ro);
		
		// 항상 Service 단에서 트랜잭션 때문에 con을 닫아줘야한다.
		close(con);
		
		return zipCodeList;
	}
}
```

#### 3.6 LoginDAO.java : ArrayList<ZipCodeVO> zipCodeList = loginDAO.selectZipCodeList(ro);  구문처럼 ZipFindService에서 loginDAO에게 DB작업을 요청한 것이다.
* String sql = "SELECT * FROM zipCode WHERE ro LIKE ?" : LIKE 구문을 사용해서 출력 
   * pstmt.setString(1, "%" + ro "%"); => %는 한 개이상이 오거나 안오거나 모두 다 성립한다. 즉 ro가 하양이면 1하양, 하양1, 1하양1 전부 다 되는 것이다.
* ZipCodeList  = new ArrayList<ZipCodeVO>(); 하나의 ZipCodeList ArrayList를 만들고 나서 do - while문으로 rs.next() 즉 다음 행의 값이 존재한다면, zipCodeVO = new ZipCodeVO();를 계속 하나씩 만들어 주고, DB에 있는 값을 모두 추가한다음 zipCodeList.add(zipCodeVO); 로 하나의 do-while이 끝날때 마다 zipCodeVO를 추가한다.


> LoginDAO.java

```java
	public ArrayList<ZipCodeVO> selectZipCodeList(String ro) {
		
		ArrayList<ZipCodeVO> zipCodeList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM zipcode WHERE ro LIKE ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + ro + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 조건에 부합하는 내 코드가 있다면?
//				   private int num;
//				   private String zip;
//				   private String dou;
//				   private String si;
//				   private String ro;
//				   private String bunzi;
				zipCodeList = new ArrayList<ZipCodeVO>();
				ZipCodeVO zipCodeVO = null;
				
				do {
					zipCodeVO = new ZipCodeVO();
					zipCodeVO.setBunzi(rs.getString("bunzi"));
					zipCodeVO.setDou(rs.getString("do"));
					zipCodeVO.setNum(rs.getInt("num"));
					zipCodeVO.setRo(rs.getString("ro"));
					zipCodeVO.setSi(rs.getString("si"));
					zipCodeVO.setZip(rs.getString("zip"));
					zipCodeList.add(zipCodeVO);
				} while(rs.next()); // 레코드가 있을동안 실행한다.
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			close(rs);
			close(pstmt);
		}
				
		return zipCodeList;
	}
```

#### 3.7 zipFind.jsp 에서 각각의 <%= zipCodeList.get(i).getZip()%> 를 클릭하면 javascirpt:winClose(zip, addr1) 함수를 불러서 winClose 함수 안에서 opener를 이용해서 각각의 zip, addr에 값을 넘겨준다.

> zipFind.jsp

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.ZipCodeVO" %>
<%@ page import="java.util.*" %>
<%
	ArrayList<ZipCodeVO> zipCodeList 
			= (ArrayList<ZipCodeVO>)request.getAttribute("zipCodeList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   function winClose(zip, addr1){
	   opener.document.myForm.zip.value = zip;
	   opener.document.myForm.addr1.value = addr1;
	   window.close()
   }
</script>
</head>
<body>
   <%
      if(zipCodeList != null && zipCodeList.size() > 0) {
   %>
	   <%
	      for(int i = 0; i < zipCodeList.size(); i++){
	    	  String addr1 = zipCodeList.get(i).getDou() +
	    			  " " + zipCodeList.get(i).getSi() +
	    			  " " + zipCodeList.get(i).getBunzi() +
	    			  " " + zipCodeList.get(i).getRo();
	   %>
	  <table>
	     <tr>
	        <td><a href="javascript:winClose('<%=zipCodeList.get(i).getZip()%>','<%=addr1%>')"><%= zipCodeList.get(i).getZip()%></a></td>
	        <td><%= addr1 %></td>
	     </tr>
	   <%
      	}	
	   %>
	  </table>   
	
   
   <%
   	  }
      else{
   %>
      <h1>검색할 도로명을 입력하세요.</h1>
      <form action="zipFind">
         <label for="ro">도로명 : </label>
         <input type="text" name="ro" id="ro" value="하양"><br>
         <input type="submit" value="우편번호검색">
      </form>
   <%
      }
   %>
</body>
</html>
```


