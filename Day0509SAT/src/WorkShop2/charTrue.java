/*
4번
ch 값이 영문자이거나 숫자일 경우만 b의 값이 true 가 출력되게 코드를 작성하세요.
각 문자는 코드값 : 
 */
package WorkShop2;

import java.util.Scanner;

public class charTrue {
	//https://devist.tistory.com/116
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char ch = '\u0000';
		
		System.out.print("char 값 입력 : ");
		ch = scan.next().charAt(0);
		
		boolean b = (ch >= '0' && ch <= '9' || // 0 - 9 => 48 - 57
				     ch >= 'A' && ch <= 'Z' || // A - Z => 65 - 90
				     ch >= 'a' && ch <= 'z')  // a - z  => 97 -123
				? true : false;
		
		System.out.println(ch + ":" + b);
		
	}

}
