// return 의 정의는?
// 메소드의 실행을 종료하고 메소드를 호출한 쪽으로 되돌아 가는 예약어
// return 예약어를 사용할 때는 특정값을 반환 할 수 도 있고, 반환하지 않을 수 도 있다.
public class ReturnTest {
	public int add(int x, int y) {
		
		return x + y;
	}
	
	public void printInfo(String info) {
		// 리턴 타입이 void일 때는 return 을 생략을 할 수 있다.
		if (info.equals("exit")) {
			System.out.println("종료");
			return;
		}
		System.out.println(info);
		
	}
	
	public static void main(String[] args) {
		
		ReturnTest rt = new ReturnTest();
		System.out.println("10 + 10 = " + rt.add(10, 10));
		
		rt.printInfo("정보");
		rt.printInfo("exit");
	}

}
