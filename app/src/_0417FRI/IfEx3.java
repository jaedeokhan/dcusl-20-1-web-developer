package _0417FRI;

import java.util.Scanner;

public class IfEx3 {

	public static void main(String[] args) {
		/*
		 응용 문제)
		 int score;
		 0 - 60 까지 -> 문자열 C 출력
		 61 - 80까지 -> 문자열 B 출력
		 81 - 100까지 -> 문자열 A 출력
		 */
		Scanner scan = new Scanner(System.in);
		int scores;
		System.out.print("점수를 입력하세요 : ");
        scores = scan.nextInt();    
        
        if (scores >= 81 && scores <= 100) {
        	System.out.println("문자열 A");
        }else if (scores >= 61 && scores <= 80){
        	System.out.println("문자열 B");
        }else if (scores >= 0 && scores <= 60) {
        	System.out.println("문자열 C");
        }
        

	}

}
