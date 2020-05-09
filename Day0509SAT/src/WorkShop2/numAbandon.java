/*
3번
num의 값 중에서 100 자리 이하를 버리는 코드를 작성 하시요.
예를 들어 322인 경우 300이 되며 234 인 경우에는 200 이 된다.
 */
package WorkShop2;

import java.util.Scanner;

public class numAbandon {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int num = 0;
		
		System.out.print("num 입력 : ");
		num = scan.nextInt();
		
		System.out.println("100 자리 이하 버려진 숫자 : " + (num - (num % 100)));
	}

}
