package _0417FRI_If_Switch;

import java.util.Scanner;

public class SwitchEx {

	public static void main(String[] args) throws java.io.IOException{
		// switch ~ case 문
		/* 표현)
 		 switch (값) {
 		 	case 값 1:
 		 		실행 구문1;
 		 		break; (선택)
		 	case 값 2:
		 		실행 구문2;
		 		break; (선택)
		 	case 값 3:
		 		실행 구문3;
		 		break; (선택)
		 		'
		 		'
		 		'
		 	default :
		 		실행 구문4;
		 		break; (선택)
		 
		 */
		Scanner scan = new Scanner(System.in);
		int value1;
		int value2;
		char operator;
		
		while (true) {
			System.out.print("ex) 1 + 2 다음과 같이 연산을 입력하세요  : ");
			value1 = scan.nextInt();
			operator = scan.next().charAt(0); 
			value2 = scan.nextInt();
			
			if (operator == 'c') {break;}
				
			switch (operator) {
				case '+':
					System.out.println(value1 + " + " + value2 + " = " + (value1 + value2));
					break;
				case '-':
					System.out.println(value1 + " - " + value2 + " = " + (value1 - value2));
					break;
				case '*':
					System.out.println(value1 + " * " + value2 + " = " + (value1 - value2));
					break;
				case '/':
					System.out.println(value1 + " / " + value2 + " = " + (value1 - value2));
					break;
				default:
					System.out.println("이용해 주셔서 감사합니다.");
			}
		}
		
		}
		
}
