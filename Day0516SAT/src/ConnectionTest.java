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
