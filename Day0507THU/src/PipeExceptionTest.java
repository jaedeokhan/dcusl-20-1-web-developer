// Pipe Exception
// 여러 개의 error 가 났을 때 catch 구문을 여러 개를 사용해서 처리를 했는데,
// try 안에서 여러 개의 error가 나면 하나의 catch 문에서 여러 개의 예외를 처리한다.
// JDK 7 version 이상
public class PipeExceptionTest {

	public static void main(String[] args) {
	
		try {
			int num = Integer.parseInt(args[0]);
		}
		catch (ArrayIndexOutOfBoundsException 
			   |NumberFormatException 
			   |ArithmeticException e) {
			// Pipe Exception 으로 하나의 catch 구문에서 예외 처리하기.
			// 여러 개를 나열하면 어느 에러가 들어왔는지 판단을 해야한다.
			if(e.getClass().getName().equals("java.lang.ArrayIndexOutOfBoundsException")) {
				System.out.println("args를 하나라도 입력하세요.");
			}
			else if(e.getClass().getName().equals("java.lang.NumberFormatException")){
				System.out.println("정수형을 입력해주세요.");
			}
			else {
				System.out.println("0으로는 나눌 수 없습니다.");
			}
		}

	}

}
