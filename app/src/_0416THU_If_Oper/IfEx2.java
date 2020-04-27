package _0416THU_If_Oper;

import java.util.Scanner;

public class IfEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		제어문(다중 조건문) 
		/*
		 	if (조건식 1) {
		 		실행 구문1;
		  }else if (조건식 2) {
		  		실행 구문2;
		  }else if (조건식 3) {
		  		실행 구문3;
		  }
			'
			'
			'
		*/
//		int score = 30;
//		if (score > 50 && score <81) {
//			System.out.println("실행 구문1");
//		}else {
//			System.out.println("위 문장은 거짓");
//		}
//		
		int scores;
		Scanner scan = new Scanner(System.in);
		System.out.print("점수를 입력하세요 : ");
        scores = scan.nextInt();       
        
        
        if ( scores > 50 && scores < 81) {
        	System.out.println("실행 구문 1");
        }else {
        	System.out.println("실행 구문 2");
        }
		
        if (scores > 80 && scores < 91) {
        	System.out.println("실행 구문 3");
        }else {
        	System.out.println("실행 구문 4");
        }
	}

}
