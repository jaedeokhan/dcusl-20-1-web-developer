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

#### 3.1.2 메모리에 데이터베이스 드라이버를 로딩하기. 
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

#### 3.1.3 Connection 객체를 생성한다.
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





