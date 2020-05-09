/*
<화면구성>
**** 도형선택 ****
1. 삼각형
2. 원
3. 사다리꼴
4. 종료
선택 : 3

**** 사다리꼴넓이 ****
아랫변 : 2
윗 변 : 3
높 이 :4 

넓이 : // 소수이하 2째 자리까지

선택 : 4
 */
package WorkShop4;

import java.util.Scanner;

public class WorkShop1 {
//		(base * height) / 2
//		r * r * 3.14 = height * 2 * 3.14
//		(high + base ) * height / 2
	
	public static void main(String[] args) {

		int choice = 0;
		int base = 0;
		int high = 0;
		int height = 0;
		double dimension = 0.0d;
		boolean stop = false;
		Scanner scan = new Scanner(System.in);
		
		while (!stop) {
			System.out.println("**** 도형선택 ****");
			System.out.println("1. 삼각형");
			System.out.println("2. 원");
			System.out.println("3. 사다리꼴");
			System.out.println("4. 종료");
			System.out.print("선택 : ");
			choice = scan.nextInt();
			
			if (choice == 1) {
				System.out.println("**** 삼각형 넓이 ****");
				System.out.print("아랫변  : ");
				base = scan.nextInt();
				System.out.print("윗 변 : ");
				high = scan.nextInt();
				System.out.print("높 이 : ");
				height = scan.nextInt();
				
				dimension = (base * height) / 2;
				
				System.out.printf("\n 넓이 : %.2f\n", dimension);
			}
			else if (choice == 2) {
				System.out.println("**** 원 넓이****");
				System.out.print("아랫변  : ");
				base = scan.nextInt();
				System.out.print("윗 변 : ");
				high = scan.nextInt();
				System.out.print("높 이 : ");
				height = scan.nextInt();
				
				dimension = (height * 2) * 3.14;
				
				System.out.printf("\n 넓이 : %.2f\n", dimension);
			}
			else if (choice == 3) {
				System.out.println("**** 사다리꼴 넓이 ****");
				System.out.print("아랫변  : ");
				base = scan.nextInt();
				System.out.print("윗 변 : ");
				high = scan.nextInt();
				System.out.print("높 이 : " );
				height = scan.nextInt();
				
				dimension = ((base + high) * height) / 2;
				
				System.out.printf("\n 넓이 : %.2f\n", dimension);
			}
			else if (choice == 4) {
				System.out.println("감사합니다^.^");
				System.out.println("정지하셨습니다.");
				stop = true;
			}
		}
		
		

	}

}
