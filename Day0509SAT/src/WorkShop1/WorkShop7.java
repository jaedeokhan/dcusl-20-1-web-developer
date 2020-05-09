/*
7. 짝수/홀수알아보기 (파일명 : WorkShop7.java)
    ( %연산자, if이용)

    Scanner 로 숫자를 입력 받는다.
    number : 243

    결과 출력
    홀수다!!
 */
package WorkShop1;

import java.util.Scanner;

public class WorkShop7 {

	public void proWorkShop7() {
		Scanner scan = new Scanner(System.in);
		System.out.print("number : ");
		int number = scan.nextInt();
		
		System.out.println((number % 2 == 0) ? "짝수다!!" : "홀수다!!");
		
	}
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int number = 0;
		String str = "";
		
		System.out.print("number : ");
		number  = scan.nextInt();
		str = (number % 2 == 0) ? "짝수다!!" : "홀수다!!";
		
		System.out.println(str);
		

	}

}
