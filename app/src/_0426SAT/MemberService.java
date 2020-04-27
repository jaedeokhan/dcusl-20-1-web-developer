package _0426SAT;

import java.util.Scanner;

public class MemberService {

	public Member member;
	public MemberService() {
		member = new Member("한재덕", "deok", "1234");
	}
	
	// 1. 로그인 시작 == 1번은 logincheck(); => id_pw_equal(); => bankmenu 선택
	// 2. 회원가입 == 2번 아직 작성 x, 회원가입 후 로그인으로 이동 => bankmenu
	// 3. 종료 => 이용해 주셔서 감사합니다.
	public void singup() {
		Scanner scan  = new Scanner(System.in);
		boolean result = true;
		while(result) {
			System.out.println("안녕하세요. 00 은행입니다.");
			System.out.println("-----------------------------");
			System.out.println("1.로그인  2.회원가입 3.종료");
			System.out.println("-----------------------------");
			System.out.print("선택  > ");
			int choice = scan.nextInt();
			if (choice == 1) {
				logincheck();
				bankmenu();
			}
			else if (choice == 2) {
				
			}
			else if (choice == 3) {
				result = false;
				System.out.println("이용해주셔서 감사합니다.");
			}
		}
		
	}
	

	public boolean id_pw_equal(String id, String pw) {
		boolean result = false;
		if (id.equals(member.getId()) && pw.equals(member.getPw())) {
			System.out.println(member.getName() + "님 로그인중...");
			result = true; 
		}
		return result;
	}
	
	public void logincheck() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디를 입력하세요 : ");
		String id = scan.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String pw = scan.nextLine();
		boolean result = false;
		result = id_pw_equal(id, pw);
		if (result) {
			System.out.println(member.getName() + "님 로그인 되었습니다.");
		}
		else {
			System.out.println("id 또는 passwd가 올바르지 않습니다.");
			logincheck();
		}
		
	}
	
	public void bankmenu() {
		boolean result = true;
		int balance = 0;
		int deposit = 0;
		int expenditure = 0;
		Scanner scan = new Scanner(System.in);
		
		while (result) {
			System.out.println("-----------------------------");
			System.out.println("1.예금조회 | 2.입금 | 3.출금 | 4.종료");
			System.out.println("-----------------------------");
			System.out.print("선택> ");
			int choice = scan.nextInt();
			if (choice == 1) {
				System.out.print("1. 현재 잔액 : ");
				System.out.println(balance);
			}
			
			else if (choice == 2) {
				System.out.print("2. 입금 할 금액을 입력 : ");
				deposit = scan.nextInt();
				balance += deposit;
				System.out.println("예금 금액 입력  : " + deposit);

			}
			else if (choice == 3) {
				System.out.print("3. 출금할 금액을 입력 : ");
				expenditure = scan.nextInt();
				balance -= expenditure;
				System.out.println("출금 금액 입력 : " + expenditure);
//				System.out.println("현재 잔고는  :" + balance);
			}
			else {
				System.out.println("3. 종료입니다.");
				result = false;
			}
			System.out.println("현재 잔고는 : "+balance);
		}
	}
	

}
