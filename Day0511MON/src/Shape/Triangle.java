package Shape;

import java.util.Scanner;

public class Triangle extends Shape{
	public static Scanner scan = new Scanner(System.in);
	public Triangle(int nData1, int nData2) {
		super(nData1, nData2);
	}

	@Override
	public double getArea() {
		
		System.out.println("**** 사각형 넓이 ****");
		System.out.print("밑변  : ");
		nData1 = scan.nextInt();
		System.out.print("높 이 : ");
		nData2 = scan.nextInt();
		return (nData1 * nData2) / 2.0;
	}
	
}
