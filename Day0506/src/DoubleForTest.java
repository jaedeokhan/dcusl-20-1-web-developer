
public class DoubleForTest {

	public static void main(String[] args) {
		// 1. 이중 포문을 두 개를 분리하는 버전
//		for (int i = 1; i <= 5; i++) {
//			
//			for (int j = 1; j <= 5 - i; j++) {
//				System.out.print(" ");
//			}
//			for (int j = 1; j <= 2 * i - 1; j++) {
//				System.out.print(j);
//			}
//			System.out.println();	
//		}
//		
//		for (int i = 1; i <= 4; i++) {
//			
//			for (int j = 1; j <= i; j++) {
//				System.out.print(" ");
//			}
//			for (int j = 1; j <= 9 - 2 * i; j++) {
//				System.out.print(j);
//			}
//			System.out.println();	
//		}
//		
//		System.out.println();
//
//		// 2. 이중 for문 하나로 해결
//		for (int i = 1; i <= 9; i++) {
//			if (i <= 5) {
//			for (int j = 1; j <= 5 - i; j++) {
//				System.out.print(" ");
//			}
//			for (int j = 1; j <= 2 * i - 1; j++) {
//				System.out.print(j);
//			}
//			System.out.println();	
//			}
//			else {
//				// i = 6
//				for (int j = 1; j <= i - 5; j++) {
//					System.out.print(" ");
//				}
//				for (int j = 1; j <= 19 - 2 * i; j++) {
//					System.out.print(j);
//				}
//				System.out.println();	
//			}
//			
//	}	
//		System.out.println();
//		// 3. 삼항 연산자를 사용해서 보다 간단하게 처리
//		// (조건식) ? 조건식이 true일 때 반환할 값 : 조건식이 false일 때 반환할 값
//		for (int i = 1; i <= 9; i++) {
//			int spaceNumber = (i <= 5) ? 5 - i : i - 5;
//			int maxNumber = (i <= 5) ? 2 * i - 1 : 19 - 2 * i;
//			
//			for (int j = 1; j <= spaceNumber; j++) {
//				System.out.print(" ");
//			}
//			for (int j = 1; j <= maxNumber; j++) {
//				System.out.print(j);
//			}
//			System.out.println();	
//			}
		/*
         	☆☆☆☆☆☆☆☆☆☆
		 	☆☆☆☆☆☆☆☆☆☆
		 	☆☆☆☆☆☆☆☆☆☆
		 	☆☆☆☆☆☆☆☆☆☆
		 	☆☆☆☆☆☆☆☆☆☆
		 */
//		for (int i = 1; i<=5; i++) {
//			for (int j = 1; j <= 10; j++) {
//				if (i == 1 || i == 5) { 
//				System.out.print("*");
//				}
//				else {
//					if (j == 1 || j == 10) {
//						System.out.print("*");
//					}
//					else {
//					System.out.print(" ");	
//					}
//				}
//			}
//			System.out.println();
//		}
//		
//		System.out.println("다음행으로 가서 준비");
//		System.out.print("start");
		
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 10; j++) {
				System.out.println(j);
			}
		}
		
		}

   }


