package _0427MON_extends;

public class InherEx4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a = new A();
		B b = a;
		C c = b;
		
		// 아무것도 나오지 않는다. Why? => 자식 클래스에서 재정의해서 아무런 값을 주지 않았기 때문이다.
		a.method();
		b.method();
		c.method();
		System.out.println();
		
		// 각각의 클래스에 있는 은닉 변수에 접근이 가능하다 => 위에서 부모 클래스에 자식 클래스를 암묵적 형변환을 통해서 가능하다.
		System.out.println(a.num);
		System.out.println(b.num);
		System.out.println(c.num);
		System.out.println();
		
		// 각각의 메소드는 재정의 하지 않았기 때문에 B,C를 상속받은 A의 객체인 a로 모두 접근이 가능하다.
		a.method_A();
		a.method_B();
		a.method_C();
		System.out.println();
		
		// b2는 C 클래스를 상속받은것 까지만 지금 포함한다.
		B b2 = new B();
		b2.method();
		System.out.println();
		
		// c2는 당연히 자신만 나온다.
		C c2 = new C();
		c2.method();
		
	}
}
class C{
	public C() {
		System.out.println("Constructor C");
	}
	
	int num = 10;
	String msg = "msg...";
	public void method() {
		System.out.println("method " + num);
	}
	
	public void method_C() {
		System.out.println("method_C " + msg);
	}
}

class B extends C{
	public B() {
		super();
		System.out.println("Constructor B");
	}
	int num = 50;
	
	public void method() {
		super.method_C();
		this.method_C();
		System.out.println("method " + num);
	}
	
	public void method_B() {
		System.out.println("method_B " + msg);
	}
}


class A extends B{
	public A() {
		super();
//		super().super();
		System.out.println("Constructor A");
	}
	int num = 100;
	
	public void method() {
		System.out.println("method " + num);
	}
	public void method_A() {
		System.out.println("method_A " + msg + super.num);
		
	}
}


// B에 대한 superclass


