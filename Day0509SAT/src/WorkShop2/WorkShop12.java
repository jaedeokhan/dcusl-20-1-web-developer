package WorkShop2;

import java.util.Scanner;

public class WorkShop12 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Input name : ");
		String name = scan.next();
		System.out.print("Input gender : ");
		String gender = scan.next();
		System.out.print("Input age : ");
		int age = scan.nextInt();
		System.out.print("Input tall : ");
		double tall = scan.nextDouble();
		
		String sex = (gender.equals("M") ? "남자" : "여자");
		
		System.out.println("이름 : " + name);
		System.out.println("성별 : " + sex);	
		System.out.println("나이 : " + age);
		System.out.println("키 : " + tall);
	}

}
