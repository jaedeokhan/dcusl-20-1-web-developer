package _0420MON;

import java.util.Random;
import java.util.Scanner;

public class ArrayEx4 {

	public static void main(String[] args) {
		//  cols, rows 를 받아서 char을 랜덤으로 10개의 값을 넣어라.
		char c = 'A';
		int cols = 0;
		int rows = 0;
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.print("행의 수를 입력하세요 : ");
			rows = scan.nextInt();
			if (rows < 1 || rows > 10) {
				System.out.println("반드시 1-10까지의 수를 입력해야합니다.");
			}
			else {
//				System.out.println();
				break;
			}
			
		}while(true);
		
		do {
			System.out.print("열의 수를 입력하세요:");
			cols = scan.nextInt();
			if (cols < 1 || cols > 10) {
				System.out.println("반드시 1-10까지의 수를 입력해야 합니다.");
			}
			else {
//				System.out.println();
				break;
			}
		} while(true);
		
		char crr[][] = new char[rows][cols]; 
		int random = 0;
		
		for (int i = 0; i < crr.length; i++) {
			for (int j = 0; j < crr[i].length; j++) {
				Random r = new Random();
//				System.out.print("값을 입력하세요 : ");
//				System.out.print("값을 입력하세요. :" + "crr" + "[" + i + "]" +  "[" + j + "]" +
//						"/" + "crr" + "[" + crr.length + "]" +"[" + crr[i].length + "]  : ");
//				random = scan.nextInt();
				crr[i][j] = (char)(c + r.nextInt(26));
				System.out.print(crr[i][j]);
                System.out.print(" ");
			}
			System.out.println();
		}
		
//		for (int i = 0; i < crr.length; i++) {
//			for (int j = 0; j < crr[i].length; j++) {
////				Random r = new Random();
//				System.out.print(crr[i][j] + " ");	
//			}
//			System.out.println();
//		}
		
		
	}

}
