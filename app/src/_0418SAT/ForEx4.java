package _0418SAT;

import java.util.Scanner;

public class ForEx4 {

	public static void main(String[] args) {
		// 중첩 반복문 (Nested loop)
		/*
		 for (변수 선언(초기화); 조건식; 증가혹은 감소 수식){
		 	실행 구문1;
		 	for (변수 선언(초기화); 조건식; 증가혹은 감소 수식){
		 		실행 구문2;
		 	}
		 }
		 
		 */
		
		//  내가 입력하는 수보다 작은 구구단을 모두 실행하기.
		
		int i;
		int j;
		int gu = 9;
		Scanner scan = new Scanner(System.in);
		System.out.print("구구단 몇 단까지 출력할까요? : ");
		int gugudan = scan.nextInt();
		
		
		for (i = 1; i <= gugudan; i++) {
			System.out.println(i+"단  :");
			for(j = 1; j <= gu; j++) {
				System.out.println(i + "*" + j + "=" + i * j);
			}
			System.out.println();
			System.out.println();
		}

	}

}
