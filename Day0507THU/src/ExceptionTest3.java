
public class ExceptionTest3 {

	public static void main(String[] args) {
		
//		throw new RuntimeException();
		
		// 요 놈은 일반 예외이다! 즉 반드시 예외처리를 해주지 않는다면 컴파일 타임에 빨간 줄 에러가 발생한다.
		try {
			throw new Exception();
		} catch (Exception e) {
			
		}
		
	}

}
