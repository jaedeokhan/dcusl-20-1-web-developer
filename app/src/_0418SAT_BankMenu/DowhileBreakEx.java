package _0418SAT_BankMenu;

import java.util.Scanner;

public class DowhileBreakEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i = 0;
		boolean result = true;
		Scanner scan = new Scanner(System.in);
		
		do {
			i++;
			if (result) {
				System.out.println("실행 구문 : " + i);
				if (i == 5) {
					result = false;
				}
				if (! result) {
					System.out.println("실행 구문이 종료합니다.");
					break;
				}
			}else {
				System.out.println("i : " + i); // 당연히 출력이 안된다.
			}
		}while( i < 10 );
		
		
	}

}
