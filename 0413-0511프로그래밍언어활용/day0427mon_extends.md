### 2020 0427 MON Day 13 extends
1. 상속
2. 형변환
   * 묵시적 형변환
   * 암시적 형변환 
3. 다형성
4. Object 형 변환
5. Override Vs Overloading
6. 은닉변수 사용
  
#### 1. 상속
#### 1.1 상속 (inheritance)
상속이란 자식이 부모가 가지고 있는 재산이나 권력을 물려받는다는 의미.
특정(자식) 클래스는 다른 (부모)클래스가 가지고 있는 모든 멤버변수나 멤버메소드를 사용할 수 있음.
ex) 자동차 => 엔진은 포함 관계이다. 이와는 반대로 자동차는 있는데 자동차의 회사! 버스와 차, 택시와 버스는 
포함 관계가 아니다.
A라는 클래스 , B 클래스가 존재한다. 각각 관계를 지어주고 싶은데 B extends A, B가 A의 클래스의 멤버 변수와 멤버 메소드의 사용권을 물려 받는다.
   * 상속 관계 표현으로는 extends keyword를 사용한다.
   * 상속 관계 용어로 super, 상위, 부모 class와 sdub, 하위, 자식, class 로 사용 함.
   * 상속의 개념에서 super와 this로 구분함.

#### 1.1 method override vs overroading
method override == 메서드를 재사용 할 수 있음  == 재정의
- overroading => 생성자, 메소드를 인자 타입, 인자 개수, 인자 위치로 여러 개 정의를 가능.  
- overriding  => 생성자 x , 메소드만 재정의를 할 수 있다.

#### 1.2 extends 와 implements 란?
extends는 확장 == 상속
implements 구현 == 인터페이스

#### 1.3 extends 사용법

```java
package _0427MON_extends;

public class InherEx {
	public static void main(String[] args) {
		SubClass sub = new SubClass();
		System.out.println(sub.a);
		System.out.println(sub.b);
		System.out.println(sub.c);
		System.out.println(sub.d);
		
		sub.methodA();
		sub.methodB();
		sub.methodC();
	}

}
// 아부지 상속
class SubClass extends SuperClass{
	// is a 관계
	int a;
	String b;
	
	public void methodA() {
		System.out.println("A");
	}
}

// 할아버지 상속
class SuperClass extends SuperSuperClass{
	int c;
	String d;
	
	public void methodB() {
		System.out.println("B");
	}
}

class SuperSuperClass{
	int e;
	String f;
	
	public void methodC() {
		System.out.println("C");
	}
}
```
#### 1.4 부모 클래스 생성자, 자식 클래스 생성자 누가 먼저 생성이 될까?
부모 클래스의 생성자가 먼저 생성이 되어진다!!!!!!!!!

```java
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
}

// 할아버지 상속
class SuperClass extends SuperSuperClass{
	int c;
	String d;

	public SuperClass() {
		System.out.println("부모 생성자");
	}

	public void methodB() {
		System.out.println("B");
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

// 부모부모 생성자
// 부모 생성자
// 자식 생성자
```

#### 1.4.1 override



#### 1.4.2


#### 1.5 super(); 는 전부 가지고 있다!
자바 세상에서는 모두 객체이다! == object라는 클래스를 상속을 받게 해놓았다.

```java
class SuperClass extends object(){
	// 요 놈이 생략되어있다.
}
```

#### 2. 형변환

#### 2.1 객체 행변환
1. 암묵적 형변환은 부모 클래스에서 은닉 변수를 사용하기 위해서는 자식 클래스에게 객체를 받아야 한다. 
   * Child c = new Child();
   * Parent p = c;
2. 자식 클래스가 부모 클래스에게 상속을 받고나서 부모 클래스에 있는 methodA()를 자식 클래스에서 methodA()를재정의(overriding)을 한다면? p.methodA()를 호출하면 자식 클래스의 메소드가 호출이 된다.


> 은닉 변수를 사용하기 위해서는 묵시적 형변환으로 a 클래스를 받는다.

```java
package Bank;

public class test {

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
//		System.out.println("method " + num);
	}
	public void method_A() {
		System.out.println("method_A " + msg);
	}
}


// B에 대한 superclass
```



#### 3. 다형성
