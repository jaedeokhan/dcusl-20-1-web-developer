### 2020 0514 THU 

#### 1. 공통적인 클래스, 인터페이스

> MemberUI.java

```java
package ui;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import action.MemberListAction;
import action.MemberModifyAction;
import action.MemberRegistAction;
import action.MemberRemoveAction;
import action.MemberSearchAction;
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
				action = new MemberRemoveAction();
				break;
			case 6:
				// 사용자로부터 입력을 받아서 검색 조건과 검색 값을 얻어와야한다.
				// 조건 1 : id로 검색, name으로 검색 
				// id로 검색을 할때는 회원이 한 명이다.
				// 그렇지만, 이름일때는 여러 명이 반환이 되게 해야한다.
				// id로 검색을 했을 때는 
				// 검색된 회원 정보는 회원 한 명! 
				// 이름으로 검색했을 때는
				// 검색된 모든 회원 정보가 리스팅되면 됨 
				action = new MemberSearchAction();
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

#### 1.1.1 vo/MemberVO.java

> MemberVO.java

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

#### 1.1.2 Controller

> Controller.java

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

#### 1.1.3 util/ConsoleUtil

> ConsoleUtil.java

```java
// 콘솔상에서 사용자로부터 데이터를 입력받거나 요청처리 결과를 사용자에게 출력해주는 역할을 하는 클래스
package util;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;

import vo.MemberVO;
import vo.SerachData;

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

	public void printRemoveSuccesMessage(String removeMemberId) {
		System.out.println(removeMemberId + " 님의 정보삭제 성공");

	}

	public void printRemoveFailMessage(String removeMemberId) {
		System.out.println(removeMemberId + " 님의 정보삭제 실패");
	}

	public SerachData getSearchData(Scanner scan) {

		// SearchData가 들어있는 클래스!
		SerachData searchData = new SerachData();

		// 검색 조건을 먼저 입력 받아라.
		System.out.print("검색 조건을 입력(아이디 | 이름 ) : ");
		String searchCondition = scan.next();

		// if/else 블록 둘 다 공통적으로 사용할 수 있는 변수 만들기
		String searchValue = null;
		if (searchCondition.equals("아이디")) {
			System.out.println("검색할 아이디  :");
			searchValue = scan.next();
		}
		else {
			System.out.println("검색할 이름 : ");
			searchValue = scan.next();
		}

		searchData.setSearchCondition(searchCondition);
		searchData.setSearchValue(searchValue);

		return searchData;
	}

	public void printSearchMemberVO(MemberVO memberVO) {

		System.out.println(memberVO);

	}

	public void printSearchMemberList(ArrayList<MemberVO> memberList) {
		// 한 개도 없다면, 즉 요소가 없다.
		if (memberList.size() == 0) {
			System.out.println("검색한 회원 정보가 존재하지 않습니다.");
		}
		else {
			System.out.println("검색결과 : " );
			// collection 의 요소의 개수 반환 ==> size()
			for (int i = 0; i < memberList.size(); i++) {
				System.out.println(memberList.get(i));
			}
		}

	}






}

```

#### 1.1.4 

#### 1.2 action = new MemberRemoveAction(); 구현
Action 에서 사용자의 입력을 받는 consoleutil로 넘기고, 비즈니스 로직은 Service단으로 넘긴다.

> MemberRemoveAction.java

```java
package action;

import java.util.Scanner;

import svc.MemberRemoveService;
import util.ConsoleUtil;

public class MemberRemoveAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		
		ConsoleUtil consoleUtil = new ConsoleUtil();
		// 삭제 대상의 id를 얻어오기.
		String removeMemberId = consoleUtil.getId("삭제할 회원의 아이디 : ", scan);
		
		// 삭제하는 작업은 Service 클래스에서 정의하기.
		MemberRemoveService memberRemoveService = new MemberRemoveService();
		
		// 삭제를 완료했다면, 응답을 해줘야한다!
		boolean removeSuccess = memberRemoveService.removeMember(removeMemberId);
		
		if (removeSuccess) {
			consoleUtil.printRemoveSuccesMessage(removeMemberId);
		}
		else {
			consoleUtil.printRemoveFailMessage(removeMemberId);			
		}
	}

}

```

#### 1.2.1 MemberRemoveService에서는 id를 검색해서 삭제할 아이디를 제거해준다.

> MemberRemoveService.java

```java
package svc;

import ui.MemberUI;

public class MemberRemoveService {

	public boolean removeMember(String removeMemberId) {
		// TODO Auto-generated method stub
		boolean removeSccuess = false;
		
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(removeMemberId.equals(MemberUI.memberList.get(i).getId())) {
				MemberUI.memberList.remove(i);
				removeSccuess = true;
				break;
			}
		}
		
		return removeSccuess;
	}
	
}
```

#### 1.3 action = new MemberSearchAction(); 구현
아이디, 이름 두 입력을 동시에 받아서 클래스를 통해서 넘겨 받아와서,
아이디이면 id에 해당하는 모든 내용을 출력한다. id는 식별자이므로 한 명만 출력이 된다.
이름은 두 명일수도 있는 식별자가 아니여서 존재하는 이름을 가진 모든 내용을 출력해준다.

> MemberSearchAction.java

```java
package action;

import java.util.ArrayList;
import java.util.Scanner;

import svc.MemberSearchService;
import util.ConsoleUtil;
import vo.MemberVO;
import vo.SerachData;

public class MemberSearchAction implements Action {

	@Override
	public void execute(Scanner scan) throws Exception {
		// select 할려면 select하고 싶은 id를 체크하기.
		ConsoleUtil consoleUtil  = new ConsoleUtil();
		
		SerachData searchData = consoleUtil.getSearchData(scan);
		
		MemberSearchService memberSearchService =  new MemberSearchService();
		
		// searchData 클래스를 받아서 존재하는지 보고 반환해주기.
		// id, name 인지 확인하고, 출력을 다르게 해줘야한다. id는 하나만, name은 여러 명이면 여러 명 출력하기!
		
		if (searchData.getSearchCondition().equals("아이디")) {
			MemberVO memberVO = memberSearchService.searchMemberVOById(searchData.getSearchValue());
			consoleUtil.printSearchMemberVO(memberVO);
		}
		
		else {
			// 회원이 1명이 아닐수도 있으니 collection 타입으로 받아야 한다.
			ArrayList<MemberVO> memberList = memberSearchService.searchMemberVOByName(searchData.getSearchValue());
			consoleUtil.printSearchMemberList(memberList);
			
		}
		
		
		
	}

}
```

#### 1.3.2 MemberSearchService 에서는 id는 한 개의 id를 찾아와서 클래스를 반환해주면 된다. 그러나 name 같은 경우는 같은 이름의 사용자가 존재하기에 ArrayList<MemberVO> 형태로 받아준다.

> MemberSearchService.java

```java
package svc;

import java.util.ArrayList;

import ui.MemberUI;
import vo.MemberVO;

public class MemberSearchService {

	public MemberVO searchMemberVOById(String memberSearchId) {

		MemberVO memberVO = null;
		
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(memberSearchId.equals(MemberUI.memberList.get(i).getId())) {
				memberVO = MemberUI.memberList.get(i);
				break;
			}
		}
		
		return memberVO;
	}

	public ArrayList<MemberVO> searchMemberVOByName(String memberSearchName) {
		
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		
		for (int i = 0; i < MemberUI.memberList.size(); i++) { 
			// 1,2 ~ 인덱스에서 같은 아이디를 찾았는데, 나머지 회원들을 다 비교하는 것은 리소스가 낭비이니! for문 밖으로 go
			// 새로 등록하는 id와 기존에 있는 id를 비교해서 만약, 동일한게 존재한다 if문으로 처리.
			if(memberSearchName.equals(MemberUI.memberList.get(i).getName())) {
				memberList.add(MemberUI.memberList.get(i));
				break;
			}
		}
		
		return memberList;
	}


	
		

	}
```

