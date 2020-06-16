### 2020 0616 TUE

### Today
1. board update pro
2. board delete form 
3. board delete pro 

### error
1. BoardModifyFormAction.java 에서 request.setAttribute("pageNum", pageNum)과 같은 데이터를 명확하게 공유를 해주기.
2. boardUpdatePro.bo 로 pageNum=1은 넘어왔지만, 계속해서 boardListbo?pageNum=작업중이던페이지 로 redirect 를 하지 못했다. 왜냐하면, BoardModifyProService 에서 if updateCount > 0 이상이면 modifySuccess 를 true 로 줘야하는데 주지 못해서 rollback이 일어나고, return 으로 false를 해줘서!!


#### 1. UpdatePro 

#### 1.1 UpdateForm.jsp => boardUpdatePro.bo 와 관련한 요청이 오면 FC(Front Controller)에서 받는다.

> UpdateForm.jsp

```html
<form method="post" name="writeform" 
action="boardUpdatePro.bo?pageNum=<%=pageNum%>" 
onsubmit="return writeSave()">
```

> BoardFrontController.java

```java
		else if(command.contentEquals("/boardUpdatePro.bo")) {
			action = new BoardUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

#### 1.2 BoardModifyProAction => UpdateForm.jsp 에서 입력한 데이터들을 받아서 Service 단으로 작업을 넘기고,  forward와 redirect를 해준다. 
* boolean modifySuccess 가 true 이면, boardList.bo?pageNum= + pageNum(작성중이였던 페이지 번호) 로 redirect를 보내준다.


> BoardModifyProAction.java

```java
package action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BoardDAO;
import svc.BoardUpdateProService;
import vo.ActionForward;
import vo.BoardVO;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardVO article = new BoardVO();
		// 답변글이나, 답변글이 아니냐 지금 그게 필요한거다!!
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setContent(request.getParameter("content"));
		article.setEmail(request.getParameter("eamil"));
		article.setIp(request.getRemoteAddr());
		article.setPasswd(request.getParameter("passwd"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setSubject(request.getParameter("subject"));
		
		String pageNum = request.getParameter("pageNum");
		
		BoardUpdateProService boardUpdateProService = 
				new BoardUpdateProService();
		
	    boolean modifySuccess = boardUpdateProService.modifyArticle(article);
	    
		ActionForward forward = null;
		if (modifySuccess) {
			forward = new ActionForward();
			// 글 등록이 성공하고, 글 목록 보기로 바로 날린다.
			forward.setUrl("boardList.bo?pageNum=" + pageNum);
			forward.setRedirect(true);
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("<history.back();>");
			out.println("</script>");
		}
		
		return forward;
	}

}
```

#### 1.3 BoardModifyProService
* 수정글이 답변글이냐 원글이냐 구분의 필요는 없기에 num, ref, re_step, re_level은 request.getParameter("변수")를 하지 않는다.
* BoardDAO.updateArticle(article)에서 select 와 update를 동시에 실행하고, 트랜잭션의 처리를 위해서 updateCount 가 0 을 초과하면 

> BoardModifyProService.java

```java
<package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardVO;


public class BoardUpdateProService {

	public boolean modifyArticle(BoardVO article) throws Exception{
		
		boolean modifySuccess = false;
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCount = boardDAO.updateArticle(article);
		
		if (updateCount > 0) {
			commit(con);
			// 요 놈!!
			modifySuccess = true;
		}
		else {
			rollback(con);
		}
		close(con);
		
		return modifySuccess;
	}
	
	
}
```

#### 1.4 BoardDAO.updateArticle(article) 

> BoardDAO.updateArticle(article)

```java
    public int updateArticle(BoardVO article)
    throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs= null;

        String dbpasswd="";
        String sql="";
		int updateCount=-1;
		
        try {
			pstmt = con.prepareStatement(
            	"select passwd from board where num = ?");
            pstmt.setInt(1, article.getNum());
            rs = pstmt.executeQuery();
            
			if(rs.next()){
			  dbpasswd= rs.getString("passwd"); 
			  if(dbpasswd.equals(article.getPasswd())){
                sql="update board set writer=?,email=?,subject=?,passwd=?";
			    sql+=",content=? where num=?";
                pstmt = con.prepareStatement(sql);

                pstmt.setString(1, article.getWriter());
                pstmt.setString(2, article.getEmail());
                pstmt.setString(3, article.getSubject());
                pstmt.setString(4, article.getPasswd());
                pstmt.setString(5, article.getContent());
			    pstmt.setInt(6, article.getNum());
                pstmt.executeUpdate();
                updateCount= 1;
			  }else{
				updateCount= 0;
			  }
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			close(rs);
			close(pstmt);
        }
		return updateCount;
    }
```

#### 2. Board Delete

#### 2.1 content.jsp => boardRemoveForm.bo 요청!

> content.jsp

```html
 <input type="button" value="글삭제" 
       onclick="document.location.href='boardRemoveForm.bo?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">

```

#### 2.2 FC 에서 boardRemoveForm.bo 로 오는 작업을 처리 => BoardRemoveFormAction으로 요청

> BoardFrontController.java

```java
		else if(command.contentEquals("/boardRemovePro.bo")) {
			action = new BoardRemoveProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
```


#### 2.3 delete.jsp에서 사용하던 연산에 필요한 작업을 action으로 옮긴다.
* Action 단위에서 클래스의 이름도 DB 와 관련한 DELETE 이름말고 Remove와 같은 것을 사용한다.

> BoardRemoveFormAction.java

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class BoardRemoveFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 기존 delete.jsp 사용하던 연산
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		//
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("board/deleteForm.jsp");
		
		return forward;
		
	}

}
```

#### 2.5 FC(Front Controller) 에서 deleteForm.jsp로 redirect를 해준다.

#### 3. deletePro => 삭제하는 것 구현하기! 

#### 3.1 deleteForm.jsp 에서 boardRemovePro.bo 로 요청이 오면 FC 에서 처리하기. 
* 아래와 같이 form 태그를 보내면 action으로 boardRemovePro.bo 로 요청을 보낸다.
* 그러면, FC(Front Controller) 에서 해당 요청을 처리한다.

> deleteForm.jsp

```html
<form method="POST" name="delForm"  action="boardRemovePro.bo?pageNum=<%=pageNum%>" 
   onsubmit="return deleteSave()"> 
```

> BoardFrontController.java

```java
		else if(command.contentEquals("/boardRemovePro.bo")) {
			action = new BoardRemoveProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
```

#### 3.2 FC에서 action 인터페이스에 BoardRemoveProAction을 할당해서 처리한다.
* Action에서는 Controller 의 역할을 주로 한다.
   * Model 에 작업을 요청한다.( service => DB)
   * forward 를 할 url, redirect 여부를 결정해서 FC로 return을 해준다.

> BoardRemoveProAction.java

```java
package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardRemoveProService;
import vo.ActionForward;

public class BoardRemoveProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int num = Integer.parseInt(request.getParameter("num"));
	    String pageNum = request.getParameter("pageNum");
	    String passwd = request.getParameter("passwd");
	    
	    
	    BoardRemoveProService boardRemoveProService 
	    		= new BoardRemoveProService();
	   

	    boolean removeSuccess = boardRemoveProService.removeArticle(num, passwd);
	    
		ActionForward forward = null;
		if (removeSuccess) {
			forward = new ActionForward();
			// 글 등록이 성공하고, 글 목록 보기로 바로 날린다.
			forward.setUrl("boardList.bo?pageNum=" + pageNum);
			forward.setRedirect(true);
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("<history.back();>");
			out.println("</script>");
		}
		
		return forward;
	}

}
```

#### 3.3 BoardRemoveProService => 
* Action 단에서 요청을 하면 Service 단에서는 con을 관리하고, commit, rollback 여부와 removeSuccess와 같은 불린형의 db 작업의 여부를 return해준다.

> BoardRemoveProService.java

```java
package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;


public class BoardRemoveProService {

	public boolean removeArticle(int num, String passwd) throws Exception {
		
		boolean removeSuccess = false;
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int deleteCount = boardDAO.deleteArticle(num, passwd);
		
		if (deleteCount > 0) {
			commit(con);
			removeSuccess = true;
		}
		else {
			rollback(con);
		}
		
		return removeSuccess;
	}
	
	
}
```

#### 3.4 Service => DB 작업 요청 (boardDAO.deleteArticle(num, passwd) 메소드
* Service 에서 DB 작업을 요청하는 이름은 CRUD 와 관련된 이름을 사용한다.
* SELECT passwd FROM board WHERE num = ? : 을 실행해서 해당하는 num의 passwd를 가져온다.
* DELETE FROM board WHERE num = ? : 해당하는 num의 모든 데이터를 삭제한다.
 
> BoardDAO.deleteArticle(num, passwd)

```java
    public int deleteArticle(int num, String passwd)
    throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        String dbpasswd="";
        int x=-1;
        try {
            pstmt = con.prepareStatement(
            	"select passwd from board where num = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            
			if(rs.next()){
				dbpasswd= rs.getString("passwd"); 
				if(dbpasswd.equals(passwd)){
					pstmt = con.prepareStatement(
            	      "delete from board where num=?");
                    pstmt.setInt(1, num);
                    pstmt.executeUpdate();
					x= 1; //�ۻ��� ����
				}else
					x= 0; //��й�ȣ Ʋ��
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
		return x;
    }
```




