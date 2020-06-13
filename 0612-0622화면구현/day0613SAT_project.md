### 2020 0613 SAT
### Today
1. 오정원 교수님 블로그에서 project import를 해서 tomcat v8.5, jdk1_8~ 설정해주>기
   * Java Build Compiler도 1.8 으로 맞춰준다.
   * Navigator bar에 들어가서 기존의 프로젝트의 setting를 모두 복사해서, component는 자신의 프로젝트 이름으로 변경해준다.
   * CP(Connection Pool) : CP를 사용하기 위해서는 tomcat/lib에 ojdbc6.jar을 넣어줘야한다. 


#### 1. project model 1 방식으로 구현

#### 1.1 테이블.sql  : board TABLE 생성
* re_step  : 원글과 답글의 순서를 확인해서 sort 해주기 위해서 사용한다.
* re_level : 만약 원글에 답글이 달리면 re_level 원글은 0, 답글은 1 , 원글에 또 답글이 달리면 그 답글도 1이다.
* SEQUENCE

> 테이블.sql

```sql
create table board(
num NUMBER primary key , -- 게시판 글 번호
writer varchar2(10) not null, -- 작성자(회원 아이디)
email varchar2(30), -- 이메일
subject varchar2(50) not null,-- 글제목
passwd varchar2(12) not null,--비밀번호
reg_date timestamp(6) not null,--작성일
readcount NUMBER default 0,--조회수
ref NUMBER not null,-- 글 그룹번호, 원글하고 답글을 동일하게 묶어주기 위해서 사용
re_step NUMBER not null, -- 같은 그룹에서 글 출력, 요 놈으로 sort를 한다.
re_level NUMBER not null, -- 답글 레벨, 들여쓰기 레벨을 접근하는 것 
content varchar2(4000) not null, -- 글 내용
ip varchar2(20) not null -- 
);

CREATE SEQUENCE board_seq;
```

#### 1.2 BoardDataBean.java : VO(ValueObject)

#### 1.3 BoardDBBean.java : DAO

#### 1.4 writeFrom.jsp : 시작 페이지 
* int num=0, ref=1, re_step=0, re_level=0 //
* 사용자가 글 내용을 보고 답변하기 요청을 했을 때 값이 넘어옴
* 사용자가 원 글을 작성할때도 사용하고, 사용자가 답변 쓰기 요청을 할때도 이창에서 쓴다. 그리고 답변글도 이창에서 사용한다.
   * Servlet에서는 다르게 처리한다. 
   * 답변글을 사용하기 위해서 input type="hidden" 으로 num, ref, re_step, re_level 을 보낸다.

#### 1.5 project/WebContent/META-INF/context.xml
*  ojdbc6.jar 를 C:\Users\330-15\Desktop\git_file\20_1_JSP_board\apache-tomcat-8.5.51\lib 에 넣어준다.

> context.xml

```java
 <Context> 
  <Resource name="jdbc/jsptest" 
   auth="Container"
   type="javax.sql.DataSource" 
   username="java" 
   password="java"
   driverClassName="oracle.jdbc.driver.OracleDriver"
   factory="org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory"
   url="jdbc:oracle:thin:@127.0.0.1:1521:XE"
   maxActive="500" 
   maxIdle="100"/>  
 </Context>

```

#### 1.6 write.jsp 에서 원글을 쓰는 것이면 num = 0 이고, 답변 글 쓰기로 사용을 하면 num을 할당해줘서 BoardDBBean에서 if(num!=0) 이거라는 것은 현재는 원글을 쓰는것이 아니라 답변  글쓰기로 사용을 하는 것이다.


#### 1.7 Main Query : 메인 쿼리 안에서 "()" 묶여서 독립적으로 실행되는 쿼리를 sub query 라고 함
   * 특히, 메인 쿼리의 FROM 뒤에 위치하는 서브쿼리를 인라인뷰라고 한다.
   * 뷰(View) : 가상 테이블의 용도는 보안! , 복잡한 SQL 구문을 정의하는 정도


#### 1.7.1 View 생성하는 방법

```sql

CREATE VIEW board_view
AS
SELECT num, page FROM board;
```

#### 1.7.2 rownum : SELECT 구문에 의해서 반한되는 레코드 번호를 부여하는 함수
* 왜? rownum을 만들어주는가?

```sql
SQL> SELECT rownum r, list1.* FROM (SELECT id, name FROM test3 ORDER BY name DESC) list1 WHERE r BETWEEN 1 AND 3; 
# 에러가 난다. 

SQL> SELECT list2.* FROM (SELECT rowname r, list1.* FROM(SELECT id, name FROM test3 ORDER BY name DESC) list1) list2 WHERE r BETWEEN 1 AND 3;
# list1 , list2는 alias이다.
```

#### 1.8 list.jsp 
* int pageSize = 10; => 한 페이지당 출력되는 글이 개수
* String pageNum = request.getParameter("pageNum"); => 사용자가 클릭한 pageNum 
* int currentPage  = Integer.parseInt(pageNum); => String으로 받아서 연산을 위해 타입 변경
* int startRow = (currentPage -1) * pageSize + 1; : 현재 페이지에서 첫 번째로 출력되는 글의 레코드 번호를 구하는 부분

```
int startRow = (CurrentPage - 1) * pageSize +  1;
만약 현재 currnetPage = 1이라면,
startRow = (currentPage - 1) * pageSize + 1;
  1      =   ( 1  -   1)     *   10   +  1;
  11     =   ( 2  -   1)     *   10   +  1;
  21     =   ( 3  -   1)     *   10   +  1;

# 전체 글의 개수를 저장할 변수 
int count = 0;

# 해당 페이지에 첫 번째로 출력되는 글의 글번호
int number = 0;
# count(*) 현재 coun가 존재하는지 DB에서 판단하고 오는 것!
count = dbPro.getArticleCount(); 

# count 0 보다 크다면 즉 1이상이면 SQL 서브 쿼리를 사용해서 모두 불러온다.
if (count > 0) {
        articleList = dbPro.getArticles(startRow, pageSize);
    }

# 페이지의 첫 번째 번호를 구하는 방법
number = count-(currentPage-1)*pageSize;
만약, 1페이지, 총 글의 개수 123개라면
 123  =  123 - ( 1 - 1  )   *  10 

```

#### 페이징 처리하는 부분

```
# 총 페이지 개수를 구하는 방법
int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
(12 + 1 = 13 ) =  (123/10==12)    + (123%10==3) -> 0이 아니기에 1을 더한다       
(12 + 0 = 12 ) =  (120/10==12)    + (120%10==0) -> 0이여서 0을 준다.


# 해당 페이지 그룹에서 첫 번째로 시작되는 페이지 구하는 방법
int startPage = (int)(currentPage - 1/10) * 10 + 1;
     11    =         (   1        - 1/10) * 10 + 1;
     21	   =         (   2        -  0  ) * 10 + 1;

# 해당 페이지 그룹의 마지막 페이지 번호
int pageBlock = 10;
int engPage = startPage + pageBlock - 1;
    20        =   11      +   10      - 1;
```




#### 2. 위의 방식을 model 2 version으로 구현하기 + Front Controller 가 추가된다.
* Front Controller : 대표 서블릿을 만들어서 처리하는 방식
   * 사용하는 이유는 Servlet의 개수가 상당히 많아지기에 여러 개의 요청을 한 번에 여러 개의 요청을 받아서 사용가능하게 하는 것이다.

#### 2.1 BoardFrontController.java : mapping은 *.vo 로 뭐로 시작하던지 vo로 끝나면 모두 처리를 해준다.
* Front Controller 는 어떤 요청이 오는지 모르기에 1. 요청 파악을 먼저 하고 2. 요청을 처리한다. 3. 포워딩을 진행한다. 
* getContextPath() 는 프로젝트 path만 얻어오는 메소드
   * http://localhost/ZESTINE/test.jsp 경우 => /ZESTINE 경로만 얻는다.
* getRequestURL() 은 프로젝트와 파일 경로까지 얻어오는 메소드
   * http://localhost/ZESTINE/test.jsp 경우 => /ZSETINE/test.jsp
* String command = requestURI.substring(contextPath.length()); 
   * requestURI을 substring으로 contextPath.length() 만큼 해주면 프로젝트의 명만 짤라주고, 나머지 file의 이름 *.bo를 얻게된다.



#### 2.2 Action.java : 각 요청을 처리하는 Action 클래스들의 규격을 정의한 인터페이스 

> Action.java

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
 
#### 2.3 ActionForward.java



> ActionForward.java

```java

```

#### 3. writeForm.jsp 에 있는 num, ref, re_step, re_level은 Servlet에서 처리하게 만들어야 한다.

#### 3.1 BoardWriteFormAction.java


#### 3.2 boardStart.jsp

#### 


