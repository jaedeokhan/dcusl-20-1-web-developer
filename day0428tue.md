### 2020 0428 TUE DAY 14
1. 상속받은 객체들의 사용
2. 추상화

#### 1. 상속받은 객체들의 사용
1. 상속여부 체크
2. 생성자
3. 메소드 - Override - 자식 클래스에서 Override를 했으면자식껄로 덮는다.  
4. 묵시적 형변환 - 은닉 변수 사용을 위해서!
5. @Override == annotation

> Z.java => this, super는 reference를 생성해서 접근하는게 아니다.
```java
package _0428TUE;

class X{
	protected int i = 10;
	protected String msg = "I am an X.";
	
	public void print() {
		System.out.println(msg);
	}
	
	public void play() {
		System.out.println("Play.."+msg);
	}
}

class Y extends X{
	protected int i = 20;
	protected String msg = "I am a Y.";
	
	@Override
	public void print() {
		System.out.println(msg);
	}
	
}

public class Z extends Y{
	int i = 30;
	protected String msg = "I am a Z.";
	
	public void print() {
		System.out.println(msg);
	}
	
	public void play() {
		System.out.println("Play.." +msg);
	}
	
	public void doZ() {
		System.out.println("do something in Z.");
	}
	
	public void test(int i) {
		// Z > Y > X
		Z z = new Z();
		Y y = z;
		X x = z;
		//문제)
		z.print();    // 1 = I am a Z.
		y.print();    // 2 = I am a Z.
		x.print();    // 3 = I am a Z.
		super.print();// 4 = I am a Z.      (x) => I am a Y.
		play();       // 5 = Play..I am a Z.
		super.play(); // 6 = Play..I am a Z.(x) => Play..I am X
		//y.doZ();    // 7 = null
		//super.super.print(); // 8 = I am a Z.
		
		System.out.println("\ni =" + i);	// 9  = 15
		System.out.println("this.i =" + this.i ); // 10 = 30
		System.out.println("super.i = " + super.i ); // 11 = 20 
		System.out.println("y.i = " + y.i);  // 12 = 20 
		System.out.println("x.i = " + x.i);  // 13 = 10
		System.out.println("((Y)this).i = " + ((Y)this).i); //14 = 20
		System.out.println("((X)this).i = " + ((X)this).i); //15 = 10
		//super.super.i = 10;                               //16 = 10
	}
	public static void main(String[] args) {
		Z z = new Z();
		z.test(15);
	}

}
```
#### 2. 추상화(abstract)
1.추상 (Abstract)
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
```java
	 abstract class A {
	 	int a;
	 	void setB(){} 		 // 추상 클래스는 일반 메소드도 가질 수 있고,
	 	abstract void setA();// 추상 메소드도 가질 수 있다. 추상 메소드는 {} 중괄호를 사용하지 않는다.
	 }
```

4.사용
```java
	 class B extends A{
	 	
	 	@Override
	 	void SetA(){
	 	    System.out.println("super class의 추상 메서드 재정의(반드시)");
	 	}
	 }
```

5.추상 클래스란 왜 있어야 하는가?
생각해보자. 동물에는 사자, 호랑이 등등에게는  공통점이 있다. 사자와 호랑이는 모두 움직인다. 그래서 우리는 move()라는 추상 메소드를 만들어 줄려고 한다. 왜냐하면 사자나 호랑이는 모두 움직이고, 동물들은 모두 움직이기에 꼭 뺄 수가 없어서 무조건 사용해야한다.

#### 2.1 








