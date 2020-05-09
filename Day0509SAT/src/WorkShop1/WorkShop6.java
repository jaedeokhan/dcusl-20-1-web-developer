/*
    값을 입력받아 0보다 작거나 100보다 크면 "입력오류!!"  출력
     (  || 연산자 , if문 이용)
점수 : 120
입력오류!!

점수 : 55
정상처리됨!!
 */
package WorkShop1;

import java.util.Scanner;

public class WorkShop6 {
	/*
	 * public void proWorkShop6() { Scanner scan = new Scanner(System.in);
	 * System.out.print("점수 : "); int score = scan.nextInt();
	 * 
	 * System.out.println((score < 0 || score > 100) ? "입력오류!!" : "정상처리됨!!");
	 * 
	 * }
	 */
	public static void main(String[] args) {
		
		// ctrl + shift + o  : 해당 객체의 import 문을 자동 생성
		Scanner scan = new Scanner(System.in);
		int score = 0;
		String str = "";
		
		System.out.print("점수 : ");
		score = scan.nextInt();
		str = (score < 0 || score > 100) ? "입력오류!!" : "정상처리됨!!";
		System.out.println(str);
		
		
	}

}
