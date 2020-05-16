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
	// 이제는 DB로 관리하기 때문에 ArrayList는 필요가 없다.
	//	public static ArrayList<MemberVO> memberList = new ArrayList<MemberVO>(); 
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
