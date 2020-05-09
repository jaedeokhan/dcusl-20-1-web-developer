package WorkShop2;

import java.util.Scanner;

public class WorkShop8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int numOfApples = 101;
		int sizeOfBucket = 10;
		int numOfBucket = (numOfApples % sizeOfBucket == 0) ?
				(numOfApples / sizeOfBucket) : (numOfApples / sizeOfBucket) + 1;
		// int numberOfBucket = ( numOfApples / sizeOfBucket) + ((numOfApples % sizeOfbucket == 0 ) ? 0 : 1);
		System.out.println("필요한 바구니의 수  : " +  numOfBucket);
	}

}
