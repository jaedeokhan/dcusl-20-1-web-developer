package _0428TUE;

import java.util.Scanner;

public class Calculator {

 public static Scanner sc = new Scanner(System.in);
 public static void main(String[] args) {

  double answer = 0;
  
  double op1 = inputOperand();
  double op2 = inputOperand();  
  System.out.print("연산자를 입력 : ");
  char operator =  sc.next().charAt(0);
  
  answer = calculate(op1, op2, operator);
  
  System.out.println( op1 + " " + operator + " " + op2 + " = " + answer );
  
  sc.close();

 }

 private static double inputOperand() {
  String input;
  boolean notVld = true;
  double operand = 0;
  while ( notVld ) {
   System.out.print("수 을(를) 입력하세요. : ");
   input = sc.nextLine();
   try {
    operand = Double.parseDouble(input);
    notVld = false;
   }
   catch ( NumberFormatException e ) {
    System.out.println( " Not Valid !! ");
   }
  }
  return operand;
 }

 private static double calculate(double opA, double opB, char operator) {
  double ans = 0;
  switch ( operator ) {
  case '+':
	   ans = opA + opB;
	   break;
  case '-':
	   ans = opA - opB;
	   break;
  case '*':
	   ans = opA * opB;
	   break;
  case '/':
	   ans = opA / opB;
	   break;
   
  default:
	   break;
   
  }
  return ans;
 }

}