package _0420MON_Array;

import java.util.Scanner;

public class ArrayEx2 {

	public static void main(String[] args) {
		// 배열 응용 1, 10 개의 값을 입력 받아서 합과 평균을 출력하기.
		int list_size = 10;
		int input = 0;
		Scanner scan = new Scanner(System.in);
		int [] Userdata = new int[list_size];
		int sum = 0;
		double avg = 0;
		
		// 입력받기.
		for(int i = 0; i < Userdata.length; i++) {
			System.out.print("점수를 입력해주세요  : " + i + "/"  + Userdata.length);
			input = scan.nextInt();
			Userdata[i] = input;
		}
		
		for(int j = 0; j < Userdata.length; j++) {
			sum += Userdata[j];
		}
		avg = sum / Userdata.length;
		System.out.println("10개의 합 : " +sum);
		System.out.println("10개의 평균: " + avg);
		
		
	}

}
