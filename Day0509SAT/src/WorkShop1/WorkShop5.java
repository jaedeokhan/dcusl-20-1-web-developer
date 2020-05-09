/*
     Scanner 로 입력 받는다.

     Input a : 5
     Input b : 13

     큰수 : 13 
 */
package WorkShop1;

import java.util.Scanner;

public class WorkShop5 {
	public void proWorkShop5() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Input a : ");
		int a = scan.nextInt();
		System.out.print("Input b : ");
		int b = scan.nextInt();
		
		// 삼항 연산자에서 조건문 안에 또 조건문 넣어주기.
		System.out.println((a > b) ? "a가 크다 " : ( b > a) ? "b가 크다" : "같다");
	}
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int a = 0;
		int b = 0;
		int max = 0;
		
		System.out.print("Input a : ");
		a = scan.nextInt();
		System.out.print("Input b : ");
		b = scan.nextInt();
		
		max = (a > b) ? a : b;
		
		System.out.println("큰 수  : " + max);
	}
}
