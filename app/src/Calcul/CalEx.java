package Calcul;

import java.util.Scanner;

public class CalEx {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// variables initialize
		double operand1 = 0.0;
		double operand2 = 0.0;
		double answer = 0.0;
		char operator ='\u0000';
		
		do {
			// operator select
			System.out.print("연산자를 선택(+, -, *, /) : ");
			operator = scan.next().charAt(0);
			
			// operand 1, 2 input
			System.out.print("두 값을 입력하세요 : ");
			operand1 = scan.nextInt();
			operand2 = scan.nextInt();
			
			// constructor create
			Calculator MyCalc = new Calculator(operator, operand1, operand2);
			
			// calc() execute
			answer = MyCalc.calc();
			
			// print
			MyCalc.printOut(answer);
			
		}while(operator != 'x'); // operator == 'x' 이면 false
		System.out.println("이용해주셔서 감사합니다.");
		
	}

}
