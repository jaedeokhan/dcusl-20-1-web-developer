/*
2번 : num 의 값에 따라 양수, 음수, 0을 출력하는 코드를 작성

 */
package WorkShop2;

import java.util.Scanner;

public class numJudge {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int num = 0;
		
		System.out.print("num 입력  :");
		num = scan.nextInt();
		
		if (num > 0) {
			System.out.println("양수");
		}
		else if (num < 0){
			System.out.println("음수");
		}
		else {
			System.out.println("0");
		}
		
		
	}

}
