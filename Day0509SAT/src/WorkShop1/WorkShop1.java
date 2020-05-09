/*
기본급과 시간 수당을 입력받아 실수령액을 구하시요.
기본급(pay) : 1500000
시간수당(o_time) : 55000

조건>
세금(tax) : 기본급의 10%

일때  실수령액(t_pay)를 구하시오.
(실수령액 = 기본급 + 시간수당 - 세금)
--출력--
실수령액 : 1405000원
 */
package WorkShop1;

import java.util.Scanner;

public class WorkShop1 {
	
	/*
	 * public void proWorkShop() { // 교수님이 한 것. Scanner scan = new
	 * Scanner(System.in); System.out.print("기본급(pay) : "); int pay =
	 * scan.nextInt(); System.out.print("시간수당(o_time) : "); int o_time =
	 * scan.nextInt();
	 * 
	 * // 연산 int tax = pay / 10; int t_pay = pay + o_time - tax;
	 * 
	 * // 결과출력 System.out.println("실수령액 : " + t_pay + "원");
	 * 
	 * }
	 */
	
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int pay = 0;
		int o_time = 0;
		int tax = 0;
		int realestate = 0;
		System.out.print("기본급을 입력하세요 : ");
		pay = scan.nextInt();
		System.out.print("시간수당을 입력하세요 : ");
		o_time = scan.nextInt();
		tax = pay / 10;
		
		realestate = pay + o_time - tax;
		System.out.println("실수령액 : " + realestate + "원");
	}

}
