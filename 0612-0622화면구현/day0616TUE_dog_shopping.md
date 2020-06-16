### 2020 0616 TUE

### Today
1. Dog List Select  
   * shoppingStart.jsp dogList.dog 요청 => FC 에서 요청 받고 => Action => Service => DB 그리고 다시 => DB => Service => Acion => FC => dogList.jsp에서 해당 DB에 있는 내용을 출력한다.

### error
1. BoardDAO.java 에서 Connection pool 을 놔두고 있었다. 원래는 JdbcUtil.java에 놔두고 사용해야한다.
2. Connection pool 을 이용하기 위해서는 Server에 context.xml 에서 Connection pool을 사용하기 위해 작성했던 Resource 문에 맞는 이름을 JdbcUtil에서 설정해야한다.

### 1. DogList project

#### 1.1 프로젝트를 진행할때 가장 먼저 해야하는것은 데이터베이스 설계!!

#### 1.1.1 CRAETE TABLE 
* TABLE 이름은 항상 복수형이 아니라, 단수형으로 저장을 해야한다.
* 테이블 생성, 시퀀스 생성, 데이터 4개 삽입

> dog.sql

```sql
CREATE TABLE dog (
   id NUMBER PRIMARY KEY,
   kind VARCHAR2(21) NOT NULL,
   price NUMBER NOT NULL,
   image VARCHAR2(20) NOT NULL,
   country VARCHAR2(30) NOT NULL,
   height NUMBER NOT NULL,
   weight NUMBER NOT NULL,
   content VARCHAR2(400) NOT NULL,
   readcount NUMBER NOT NULL
)

--  id에 사용하기 위해 SEQUENCE를 생성 , START는 1로 하고, CACHE를 사용하지 않는다.
CREATE SEQUENCE dog_seq START WITH 1 NOCACHE;

INSERT INTO dog VALUES(dog_seq.nextval, '세파트', 10000, 'se.jpg', '독일', 1, 10, '세파트는 용맹하다', 0);
INSERT INTO dog VALUES(dog_seq.nextval, '푸들', 20000, 'pu.jpg', '프랑스', 1, 5, '푸들은 귀엽다', 0);
INSERT INTO dog VALUES(dog_seq.nextval, '시바견', 5000, 'si.jpg', '일본', 1, 5, '시바견은 ㅋㅋㅋㅋㅋ', 0);
INSERT INTO dog VALUES(dog_seq.nextval, '불독', 10000, 'bul.jpg', '독일', 1, 10, '불독은 사납다', 0);


-- 마지막에는 COMMIT 까지 완벽하게 해준다.
COMMIT

```

#### 1.2. 데이터베이스 설계해서 강아지 사진이 필요하므로 WebContent > image 폴더를 생성하고, 사진을 담는다.  
* bul.jpg, si.jpg, se.jpg, fo.jpg => 불독, 시바견, 세퍼드, 푸들

#### 1.3 Programming 상에서 사용할 데이터들을 정의한다.

#### 1.3.1 DogVO => 사용할 데이터들을 캡슐화, Getter, Setter

> DogVO.java

```java
package vo;

public class DogVO {
	private int id;
	private String kind;
	private int price;
	private String image;
	private String country;
	private int height;
	private int weight;
	private String content;
	private int readCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

}
```

#### 1.4 기존에 사용한 db, controller, action 패키지들을 가져와서 사용한다.

#### 1.4.1 db => JdbcUtil
* 기존의 oracle에 연결하는 방법과는 달리, CP를 사용한다.

> JdbcUtil.java

```java
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	static {
		// static 요것은 클래스를 읽자마자 바로 실행되는 영역이다.
		// 즉, 클래스를 읽자마자 oracle drive를 바로 읽어들이려고 하는 것이다.
		// forName() 메소드는 괄호안에 있는 클래스를 메모리에 로딩하는 것이다.
		// forName() 처리를 하면 반드시 예외처리를 해줘야한다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		// DB 연결 작업
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/oracle");
			con = ds.getConnection();
			con.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con) {
		// 여기서 예외처리를 해주자.
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void close(Statement stmt) {
		// 여기서 예외처리를 해주자.
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void close(ResultSet rs) {
		// 여기서 예외처리를 해주자.
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 트랜잭션 처리 부분
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
   
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
```


#### 1.4.2 DogFrontContoller 에서 dogList.dog 로 오는 요청을 처리한다.

> DogFrontController.java

```java
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.DogListAction;
import vo.ActionForward;


@WebServlet("*.dog")
public class DogFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DogFrontController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 이전에 서블릿의 처리는 유형은 정해져있었다.
		// 1. 요청파악
		String requestURI = request.getRequestURI();
		// 요청 URL : http://localhost:8081/project/boardWriteForm.bo 라는 요청이 넘어 온다면
		// requestURI : /project/boardWriteForm.bo 가 반환이 된다.
		
		String contextPath = request.getContextPath();
		// contextPath = /project 가 반환이 된다.
		
		// contextPath.length() 를 넣어주면 /project/ 에서  끝의 / 를 가지게 된다.
		String command = requestURI.substring(contextPath.length());
		// command ==> /boardWriteForm.bo 가 된다.
		
		// 2. 요쳥처리
		// 다형성을 이용해야 편리하게 가능하다. 
		// Action 인터페이스로 두면 많은 변수를 받을 수 있다.
		Action action = null;
		ActionForward forward = null;
		if (command.contentEquals("/dogList.dog")) {
			// 모든 요소를 처리할 수 있게끔 다형성을 이용한다. 
			action = new DogListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		// 요청 처리를 했으면 3. 포워딩을 해야한다.
		if (forward != null) {
			// 각  Action 클래스의 execute 메소드가 정상적으로 실행되서 비지니스 로직 실행이 성공했을 떼
			if (forward.isRedirect()) {
				// redirect가 true 면 redirect 방식으로 사용
				response.sendRedirect(forward.getUrl());
			}
			else {
				// dispatcher를 사용해서 forward한다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
		else {
				
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
```

#### 1.4.3 FC(Front Controller)에 구현할 인터페이스를 작성한다.

> action.interface

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception;
}
```

#### 1.5 shoppingStart.jsp 를 사용해서 시작 페이지로 사용한다.
* dogList.dog 의 하이퍼 링크를  dogLIst.dog 요청이 DogFrontController에서 처리한다.

> shoppingStart.jsp

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

<a href="dogList.dog">개 목록 보기</a>

</body>
</html>
```

#### 1.6 DogListAction 에서 controller의 역할을 한다!! => (service => model), vie(jsp)
* Action에서는 비지니스 로직 처리를 위해 Service => DAO => DB에 요청을 한다.

> DogListAction.java

```java
package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogListService;
import vo.ActionForward;
import vo.DogVO;

public class DogListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		DogListService dogListService 
						= new DogListService();
		
		ArrayList<DogVO> dogList = dogListService.getDogList();
		
		request.setAttribute("dogList", dogList);
		
		ActionForward forward = new ActionForward(); 
		forward.setUrl("dogList.jsp");
		
		return forward;
	}

}
```

#### 1.7 DogListService => 현재 DB에 존재하는 dogList를 받기 위해서 DB에 요청한다.

> DogListService.java

```java
package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.DogDAO;
import vo.DogVO;

public class DogListService {

	public ArrayList<DogVO> getDogList() throws Exception {
		
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
		
		ArrayList<DogVO> dogList = dogDAO.selectDogList();
			
		close(con);
		
		return dogList;
	};
}
```

#### 1.8 DogDAO => selectDogList();
* selectDogList() 메소드에서는 ArrayList를 하나 만들고, 하나의 개 정보를 담을 수 있는 DogVO를 만들어서 do-while 문을 통해 마지막 줄까지 각각의 개의 정보를 dogList에 담아주고, 끝나면 return dogList를 해준다.

> DogDAO.java

```java
package dao;
 
import java.sql.*;
import vo.DogVO;
import java.util.*;
import static db.JdbcUtil.*; 

 public class DogDAO {
    
	private Connection con;
 	private static DogDAO instance = new DogDAO();
    
    public static DogDAO getInstance() {
        return instance;
    }
    
    private DogDAO() {
    }
    
    public void setConnection(Connection con) {
    	this.con = con;
    }

	public ArrayList<DogVO> selectDogList() {
		
		ArrayList<DogVO> dogList = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM dog";
		
		
		try {
		    pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dogList = new ArrayList<DogVO>();
				DogVO dogVO = null;
				do {
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
					dogList.add(dogVO);
				} while(rs.next());
			}
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return dogList;
	}
  
 }
```

#### 1.9 dogList.jsp : 처음의 요청은 **shoppingStart.jsp => FC => Action => Service => DB** 로 가서 select 를 수행한 후 ArrayList에 각각의 개 정보를 DogVO 객체를 통해서 모두 담아서, **DB => Service => Action => FC => dogList.jsp** 와 같은 형태로 데이터를 이동시킨다.
* dogList에서는 DB에 저장되어있는 Image name의 명으로 <img> 태그를 사용해서 각각의 image 사진을 출력한다.

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
      text-align : center;
   }
</style>
</head>
<body>

<table id="dog_table" border="1"> 
<%
   for (int i = 0; i < dogList.size(); i++){
	   DogVO dog = (DogVO)dogList.get(i);
%>
	   <tr>
	      <td><%= dog.getId()%></td>
	      <td><%= dog.getKind() %></td>
	      <td><%= dog.getPrice() %></td>
	      <td><img src="image/<%= dog.getImage() %>"></td>
	      <td><%= dog.getCountry() %></td>
	      <td><%= dog.getHeight() %></td>
	      <td><%= dog.getWeight() %></td>
	      <td><%= dog.getContent() %></td>
	      <td><%= dog.getReadCount() %></td>
	   </tr>
<%
   }
%>
</table>
</body>
</html>
```



