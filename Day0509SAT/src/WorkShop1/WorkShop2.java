/*
각 변수의 값을 Scanner 로 받아 출력하시요.
(변수명 :name,department,position,pay)
입력
이름 : 홍길동    
부서 : 자재부     
직위 : 대리       
급여 : 1500000원

출력
이름 : 홍길동    
부서 : 자재부     
직위 : 대리       
급여 : 1500000원
 */
package WorkShop1;

import java.util.Scanner;

public class WorkShop2 {

	/*
	 * public void proWorkShop2() { Scanner scan = new Scanner(System.in);
	 * System.out.print("이름 : "); String name = scan.next();
	 * System.out.print("부서 : "); String department = scan.next();
	 * System.out.print("직위 : "); String position = scan.next();
	 * System.out.print("급여 : "); int pay = scan.nextInt();
	 * 
	 * System.out.println("이름 : " + name); System.out.println("부서 : " + department);
	 * System.out.println("직위 : " + position); System.out.println("이름 : " + pay); }
	 */
	
	public static void main(String[] args) {
		String name = "";
		String department = "";
		String position = "";
		int pay = 0;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("이름을 입력하세요  : ");
		name = scan.nextLine();
		System.out.print("부서를 입력하세요 : ");
		department = scan.nextLine();
		System.out.print("직위를 입력하세요 : ");
		position = scan.nextLine();
		System.out.print("급여를 입력하세요 : ");
		pay = scan.nextInt();
		
		System.out.println("이름 : " + name);
		System.out.println("부서 : " + department);
		System.out.println("직위 : " + position);
		System.out.println("급여 : " + pay + "원");
	}

}
