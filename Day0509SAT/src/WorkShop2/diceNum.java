/*
문제 13 : 
두 개의 주사위를 던졌을 때 두 수의 합이 6이 되는 모든 경우가 몇 개인지 계산하는 코드를 생성하시요.
 */
package WorkShop2;

public class diceNum {

	public static void main(String[] args) {
		
		int totalcount = 0;
		int hapcount = 0;
		
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				totalcount++;
				if ((i + j ) == 6) {
					System.out.println(i + "+" +  j + "=" + (i + j) );
					hapcount++;
				}
			}
		}
		System.out.println("총 : " + totalcount);
		System.out.println("두 수의 합 6 : " + hapcount);

	}

}
