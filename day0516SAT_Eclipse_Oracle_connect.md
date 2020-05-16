### 2020 0516 SAT 

#### 1. Eclipse와 Oracle Database jdbc 연결하기.

#### 1.1 Eclipse 에서 

#### 1.1.1 DataBase Connect => new 하고, jar 11 선택하기.
![test1](https://user-images.githubusercontent.com/45028904/82106063-ce76d800-9759-11ea-95db-5b13cc656d70.png)

* 선택하고 나면 아래와 같이 나온다.
![image](https://user-images.githubusercontent.com/45028904/82106267-dedb8280-975a-11ea-9eb7-6a15ec25dd21.png)

#### 1.1.2 Properties 에서 Connection URL, Password, User ID 항목을 수정하기
* Coonection URL 에서 localhost는 자신의 컴퓨터 아이피인 127.0.0.1 을 나타낸다. XE는 Oracle Server이다.
* Password : 기존에 CREATE USER해준 java
* User ID : 마찬가지로 설정해준 java로 한다.
![image](https://user-images.githubusercontent.com/45028904/82106289-f581d980-975a-11ea-8943-4f17131fce1c.png)

#### 1.1.3 Next 하고나서 , Test Connection 테스트하기.
![image](https://user-images.githubusercontent.com/45028904/82106365-632e0580-975b-11ea-89d0-3521d9ccafe2.png)

* Test Connection 하기.
![image](https://user-images.githubusercontent.com/45028904/82106370-70e38b00-975b-11ea-863b-501a1bea3130.png)

* 위와 같이 Ping succeeded! 가 나와야 한다.

#### 2. Project 하나 생성하고, SQL file 하나 만들기.
#### 2.1 SQL file 다음과 같이 하나 생성
![image](https://user-images.githubusercontent.com/45028904/82107177-9fb03000-9760-11ea-985a-c1cb92142acb.png)

#### 2.2 테이블 하나 생성하기.
* id : VARCHAR2 로 id가 String이기에 12자리로 준다. 그리고 식별자(PRIMARY KEY)이다.
* name : VAHCHAR2 로 한글으로는 5자리 정도 준다 = 15byte. NOT NULL로 NULL 값을 주지 않느낟.
* addr : NOT NULL
* passwd : NOT NULL

> member.sql
```sql
-- 간단한 SQL 구문 실행 
-- 테이블 생성

CREATE TABLE member(
   id VARCHAR2(12) PRIMARY KEY,
   name VARCHAR2(15) NOT NULL,
   addr VARCHAR2(50) NOT NULL,
   passwd VARCHAR2(12) NOT NULL
);
```

#### 3. Java Program에서 Oracle Server로 명령어 날리기.

#### 3.1 설명.txt
```
자바에서 데이터베이스 작업의 순서.
1. 드라이버 파일을 클래스패스에 복사한다.
  - 드라이버 파일을 읽어야 사용할 수 있다. (ojdbc)

2. java.sql 패키지의 API들을 import한다.

3. 메모리에 데이터베이스 드라이버를 로딩한다.

4. Connection 객체 생성
  - Connection : 데이터베이스와 연결을 하는 역할

5. Statement 객체 생성
   - Statement : SQL 구문을 데이터베이스 전송하는 역할을 한다.

5-1. int executeUpdate(String sql){}
   - SELECT 구문을 제외한 INSERT, UPDATE, DELETE 등의 SQL 구문을 실행할 때 사용하는 메소드
   - SQL 구문을 실행한 후 적용된 행수를 반환해주는 메소드이다.

5-2. Select 구문은 executeUpdate말고 다른 메소드를 사용해줘야한다.
   - ResultSet executeQuery(Stirng sql){} : SELECT 구문을 실행하는 메소드이다.
   - ResultSet : SELECT 구문이 실행되고 나서 반환되는 레코드셋(레코드덩어리)를 참조하는 인터페이스   
   - 처음에 ResultSet이 참조하는 라니은 레코드셋의 top부분임, 즉, 실제 레코드가 있는 부분은 아니다. 속성을 가르킨다.
      - 그래서, 작업하는 라인을 value가 있는 라인으로 이동을 시켜야 한다.

5-2-1. ResultSet의 메소드
   - boolean next() : 현재 작업할 커서의 위치를 다음레코드(다음라인)으로 이동시킨다. 다음에 이동할 레코드가 있다면 true를 반환, 없다면 false

5-2-2. 해당 레코드에 특정 컬럼의 값을 조회하는 메소드
   - String getString(int columnNumber){} : 인자로 지정된 번호의 컬럼의 값을 반환 
      - String name = rs.getString(1); // 1은 첫 번째 칼럼인 id를 나타낸다.
   - String getString(String columnName){} : 인자로 지정된 이름의 컬럼 값을 반환 
      - String name = rs.getString("id");

```
#### 3.1.1 드라이버 파일을 클래스 패스에 복사한다.

* 1 : Project에서 Properties 들어가기 
* 2 : Add External JARs : 들어가기
* 3 : odbc6 선택 후 열고, Apply and Close 하기.
![image](https://user-images.githubusercontent.com/45028904/82107777-367eeb80-9765-11ea-9b30-d368fb94cf2c.png)

#### 3.1.2 ConnectionTest 클래스를 하나 만들어서 java.sql api들을 사용해서 연동하기.

> ConnectionTest.java

```java
import java.sql.*;
public class ConnectionTest {

	public static void main(String[] args) {
		
		

	}

}
```

#### 3.1.3 메모리에 데이터베이스 드라이버를 로딩하기. 
* static {}

> CoonectionTest.java

```java
import java.sql.*;
public class ConnectionTest {
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
	public static void main(String[] args) {
		
		

	}

}
```

#### 3.1.4 Connection 객체를 생성한다.
* Connection con;  : 인터페이스 변수 정의
* void connect(){} :
   * DriverManager.getConnection("연결정보", "계정", "비밀번호"); 사용해서 con 인터페이스 변수에게 넘겨주고, success 판단하기.

```java
import java.sql.*;
public class ConnectionTest {
	// Connection이라고 인터페이스 데이터 변수 정의
	Connection con;
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
	
	void connect() {
		// db 연결 작업을 해주는 것.
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
											  "java",
											  "java");
			System.out.println("connection success!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		ConnectionTest conn =  new ConnectionTest();
		
		conn.connect();

	}

}

```

#### 3.1.5 Statement 객체 생성
* Statement : SQL 구문을 데이터베이스 전송하는 역할을 한다.
   * int executeUpdate(String sql){] 
   
> ConnectionTest.java

```java
import java.sql.*;
public class ConnectionTest {
	// Connection이라고 인터페이스 데이터 변수 정의
	Connection con;
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
	
	void connect() {
		// db 연결 작업을 해주는 것.
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
											  "java",
											  "java");
			System.out.println("Connection Success!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void insert(String id, String name, String addr, String passwd) {
		// 데이터베이스에 데이터를 삽입하는 SQL 구문 (INSERT)
		// 1. INSERT INTO 테이블명  VALUES(데이터1, 데이터2, 데이터3, 데이터 4);
		String sql = "INSERT INTO member VALUES('" + id + "','" 
							   + name + "','"
							   + addr + "','" 
							   + passwd + "')";
		Statement stmt = null;
		
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			connect();
			stmt = con.createStatement();
			int insertCount = stmt.executeUpdate(sql);
			
			// insertCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
			if (insertCount > 0) {
				System.out.println("INSERT Success!!");
			}
			else {
				System.out.println("INSERT Fail!!");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
			// 작업한 리소르를 모두 해제해주기.
			try {
				con.close();
				stmt.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		ConnectionTest conn =  new ConnectionTest();
		
//		conn.connect();
		conn.insert("bbb", "한재덕", "대구시", "1234");
		
	}
}
```

#### 3.2 INSERT 2번째 방법


```java
import java.sql.*;
public class ConnectionTest {
	// Connection이라고 인터페이스 데이터 변수 정의
	Connection con;
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
	
	void connect() {
		// db 연결 작업을 해주는 것.
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
											  "java",
											  "java");
			System.out.println("Connection Success!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void insert(String id, String name, String addr, String passwd) {
		// 데이터베이스에 데이터를 삽입하는 SQL 구문 (INSERT)
		// 1. INSERT INTO 테이블명  VALUES(데이터1, 데이터2, 데이터3, 데이터 4);
		/*
		 * String sql = "INSERT INTO member VALUES('" + id + "','" + name + "','" + addr
		 * + "','" + passwd + "')";
		 */		
		
		// 2. INSERT INTO 테이블명 (데이터를 삽입할 컬럼명1, 2..지정해주기 가능) VALUES(데이터1, 데이터2...);
		// 테이블명 안에 있는 속성들과 값들의 개수가 서로 매핑이 되야한다.
		String sql = "INSERT INTO member(id, name, addr, passwd) VALUES('" + id + "','" 
							+ name + "','" + addr + "','" + passwd + "')";
		
		Statement stmt = null;
		
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			connect();
			stmt = con.createStatement();
			int insertCount = stmt.executeUpdate(sql);
			
			// insertCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
			if (insertCount > 0) {
				System.out.println("INSERT Success!!");
			}
			else {
				System.out.println("INSERT Fail!!");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
			// 작업한 리소르를 모두 해제해주기.
			try {
				con.close();
				stmt.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}		
}
	
	public static void main(String[] args) {
		
		ConnectionTest conn =  new ConnectionTest();
		
//		conn.connect();
		
//		conn.insert("bbb", "한재덕", "대구시", "1234");
		
//		conn.insert("ccc", "박정원", "경산시", "3333");
		

		
	}

}
```

#### 3.3 selectALL(){} 메소드 사용
SELECT는 다른 INSERT, UPDATE, DELETE 구문과는 다른 메소드를 사용한다.

> ConnectionTest.java => void selectAll(){} 메소드 추가.

```java
import java.sql.*;
public class ConnectionTest {
	// Connection이라고 인터페이스 데이터 변수 정의
	Connection con;
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
	
	void connect() {
		// db 연결 작업을 해주는 것.
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
											  "java",
											  "java");
			System.out.println("Connection Success!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void insert(String id, String name, String addr, String passwd) {
		// 데이터베이스에 데이터를 삽입하는 SQL 구문 (INSERT)
		// 1. INSERT INTO 테이블명  VALUES(데이터1, 데이터2, 데이터3, 데이터 4);
		/*
		 * String sql = "INSERT INTO member VALUES('" + id + "','" + name + "','" + addr
		 * + "','" + passwd + "')";
		 */		
		
		// 2. INSERT INTO 테이블명 (데이터를 삽입할 컬럼명1, 2..지정해주기 가능) VALUES(데이터1, 데이터2...);
		// 테이블명 안에 있는 속성들과 값들의 개수가 서로 매핑이 되야한다.
		String sql = "INSERT INTO member(id, name, addr, passwd) VALUES('" + id + "','" 
							+ name + "','" + addr + "','" + passwd + "')";
		
		Statement stmt = null;
		
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			connect();
			stmt = con.createStatement();
			int insertCount = stmt.executeUpdate(sql);
			
			// insertCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
			if (insertCount > 0) {
				System.out.println("INSERT Success!!");
			}
			else {
				System.out.println("INSERT Fail!!");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
			// 작업한 리소르를 모두 해제해주기.
			try {
				con.close();
				stmt.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	void selectAll() {
		// 출력하는 작업해보기.		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member";
		
		try {
			connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			// ResultSet에 저장된 데이터 얻기 - 결과가 2개 이상인 경우
			while (rs.next()) { // 다음 레코드가 있다면
				String id = rs.getString(1);
				String name = rs.getString(2);
				String addr = rs.getString("addr");
				String passwd = rs.getString("passwd");
				System.out.printf("%s, %s, %s, %s\n", id, name, addr, passwd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				// 사용한 자원 모두 잡아주기
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				// 예외를 모두 잡아주기
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		ConnectionTest conn =  new ConnectionTest();
		
//		conn.connect();
		
//		conn.insert("bbb", "한재덕", "대구시", "1234");
		
//		conn.insert("ccc", "박정원", "경산시", "3333");
		
		conn.selectAll();
		
	}

}
```

#### 3.4 SELECT를 name이 이씨인 사람만 출력하기.
* LIKE : 특정한 패턴을 비교할때 사용한다. 
   * % : %문자가 들어간 위치에 하나도 안와도 되고, 어떤 문자가 와도 상관이 없는 것이다.
   * '한%' 으로 하면 한으로 시작되서 한으로 끝나거나 한으로 쭉 문자가 나오거나이다.

여기에서 String sql = "" 쌍따옴표의 구문안에 홑따옴표 처리하기.
```java
// firstname 즉 성을 받아서 성이 한씨인 사람만 검색해주는 select를 만들기 위한 구문
String sql = "SELECT * FROM member WHERE name LIKE '" + firstname + "%'"; 

// 
String sql = "UPDATE member SET name='" + name + "'" 
                           + ", addr='" + addr + "'"
			   + ", passwd='" + passwd + "'"
			   + " WHERE id='" + id + "'";

String sql = "INSERT INTO member(id, name, addr, passwd) VALUES ('" + id "','" 
                                                       + name + "','" 
						       + addr + "','"
						       + passwd + "')";
```

```java
	void update(String id, String name, String addr, String passwd) {
		// update : 테이블의 데이터를 수정하는 구문
		// update : 수정할 테이블명
		// SET 수정할컬럼명 = 수정할값,...
		String sql = "UPDATE member SET name='" + name + "'"
					+ ", addr='" + addr + "'"
					+ ", passwd='" + passwd + "'" 
					+ " WHERE id = '" + id + "'";

		Statement stmt = null;

		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			connect();
			stmt = con.createStatement();
			int updateCount = stmt.executeUpdate(sql);
			
			// updateCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
			if (updateCount > 0) {
				System.out.println("UPDATE Success!!");
			}
			else {
				System.out.println("UPDATE Fail!!");
			}
		
		} catch (SQLException e) {
		// TODO: handle exception
		// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
		// 작업한 리소르를 모두 해제해주기.
			try {
				con.close();
				stmt.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
```


#### 3.5 update() 메소드 처리하기. 원하는 id를 찾아서 name, addr, passwd 업데이트하기.
* 여기서도 위와 마찬가지로 '' 홑따옴표로 감싸는거 천천히 해보기.

> ConnectionTest.java

```java
import java.sql.*;
public class ConnectionTest {
	// Connection이라고 인터페이스 데이터 변수 정의
	Connection con;
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
	
	void connect() {
		// db 연결 작업을 해주는 것.
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
											  "java",
											  "java");
			System.out.println("Connection Success!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void insert(String id, String name, String addr, String passwd) {
		// 데이터베이스에 데이터를 삽입하는 SQL 구문 (INSERT)
		// 1. INSERT INTO 테이블명  VALUES(데이터1, 데이터2, 데이터3, 데이터 4);
		/*
		 * String sql = "INSERT INTO member VALUES('" + id + "','" + name + "','" + addr
		 * + "','" + passwd + "')";
		 */		
		
		// 2. INSERT INTO 테이블명 (데이터를 삽입할 컬럼명1, 2..지정해주기 가능) VALUES(데이터1, 데이터2...);
		// 테이블명 안에 있는 속성들과 값들의 개수가 서로 매핑이 되야한다.
		String sql = "INSERT INTO member(id, name, addr, passwd) VALUES('" + id + "','" 
							+ name + "','" + addr + "','" + passwd + "')";
		
		Statement stmt = null;
		
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			connect();
			stmt = con.createStatement();
			int insertCount = stmt.executeUpdate(sql);
			
			// insertCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
			if (insertCount > 0) {
				System.out.println("INSERT Success!!");
			}
			else {
				System.out.println("INSERT Fail!!");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
			// 작업한 리소르를 모두 해제해주기.
			try {
				con.close();
				stmt.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	void selectAll() {
		// 출력하는 작업해보기.		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member";
		
		try {
			connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			// ResultSet에 저장된 데이터 얻기 - 결과가 2개 이상인 경우
			while (rs.next()) { // 다음 레코드가 있다면
				String id = rs.getString(1);
				String name = rs.getString(2);
				String addr = rs.getString("addr");
				String passwd = rs.getString("passwd");
				System.out.printf("id=%s, name=%s, addr=%s, passwd=%s\n", id, name, addr, passwd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				// 사용한 자원 모두 잡아주기
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				// 예외를 모두 잡아주기
				e.printStackTrace();
			}
		}
		
	}
	
	void selectByFristName(String firstName) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE name LIKE '" + firstName + "%'";
		
		try {
			connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			// ResultSet에 저장된 데이터 얻기 - 결과가 2개 이상인 경우
			while (rs.next()) { // 다음 레코드가 있다면
				String id = rs.getString(1);
				String name = rs.getString(2);
				String addr = rs.getString("addr");
				String passwd = rs.getString("passwd");
				System.out.printf("id=%s, name=%s, addr=%s, passwd=%s\n", id, name, addr, passwd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				// 사용한 자원 모두 잡아주기
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				// 예외를 모두 잡아주기
				e.printStackTrace();
			}
		}
	}
	
	void update(String id, String name, String addr, String passwd) {
		// update : 테이블의 데이터를 수정하는 구문
		// update : 수정할 테이블명
		// SET 수정할컬럼명 = 수정할값,...
		String sql = "UPDATE member SET name='" + name + "'"
					+ ", addr='" + addr + "'"
					+ ", passwd='" + passwd + "'" 
					+ " WHERE id = '" + id + "'";

		Statement stmt = null;

		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			connect();
			stmt = con.createStatement();
			int insertCount = stmt.executeUpdate(sql);
			
			// insertCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
			if (insertCount > 0) {
				System.out.println("UPDATE Success!!");
			}
			else {
				System.out.println("UPDATE Fail!!");
			}
		
		} catch (SQLException e) {
		// TODO: handle exception
		// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
		// 작업한 리소르를 모두 해제해주기.
			try {
				con.close();
				stmt.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		ConnectionTest conn =  new ConnectionTest();
		
//		conn.connect();
		
		// 1. 데이터 하나를 insert하기.
//		conn.insert("bbb", "한재덕", "대구시", "1234");
		
		// 2. 두 번째 데이터 insert하기.
//		conn.insert("ccc", "박정원", "경산시", "3333");
		
		// 3. 모든 데이터 검색하기.
//		conn.selectAll();
//		
		// 4. 성이 한씨인 name을 LIKE '한%' 키워드를 사용해서 검색하기.
//		System.out.println("성이 한씨인 회원 검색 ");
//		conn.selectByFristName("한");
		
		// 5. 다음과 같은 4개의 내용으로 수정해서 출력하기.
//		System.out.println("수정 후 ");
//		conn.update("aaa", "김정원", "부산", "0000");
		
		conn.selectAll();
	}

}
```

#### 3.6 DELETE 구문 설정하기.
CRUD에서 마지막인 Delete 구문 설정하기.

> ConnectionTest.java

```java
import java.sql.*;
public class ConnectionTest {
	// Connection이라고 인터페이스 데이터 변수 정의
	Connection con;
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
	
	void connect() {
		// db 연결 작업을 해주는 것.
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
											  "java",
											  "java");
			System.out.println("Connection Success!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void insert(String id, String name, String addr, String passwd) {
		// 데이터베이스에 데이터를 삽입하는 SQL 구문 (INSERT)
		// 1. INSERT INTO 테이블명  VALUES(데이터1, 데이터2, 데이터3, 데이터 4);
		/*
		 * String sql = "INSERT INTO member VALUES('" + id + "','" + name + "','" + addr
		 * + "','" + passwd + "')";
		 */		
		
		// 2. INSERT INTO 테이블명 (데이터를 삽입할 컬럼명1, 2..지정해주기 가능) VALUES(데이터1, 데이터2...);
		// 테이블명 안에 있는 속성들과 값들의 개수가 서로 매핑이 되야한다.
		String sql = "INSERT INTO member(id, name, addr, passwd) VALUES('" + id + "','" 
							+ name + "','" + addr + "','" + passwd + "')";
		
		Statement stmt = null;
		
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			connect();
			stmt = con.createStatement();
			int insertCount = stmt.executeUpdate(sql);
			
			// insertCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
			if (insertCount > 0) {
				System.out.println("INSERT Success!!");
			}
			else {
				System.out.println("INSERT Fail!!");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
			// 작업한 리소르를 모두 해제해주기.
			try {
				con.close();
				stmt.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	void selectAll() {
		// 출력하는 작업해보기.		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member";
		
		try {
			connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			// ResultSet에 저장된 데이터 얻기 - 결과가 2개 이상인 경우
			while (rs.next()) { // 다음 레코드가 있다면
				String id = rs.getString(1);
				String name = rs.getString(2);
				String addr = rs.getString("addr");
				String passwd = rs.getString("passwd");
				System.out.printf("id=%s, name=%s, addr=%s, passwd=%s\n", id, name, addr, passwd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				// 사용한 자원 모두 잡아주기
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				// 예외를 모두 잡아주기
				e.printStackTrace();
			}
		}
		
	}
	
	void selectByFristName(String firstName) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE name LIKE '" + firstName + "%'";
		
		try {
			connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			// ResultSet에 저장된 데이터 얻기 - 결과가 2개 이상인 경우
			while (rs.next()) { // 다음 레코드가 있다면
				String id = rs.getString(1);
				String name = rs.getString(2);
				String addr = rs.getString("addr");
				String passwd = rs.getString("passwd");
				System.out.printf("id=%s, name=%s, addr=%s, passwd=%s\n", id, name, addr, passwd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				// 사용한 자원 모두 잡아주기
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				// 예외를 모두 잡아주기
				e.printStackTrace();
			}
		}
	}
	
	void update(String id, String name, String addr, String passwd) {
		// update : 테이블의 데이터를 수정하는 구문
		// update : 수정할 테이블명
		// SET 수정할컬럼명 = 수정할값,...
		String sql = "UPDATE member SET name='" + name + "'"
					+ ", addr='" + addr + "'"
					+ ", passwd='" + passwd + "'" 
					+ " WHERE id = '" + id + "'";

		Statement stmt = null;

		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			connect();
			stmt = con.createStatement();
			int updateCount = stmt.executeUpdate(sql);
			
			// updateCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
			if (updateCount > 0) {
				System.out.println("UPDATE Success!!");
			}
			else {
				System.out.println("UPDATE Fail!!");
			}
		
		} catch (SQLException e) {
		// TODO: handle exception
		// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
		// 작업한 리소르를 모두 해제해주기.
			try {
				con.close();
				stmt.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	void delete(String id) {
		// DELETE : 테이블에서 특정 레코드를 삭제하는 역할
		// DELETE 삭제할테이블명
		// WHERE 조건=조건내용
		String sql = "DELETE member " 
				   + "WHERE id= '" + id + "'";

		Statement stmt = null;
	
		// 데이터베이스 작업을 할때는 항상 예외처리를 해야한다.
		try {
			connect();
			stmt = con.createStatement();
			int deleteCount = stmt.executeUpdate(sql);
			
			// deleteCount 가 0을 넘는다면 데이터가 하나라도 삽입이 된 것이다.
			if (deleteCount > 0) {
				System.out.println("DELETE Success!!");
			}
			else {
				System.out.println("DELETE Fail!!");
			}
		
		} catch (SQLException e) {
		// TODO: handle exception
		// 발생한 예외 정보를 출력해주는 메소드이다.
			e.printStackTrace();
		}
		finally {
		// 작업한 리소르를 모두 해제해주기.
			try {
				con.close();
				stmt.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public static void main(String[] args) {
		
		ConnectionTest conn =  new ConnectionTest();
		
//		conn.connect();
		
		// 1. 데이터 하나를 insert하기.
//		conn.insert("bbb", "한재덕", "대구시", "1234");
		
		// 2. 두 번째 데이터 insert하기.
//		conn.insert("ccc", "박정원", "경산시", "3333");
		
		// 3. 모든 데이터 검색하기.
//		conn.selectAll();
//		
		// 4. 성이 한씨인 name을 LIKE '한%' 키워드를 사용해서 검색하기.
//		System.out.println("성이 한씨인 회원 검색 ");
//		conn.selectByFristName("한");
		
		// 5. 다음과 같은 4개의 내용으로 수정해서 출력하기.
//		System.out.println("수정 후 ");
//		conn.update("aaa", "김정원", "부산", "0000");
		
		// 6. Delete 구문 실행하기.
		conn.selectAll();
		System.out.println("아이디가 aaa인 데이터를 삭제 후");
		conn.delete("aaa");
		conn.selectAll();
	}

}

```

