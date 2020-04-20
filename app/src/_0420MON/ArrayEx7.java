package _0420MON;

import java.util.Scanner;

public class ArrayEx7 {

	public static void main(String[] args) {
		/*
		 연습문제)
		 정수 타입의 배열을 생성하여 1~10까지 저장하고, 출력하는 프로그램 작성
		 */
		int list_size = 10;
		int []arr = new int[list_size];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
			System.out.println("arr[" + i + "] : " + arr[i]);
		}
		
		/*
		 응용 예제1) 입력하는 학생의 수에 맞게 시험 성적의 합계와 평균을 작성
		 */
		int listsize = 10;
		int sum = 0;
		double avg = 0;
		int score;
		Scanner scan = new Scanner(System.in);
		int []students = new int[listsize];
		
		for (int i = 0; i < students.length; i++) {
			System.out.print("점수를 입력하세요[" + (i + 1) + "]/[" + students.length + "] : " );
			score = scan.nextInt();
			sum += score;
		}
		
		avg = sum / students.length;
		System.out.println("sum : " + sum);
		System.out.println("avg : " + avg);
	}

}
