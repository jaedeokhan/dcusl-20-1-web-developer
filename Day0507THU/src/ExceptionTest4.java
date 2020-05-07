
public class ExceptionTest4 {

	public void exceptionMethod() throws Exception{
		// throw를 사용해서 자기가 처리를 안하고 던지는 이유는? 
		// try ~ catch 구문의 중복을 피하기 위해서! 
		// 모든 구문에서 try ~ catch 구문을 다 넣어야 해서! 중복성을 걷어낸다.
		throw new Exception();
		
		
	}
	
	public static void main(String[] args) {
		
		ExceptionTest4 et4 = new ExceptionTest4();
		try {
			et4.exceptionMethod();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
