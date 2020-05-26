package Calcul;

import java.util.Scanner;

public class CalculString {
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		// variables initialize
		double operand1 = 0.0;
		double operand2 = 0.0;
		double answer = 0.0;
		char operator ='\u0000';
		
		do {
			// operator select
			System.out.println();
		}while(operator != 'x'); // operator == 'x' 이면 false
			
	   double calc(char optest) {
		      char op;
		      switch(op) {
		      case '+':
		    	  System.out.println("+");
		    	  break;
		      case '-':
		    	  System.out.println("-");
		         break;
		      case '*':
		         System.out.println("*");
		         break;
		      case '/':
		    	 System.out.println("/");
		         break;
		      default:
		    	  System.out.println("Error! operator is not correct");
		    	  break;
		      

		      }
	
	}
	
	}
}

	   

