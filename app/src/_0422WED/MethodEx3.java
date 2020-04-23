package _0422WED;

import java.util.Scanner;

public class MethodEx3 {
	static void gugu(int value) {
		for(int i = 1; i <= value; i++) {
			System.out.println(i + "단 입니다.");
				for(int j = 1; j <= 9; j++) {
					System.out.println(i +"*" + j + "=" + i * j);
				}
				System.out.println();
		}
	}
	
	static void set_gugu(int value) {
		for(int i = 2; i <= value; i++) {
			System.out.println(i + "단 입니다.");
				for(int j = 1; j <= 9; j++) {
					System.out.println(i +"*" + j + "=" + i * j);
				}
				System.out.println();
		}
	}		
	static void array_gugu(int value) {
		System.out.println(value + "단이 시작되었습니다.");
		for(int i = 1; i <= value; i++) {
			System.out.println(i + "단 입니다.");
				for(int j = 1; j <= 9; j++) {
					System.out.println(i +"*" + j + "=" + i * j);
				}
		}
		System.out.println(value + "단이 종료되었습니다.");
		System.out.println();
		
	}
	static void guguDan3(int [] arr) {
		for (int i = 0; i < arr.length; i++ ) {
			for(int j = 1; j < 10; j++) {
				System.out.println(arr[i] + " * " + " = " + (arr[i] * j) + " ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		/*
		 실습문제1) 구구단 출력 메소드를 작성하시오. (args 를 이용해서 단을 전달하기)
		 */
		Scanner scan = new Scanner(System.in);
//		System.out.print("구구단 단을 입력하세요 : ");		
//		int value = scan.nextInt();
//		gugu(value);
////		
//		/*
//		 실습문제2) 2~7 단 범위의 구구단을 출력하는 메소드를 작성하시오.
//		 */
//		System.out.print("숫자 7을 입력해주세요 : ");		
//		int value2 = scan.nextInt();
//		set_gugu(value2);
//		
		/*
		 실습문제3) 배열에 나열된 정수의 값만큼 구구단을 출력하시오.
		 */
//		int listsize = 3;
//		int dan = 0;
//		int []arr = new int[listsize];
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print("원하는 단을 입력하세요 : ");
//			dan = scan.nextInt();
//			array_gugu(dan);
//		}
		int [] arr = {2, 6, 8};
		guguDan3(arr);
	}

}
