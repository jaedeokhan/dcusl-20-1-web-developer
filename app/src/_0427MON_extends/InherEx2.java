package _0427MON_extends;

public class InherEx2 {

	public static void main(String[] args) {
		/*
		 - 형 변환
		 - Object 형 변환
		 - 오버라이드 사용
		 - 은닉변수 사용 (Over shadow)
		 
		 표현)
		 객체 생성
		 A a = new A();
		 B b = new B();
		 B b = a;
		 
		 */
		A1 a = new A1();
//		B b = new B(); // 묵시적 형변환
//		System.out.println(a.y);
		B1 b = a;
		
		// 은닉 변수란? Class A의 int z = 10; Class B의 int z = 50; 이면 z = 50을 가져오고 싶을 때
//		 B b = a; 를 형변환을 해서 사용할 수 있도록 한다.
		System.out.println(a.z); // 10
		System.out.println(a.z); // 10
		System.out.println(b.z); // 50
		a.m();
		
		// 한 번 overriding 을 하면 값은 오버라이딩 한 값이 나온다.
		b.m();
		// super란? direct로 간다
		// 참조는? 객체가 생성되었을때, 즉 메모리에 올라왔을때 생성이 된다.

	}
	
}

class A1 extends B1{
	int y = 5;
	int z  = 10;
	public void m() {
		System.out.println("C");
	}
}

class B1{
	int  z = 50;
	public void m() {
		System.out.println("B");
	}
}









