package _0429WED;

import java.util.Scanner;

public class InterfaceEx extends abstractC implements InterfaceA, InterfaceB{
	/*
	인터페이스(interface) 
	인터페이스는 추상화에서 확장한 개념이다.
	인터페이스는 명세서(기술서)이다. 
	abstract은 일반 클래스로도 사용을 가능하지만, Interface는 그냥 명세서로 사용을 하는 것이다 => 추상 메소드만 가능!
	interface는 설계의 개념이 더 강하다!
	1.특징
	객체를 생성 할 수 없음. => 구현체가 없다.
	class에서 상속시 implements keyword를 사용한다.
	implements == 구현 , 명세서를 보고 구현을 해서 사용하라!
	다중 상속이 가능 함 => 여러 개의 interface를 받을 수 있다.
	interface에는 상수와 추상 메서드만이 존재 함
	interface 간의 상속시에는 extends를 사용함.
	일반적인 메소드를 가질 수 없음
	상속시 interface에 있는 모든 메소드를 재정의하여 사용.
	interface에 정의된 메서드 중 하나라도 재정의하지 않는다면 abstract class로 정의할 수 있음.
	표현)
	   interface A{
	                 변 수    : [final][static] => 생략되어 있음.
	        ex) int A = 10;
	   		메서드 : [public][abstract] => 생략되어 있음.
	   		ex) void method();
	   }
	사용1)
	abstract class B implements A{
		추상 메서드 정의...
		일반적인 메서드 정의...
	}
	
	 */	
	@Override
	public void setA() {
		System.out.println("setA method...");
		
	}

	@Override
	public String method() {
		// TODO Auto-generated method stub
		return "Override method";
	}
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		InterfaceEx obj = new InterfaceEx();
		obj.setB();
		System.out.println("number : " + (B + A));
		System.out.println(obj.method());
		obj.setA();
	}



}

interface InterfaceA{
	int B = 100;
	String method();
}


interface InterfaceB{
	int A = 10;
	void setA();
}

abstract class abstractC{
	void setB() {
		System.out.println("B");
	}
}





		
		
