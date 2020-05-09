/*
1번 :아래의 코드는 사과를 담는데 필요한 바구니(버켓)의 수를 구하는 코드이다. 만일
사과의 수가 123개이고 하나의 바구니에는 10개의 사과를 담을 수 있다면, 13개의 바구니
가 필요할 것이다. (1)에 알맞은 코드를 넣으시오.
 */
package WorkShop2;

import java.util.Scanner;

public class Bucket {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int bucket = 10;
		int apple = 0;
		int numOfBucket = 0;
		
		System.out.print("사과의 수를 입력 : ");
		apple = scan.nextInt();
		
		numOfBucket = (apple % bucket == 0) ? apple / bucket : apple / bucket + 1;
		System.out.println("bucket : " + numOfBucket);
		

	}

}
