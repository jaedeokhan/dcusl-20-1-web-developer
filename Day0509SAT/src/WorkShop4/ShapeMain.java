/*
1번 :
1. Workshop1
<화면구성>

**** 도형선택 *****
1. 삼각형
2. 원
3. 사다리꼴
4. 종료
선택 : 3

**** 사다리꼴넓이 ****
아랫변: 2
윗   변: 3
높   이: 4

넓이 : ?   <---소수이하 2째자리까지
-----------------------------------------------------
**** 도형선택 *****
1. 삼각형
2. 원
3. 사다리꼴
4. 종료
선택 : 4

감사합니다^.^
정지하셨습니다.
 */
package WorkShop4;

import java.util.Scanner;

abstract class Shape{
	Scanner scan = new Scanner(System.in);
	int nData1, nData2;
	Shape(int data1, int data2){
		this.nData1 = data1;
		this.nData2 = data2;
	}
	abstract public double getArea();
}

class Triangle extends Shape{
	Triangle(int data1, int data2){
		super(data1, data2);
	}
	
	public double getArea() {
		int dimension = 0;
		System.out.println("**** 삼각형 넓이 ****");
		System.out.print("아랫변  : ");
		nData1 = scan.nextInt();
		System.out.print("높 이 : ");
		nData2 = scan.nextInt();
		
		dimension = (nData1 * nData2) / 2;
		return dimension;
	}
}

class Rectangle extends Shape{

	Rectangle(int data1, int data2) {
		super(data1, data2);
	}
	
	public double getArea() {
		int dimension = 0;
		System.out.println("**** 삼각형 넓이 ****");
		System.out.print("아랫변  : ");
		nData1 = scan.nextInt();
		System.out.print("높 이 : ");
		nData2 = scan.nextInt();
		
		dimension = (nData1 * nData2);
		return dimension;
	}
}

class Trapezoid extends Shape{
	// 아랫변, 윗변 , 높이 입력받기
	int data3;
	Trapezoid(int data1, int data2, int data3) {
		super(data1, data2);
		this.data3 = data3;
		
		
	}
	public double getArea() {
		int dimension = 0;
		System.out.println("**** 사다리꼴 넓이 ****");
		System.out.print("아랫변  : ");
		nData1 = scan.nextInt();
		System.out.print("밑변 : ");
		nData2 = scan.nextInt();
		System.out.print("높이  : " );
		data3 = scan.nextInt();
		dimension = (nData1 + nData2) * data3 / 2;
		return dimension;
	}
	
}

public class ShapeMain {
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		int choice = 0;
		boolean stop = false;
		double dimension = 0;
		
		while (!stop) {
			System.out.println("**** 도형선택 ****");
			System.out.println("1. 삼각형");
			System.out.println("2. 직사각형");
			System.out.println("3. 사다리꼴");
			System.out.println("4. 종료");
			System.out.print("선택 : ");
			choice = scan.nextInt();
			
			if (choice == 1) {
				Triangle tr = new Triangle(3, 5);
				dimension = tr.getArea();
				System.out.printf("\n 넓이 : %.2f\n", dimension);
			}
			else if (choice == 2) {
				Rectangle rect = new Rectangle(3,5);
				dimension = rect.getArea();
				System.out.printf("\n 넓이 : %.2f\n", dimension);
			}
			else if (choice == 3) {
				Trapezoid trap = new Trapezoid(3,5,2);
				dimension = trap.getArea();
				System.out.printf("\n 넓이 : %.2f\n", dimension);
			}
			else if (choice == 4) {
				System.out.println("감사합니다^.^");
				System.out.println("정지하셨습니다.");
				stop = true;
		}
		
	}


	}
}
