
public class ExceptionTest1 {

	public static void main(String[] args){
		/*
		 예외처리 : 정수를 0으로 나눔
		 */
		int result = 0;
		
		try {
			int num = Integer.parseInt(args[0]);
			// num 값이 0이 전달되면 에러가 발생한다.
			result = 10 / num;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("args를 하나 입력해주세요.");
		}
		catch(ArithmeticException e)
		{
			System.out.println("정수를 0으로 나누면 안돼요.");
		}
		catch(NumberFormatException e) {
			System.out.println("인자를 정수 타입으로 입력해주세요.");
		}
		
		// 이것을 간단하게 하고 싶다면?
		catch(Exception e) {
			// Exception으로 모든 에러를 받아서 getClass해서 getName으로 클래스의 이름을 가져온다.
			System.out.println(e.getClass().getName());
		}
		System.out.println(result);
		System.out.println("프로그램 계속 실행");
		
	}

}
