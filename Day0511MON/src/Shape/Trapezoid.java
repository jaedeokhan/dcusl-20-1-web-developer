package Shape;

import java.util.Scanner;

public class Trapezoid extends Shape{
	public static Scanner scan = new Scanner(System.in);
	int nData3;
	
	public Trapezoid(int nData1, int nData2, int nData3) {
		super(nData1, nData2);
		this.nData1 = nData3;
	}

	@Override
	public double getArea() {
		System.out.println("**** 사각형 넓이 ****");
		System.out.print("윗변  : ");
		nData1 = scan.nextInt();
		System.out.print("아랫변 : ");
		nData2 = scan.nextInt();
		System.out.print("높이 : ");
		nData3 = scan.nextInt();
		
		return ((nData1 + nData2) * nData3) / 2.0;
	}

}
