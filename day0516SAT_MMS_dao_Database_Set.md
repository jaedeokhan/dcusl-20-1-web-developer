### 2020 0516 SAT 

#### 1. MMS를 데이터베이스 패키지인 dao를 생성하여 연동하기.

#### 1.1 jdbc 연동하기
데이터베이스 사용을 원하는 project에서 우측 클릭 후 Properties를 누르고 나서 다음과 같이 시행 후 Apply 실행하기.

![image](https://user-images.githubusercontent.com/45028904/82111637-8cfa2300-9781-11ea-9e5d-3577b06798ea.png)

#### 1.2 db 처리하는 부분은 별도로 설계한다. 코드의 중복을 막기 위해서!!
MMS 프로젝트에서 packge는 db, Class는 JdbcUtil을 생성한다.
* static {}
* Connection getConnection() {}
* close() {}  

> JdbcUtil.java

```java
package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		// db 연결 작업을 해주는 것.
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
											  "java",
											  "java");
			System.out.println("Connection Success!!");
		} catch (SQLException e) {
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
}
```

#### 1.2.1 MemberVO에서 사용한 데이터들의 테이블을 생성해주기.
기존에 member테이블을 만들어줘서 충돌을 방지하기 위해서 memeber1 테이블을 만든다.
gender  같은 경우에는 'M', 'F'로 받기 때문에 CHAR이라는 고정 값으로 준다.

```sql
CREATE TABLE member1 (
	id VARCHAR2(12) PRIMARY KEY,
	passwd VARCHAR2(12) NOT NULL,
	name VARCHAR2(15) NOT NULL,
	email VARCHAR2(30) NOT NULL,
	addr VARCHAR2(100) NOT NULL,
	age NUMBER NOT NULL,
	gender CHAR(1) NOT NULL,
	tell VARCHAR2(20) NOT NULL
);
```

#### 1.3 MemberRegistAction 처리를 해주기.
MemberRegistService.java에 ArrayList 사용하는 부분을 DB로 변경을 해준다.
트랜잭션 처리 때문에 Connection 객체는 Service에서 다룬다.
계좌이체를 한다고 하면 내가 효재한다. 입금을 할려면 나의 계좌에서 출금을 하고, 효재의 계좌에 입금이 되야한다. 여러 개의 작업을 하나로 묶어서 하나라도 실패하면 실패 처리를 한다. 
* dao : 데이터베이스 sql이 들어있다, 그 작업들을 service에서 여러 개를 호출을 할 것이다. 그래서 트랜잭션 처리는 비즈니스 단위인 서비스단에서 처리를 하는 것이다.
* Java에서 하나의 트랜잭션으로 처리되어야 하는 작업들은 동일한 Connection으로 다루어야 한다.
* Connection 객체의 생성과 소멸 작업은 Service 클래스에서 한다.

> MemberRegistAction.java 

```java

```

#### 1.3.2 MemberDAO => select 추가
MemberDAO에서는 SELECT와 같은 명령어 이름을 줘야하낟.

```java
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static db.JdbcUtil.*;

public class MemberDAO {
	// Java DAO는 싱글톤 패턴을 사용한다.
	// singleton 패턴
	// 해당 클래스의 기능을 다른 클래스에서 사용할 때 첫번째로만 사용할 때만 객체를 생성하고, 그 다음 부터는
	// 이미 생성된 객체를 공유해서 사용하게 하는 패턴
	// 왜? 이렇게 하는가? => 메모리 사용을 절약하기 위해서 하는 것이다.
	// 주로 메소드 위주로 정의된 클래스에서 사용한다. 기능만 다른 클래스에서 호출해서 사용하면 되는데 굳이 객체 생성할 필요가 없다!
	Connection con;
	
	private static MemberDAO memberDAO;
	
	
	private MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			// getInstance() 메소드가 처음으로 호출됬으면!
			// 처음에 사용할때만 객체를 하나 올리고, 한 개 이후부터는 만들지 않는다.
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public String selectExistId(String id) {
		String dbId = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM member1 WHERE id='" + id + "'";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			if (rs.next()) {
				// select 구문에 의해서 반환된 레코드가 하나라도 있으면
				dbId = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		return dbId;
	}
}
```
#### 1.3.3 MemberDAO => insertMember 추가
insertMember를 DAO에 추가하기.
여기서도 comma error가 떴는데 INSERT 구문에서 error가 발생한 것이다.
newMemberVO.getAge() 는 '' 요 놈 처리를 제거해줘야 한다. 숫자는 String이 아니여서 해줄 필요가 없다.

> MemberDAO.java 

```java
	public int insertMember(MemberVO newMemberVO) {
		int insertCount = 0;
		
		String sql = "INSERT INTO member1(id, passwd, name, email, addr, age, gender, tell) "
				+ "VALUES('" + newMemberVO.getId() 
				+ "','" + newMemberVO.getPasswd() 
				+ "','" + newMemberVO.getName() 
				+ "','" + newMemberVO.getEmail()
				+ "','" + newMemberVO.getAddr() 
				+ "'," + newMemberVO.getAge()
				+ ",'" + newMemberVO.getGender() 
				+ "','" + newMemberVO.getTell() + "')";

		Statement stmt = null;
		
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			stmt = con.createStatement();
			insertCount = stmt.executeUpdate(sql);
			// insertCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
		} catch (SQLException e) {
			// TODO: handle exception
			// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
		// 작업한 리소르를 모두 해제해주기.
			close(stmt);
		}
		return insertCount;
	}
```

#### 1.3.4 MemberRegistAction.java
Connection은 Service에서 관리를 한다. 나중에 트랜잭션을 처리하기 위해서는 dao에서 넘어오는것이 아니라,  Service에서 connection을 끊어주는 것을 설정한다.

memberDAO.selectExistId(newMemberVo.get()); 
memberDAO.insertMember(newMemberVO); 

> MemberRegistAction.java

```java
// 회원등록 요청을 처리하는 비지니스 로직이 구현되는 클래스!! => 사용자의 입력이 아니라!
// 트랜잭션 처리 때문에 Connection 객체는 Service 에서 다룬다.

package svc;

import ui.MemberUI;
import vo.MemberVO;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberRegistService {

	public boolean registMember(MemberVO newMemberVO) {
		
		// false를 반환을 하는 경우는 회원 가입을 할려고 하는데 식별자 변수인 id가 이미 존재한다면 false를 반환시킨다.
		/*
		 * boolean registSuccess = true;
		 * 
		 * // MemberUI에 있는 memberList의 요소들을 체크해야한다. // memberList를 static으로 줘서 바로 접근이
		 * 가능하다. for (int i = 0; i < MemberUI.memberList.size(); i++) { // 1,2 ~ 인덱스에서
		 * 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go // 새로 등록하는 id와 기존에 있는
		 * id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
		 * if(newMemberVO.getId().equals(MemberUI.memberList.get(i).getId())) {
		 * registSuccess = false; break; } }
		 * 
		 * if (registSuccess) { // registSuccess MemberUI.memberList.add(newMemberVO); }
		 * return registSuccess;
		 */
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		boolean registSuccess = true;
		// 새롭게 가입할 id를 인자로 던진다.
		String id = memberDAO.selectExistId(newMemberVO.getId());
		
		if (id != null) {			// id가 null이 아니면 해당 id를 누가 쓰고 있다면?
			registSuccess = false;
		}
		else {
			int insertCount = memberDAO.insertMember(newMemberVO);
			if (insertCount > 0) {
				System.out.println("INSERT Success!");
			}
			else { 
				System.out.println("INSERT Fail!");
			}
		}
		// 서비스에서 만들고 서비스에서 conn를 해제한다.
		close(con);
		return registSuccess;
		
		
	}
	
	
}
```

#### 1.4 MemberListService() DB 연동하기. 

> MemberListService.java

```java
package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import ui.MemberUI;
import vo.MemberVO;
import static db.JdbcUtil.*;

public class MemberListService {

	/*
	 * public ArrayList<MemberVO> getMemberList() {
	 * 
	 * return MemberUI.memberList;
	 * 
	 * }
	 */
	
	public ArrayList<MemberVO> getMemberList() {
	
	Connection con = getConnection();
	MemberDAO memberDAO = MemberDAO.getInstance();
	memberDAO.setConnection(con);
	
	ArrayList<MemberVO> memberList = memberDAO.selectMemberList();
	
	
	return memberList;
	}
	
}
```

#### 1.4.2 MemberDAO에 selectExistId(String id) 사용하기!

> MemberDAO.java

```java
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.MemberVO;

import static db.JdbcUtil.*;

public class MemberDAO {
	// Java DAO는 싱글톤 패턴을 사용한다.
	// singleton 패턴
	// 해당 클래스의 기능을 다른 클래스에서 사용할 때 첫번째로만 사용할 때만 객체를 생성하고, 그 다음 부터는
	// 이미 생성된 객체를 공유해서 사용하게 하는 패턴
	// 왜? 이렇게 하는가? => 메모리 사용을 절약하기 위해서 하는 것이다.
	// 주로 메소드 위주로 정의된 클래스에서 사용한다. 기능만 다른 클래스에서 호출해서 사용하면 되는데 굳이 객체 생성할 필요가 없다!
	Connection con;
	
	private static MemberDAO memberDAO;
	
	
	private MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			// getInstance() 메소드가 처음으로 호출됬으면!
			// 처음에 사용할때만 객체를 하나 올리고, 한 개 이후부터는 만들지 않는다.
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public String selectExistId(String id) {
		String dbId = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM member1 WHERE id='" + id + "'";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			if (rs.next()) {
				// select 구문에 의해서 반환된 레코드가 하나라도 있으면
				dbId = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		return dbId;
	}
}
```

#### 1.5 MemberViewService() DB 연동하기.

> MemberViewService.java

```java
package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import ui.MemberUI;
import vo.MemberVO;

public class MemberViewService {

	public MemberVO getMemberVO(String memberViewId) {
		
		/*
		 * MemberVO memberVO = null;
		 * 
		 * for (int i = 0; i < MemberUI.memberList.size(); i++) { // 1,2 ~ 인덱스에서 같은 아이디를
		 * 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go // 새로 등록하는 id와 기존에 있는 id를
		 * 비교해서 만약, 동일한게 존재한다 if문으로 처리.
		 * if(memberViewId.equals(MemberUI.memberList.get(i).getId())) { memberVO =
		 * MemberUI.memberList.get(i); break; } }
		 * 
		 * return memberVO;
		 */
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		MemberVO memberVO = memberDAO.selectMemberVO(memberViewId);
		close(con);
		
		return memberVO;
		
	}
	
	
}
```

#### 1.5.2 MemberDAO에 


#### 1.6 MemberModifyService() DB 연동하기.

#### 1.7 MemberSearchService() DB 연동하기.



