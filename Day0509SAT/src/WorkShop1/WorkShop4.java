/*
 입력받아 계산하시오
   **** 삼각형의 넓이 구하기  ****
   밑변 :  10  <---삼각형넓이 = (밑변 * 높이)/2
   높이 :   3
   

   결과 출력:
   넓이 :   ?  <--- 실수형으로 구하기(형변환)
 */
package WorkShop1;

import java.util.Scanner;

public class WorkShop4 {
	
	/*
	 * public void proWorkShop4() { Scanner scan = new Scanner(System.in);
	 * System.out.println("**** 삼각형의 넓이 구하기  ****"); System.out.print("밑변 : "); int
	 * base = scan.nextInt(); System.out.print("높이 : "); int height =
	 * scan.nextInt();
	 * 
	 * // 연산 => 우측에 연산시의 값이 double이어야 한다. 아니면 값이 다 잘린다! double dimension =
	 * (double)(base * height) / 2;
	 * 
	 * // 출력 System.out.println("넓이 : " + dimension);
	 * 
	 * }
	 */
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int base = 0;
		int height = 0;
		
		System.out.print("밑변 : ");
		base = scan.nextInt();
		System.out.print("높이 : ");
		height = scan.nextInt();
		
		double dimension = (double)(base * height) / 2;
		
		System.out.println("넓이 : " + dimension);
		
	}

}
