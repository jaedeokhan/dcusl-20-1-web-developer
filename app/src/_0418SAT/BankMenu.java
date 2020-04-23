package _0418SAT;

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
		
		do {
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
//				System.out.println("현재 잔고는  :" + balance);
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
		
//		switch(choice) {
//			case 1:
//				System.out.println("1. 예금입니다.");
//				break;
//			case 2:
//				System.out.println("2. 출금입니디.");
//				break;
//			case 3:
//				System.out.println("3. 잔고입니다.");
//				break;
//			case 4:
//				System.out.println("4. 종료입니다.");
//				break;
//			default:
//				System.out.println("이용해 주셔서 감사합니다.");
//		}
		
		
	}

}
