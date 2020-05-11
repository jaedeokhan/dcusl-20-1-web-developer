package Shape;

import java.util.Scanner;

public class ShapeEx {
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean stop = false;
		double dimension = 0.0d;
		int menu  = 0;
		// 부모 클래스가 여러개의 자식 클래스로 표현될 수 있는 형태
		Shape shape = null;
		
		do {
			System.out.println("**** 도형선택 ****");
			System.out.println("1. 삼각형");
			System.out.println("2. 사각형");
			System.out.println("3. 사다리꼴");
			System.out.println("4. 종료");
			System.out.print("도형 선택 : ");
			menu = scan.nextInt();
			
			switch (menu) {
			case 1:
				System.out.println("**** 삼각형 넓이 ****");
				System.out.print("밑변 : ");
				int bottom = scan.nextInt();
				System.out.print("높이 : ");
				int height = scan.nextInt();
				shape = new Triangle(bottom, height);
				System.out.printf("넓이 : %.2f\n", shape.getArea());
				break;
				
			case 2:
				System.out.println("**** 사각형 넓이 ****");
				System.out.print("밑변 : ");
				bottom = scan.nextInt();
				System.out.print("높이 : ");
				height = scan.nextInt();
				shape = new Rectangle(bottom, height);
				System.out.printf("넓이 : %.2f\n", shape.getArea());
				break;
				
			case 3:
				System.out.println("**** 사다리꼴 넓이 ****");
				System.out.print("밑변 : ");
				bottom = scan.nextInt();
				System.out.print("윗변 : ");
				int top = scan.nextInt();
				System.out.print("높이 : ");
				height = scan.nextInt();
				shape = new Trapezoid(bottom, top, height);
				System.out.printf("넓이 : %.2f\n", shape.getArea());
				break;
			
			case 4:
				stop = true;
				System.out.println("이용해주셔서 감사합니다.");
				break;
				
			default:
				System.out.println("잘못된 번호를 입력하셨습니다.(1/2/3/4)번을 입력해주세요.");
				break;
			}
			
			
		}while(!stop);
	}

}
