package _0417FRI;

import java.util.Scanner;

public class SwitchEx2 {

	public static void main(String[] args) throws java.io.IOException {
		/* 예외처리)
		 
		 */
		Scanner scan = new Scanner(System.in);
		System.out.print("Yes/no ?");
		// char c로 입력을 받는다.
//		char b = (char) System.in.read();
		
		String c = scan.nextLine();
		switch (c) {
			case "y":
			case "Y":
			case "Yes":
			case "yes":
			System.out.println("yes test");
			break;
		}
		
	}

}
