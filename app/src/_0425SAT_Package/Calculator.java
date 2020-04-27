package _0425SAT_Package;

public class Calculator {
	
	int a;
	int b;
	char operation;
	
	void setNumber(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	int  getPlus() {
		return this.a + this.b;
	}
	
	int getMinus() {
		return this.a - this.b;
	}
	
	int getMultiple() {
		return this.a * this.b;
	}
	
	int getDivision() {
		return this.a / this.b;
	}
	
	void result(char operation, int a, int b) {
		int result;
		switch(operation) {
		case '+':
			result = getPlus();
			System.out.println(result);
			break;
		case '-':
			result = getMinus();
			System.out.println(result);
			break;
		case '*':
			result = getMultiple();
			System.out.println(result);
			break;
		case '/':
			result = getDivision();
			System.out.println(result);
			break;
		}
	}
	
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		int plus;
		int minus;
		int multiple;
		int division;
		
		cal.setNumber(10, 5);
		plus = cal.getPlus();
		minus = cal.getMinus();
		multiple = cal.getMultiple();
		division = cal.getDivision();
		
		System.out.println("plus     : " + cal.a + " + " + cal.b + " = " + plus);
		System.out.println("minus    : " + cal.a + " - " + cal.b + " = " + minus);
		System.out.println("multiple : " + cal.a + " * " + cal.b + " = " + multiple);
		System.out.println("division : " + cal.a + " / " + cal.b + " = " + division);
		
		int a = 10, b = 5;
		cal.result('+', a, b);
		cal.result('-', a, b);
		cal.result('*', a, b);
		cal.result('/', a, b);
		
		// refer class 
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		char oper = args[2].charAt(0);
		cal.result(oper, n1, n2);
		
		
		
		
		
		
		
	}
}


