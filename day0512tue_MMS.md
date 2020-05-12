### 2020 0512 TUE 
### MMS 실습 -> 회원관리
1. vo/MemberVO.class
2. ui/MemberUI.class
3. controller/Controller.class
4. action/Action.interface
   * MemberRegistAction.class : action interface를 implements해서 action 참조 변수로 접근을 사용가능.
5. svc
6. dao : 데이터베이스 관련

#### 1. 정보 시스템을 만들려면 데이터 설계부터 먼저 들어가야한다.
우리가 다룰 회원정보를 먼저 설계해야한다.

#### 1.1 데이터를 저장하는 class => VO(ValueObject) 즉 값을 담는 곳!!
VO(Value Object) : 자바에서 특정 값을 저장하는 역할을 하는 클래스
회원 한 명의 정보를 저장하는 클래스

> vo/MemberVO.java
```java
package vo;
// VO(Value Object) : 자바에서 특정 값을 저장하는 역할을 하는 클래스
// 회원 한 명의 정보를 저장하는 클래스
public class MemverVO {
	// 속성을 지금 private으로 모두 캡슐화를 시켰다.
	// 외부에서 메소드를 통해서 이 속성들을 다루게 해줘야 한다. 즉 Getter, Setter가 필요하다.
	// 중요한 것! 식별자 변수가 필요하다. ==> id
	private String id;
	private String name;
	private String email;
	private String addr;
	private int age;
	private String gender;
	private String tell;
	
	// Alt + Shift + s = >Getter, Setter 생성
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}	
}
```

#### 1.2 UserInterface를 만들어야 한다!!
웹 페이지, 앱 화면등을 나타낸다.

> ui/MemberUI.java 

```java
package ui;

import java.util.Scanner;

import action.Action;
import action.MemberRegistAction;
import controller.MemberController;

public class MemvberUI {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Controller 생성
		// Controller : 요청을 받는 클래스 MVC 패턴의 C 부분
		// 앞에 커서를 나두고 => ctrl + 1
		MemberController memberController = new MemberController();
		
		// 다형성을 이용해서 요청 처리를 하기위한 인터페이스 변수 선언
		// 인터페이스를 구현한 클래스 객체들은 인터페이스 레퍼런스 변수로 참조가 가능..
		Action action = null;
		
		boolean stop = false;
		int menuNumber = 0;
		
		do {
			// 사용자가 종료를 하기 전까지 계속해서 메뉴를 보여주기!
			System.out.println("====회원관리====");
			System.out.println("1.회원등록");
			System.out.println("2.회원목록보기");
			System.out.println("3.회원정보보기");
			System.out.println("4.회원정보수정");
			System.out.println("5.회원정보삭제");
			System.out.println("6.회원정보검색");
			System.out.println("7.프로그램종료");
			
			System.out.print("메뉴번호 : ");
			menuNumber = scan.nextInt();
			
			switch (menuNumber) {
			case 1:
				// action 인터페이스에  레퍼랜스 변수로 MemberRegistAction을 사용!
				// 각각 요청을 처리하는 메뉴에 따라서 하니씩 만들어준다.
				action = new MemberRegistAction(); //패키지명.기능이름.패턴이름
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			default:
				break;
			}
			
			if (action != null) {
				// 사용자가 메뉴를 정확하게 (1-7)번 선택을 했으면!
				// Controller!, 요청 처리를 넘겨야 하고, Scanner로 입력도 받아야하니 scan도 넘겨준다.
				MemberController.processRequest(action, scan);
			}
			
		} while (!stop);
		
	}
}
```

#### 1.3 MemberController

```java
package controller;

import java.util.Scanner;

import action.Action;

public class MemberController {

	public static void processRequest(Action action, Scanner scan){
		// TODO Auto-generated method stub
		try {
			// 넘어오는 예외를 여기서 모두 처리한다!!
			action.execute(scan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
```

#### 1.4 Action
각 요청을 처리하는 Action 클래스들의 규격을 정의하는 인터페이스
OA
> action/Action.interface

```java
package action;

import java.util.Scanner;

// 각 요청을 처리하는 Action 클래스들의 규격을 정의하는 인터페이스

public interface Action{
	// 직접 요청을 처리하는 메소드!
	public void execute(Scanner scan) throws Exception ;
}
```

#### 1.5 MemberRegistAction.java

```java
package action;

import java.util.Scanner;

public class MemberRegistAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		// 예외를 throws로 상위로 하나 던져주고! => MemberController
	}

}OA
```


