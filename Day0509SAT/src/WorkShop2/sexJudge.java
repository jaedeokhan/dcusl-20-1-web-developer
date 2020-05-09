/*
5.
4개의 값을 입력받아 처리하기.
input name : 장윤정
input gender : F
input age : 25
input tall : 173.3
(성별 : M이면 "남자", 나머지 "여자" - 삼항 연산자 이용
 */
package WorkShop2;

import java.util.Scanner;

public class sexJudge {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String name ="";
		String sex = "";
		char gender = '\u0000';
		int age = 0;
		double tall = 0.0d;
		
		System.out.print("Input name : ");
		name = scan.nextLine();
		System.out.print("Input gender : ");
		gender = scan.next().charAt(0);
		System.out.print("Input name : ");
		age = scan.nextInt();
		System.out.print("Input name : ");
		tall = scan.nextDouble();
		
		sex = (gender == 'M') ? "남자" : "여자";
		
		System.out.println("이름 : " + name);
		System.out.println("성별 : " + sex);	
		System.out.println("나이 : " + age);
		System.out.println("키 : " + tall);
	}

}
