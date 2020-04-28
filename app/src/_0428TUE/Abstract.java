package _0428TUE;
/*1.추상 (Abstract)
   객체의 모호함을 class로 표현하기위함.
   일반적으로 사물들의 공통된 특징, 즉 추상적 특징을 파악해 인식의 대상으로 삼는 행위이다.
   공통된 행위나 필드를 모아서 클래스를 만드는 것이다.
   
 2.특징
 class에 사용 시 추상 class의 의미 (ex : abstract class).  
  일반 메서드와 추상 메서드 사용 가능 함.
  객체를 생성 할 수 없음.
  상속관계에서 재정의 함. => 상속을 받아야지 사용이 가능하다.
       내부 익명 클래스(무명 anonymous class)로 객체생성 할 수 는 있음.	
       추상 메소드는 구현한지 않는다. => 모호하기 때문에 body를 가지지 못한다.   
       상속시 extends keyword를 사용함.

 3.표현
 abstract class A {
 	int a;
 	void setB(){} 		 // 추상 클래스는 일반 메소드도 가질 수 있고,
 	abstract void setA();// 추상 메소드도 가질 수 있다. 추상 메소드는 {} 중괄호를 사용하지 않는다.
 }
 
 4.사용
 class B extends A{
 	
 	@Override
 	void SetA(){
 	    System.out.println("super class의 추상 메서드 재정의(반드시)");
 	}
 }
*/
public class Abstract extends A{

	@Override 
	void m() {
		
	}
	
	public static void main(String[] args) {
		
	}

}

abstract class A {
	abstract void m(); // 추상 메소드는 {} 몸통을 가질 수 없다. => 모호하기 때문에 body를 가지지 못한다.
}

// 내부 클래스 = 클래스 => 클래스
class A2{
	class B{
		
	}
}



