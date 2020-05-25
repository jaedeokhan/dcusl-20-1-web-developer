package ex1;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBAction {
	
	// Singleton : 하나의 커넥션 객체를 사용하도록 하는 것 => 무분별한 객체 사용을 막는 것이다.
	// 하나의 conn만을 생성해서 사용을 하기 위해서 작성을 한다.
	// 지금의 목적은 connection 객체를 사용하는 것이다!! => 반복되는 객체를 클래스화! 밑에 생성자!
	private static DBAction instance = new DBAction();
	private Connection conn;
	private DataSource ds;
	
	// Constructor
	public DBAction() {
		
//		try {
//			String dbURL = "jdbc:mysql://localhost:3306/bbs";
//			String dbID = "root";
//			String dbPW = "root";
//			
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
//			System.out.println("Connection Success!!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			InitialContext initctx = new InitialContext();
			Context ctx = (Context)initctx.lookup("java:/comp/env");
			ds = (DataSource)ctx.lookup("jdbc/oracle");

		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	// Abstract class 는 new 해서 사용을 할 수 없다. 그래서 get instance를 사용한다.
	// getInstance를 통해서 객체 생성을 하는 것이 singleton의 특징이다.
	public static DBAction getInstance() {
		if (instance == null) {
			instance = new DBAction();
		}
		return instance;
	}
	
	public Connection getConnection() {
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;	
	}
}
