package WorkShop2;

import java.util.Scanner;

public class WorkShop9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.print("num : ");
		int num = scan.nextInt();
		
		System.out.println((num > 0) ? "양수" : (num < 0) ? "음수" : "0");
	}

}
