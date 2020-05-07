
class ArgsException extends Exception{
	
	private int argsNumber;
	
	// ctrl + space 
	public ArgsException(String msg) {
		super(msg); // Exception 클래스의 정의된 속성에 할당한다.
	}
	
	// alt + shift + s
	public int getArgsNumber() {
		return argsNumber;
	}
	
	public void setArgsNumber(int argsNumber) {
		this.argsNumber = argsNumber;
	}
	
}

public class ExceptionTest7 {

	public static void main(String[] args) {
		// tyr 입력 후 ctrl + space 
		try {
			if (args.length != 2) {
				ArgsException ae = new ArgsException("인자를 2 개 입력하세요.");
				ae.setArgsNumber(args.length);
				throw ae;
			}
			else {
				int num1 = Integer.parseInt(args[0]);
				int num2 = Integer.parseInt(args[1]);	
				System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
			}
		} catch (ArgsException e) {
			System.out.println(e.getMessage());
			System.out.println("당신이 입력한 인자수는 " + e.getArgsNumber() + "개 입니다.");
		}
	}

}
