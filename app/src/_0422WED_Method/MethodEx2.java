package _0422WED_Method;

import java.util.Scanner;

public class MethodEx2 {

	public static int oddSum(int value) {
		int sum = 0;
		for (int i = 1; i <= value; i++) {
			if (i % 2 != 0) {
				sum += i;
			}
		}
		return sum;
	}
	public static int evenSum(int value) {
		int sum = 0;
		for (int i = 1; i <= value; i++) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
	public static void main(String[] args) {
		/*
		 문제 ) 원하는 값의 크기를 입력받아 짝수, 홀수의 합을 구하는 메소드를 정의하기.
		 */
		Scanner scan = new Scanner(System.in);
		System.out.print("원하는 홀수의 값의 크기를 입력 : ");
		int odd = scan.nextInt();
		int odd_result = oddSum(odd);
		System.out.println("홀수의 합  :" + odd_result);
		
		System.out.print("원하는 짝수의 값의 크기를 입력 : ");
		int even = scan.nextInt();
		int even_result = evenSum(even);
		System.out.println("짝수의 합 : " + even_result);

	}

}
