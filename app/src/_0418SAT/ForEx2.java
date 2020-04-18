package _0418SAT;

import java.util.Scanner;

public class ForEx2 {

	public static void main(String[] args) {
		/*
		 내가 원하는 구구단 출력하기.
		 */
		
		int gu = 9;
		
		Scanner scan = new Scanner(System.in);
		int gugudan; 
		System.out.print("구구단을 실행 할 원하는 단 : ");
		gugudan = scan.nextInt();
		for (int i = 1; i <= gu; i++) {
			System.out.println(gugudan + "*" + i + "=" + gugudan * i);
		}
		
	}

}
