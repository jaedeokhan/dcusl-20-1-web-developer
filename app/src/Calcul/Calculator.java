package Calcul;

public class Calculator {

	   // 1. field
	   char op;//operator
	   static double two; //static이 붙으면서 class의 변수가 된다. static이 붙지않은 나머지는 instance 변수
	   double op1;//operand
	   double op2;//operand
	   
	   // 2. constructor
	   public Calculator(char oprt, double op1, double op2) {
	      this.op = oprt;
	      this.op1 = op1;
	      this.op2 = op2;
	   };
	   
	   // 3. method
	   
	   
	   public double calc() {
	      double answer = 0.0; //지역변수(local variable)
	      
	      switch(this.op) {
	      case '+':
	         answer = op1 + op2;
	         break;
	      case '-':
	         answer = op1 - op2;
	         break;
	      case '*':
	         answer = op1 * op2;
	         break;
	      case '/':
	         answer = op1 / op2;
	         break;
	      default:
	    	  System.out.println("Error! operator is not correct");
	    	  break;
	      }
	      
	      return answer;
	   }
	   public void printOut(double answer) {
	      System.out.print(op1 + " ");
	      System.out.print(op + " ");
	      System.out.print(op2 + " = ");
	      System.out.println(answer);
	   }
	   
}