### 2020 0511 MON 

#### 1. 0509 SAT WorkShop17.java => 추상 클래스 활용

#### shape 추상 클래스
> shape.java
```java
package Shape;

public abstract class shape {
	// 생성자 생성 단축키 => Shift + Alt + s
	int nData1, nData2;

	public shape(int nData1, int nData2) {
		super();
		this.nData1 = nData1;
		this.nData2 = nData2;
	}
	
	// 각 자식 클래스에서 오버라이딩할 메소드 정의
	// 메소드 기능 => 면접 즉 넓이를 구하는 메소드!	
	abstract public double getArea();
}
```

#### Rectangle은 Shape를 상속 받음
> Rectangle.java 
```java
package Shape;

import java.util.Scanner;

public class Rectangle extends Shape{
	public static Scanner scan = new Scanner(System.in);
	// 자식 클래스를 생성하기전에 부모 클래스를 먼저 생성을 해줘야한다.
	// 빈 생성자를 찾지 못해서 에러가 나기 때문에, 정의한 생성자를 따로 호출을 해줘야 한다.
	public Rectangle(int nData1, int nData2) {
		super(nData1, nData2);
	}

	@Override
	public double getArea() {
		
		System.out.println("**** 사각형 넓이 ****");
		System.out.print("밑변  : ");
		nData1 = scan.nextInt();
		System.out.print("높 이 : ");
		nData2 = scan.nextInt();
		return (double)nData1 * nData2;
	}

}
```

#### Triangle도 Shape.java를 상속받음.
> Triangle
```java
package Shape;

import java.util.Scanner;

public class Triangle extends Shape{
	public static Scanner scan = new Scanner(System.in);
	public Triangle(int nData1, int nData2) {
		super(nData1, nData2);
	}

	@Override
	public double getArea() {
		
		System.out.println("**** 사각형 넓이 ****");
		System.out.print("밑변  : ");
		nData1 = scan.nextInt();
		System.out.print("높 이 : ");
		nData2 = scan.nextInt();
		return (nData1 * nData2) / 2.0;
	}
	
}
```

#### Trapezoid는 Shape라는 추상 클래스를 상속 받아서 사용한다.
```java
package Shape;

import java.util.Scanner;

public class Trapezoid extends Shape{
	public static Scanner scan = new Scanner(System.in);
	int nData3;
	
	public Trapezoid(int nData1, int nData2, int nData3) {
		super(nData1, nData2);
		this.nData1 = nData3;
	}

	@Override
	public double getArea() {
		System.out.println("**** 사각형 넓이 ****");
		System.out.print("윗변  : ");
		nData1 = scan.nextInt();
		System.out.print("아랫변 : ");
		nData2 = scan.nextInt();
		System.out.print("높이 : ");
		nData3 = scan.nextInt();
		
		return ((nData1 + nData2) * nData3) / 2.0;
	}

}
```

#### ShapeEx 여기서는 위의 세 가지의 도형들을 다형성을 이용해서 출력을 한다.


```java

```


#### 1.1 Parent.java, Child.java 두 개를 이용해서 Constructor 사용 이해하기.
> Parent.java

```java
package Shape;

public class Parent {
	
	public Parent() {
		// 부모 클래스의 생성자가 호출이 될 때 생성이 되었다는거를 알려주기 위해서 출력문을 하나 만든다.
		System.out.println("Parent Constructor");
	}
}	
```

> Child.java
```java
package Shape;

public class Child extends Parent{
	public Child() {
		System.out.println("Child Constructor");
	}
	
}
```

> ConstructorTest.java
이 Main에서는 Child.java를 참조 변수로 불러오서 실행을 하면 Child.java는 Parent.java를 상속을 받았으니 당연히 Parent.java의 생성자가 호출이 되고 그 다음에 Child.java의 생성자가 호출이 된다.
```java
package Shape;

public class ConstructorTest {

	public static void main(String[] args) {
		
		Child ch = new Child();
		
	}

}
// Parent Constructor 
// Child Constructor
```



