### 2020 0513 WED 
#### 0512 tue 에 진행한 MMS를 계속 진행
1. MMS
   * vo/MemberVO.java : ValueObject로 속성, Getter, Setter 정의
   * ui/MemberUI.java : 사용자에게 UserInterface 제공
   * action/Action.interface
      * MemberRegistAction.java : 사용자 회원 등록 
         * util/ConsoleUtil.java : 사용자 회원 등록 요구 
	    * getNewsMemberVO(Scanner scan){}
	    * printRegistSuccessMessage(newMemberVO.getId()){}
	 * svc/MemberRegistService.java 
   * controller/MemberController.java
      * processRequest(Action action, Scanner scan){} : 모든 사용자의 입력 예외를 처리한다.



#### 1.1 VO(Visual Object)
priavte 속성 셋팅, Getter, Setter, toString() 재정의해서 사용!

> memberVO.java

```java
package vo;
// VO(Value Object) : 자바에서 특정 값을 저장하는 역할을 하는 클래스
// 회원 한 명의 정보를 저장하는 클래스
public class MemberVO {

	// 속성을 지금 private으로 모두 캡슐화를 시켰다.
	// 외부에서 메소드를 통해서 이 속성들을 다루게 해줘야 한다. 즉 Getter, Setter가 필요하다.
	// 중요한 것! 식별자 변수가 필요하다. ==> id
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String addr;
	private int age;
	private String gender;
	private String tell;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	// 회원 정보를 어떻게 보여줄지 나열하기.
	@Override
	public String toString() {
		return "아이디 : " + id +
			   ", 이름 : " + name +
			   ", 주소 : " + addr +
			   ", 이메일 : " + email +
			   ", 비밀번호 : " + passwd +
			   ", 나이 : " + age +
			   ", 전화번호 : " + tell;
	}

}

```

#### 1.2 UI(User Interface)
UI에서는 action 인터페이스를 구현하는 각각의 클래스들을 action 참조 변수에 대입해서 각각의 클래스를 참조해서 사용

> memberUI.java

```java
package ui;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import action.MemberListAction;
import action.MemberModifyAction;
import action.MemberRegistAction;
import action.MemberViewAction;
import controller.MemberController;
import vo.MemberVO;

public class MemberUI {
	
	// public static으로  ArrayList
	public static ArrayList<MemberVO> memberList = new ArrayList<MemberVO>(); 
	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		
		scan.useDelimiter(System.getProperty("line.separator"));
		
		
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
				action = new MemberListAction();
				break;
			case 3:
				action = new MemberViewAction();
				break;
			case 4:
				action = new MemberModifyAction();
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

#### 1.3 Action
각 요청을 처리하는 Action 클래스들의 규격을 정의하는 인터페이스이다.

> Action.interface

```java
package action;

import java.util.Scanner;

// 각 요청을 처리하는 Action 클래스들의 규격을 정의하는 인터페이스

public interface Action{
	// 직접 요청을 처리하는 메소드!
	public void execute(Scanner scan) throws Exception ;
}

```

#### 1.4 Controller
사용자가 입력하는 모든 입력 에러를 여기서 받도록 한다.

> MemberController.java

```java
package controller;

import java.util.Scanner;

import action.Action;

public class MemberController {
	// 사용자가 입력하는 모든 에러를 여기서 받는다.
	public static void processRequest(Action action, Scanner scan){
		// TODO Auto-generated method stub
		// 실질적으로 구현한 execute 메소드가 넘어온다.
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

#### 1.5 util
콘솔상에서 사용자로부터 데이터를 입력받거나 요청처리 결과를 사용자에게 출력해주는 역할을 하는 클래스

* getNewMemberVO(Scanner scan) :  새롭게 가입하는 회원 정보를 return 해주는 메소드
* MemberVO newMemberVO = consoleutil.getNewMemberVO(scan); 
위와 같이 새로운 사용자의 정보를 입력 받은것을 newMemberVO에게 넘겨준다.
* public void printRegistSuccessMessage(String id) {} : MemberVO에서 toString()을 재정의해서 출력하게 해놓음.
   * MemberRegistAction에서 consoleutil.printRegistSuccessMessage(newMemberVO.getId()); 를 newMemberVO에.getId() 메소드를 이용해서id를 사용해서 넘긴다.
* public void printRegistFailMessage(String id){}

> ConsoleUtil.java

```java
package util;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;

import vo.MemberVO;

public class ConsoleUtil {

	public MemberVO getNewMemberVO(Scanner scan) {
		
		System.out.println("====회원등록====");
		System.out.print("회원 아이디 : ");
		String id = scan.next();
		
		System.out.print("회원 비밀번호 : ");
		String passwd = scan.next();

		System.out.print("회원 이름 : ");
		String name = scan.next();
		
		System.out.print("회원 이메일 : ");
		String email = scan.next();
		
		System.out.print("회원 주소 : ");
		String addr = scan.next();

		System.out.print("회원 성별 : ");
		String gender = scan.next();

		System.out.print("회원 전화번호 : ");
		String tell = scan.next();
		
		System.out.print("회원 나이 : ");
		int age = scan.nextInt();
		
		// 위의 속성들을 하나의 객체로 묶기.
		MemberVO newMemberVO = new MemberVO();
		newMemberVO.setAddr(addr);
		newMemberVO.setAge(age);
		newMemberVO.setEmail(email);
		newMemberVO.setGender(gender);
		newMemberVO.setId(id);
		newMemberVO.setName(name);
		newMemberVO.setPasswd(passwd);
		newMemberVO.setTell(tell);
		
		// 모든 속성을 set 해주고 return 으로 넘겨준다.
		return newMemberVO;
	}

	public void printRegistSuccessMessage(String id) {
		// TODO Auto-generated method stub
		System.out.println(id + " 님 회원 등록이 성공되었습니다.");
		
	}
	public void printRegistFailMessage(String id) {
		// TODO Auto-generated method stub
		System.out.println(id + " 님 회원 등록이 실패되었습니다.");
		
	}
}
```



#### 1.6 svc
 회원등록 요청을 처리하는 비지니스 로직이 구현되는 클래스!! => 사용자의 입력이 아니라!

> MemberRegistService.java

```java
// 회원등록 요청을 처리하는 비지니스 로직이 구현되는 클래스!! => 사용자의 입력이 아니라!
package svc;

import ui.MemberUI;
import vo.MemberVO;

public class MemberRegistService {

	public boolean registMember(MemberVO newMemberVO) {
		
		// false를 반환을 하는 경우는 회원 가입을 할려고 하는데 식별자 변수인 id가 이미 존재한다면 false를 반환시킨다.
		boolean registSuccess = true;
		
		// MemberUI에 있는 memberList의 요소들을 체크해야한다.
		// memberList를 static으로 줘서 바로 접근이 가능하다.
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(newMemberVO.getId().equals(MemberUI.memberList.get(i).getId())) {
				registSuccess = false;
				break;
			}
		}
		
		if (registSuccess) {
			// registSuccess
			MemberUI.memberList.add(newMemberVO);	
		}
		return registSuccess;
	}
	
	
}

```

#### 1.7 MemberResitAction
* action = new MemberRegistAction(); UI 클래스에서 다음과 같이 action 참조 변수로 MemberRegistAction에 접근을 가능하게 한다.
* action.interface에 있는 execute를 각각의 Action에서 재정의해서 사용한다.

> MemberRegistAction.java

```java
// 회원등록 요청을 처리하는 클래스
package action;

import java.util.Scanner;

import svc.MemberRegistService;
import util.ConsoleUtil;
import vo.MemberVO;

public class MemberRegistAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		// 예외를 throws로 상위로 하나 던져주고! => MemberController
		// 별도의 클래스들을 만들어서 작업을 하겠다. => 콘솔 작업하는 부분!!
		ConsoleUtil consoleutil = new ConsoleUtil();
		
		// 사용자의 데이터 입력 처리 => 비지니스 로직을 처리하기 전에 전처리 작업을 한 것.
		// getNewMemberVO에서 새로운 사용자의 정보를 입력 받아서 newMemberVO에 return을 해준다.
		MemberVO newMemberVO = consoleutil.getNewMemberVO(scan);
		
		// 비지니스로직 호출 => 서비스 클래스에 정의가 되야한다.
		MemberRegistService memberRegistService = new MemberRegistService(); 
		
		// 사용자가 등록 요청을 하고 나면 항상 응답을 해줘야 한다.
		// 예) 회원 가입을 요청을 하면, 결과를 리턴 해줘야 한다.
		// MemberVO 객체를 담을 수 있는 공간이 필요하다. 회원 가입과 회원 탈퇴를 회원별로 받아줘야 하기 때문이다.
		// 즉 데이터베이스에 그러한 공간을 만들어 줘야한다.
		// 지금은 메모리상에 나두고 관리를 한다.
		// collection으로  만들고 관리한다.
		boolean registSuccess = memberRegistService.registMember(newMemberVO);
		
		if (registSuccess) {
			// 등록 성공
			consoleutil.printRegistSuccessMessage(newMemberVO.getId());
		}
		else {
			// 등록 실패
			consoleutil.printRegistFailMessage(newMemberVO.getId());			
		}
		
	}
	

}

```

#### 2. MemberListAction()
* action = new MemberListAction(); 으로 action의 execute를 오버라이딩해서 참조한다.
* MemberListService memberListService = new MemberListService();  : 현재 저장하고 있는 memberlist를Service를 통해서 가져온다.
* ArrayList<MemberVO> memberList = memberListService.getMemberList(); : ArrayList형의 Collection으로 Service의 .getMemberList() 메소드를 만들어서 담는다.
* consoleUtil.printMemberList(memberList); : consoleUtil 참조 변수를 생성해서 ConsoleUtil에서 printMemberList(memberList); 메소드를 사용해 memberList를 받아서 출력한다.

> MemberListAction.java

```java
package action;

import java.util.ArrayList;
import java.util.Scanner;

import svc.MemberListService;
import util.ConsoleUtil;
import vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		// 현재까지 저장하고 있는 memeberList를 가져와야한다.
		MemberListService memberListService = new MemberListService();
		
		// getMemerList 메소드에게 memberList가 ArrayList를 받고
		ArrayList<MemberVO> memberList = memberListService.getMemberList();
		
		ConsoleUtil consoleUtil = new ConsoleUtil();
		consoleUtil.printMemberList(memberList);
		
		
	}

}

```

#### 2.1 MemberListService
되게 간단하다. MemberUI.memberList; 를 return 해주면 된다.
사용하려면 일단, MemberUI에 memberList 참조 변수로 ArrrayList collection을 정적으로 생성해야 한다.

> MemberListService.java

```java
package svc;

import java.util.ArrayList;

import ui.MemberUI;
import vo.MemberVO;

public class MemberListService {

	public ArrayList<MemberVO> getMemberList() {
		
		return MemberUI.memberList;
		
	}
	
	
}

```

#### 2.2 ConsoleUtil
* printMemberList(ArrayList<MemberVO> memberlist){} 메소드를 사용해서 회원 등록한 회원이 0 이 아니면 모두 출력해준다.

> ConsoleUtil.java

```java
	public void printMemberList(ArrayList<MemberVO> memberList) {
		
		// 회원등록한 회원이 없으면, 
		if (memberList.size() == 0) {
			System.out.println("등록된 회원이 없습니다.");
		}
		else {
			// 사이즈가 0이 아니면 회원이 한 명이라도 등록이 되어 있는 것이다.
			for (int i = 0; i < memberList.size(); i++) {
				// toStirng 객체가 호출이 되면서 실행이 된다.
				System.out.println(memberList.get(i));
			}
		}
	}
```

#### 3. action = new MemberViewAction() 처리하기.
id를 체크해서 존재하는 id이면 회원정보보기 출력!

#### 3.1 MemberViewAction에 consoleUtil로 입력 처리하고, Service로 비지니스 로직 처리한다.
* ConsoleUtil consoleUtil = new ConsoleUtil(); : 콘솔 처리 하기 위해 생성
* String memberViewId = consoleUtil.getId("정보를 볼 회원 아이디 :", scan); : 정보를 원하는 회원 아이디라는 문자열과 , scan을 memberViewId에 넘겨준다.
* MemberViewService memberViewService = new MemberViewService(); : View에 맞는 SService를 즉 비지니스 로직을 처리하기 위해 생성한다.
* MemberVO memberVO = memberViewService.getMemberVO(memberViewId);  : Service로 부터 .getMemberVO(memberViewId)메소드에서 memberViewId를 받아서 처리하고 memverVO로 넘겨준다.
* consoleUtil.printMemberVOInfo(memberVO); : 사용자가 입력한 id를 받은 memverVO 로 .getID()를 출력하고, memberVo를 호출해서 toString()을 부르게 되서 모두 출력 

> MemverViewAction.java

```java
package action;

import java.util.Scanner;

import svc.MemberViewService;
import util.ConsoleUtil;
import vo.MemberVO;

public class MemberViewAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {

		ConsoleUtil consoleUtil = new ConsoleUtil();
		// 검색, 수정, 삭제도 아이디 요청이 필요하다.
		// 비지니스 로직 처리 전 전처리 작업!
		String memberViewId = consoleUtil.getId("정보를 볼 회원 아이디 :", scan);
		
		// 비지니스 로직 처리하기.
		MemberViewService memberViewService = new MemberViewService();
		MemberVO memberVO = memberViewService.getMemberVO(memberViewId); 
		
		consoleUtil.printMemberVOInfo(memberVO);
				
				
	}

}
```

#### 3.2 ConsoleUtil() 처리
* getId(String label, Scanner scan){} 메소드 추가
* printMemberVOInfo(MemverVO memberVO){} 메소드로 얻은 아이디 출력

> ConsoleUtil.java 

```java
// 콘솔상에서 사용자로부터 데이터를 입력받거나 요청처리 결과를 사용자에게 출력해주는 역할을 하는 클래스
package util;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;

import vo.MemberVO;

public class ConsoleUtil {

	public MemberVO getNewMemberVO(Scanner scan) {
		
		System.out.println("====회원등록====");
		System.out.print("회원 아이디 : ");
		String id = scan.next();
		
		System.out.print("회원 비밀번호 : ");
		String passwd = scan.next();

		System.out.print("회원 이름 : ");
		String name = scan.next();
		
		System.out.print("회원 이메일 : ");
		String email = scan.next();
		
		System.out.print("회원 주소 : ");
		String addr = scan.next();

		System.out.print("회원 성별 : ");
		String gender = scan.next();

		System.out.print("회원 전화번호 : ");
		String tell = scan.next();
		
		System.out.print("회원 나이 : ");
		int age = scan.nextInt();
		
		// 위의 속성들을 하나의 객체로 묶기.
		MemberVO newMemberVO = new MemberVO();
		newMemberVO.setAddr(addr);
		newMemberVO.setAge(age);
		newMemberVO.setEmail(email);
		newMemberVO.setGender(gender);
		newMemberVO.setId(id);
		newMemberVO.setName(name);
		newMemberVO.setPasswd(passwd);
		newMemberVO.setTell(tell);
		
		// 모든 속성을 set 해주고 return 으로 넘겨준다.
		return newMemberVO;
	}

	public void printRegistSuccessMessage(String id) {
		// TODO Auto-generated method stub
		System.out.println(id + " 님 회원 등록이 성공되었습니다.");
		
	}
	public void printRegistFailMessage(String id) {
		// TODO Auto-generated method stub
		System.out.println(id + " 님 회원 등록이 실패되었습니다.");
		
	}

	public void printMemberList(ArrayList<MemberVO> memberList) {
		
		// 회원등록한 회원이 없으면, 
		if (memberList.size() == 0) {
			System.out.println("등록된 회원이 없습니다.");
		}
		else {
			// 사이즈가 0이 아니면 회원이 한 명이라도 등록이 되어 있는 것이다.
			for (int i = 0; i < memberList.size(); i++) {
				// toStirng 객체가 호출이 되면서 실행이 된다.
				System.out.println(memberList.get(i));
			}
		}
	}

	public String getId(String label, Scanner scan) {
		// 검색, 수정, 삭제 3개의 요청 처리를 위해 하나로 작성
		System.out.print(label);
		return scan.next();
	}

	public void printMemberVOInfo(MemberVO memberVO) {
		
		System.out.println(memberVO.getId() + " 아이디 회원의 정보");
		System.out.println(memberVO);
		
	}

	
}

```

#### 3.3 MemberViewService
* public MemberVO getMemberVO(String memberViewID){} 메소드 처리하기.
   *  id를 찾고, memverVO에 id를 넣어준다.
> MemberViewService.java 

```java
package svc;

import ui.MemberUI;
import vo.MemberVO;

public class MemberViewService {

	public MemberVO getMemberVO(String memberViewId) {
		
		MemberVO memberVO = null;
		
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(memberViewId.equals(MemberUI.memberList.get(i).getId())) {
				memberVO = MemberUI.memberList.get(i);
				break;
			}
		}
		
		return memberVO;
	}
	
	
}

```

#### 4. 회원정보수정 ==> action = MemberModifyAction();
* ConsoleUtil consoleutil = new ConsoleUtil();
   * 사용자의 입력 처리하기 위해서 consoleutil을 참조 변수로 추가
* String memberModifyId = consoleutil.getId("수정하고 싶은 회원 아이디 입력 : ", scan);
   * consoleutil.getId 메소드로 문자열과 , scan 을 넘겨서 다시 id를 memberModifyId에 return을 받는다.
* MemberModifyService memberModifyService = new MemberModifyService();
   * 비지니스 로직 처리를 위해 MemberModifyService 참조 변수를 생성
* MemberVO oldMemberVO = memberModifyService.getOldMemberVO(memberModifyId);
   * 수정하고 싶은 memvberModifyId와 기존에 존재하는 id를 비교해서 넘겨준다.
* MemberVO newMemberVO = consoleutil.getNewMemberVO(oldMemberVO ,scan); 
   * 회원등록에 사용하던 getNewMemberVO를 scan을 추가해서 메소드 오버로딩을 해서 수정 해야하는 항목들을 추가해준다.
* boolean modifySuccess = memberModifyService.modifyMemberVO(newMemberVO); 
   * 위에서 수정이 완료가 되었으면 Service 단으로 가서 완료되었는지 아닌지 true, false로 반환해준다.
* if, else 로 modifySuccess가 참이면 consoleutil.printModifySuccessMessage(memberModifyId); 호출하고, 거짓이면 consoleutil.printModifyFailMessage(memberModifyId); 호출 해준다.
   * 참이면 : System.out.println(id + " 아이디 회원의 정보가 수정이 완료되었습니다."); 
      * 이렇게 간단하게 출력을 해준다.


#### 4.1 MemberModifyAction
가장 먼저 처리할 것은? 어떤 회원의 정보를 수정할 것인가?
즉 id를 선택해야한다.

> MemberModifyAction.java

```java
package action;

import java.util.Scanner;

import svc.MemberModifyService;
import util.ConsoleUtil;
import vo.MemberVO;

public class MemberModifyAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		
		// 사용자의 입력 처리 => 전처리라고 생각
		ConsoleUtil consoleutil = new ConsoleUtil();
		
		String memberModifyId = consoleutil.getId("수정하고 싶은 회원 아이디 입력 : ", scan);
		
		// 비지니스 로직 처리 
		MemberModifyService memberModifyService = new MemberModifyService();
		
		// 식별자를 넣어줘야한다.
		// oldMember 가져오고
		MemberVO oldMemberVO = memberModifyService.getOldMemberVO(memberModifyId);
		
		// 메소드 오버로딩으로 구현하기.
		MemberVO newMemberVO = consoleutil.getNewMemberVO(oldMemberVO ,scan);
		System.out.println(newMemberVO);
		
		// 수정을 했으면 응답을 줘야한다.
		boolean modifySuccess = memberModifyService.modifyMemberVO(newMemberVO);
		
		if (modifySuccess) {
			// . 을 찍으면 hip에 포인팅하는 것이다. String 변수로 잡아준다.
			consoleutil.printModifySuccessMessage(memberModifyId);
		}
		else {
			consoleutil.printModifyFailMessage(memberModifyId);
		}
		
		
		
	}

}

```


#### 4.2 ConsoleUtil
* getNewMemberVO(MemberVO oldMemberVO, Scanner scan) {} 
   * 메소드 오버로딩을 해서 업데이트 할 항목들을 입력 받는다.
* public void printModifySuccessMessage(String id) 
   * 성공을 알리는 문자열을 출력한다.
* public void printModifyFailMessage(String id)
   * 실패

> ConsoleUtil.java

```java
	public MemberVO getNewMemberVO(MemberVO oldMemberVO, Scanner scan) {
		// TODO Auto-generated method stub
		MemberVO newMemberVO = null;
		// 수정할려는 작업이 없으면 null이 나오고 실패를 하게 만든다.
		
		// null이 아니다. 즉 회원이 정상적으로 넘어왔다면
		if (oldMemberVO != null) {
			newMemberVO = new MemberVO();
			System.out.println("====정보 수정====");
			System.out.println("이전 회원 이름 : " + oldMemberVO.getName());
			System.out.print("회원 이름 : ");
			String name = scan.next();
			
			System.out.println("이전 비밀번호 : " + oldMemberVO.getPasswd());
			System.out.print("회원 비밀번호 : ");
			String passwd = scan.next();
			
			System.out.println("이전 이메일 : " + oldMemberVO.getEmail());
			System.out.print("회원 이메일 : ");
			String email = scan.next();
			
			System.out.println("이전 주소  : " + oldMemberVO.getAddr());
			System.out.print("회원 주소 : ");
			String addr = scan.next();

			System.out.println("이전 성별  : " + oldMemberVO.getGender());
			System.out.print("회원 성별 : ");
			String gender = scan.next();

			System.out.println("이전 전화번호 : " + oldMemberVO.getTell());
			System.out.print("회원 전화번호 : ");
			String tell = scan.next();
			
			System.out.println("이전 나이 : " + oldMemberVO.getAge());
			System.out.print("회원 나이 : ");
			int age = scan.nextInt();
			
			// 위의 속성들을 하나의 객체로 묶기.
			newMemberVO.setId(oldMemberVO.getId());
			newMemberVO.setAddr(addr);
			newMemberVO.setAge(age);
			newMemberVO.setEmail(email);
			newMemberVO.setGender(gender);
			newMemberVO.setName(name);
			newMemberVO.setPasswd(passwd);
			newMemberVO.setTell(tell);
		}
		
		return newMemberVO;
	}

	public void printModifySuccessMessage(String id) {
		
		System.out.println(id + " 아이디 회원의 정보가 수정이 완료되었습니다.");
		
	}

	public void printModifyFailMessage(String id) {
		System.out.println(id + " 아이디 회원의 정보가 수정이 실패되었습니다.");		

	}
```

#### 4.3 MemberModifyService

> MemberModifyService.java

```java
package svc;

import ui.MemberUI;
import vo.MemberVO;

public class MemberModifyService {

	public MemberVO getOldMemberVO(String memberModifyId) {
		
		MemberVO oldMemberVO = null;
		
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(memberModifyId.equals(MemberUI.memberList.get(i).getId())) {
				oldMemberVO = MemberUI.memberList.get(i);
				break;
			}
		}
		
		return oldMemberVO;
		// 화면에 보여주는 작업은 consoleUtil에서 처리
	}

	public boolean modifyMemberVO(MemberVO newMemberVO) {
		// 수정하기.
		boolean modifySuccess = false;
		// newMemberVO가 제대로 넘어온다면!
		if (newMemberVO != null) {
			// id 값이 똑같은거를 찾아서 
			for (int i = 0; i < MemberUI.memberList.size(); i++) { 
				// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
				// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
				if(newMemberVO.getId().equals(MemberUI.memberList.get(i).getId())) {
					// 해당 객체에 참조하는 객체에 해당하는 인덱스에 newMemberVO로 바꾸겠다.
					MemberUI.memberList.set(i, newMemberVO);
					break;
				}
			}
			modifySuccess = true;
		}
		
		return modifySuccess;
	}
}

```
