package _0427MON_extends;

public class InherEx {
	/*
	1. 상속 (inheritance)
	 상속이란 자식이 부모가 가지고 있는 재산이나 권력을 물려받는다는 의미.
	 특정(자식) 클래스는 다른 (부모)클래스가 가지고 있는 모든 멤버변수나 멤버메소드를 사용할 수 있음.
	 ex) 자동차 => 엔진은 포함 관계이다. 이와는 반대로 자동차는 있는데 자동차의 회사! 버스와 차, 택시와 버스는 
	 포함 관계가 아니다.
	 A라는 클래스 , B 클래스가 존재한다. 각각 관계를 지어주고 싶은데 B extends A, B가 A의 클래스의 멤버 변수와 멤버 메소드의 사용권을 물려 받는다.
	   상속 관계에서 자식 클래스의 객체를 생성하였을때 부모 클래스 객체도 생성되어짐
	 - 상속 관계 표현으로는 extends keyword를 사용한다.
	 - 상속 관계 용어로 super, 상위, 부모 class와 sub, 하위, 자식, class 로 사용 함.
	 - 상속의 개념에서 super와 this로 구분함.
	 - parent class를 this로도 접근을 다 할수는 있다!
	
	2.method override == 메서드를 재사용 할 수 있음  == 재정의
	- overloading => 생성자, 메소드를 인자 타입, 인자 개수, 인자 위치로 여러 개 정의를 가능.  
	- overriding  => 생성자 x , 메소드만 재정의를 할 수 있다.
	3. 단일 상속 (자바에서는 다중 상속 X). => 인터페이스에서는 다중 상속이 가능하다.
	4. extends와 implements를 함께 사용할 수 있음.
	형식) 
		class B {...}
		class C {...}
		class D {...}
		( x ) public class A extends B extends D{
			// 다중 상속은 되지 않는다.
		} 
		( o ) public class A extends B {
			문장 구현;
		}
		( o ) public class A extends C {
		}
	
	 */
	
	public static void main(String[] args) {
		SubClass sub = new SubClass();
		System.out.println(sub.a);
		System.out.println(sub.b);
		System.out.println(sub.c);
		System.out.println(sub.d);
		
		sub.methodA();
		sub.methodB();
//		sub.methodC();
		sub.method();
		
		// Super에 있는  method를 sub의 method에서 재정의를 했기 때문에 동일하게 나온다. => DDD
		SuperClass Super = sub;
		Super.method();
	}

}

// 아부지 상속
class SubClass extends SuperClass{
	// is a 관계
	int a;
	String b;
	  
	public SubClass() {
		// super(); 가 생략이 되어있다.
		System.out.println("자식 생성자");
	}
	
	public void methodA() {
		System.out.println("A");
	}
	
	public void method() {
		System.out.println("DDD");
	}
}

// 할아버지 상속
class SuperClass {//extends SuperSuperClass
	int c;
	String d;
	
	public SuperClass() {
		System.out.println("부모 생성자");
	}
	
	public void methodB() {
		System.out.println("B");
	}
	

	public void method() {
		System.out.println("D");
	}
}

class SuperSuperClass{
	int e;
	String f;
	
	public SuperSuperClass() {
		// super(); 가 생략이 되어있다. 
		System.out.println("부모부모 생성자");
	}
	
	public void methodC() {
		System.out.println("C");
	}
}