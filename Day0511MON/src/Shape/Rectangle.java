package Shape;

import java.util.Scanner;

public class Rectangle extends Shape{
	public static Scanner scan = new Scanner(System.in);
	// 자식 클래스를 생성하기전에 부모 클래스를 먼저 생성을 해줘야한다.
	// 빈 생성자를 찾지 못해서 에러가 나기 때문에, 정의한 생성자를 따로 호출을 해줘야 한다.
	public Rectangle(int nData1, int nData2) {
		super(nData1, nData2);
	}

	@Override
	public double getArea() {
		
//		System.out.println("**** 사각형 넓이 ****");
//		System.out.print("밑변  : ");
//		nData1 = scan.nextInt();
//		System.out.print("높 이 : ");
//		nData2 = scan.nextInt();
		return (double)nData1 * nData2;
	}

}
