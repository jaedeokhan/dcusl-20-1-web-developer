package Bank;

import java.util.Scanner;

public class BankMenu {
	
	public static void main(String[] args) {
		// 2020 0418 SAT 결과물 도출하는 응용 프로그램 -> Bank Program
//		"-----------------------------------";
//		"1.예금 | 2. 출금 | 3. 잔고 | 4.종료"
//		"-----------------------------------";
//		"선택>"
		boolean run = true;
		int balance = 0;
		int deposit = 0;
		int expenditure = 0;
		Scanner scan = new Scanner(System.in);
		
		// 회원가입
		boolean signup = true;
		String yesno;
		String idchcek = "deok";
		String passwdcheck = "1234";
		String id;
		String passwd;
		String email;
		
		do {
			System.out.println("안녕하세요.");
			System.out.print("회원가입을 진행하시겠나요? y/n : ");
			yesno = scan.nextLine();
			if (yesno.contentEquals("y")  || yesno.contentEquals("yes")
					|| yesno.contentEquals("Y")) {
				System.out.print("생성할 아이디를 입력해주세요 : ");
				id = scan.nextLine();
				System.out.print("비밀번호를 입력해주세요 : ");
				passwd = scan.nextLine();
				System.out.print("이메일을 입력해주세요 : ");
				email = scan.nextLine();
			}
			else if (yesno.contentEquals("n") ) {
				System.out.println("안녕히가세요.");
				break;
			}
			
			System.out.println("로그인을 해주시길 바랍니다.");
			System.out.print("아이디를 입력해주세요 : ");
			id = scan.nextLine();
			if (idchcek.contentEquals(id)) {
				System.out.print("비밀번호를 입력해주세요 : ");
				passwd = scan.nextLine();
				if(passwdcheck.contentEquals(passwd)) {
					while(!passwdcheck.contentEquals(passwd)) {
						  System.out.println("비밀번호를 다시 입력해주세요.");
						  passwd = scan.nextLine();
						  System.out.println("안녕하세요. 00 은행입니다. 비밀번호를 다시 입력해주세요.");
						}
				}
			}
			else {
				break;
			}
				
			System.out.println("-----------------------------");
			System.out.println("1.예금 | 2.출금 | 3.종료");
			System.out.println("-----------------------------");
			System.out.print("선택> ");
			int choice = scan.nextInt();
			
			if (choice == 1) {
				System.out.print("1. 입금 할 금액을 입력 : ");
				deposit = scan.nextInt();
				balance += deposit;
				System.out.println("예금 금액 입력  : " + deposit);

			}
			else if (choice == 2) {
				System.out.print("2. 출금할 금액을 입력 : ");
				expenditure = scan.nextInt();
				balance -= expenditure;
				System.out.println("출금 금액 입력 : " + expenditure);
//				System.out.println("현재 잔고는  :" + balance);
			}
			else {
				System.out.println("3. 종료입니다.");
				run = false;
			}
			System.out.println("현재 잔고는 : "+balance);
			
			
		}while(run);
		System.out.println("이용해 주셔서 감사합니다.");
		
		
		
	}

}
