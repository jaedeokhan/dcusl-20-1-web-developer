### 2020 0615 MON

### Today
1. Board Write 처리하기
1> boardStart.jsp 게시판 글쓰기 클릭하면 boardWriteForm.bo 로 요청을 한다. 
2> BoardFrontController.java 에서 /boardWriterForm.bo  요청을 받고 
3> BoardWriterFormAction.java를 action 인터페이스에 담고, execute를 요청한다. 
4> BoardWriterFormAction.java에서 num != 0 이 아닐때 즉 기존에 해당하는 목록이 있을때는 해당하는 객체들을 들고 board/writeForm.jsp로 간다.
5> 다시, BoardWriteFormAction에서 위에 해당하는 writeForm.jsp로 forward.setUrl를 설정하고 return을 Controller에게 해주고, controller는 해당하는 .jsp로 보낸다.
6> writeForm.jsp 에서 작성할 문구를 작성하고, 글쓰기를 누르면, boardWritePro.bo로 post 방식으로 요청을 한다.
7> BoardFrontController에서는 boardWritePro.bo 에 해당하는 요청이 오면, action 인터페이스에 BoardWriteProAction() 객체를 생성해준다.
8> BoardWriteProAction에서는 작성한 요청들을 받아서 boardWriteProService의 writeArticle(article) 메소드로 작업을 보낸다.
9> BoardWriteproService에서는 insertCount 를 boardDAO.insertArticle(article)메소드로 작업을 요청해서 insertCount를 받아서 boolean writeSuccess를 반환해준다.
10> DAO => Service => Action으로 다시 받아서 BoardFrontController에서 .jsp로 작업을 redirect, forward 해준다.
2. Board List 처리하기
1> BoardStart.jsp 에서 게시판글쓰기 하이퍼 링크를 누른다.
2> boardWriteForm.bo 으로 요청을 하면 BoardFrontController 에서 BoardWriteFormAction으로 가서 다시 setUrl로 board/writeForm.jsp로 해주면 BoardFrontController에서는 forward 해준다.
3> 
4> 
5> 

3. Board Content 상세보기 
4. Board Update 처리하기
5. error 
1> FC(Front Controller) 에서 적절하지 않은 {} 처리
2> Action 에서 request.setAttribute("key", "value"), key 와 value 가 일치하지 않아서 에러가 발생

### error 1 :  boardFrontController에서 else if 문이 {} 잘 닫히지 않아서 적절하게 이루어 지지 않았다.
* 아래와 같이 FC(Front Controller)에서 작업을 할당해줄때 {} 컬리브레이션을 제대로 닫지 않았다.

> error 1 :  해당 에러 코드

```java
		else if(command.contentEquals("/boardContent.bo")) {
			action = new BoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		# 여기서 '}' 처리가 제대로 이루어지지 않았다.
```

> error 1 :  에러 해결 코드

```java
		else if(command.contentEquals("/boardList.bo")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.contentEquals("/boardContent.bo")) {
			action = new BoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

### error 2 : BoardContentAction.java 와 BoardUpdateAction에서도 request.setAttribute("article", article) 를 key와 value 값이 정확하게 입력을 해주지 않아서 계속해서 에러가 나왔다.

> error2 : 에러 코드

```java
# 여기서 보면 article 을 써야하는데, atiicle 과 같이 key 와 value 가 맞지 않다.
request.setAttribute("atiicle", article);
```

> erro2 : 에러 해결 코드

```java
request.setAttribute("article", article);
```


#### 1. Model 1을 사용할때 writePro.jsp 에서 요청을 처리를 했지만, Model 2에서는 BoardFrontController.java로 처리 

#### 1.1 BoardFrontController.java에서 

#### 1.2 writeForm.jsp에서 넘어오는 데이터를 BoardWriteProAction.java 에서 받아서 BoardWriteProService 단으로 넘겨서 BoardDAO 요청을 받기
* boolean writeSuccess = boardWriteProService.writeArticle(article);
   * service 단으로 가서 writeArticle 요청하기
* writeSuccess, 즉 반영된 레코드 수가 0 이상인게 true 이면 forward 객체를 생성해서 setUrl, setRedirect 를 설정해준다.
* 여기서 정확환 setUrl("boardList.bo") 를 설정하고, return 을 명확하게 줘야한다.

> BoardWriteProAction.java

```java
package action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardVO;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		BoardVO article = new BoardVO();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(request.getParameter("ref")));
		
		article.setWriter(request.getParameter("writer"));
		article.setContent(request.getParameter("content"));
		article.setEmail(request.getParameter("eamil"));
		article.setIp(request.getRemoteAddr());
		article.setPasswd(request.getParameter("passwd"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setSubject(request.getParameter("subject"));
		
		BoardWriteProService boardWriteProService = 
				new BoardWriteProService();
		boolean writeSuccess  = boardWriteProService.writeAritcle(article);
		
		ActionForward forward = null;
		if (writeSuccess) {
			forward = new ActionForward();
			// 글 등록이 성공하고, 글 목록 보기로 바로 날린다.
			forward.setUrl("boardList.bo");
			forward.setRedirect(true);
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("<history.back();>");
			out.println("</script>");
		}
		return forward;
	}

}
```

#### 1.2 BoardWriteProService.java  => boardDAO.insertArticle(article)
* service 에서 BoardDAO에 insertArticle 요청을 해서 INSERT 를 실행한 레코드 수를 요청을 받기
* int insertCount가 1 이상이면 commit을 하고,  writeSuccess는 true로 주고, else이면 rollback 을 한다. 

> BoardWriteProService.java

```java
package svc;

import java.sql.Connection;
import static db.JdbcUtil.*;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardWriteProService {

	public boolean writeAritcle(BoardVO article) throws Exception{
		
		boolean writeSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int insertCount = boardDAO.insertArticle(article);
		if (insertCount > 0) {
			commit(con);
			writeSuccess = true;
		}
		else {
			rollback(con);
		}
		return writeSuccess;
	}
}
```

#### 1.3 BoardDAO.java => insertArticle(article)
* return 을 insertCount로 받는다.

> BoardDAO.insertArticle(BoardVO article)


```java
    public int insertArticle(BoardVO article)
    throws Exception {
    	int insertCount  = 0;
        PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 답변글 처리에 필요한 값들을 변수에 저장
		int num=article.getNum();
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();

		// 관련글 번호를 저장할 변수
		// 원글을 작성했을 때 관련글 번호를 새로 생성할 것임
		int number=0;
        String sql="";

        try {

            // 새로운 관련글 번호를 구하는 SQL 구문
            pstmt = con.prepareStatement
            		("select max(num) from board");
			rs = pstmt.executeQuery();

			// 이미 다른글이 작성되어있다면!
			// 지금 등록하는 글이 첫 번째 글이 아니면
			if (rs.next())
		      number=rs.getInt(1)+1;
			// 지금 작성한 글이 첫번째이면! => 글의 목록을 1로 준다.
		    else
		      number=1;

			// 원글 쓰는것이 아니라, 답변 글을 쓸때!
		    if (num!=0)
		    {
		      sql="update board set re_step=re_step+1 " +
		      		"where ref= ? and re_step> ?";
              pstmt = con.prepareStatement(sql);
              pstmt.setInt(1, ref);
			  pstmt.setInt(2, re_step);
			  pstmt.executeUpdate();

			  // 지금 등록하는 글의 값 구하기 - 자신
			  re_step=re_step+1;
			  re_level=re_level+1;
		     }
		    // 현재 작성하는게 원글이면! 왜냐하면, 0이니까!
		    else{
		  	  ref=number;
			  re_step=0;
			  re_level=0;
		     }
            // ������ �ۼ�
            sql = "insert into board" +
           "(num,writer,email,subject,passwd,reg_date,";
            sql+="ref,re_step,re_level,content,ip) " +
            "values(board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getEmail());
            pstmt.setString(3, article.getSubject());
            pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date());
            pstmt.setInt(6, ref);
            pstmt.setInt(7, re_step);
            pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());

            insertCount = pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			close(rs);
			close(pstmt);
        }

        return insertCount;
    }
```

#### 2. BoardList 에 관련한 작업

#### 2.1 BoardListAction.java => 기존에 list.jsp에 있는 연산을 하는 행위는 모두 Action에 위치해야한다.
* 기존에 페이징 관련에 사용하기 위한 연산을 모두 Action에 배치 시키고, 변수만 전달을 해준다.


> BoardListAction.java

```java
package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import svc.BoardListService;
import vo.ActionForward;
import vo.BoardVO;
import vo.PageVO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			//데이터들을 PageVO로 담아서 보내기 위해서는 지역 변수가 아니라, 전역 변수로 밖으로 빼준다.
			int pageCount = 0;
			int startPage = 0;
			int endPage = 0;
		 	int pageSize = 10;
		 
			String pageNum = request.getParameter("pageNum");
		    if (pageNum == null) {
		        pageNum = "1";
		    }
			
		    // 하단에 페이지 번호를 가지고 연산하는 부분이 있다.
		    // 연산이 제대로 될 수 있도록 정수로 변환한다.
		    int currentPage = Integer.parseInt(pageNum);
		    
		    // 해당 페이지에 첫 번째로 출력되는 글의 레코드 번호를 구하는 부분 
		    // 현재 페이지가 1이라면
		    // (1 - 1) * 10 + 1 =====> 1번 레코드 가져온다.
		    // (2 - 1) * 10 + 1 =====> 11번 레코드 가져온다.
		    // (3 - 1) * 10 + 1 =====> 21번 레코드 가져온다.
		    int startRow = (currentPage - 1) * pageSize + 1;
		 
		    // 전체 글의 개수를 저장할 변수 => board 테이블 자체에 글이 몇개인지?
		    int count = 0;
		    // 해당 페이지에 첫 번째로 출력되는 글의 글번호
		    int number=0;

		    // 해당 페이지에 출력되는 글정보들을 저장할 컬렉션
		    List<BoardVO> articleList = null;
		    BoardListService boardListService = 
		    		new BoardListService();
		    
		    count = boardListService.getArticleCount();
		    
		    // 번호 == 글이 하나라도 있으면!
		    if (count > 0) {
		        articleList = boardListService.getArticles(startRow, pageSize);
		    }
			// 페이지에 첫 번째 번호를 구하는 방법
			// 1페이지, 총 글의 개수 : 123개라면
			// 123 - (1 - 1) * 10 ===> 123이다.
			// 2페이지, 총 글의 개수 : 130개라면
			// 130 - (2 - 1) * 10 ===> 120
			number=count-(currentPage-1)*pageSize;
			
			//########################################################################
	        if(count > 0) {
				// 총 페이지 개수를 구하는 것이다.
		        // 글이 123개, pageSize 10 으로 나누면 => 12
		        // count % pageSize ==> 3 ==>     + 1
		        // pageCount = 13개이다.
		    	pageCount = count / pageSize + 
		        		( count % pageSize == 0 ? 0 : 1);
				 
		        // 해당 페이지 그룹에서 첫 번재로 시작되는 페이지번호
		        startPage = (int)(currentPage - 1/10)*10+1;
				
		        int pageBlock=10;
		        
		        // 해당 페이지 그룹의 마지막 페이지 번호
				//              21      +   10 - 1
		        endPage = startPage + pageBlock-1;
		        
		        // 마지막 페이지 그룹의 경우 
		        if (endPage > pageCount) endPage = pageCount;
	        }
	       
	    // 클래스를 공유해서 하나의 속성값으로 보내는 것이 더 효율적이다.
	    PageVO pageVO = new PageVO();
	    pageVO.setCount(count);
	    pageVO.setNumber(number);
	    pageVO.setCurrentPage(currentPage);
	    pageVO.setPageCount(pageCount);
	    pageVO.setStartPage(startPage);
	    pageVO.setEndPage(endPage);
	    
	    request.setAttribute("pageVO", pageVO);
	    request.setAttribute("articleList", articleList);
	    
	    ActionForward forward = new ActionForward();
	    forward.setUrl("board/list.jsp");
	   
		return forward;
	}

}
```

#### 2.2 BoardListService에서 boardDAO에 현재 article 의 모든 카운트를 얻어온다.count(*)
* Service 단에서는 항상 connection을 관리해줘야 한다.
* DAO 에게 직접적으로 요청을 보내는것 또한 service이다.

> BoardListService.java => boardDAO.selectArticleCount();

```java
	public int getArticleCount() throws Exception {
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int articleCount = boardDAO.selectArticleCount();
		
		close(con);
		
		return articleCount;
	}
```

#### 2.3 BoardDAO 에서 SELECT COUNT(*) FROM board; 로 모든 행의 갯수를 가져온다.
* 전체 글의 갯수를 얻어오기.

> BoardDAO.java => selectArticleCount()

```java
	public int selectArticleCount()
    throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs =null;

        int articleCount = 0;

        try {
            con = getConnection();
            
            pstmt = con.prepareStatement
            		("select count(*) from board");
            rs = pstmt.executeQuery();

            if (rs.next()) {
               articleCount= rs.getInt(1);
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	close(rs);
        	close(pstmt);
        }
		return articleCount;
    }
```

#### 2.4 BoardListService에서 DAO에게 선택할 리스트 목록을 모두 가져온다.
* BoardListService에서 BoardDAO 에게 모든 글의 리스트와 목록을 가져오게 한다.

> BoardListService.java => BoardDAO.selectArticleList(startRow, pageSize);

```java
	public List selectArticleList(int start, int end)
    throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        List articleList=null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            
            pstmt = con.prepareStatement(
			"select list2.* from(select rownum r, list1.*  " +
			"from(select *  from board order by ref desc, re_step asc)list1) " +
			"list2 where r between ? and ?");
            pstmt.setInt(1, start);
			pstmt.setInt(2, start+end-1);
            rs = pstmt.executeQuery();
    /*    try {
         conn  = getConnection( );
            stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            String query ="select * from board order by ref desc, re_step asc";
            rs = stmt.executeQuery( query );

      int skip  = start - 1;

      if( skip > 0 )
        rs.absolute( skip );
        */
                if (rs.next()) {
                int i=0;
                articleList = new ArrayList(end);
                do{
                  BoardVO article= new BoardVO();
      article.setNum(rs.getInt("num"));
      article.setWriter(rs.getString("writer"));
                  article.setEmail(rs.getString("email"));
                  article.setSubject(rs.getString("subject"));
                  article.setPasswd(rs.getString("passwd"));
         article.setReg_date(rs.getTimestamp("reg_date"));
      article.setReadcount(rs.getInt("readcount"));
                  article.setRef(rs.getInt("ref"));
                  article.setRe_step(rs.getInt("re_step"));
      article.setRe_level(rs.getInt("re_level"));
                  article.setContent(rs.getString("content"));
         article.setIp(rs.getString("ip")); 
      
                  articleList.add(article);
                  i++;
       }while(rs.next()&& i<end);
   }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
           close(rs);
           close(pstmt);
        }
  return articleList;
    }
```


#### 2.5 PageVO
* count, number, currentPage, endPage, pageCount 캡슐화하고, Getter, Setter를 사용해서 메소드 생성하기.

> vo/PageVO.java

```java
package vo;

public class PageVO {
	private int count;
	private int number;
	private int currentPage;
	private int endPage;
	private int pageCount;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
```
 
#### 2.6  list.jsp 에서 BoardListAction.java 에서 setAttribute한 데이터들을 불러 들인다.
* Action에서 setAttribute한 데이터들을 사용한다.
   * request.getAttribute("데이터이름");
   * PageVO 에 있는 데이터들을 사용
   * articleList에 있는 데이터들을 사용

> list.jsp

```html
<%@ page contentType = "text/html;charset=UTF-8" %>
<%@ page import = "dao.BoardDAO" %>
<%@ page import = "vo.BoardVO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import ="vo.PageVO" %>
<%@ include file="/view/color.jsp"%>

<%
   SimpleDateFormat sdf = 
   				new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%
   PageVO pageVO = (PageVO)request.getAttribute("pageVO");
   int count = pageVO.getCount();
   int number = pageVO.getNumber();
   int currentPage = pageVO.getCurrentPage();
   int endPage = pageVO.getEndPage();
   int startPage = pageVO.getStartPage();
   int pageCount = pageVO.getPageCount();
   List<BoardVO> articleList = (List<BoardVO>)request.getAttribute("articleList");
   
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="<%=bodyback_c%>">
<center><b>글목록(전체 글:<%=count%>)</b>
<table width="700">
<tr>
    <td align="right" bgcolor="<%=value_c%>">
    <a href="writeForm.jsp">글쓰기</a>
    </td>
</table>

<%
	if (count == 0) {
%>
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
    <td align="center">
    게시판에 저장된 글이 없습니다.
    </td>
</table>

<%
	} else {
%>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="<%=value_c%>"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
<%
	for (int i = 0 ; i < articleList.size() ; i++) {
          BoardVO article = (BoardVO)articleList.get(i);
%>
   <tr height="30">
    <td align="center"  width="50" > <%=number--%></td>
    <td  width="250" >
	<%
		  // 들여쓰기 이미지의 넓이를 저장하는 변수 
	      int wid=0; 
	      if(article.getRe_level()>0){
	      // wid == level 1이면 공백은 5
	      // wid == level 2이면 공백은 10
	        wid=5*(article.getRe_level());
	%>
	  <img src="board/images/level.gif" width="<%=wid%>" height="16">
	  <img src="board/images/re.gif">
	<%}else{%>
	  <img src="board/images/level.gif" width="<%=wid%>" height="16">
	<%}%>
           
      <a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
           <%=article.getSubject()%></a> 
          <% if(article.getReadcount()>=20){%>
         <img src="images/hot.gif" border="0"  height="16"><%}%> 
         </td>
    <td align="center"  width="100"> 
       <a href="mailto:<%=article.getEmail()%>">
       <%=article.getWriter()%></a></td>
    <td align="center"  width="150">
    <%= sdf.format(article.getReg_date())%></td>
    <td align="center"  width="50"><%=article.getReadcount()%>
    </td>
    <td align="center" width="100" ><%=article.getIp()%></td>
  </tr>
     <%}%>
</table>
<%}%>

<%
 // 페이징 처리하는 부분이다. 
    if (count > 0) {

        
        // 첫 번째 페이지 그룹이 아니면, [이전] 링크를 걸어준다.
        if (startPage > 10) {
        // 이전 그룹의 첫 페이지로 이동한다.	
        	%>
        <a href="boardlist.jsp?pageNum=<%= startPage - 10 %>">[이전]</a>
<%      }
        // startPage부터 endPage 까지 찍는 것이다. 
        for (int i = startPage ; i <= endPage ; i++) {  %>
        <a href="boardlist.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
        }
        // 마지막 페이지 그룹이 아니면... 즉 다음 그룹이 있다
        // startPage + 10 이라면 ==? 1 + 10 => 11 로 간다. 다음 페이지로 간다.
        if (endPage < pageCount) {  %>
        <a href="boardlist.jsp?pageNum=<%= startPage + 10 %>">[다음]</a>
<%
        }
    }
%>
</center>
</body>
</html>
```

#### 3. 해당 글의 상세 페이지 보여주기
* action 까지가 controller 이고, service 부터는 모델이다.

#### 3.1 list.jsp 에서 해당 내용 상세보기는 boardContent.bo 로 요청을 보내준다.

> list.jsp => 83 line

```java
      <a href="boardContent.bo?num=<%=article.getNum()%>&pageNum=<%=currentPage%>>
```

#### 3.2 BoardFrontController => boardConent.bo 와 관련해서 요청이 오면 BoardContentAction으로 처리하게 요청 

> BoardFrontController.java

```java
		else if(command.contentEquals("/boardContent.bo")) {
			action = new BoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

#### 3.3 BoardContentAction => 기존의 content.jsp에서 요청한 데이터 num, pageNum을 처리하고, BoardConentService 단으로 getArticle(num) 메소드르 사용하고, ActionFoward  객체를 토앻서 setUrl, redirect 여부를 결정하고, forward를 return해준다.
* article, pageNum 을 setAttribute 해준다.
* setUrl, redirect 결정

> BoardContentAction.java

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import svc.BoardContentService;
import vo.ActionForward;
import vo.BoardVO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardContentService boardContentService = 
				new BoardContentService();
		
		BoardVO article =  boardContentService.getArticle(num);
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("board/content.jsp");
		
		return forward;
	}

}
```

#### 3.4 BoardContentService => DAO에 selectArticle(num) 메소드를 통해서 BoardVO타입으로 article를 해당 상세내용의 데이터들을 모두 얻는다.
* service 단부터는 Model 의 역할이다.
* Connection 관리는 트랙잭션 때문에 service 단에서 수행한다.

> BoardConetentService.java 

```java
package svc;
import static db.JdbcUtil.*;


import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardContentService {

	public BoardVO getArticle(int num) throws Exception{
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);	    
		
        BoardVO article = boardDAO.selectArticle(num);
        
        close(con);
        
		return article;
	}	
}
```

#### 3.5 BoardDAO => Service에서 요청한 작업을 처리한다. 
* UPDATE 구문으로 readcount + 1을 해준다.
* SELECT 구문으로 where num = ? 해당하는 모든 컬럼을 불러온다.

> BoardDAO.selectArticle(int num)

```java
    public BoardVO selectArticle(int num)
    throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardVO article=null;
        try {
            pstmt = con.prepareStatement(
            	"update board set readcount=readcount+1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

            pstmt = con.prepareStatement(
            	"select * from board where num = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                article = new BoardVO();
                article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
                article.setEmail(rs.getString("email"));
                article.setSubject(rs.getString("subject"));
                article.setPasswd(rs.getString("passwd"));
			    article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
                article.setRef(rs.getInt("ref"));
                article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
                article.setContent(rs.getString("content"));
			    article.setIp(rs.getString("ip"));     
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	close(rs);
        	close(pstmt);
        }
		return article;
    }
```

#### 4. Board Update 처리하기 

#### 4.1 UpdateForm.jsp => 기존에 모델 1에서 처리하는 연산을 모두 UpdateFormAction.java 로 모두 넘겨준다.

> UpdateForm.jsp

```html
<%@ page contentType = "text/html;charset=UTF-8" %>
<%@ page import = "dao.BoardDAO" %>
<%@ page import = "vo.BoardVO" %>
<%@ include file="/view/color.jsp"%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<%
   BoardVO article = (BoardVO)request.getAttribute("article");
   String pageNum = (String)request.getAttribute("pageNum");
%>

<body bgcolor="<%=bodyback_c%>">  
<center><b>글수정</b>
<br>
<form method="post" name="writeform" 
action="boardUpdatePro.bo?pageNum=<%=pageNum%>" 
onsubmit="return writeSave()">
<table width="400" border="1" cellspacing="0" cellpadding="0"  bgcolor="<%=bodyback_c%>" align="center">
  <tr>
    <td  width="70"  bgcolor="<%=value_c%>" align="center">이 름</td>
    <td align="left" width="330">
       <input type="text" size="10" maxlength="10" name="writer" value="<%=article.getWriter()%>">
	   <input type="hidden" name="num" value="<%=article.getNum()%>"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="<%=value_c%>" align="center" >제 목</td>
    <td align="left" width="330">
       <input type="text" size="40" maxlength="50" name="subject" value="<%=article.getSubject()%>"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="<%=value_c%>" align="center">Email</td>
    <td align="left" width="330">
       <input type="text" size="40" maxlength="30" name="email" value="<%=article.getEmail()%>"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="<%=value_c%>" align="center" >내 용</td>
    <td align="left" width="330">
     <textarea name="content" rows="13" cols="40"><%=article.getContent()%></textarea></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="<%=value_c%>" align="center" >비밀번호</td>
    <td align="left" width="330" >
     <input type="password" size="8" maxlength="12" name="passwd">
     
	 </td>
  </tr>
  <tr>      
   <td colspan=2 bgcolor="<%=value_c%>" align="center"> 
     <input type="submit" value="글수정" >  
     <input type="reset" value="다시작성">
     <input type="button" value="목록보기" 
       onclick="document.location.href='boardlist.bo?pageNum=<%=pageNum%>'">
   </td>
 </tr>
 </table>
</form>
</body>
</html>      

```

#### 4.2 BoardFrontController => UpdateForm.jsp 'boardUpdateForm.bo' 요청을 하면 처리한다.

> BoardFrontController.java

```java
		else if(command.contentEquals("/boardUpdateForm.bo")) {
			action = new BoardUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
```

#### 4.3 BoardUpdateFormAction => 기존의 UpdateForm.jsp 에서 처리하는 연산을 처리해주고, Service 단에 요청과 FC에게 특정 .jsp 가 담긴 forward를 넘겨준다.

> BoardUpdateFormAction.java 

```java
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardUpdateFormService;
import vo.ActionForward;
import vo.BoardVO;

public class BoardUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardUpdateFormService boardUpdateFormService =
				new BoardUpdateFormService();
		
	
		BoardVO article =  boardUpdateFormService.getUpdateArticle(num);
		request.setAttribute("article", article);
//		request.setAttribute("pageNum", pageNum);

		ActionForward forward = new ActionForward();
		forward.setUrl("board/updateForm.jsp");
		
		return forward;
	}

}

```

#### 4.4 BoardUpdateFormAction => Action에서 요청한 작업을 DAO에게 요청한다.

> BoardUpdateFormAction.java

```java
package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardUpdateFormService {

	public BoardVO getUpdateArticle(int num) throws Exception {
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		BoardVO article = boardDAO.selectUpdateArticle(num);
		
		close(con);
		
		return article;
	}

}

```

#### 4.5 BoardDAO.java => Servic에서 요청한 DB작업을 실행해서 돌려준다.

> BoardDAO.selectUpdateArticle(int num) 

```java
    public BoardVO selectUpdateArticle(int num)
    throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardVO article=null;
        try {
            con = getConnection();

            pstmt = con.prepareStatement(
            	"select * from board where num = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                article = new BoardVO();
                article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
                article.setEmail(rs.getString("email"));
                article.setSubject(rs.getString("subject"));
                article.setPasswd(rs.getString("passwd"));
			    article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
                article.setRef(rs.getInt("ref"));
                article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
                article.setContent(rs.getString("content"));
			    article.setIp(rs.getString("ip"));     
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
           close(rs);
           close(pstmt);
        }
		return article;
    }
```

#### 4.6 DAO => Service => Action => FrontController => 해당하는 .jsp(redirect, forward) 방식으로 결정되서 진행된다

