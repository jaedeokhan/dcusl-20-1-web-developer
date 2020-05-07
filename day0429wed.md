### 2020 0429 WED Day 15
1. 추상화란? => 공통점이 있는 모호한 개념들을 동물, 도형과 같은 포괄적인 것을 포함하는 것이다.
2.
3.
4.
5.

#### 1. 추상화란?

> Shape.java => 추상화 클래스
```java
package _0429WED;

public abstract class Shape {
	protected int x,y;
	public abstract double area();
	public abstract double circumference();
	
	

}
```
#### 1.1 추상화 클래스 상속 받아서 Circle, Rect 에서 구현

> Circle.java
```java
package _0429WED;

public class Circle extends Shape{
	
	protected int r;
	
	public Circle() {
		r = 0;
	}
	
	public Circle(int r) {
		this.r = r;
	}
	
	public void setRadius(int r) {
		this.r = r;
	}
	public int getRadius() {
		return r;
	}

	@Override
	public double area() {
		return Math.PI * r * r;
	}

	@Override
	public double circumference() {
		return Math.PI * 2 * r;
		
	}
	
}
```

> Rect.java
```java
package _0429WED;

public class Rect extends Shape{

	protected int width, height;
	public Rect() {
		this(0, 0);
	}
	
	public Rect(int w, int h) {
		width = w;
		height = h;
		x = getWidth();
		y = getHeight();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setSize(int w, int h) {
		width = w;
		height = h;
	}
	
	public int getSize() {
		return x * y;
	}
	
	@Override
	public double area() {
		return width * height;
	}

	@Override
	public double circumference() {
		return 2 * (width + height);
	}

}
```

#### 1.1.1 ShapeUser.java에서 구현한 것 사용하기.
```java
package _0429WED;

public class ShapeUser {

	public static void main(String[] args) {
		Circle circle = new Circle(5);
		System.out.println("shape 1 area : " + circle.area());
		System.out.println("shape 1 circumference : " + circle.circumference());
		
		Rect rect  = new Rect(5, 5);
		System.out.println("rect 1 area : " + rect.area());
		System.out.println("rect 1 circumference  :" + rect.circumference());
		
		// 다형성 비유 설명
		
		
	}

}
```
#### 1.1.2 클래스를 배열로 받기
* 다형성
* Shape 이라는 추상화 클래스로 Circle, Rect 클래스를 배열로 만들어서 담는다.
```java
package _0429WED;

public class ShapeUser {

	public static void main(String[] args) {
		// 도형을 담는 배열을 만들어라.
		Circle circle = new Circle(5);
		Circle circle2 = new Circle(7);
		Rect rect  = new Rect(5, 5);
		
		// 다형성 비유 설명
		Shape[] shape = new Shape[3];
		shape[0] = circle;
		shape[1] = circle2;
		shape[2] = rect;
		
		// 피자통!! -> 주문이 왔어. 계속해서 왔다갔다 하는 것보다는 피자통에 넣어서 한 번에 가져다 주는 것이 좋다.
		// 필요한거를 원할때 툭툭 끄집어낸다. 얘는 원이다. 원이다. 사각형이다!! 
		Circle c2  = (Circle)shape[0]; // 강제 캐스팅 => 자식으로 돌아간다. 
		for (int i = 0; i < shape.length; i++) {
		    System.out.println(i + "=area : " + shape[i].area()); // 오버라이드 메소드라서 가능하다 
		    										// 엄밀히 말하자면 강제 캐스팅을 해줘야한다.
		    // 부모의 타입인 shape로는 자식의 것을 사용하지 못한다.
		    System.out.println(i + "=cicum : " +shape[i].circumference());
		    System.out.println();
		}
		
		// 부모의 타입에서는 자식의 클래스에 접근을 하지 못하기 때문에 강제 형 변환을 해서 오버라이딩 한 메소드말고도 모두 다 접근을
		// 가능하게 만드는 것이다.
		Rect r = (Rect)shape[2];
		
		System.out.println();
		System.out.println(r.getSize());
		
	}
}
```

#### 2.인터페이스
인터페이스(interface) 
인터페이스는 추상화에서 확장한 개념이다.
인터페이스는 명세서(기술서)이다. 
abstract은 일반 클래스로도 사용을 가능하지만, Interface는 그냥 명세서로 사용을 하는 것이다 => 추상 메소드만 가능!
interface는 설계의 개념이 더 강하다!

1. 특징
객체를 생성 할 수 없음. => 구현체가 없다.
class에서 상속시 implements keyword를 사용한다.
implements == 구현 , 명세서를 보고 구현을 해서 사용하라!
다중 상속이 가능 함
interface에는 상수와 추상 메서드만이 존재 함
interface 간의 상속시에는 extends를 사용함.
일반적인 메소드를 가질 수 없음
상속시 interface에 있는 모든 메소드를 재정의하여 사용.
interface에 정의된 메서드 중 하나라도 재정의하지 않는다면 abstract class로 정의할 수 있음.

```java
// 다중 상속이 가능하다.
class A implements B,C{	
}

interface B{
}

interface C{
}
```

#### 2.1 


#### 3.

#### 4. 



