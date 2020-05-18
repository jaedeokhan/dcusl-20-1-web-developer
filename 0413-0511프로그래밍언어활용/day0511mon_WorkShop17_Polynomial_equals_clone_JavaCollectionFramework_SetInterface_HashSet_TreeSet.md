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
		this.nData3= nData3;
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
package Shape;

import java.util.Scanner;

public class ShapeEx {
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean stop = false;
		double dimension = 0.0d;
		int menu  = 0;
		// 부모 클래스가 여러개의 자식 클래스로 표현될 수 있는 형태
		Shape shape = null;
		
		do {
			System.out.println("**** 도형선택 ****");
			System.out.println("1. 삼각형");
			System.out.println("2. 사각형");
			System.out.println("3. 사다리꼴");
			System.out.println("4. 종료");
			System.out.print("도형 선택 : ");
			menu = scan.nextInt();
			
			switch (menu) {
			case 1:
				System.out.println("**** 삼각형 넓이 ****");
				System.out.print("밑변 : ");
				int bottom = scan.nextInt();
				System.out.print("높이 : ");
				int height = scan.nextInt();
				shape = new Triangle(bottom, height);
				System.out.printf("넓이 : %.2f\n", shape.getArea());
				break;
				
			case 2:
				System.out.println("**** 사각형 넓이 ****");
				System.out.print("밑변 : ");
				bottom = scan.nextInt();
				System.out.print("높이 : ");
				height = scan.nextInt();
				shape = new Rectangle(bottom, height);
				System.out.printf("넓이 : %.2f\n", shape.getArea());
				break;
				
			case 3:
				System.out.println("**** 사다리꼴 넓이 ****");
				System.out.print("밑변 : ");
				bottom = scan.nextInt();
				System.out.print("윗변 : ");
				int top = scan.nextInt();
				System.out.print("높이 : ");
				height = scan.nextInt();
				shape = new Trapezoid(bottom, top, height);
				System.out.printf("넓이 : %.2f\n", shape.getArea());
				break;
			
			case 4:
				stop = true;
				System.out.println("이용해주셔서 감사합니다.");
				break;
				
			default:
				System.out.println("잘못된 번호를 입력하셨습니다.(1/2/3/4)번을 입력해주세요.");
				break;
			}
			
			
		}while(!stop);
	}

}
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

#### 2. 부모 클래스로 Person을 만들고 자식 클래스는 President, Student, Employee를 만들어서 다형성을 사용한다.

#### Person
> Person.java
```java
package Person;

public class Person {
	String name;
	int age;
	
	public void showSleepingStyel() {
		System.out.println("사람마다 잠 자는 방법은 모두 다르다.");
	}
	
}
```

#### President 클래스
> President.java

```java
package Person;

public class President extends Person{
	
	@Override
	public void showSleepingStyel() {
		System.out.println("대통령도 그냥 사람이다.");
	}
}
```

#### Stduent 클래스
> Studnet.java

```java
package Person;

public class Student extends Person{
	
	@Override
	public void showSleepingStyel() {
		System.out.println("학생도 사람이다~");
	}
}
```

#### Employee 클래스
> Employee.java

```java
package Person;

public class Employee extends Person{

	@Override
	public void showSleepingStyel() {
		// TODO Auto-generated method stub
		System.out.println("직장인도 자는 방법은 똑같다!");
	}
}
```

#### PersonEx 클래스에서 다형성을 메소드를 이용해서 사용, 배열을 이용해서 사용
> Personex.java

```java
package Person;

class  PersonInfo{
	// 인자로 전달된 사람의 잠자는 스타일을 출력해주는 메소드 정의!
	// 다형성을 이용하지 않으면 어떤 식으로 정의를 해야하는가?
	/*
	 * public void printSleepInfo(President president) {
	 * president.showSleepingStyel(); } public void printSleepInfo(Student student)
	 * { student.showSleepingStyel(); } public void printSleepInfo(Employee
	 * employee) { employee.showSleepingStyel(); }
	 */
	
	// 위와 같이 하면 코드의 중복성이 발생하고 객체를 여러 개를 생성해야한다는 낭비가 존재!
	// 그래서, 다형성을 사용하면 parameter 타입이 person으로 사용한다.
	public void printSleepingInfo(Person person) {
		person.showSleepingStyel();
	}
}

public class PersonEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonInfo pi = new PersonInfo();
		President presient = new President();
		Student student = new Student();
		Employee employee = new Employee();
		
		pi.printSleepingInfo(presient);
		pi.printSleepingInfo(student);
		pi.printSleepingInfo(employee);
		
		// 배열로도 저장이 가능하다.
		System.out.println();
		System.out.println("배열을 사용해서 출력하는 방법!!!");
		Person[] personArray = new Person[3];
		personArray[0] = presient;
		personArray[1] = student;
		personArray[2] = employee;
		
		
		for (int i = 0; i < personArray.length; i++) {
			personArray[i].showSleepingStyel();
		}
		
		
	}

}
```

#### 3. 가전제품 여러 종류로 주문하는 기능을 다형성 이용해서 처리하기.
https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html => Object 클래스 api document 
* Object 클래스의 toStirng() 메소드를 사용
   * 기존의 toString() 메소드를 재정의해서 사용하기.
   * Java에서 객체가 print 메소드의 인자로 지정되거나 "+" 지정되거나 연산이 되면 해당 객체의 toString() 메소드가 자동으로 호출이 된다.

> ParameterPolyTest2.java

```java
// 가전제품 여러 종류를 주문하는 기능을 다형성을 이용해서 처리하라.
package Person;

class Gajun{
	
}

class Tv extends Gajun{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Tv";
	}
}

class Computer extends Gajun{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Computer";
	}
}

class Radio extends Gajun{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Radio";
	}
}

// 제품을 구매하는 기능을 구현하는 클래스
class Buyer {
	// 제품을 구매하는 메소드를 정의하기.
	// 만약 다형성을 사용하지 않는다면?
	// method overloading 
	// 다형성을 이용하지 않는다면? 즉 가전제품이 수억개가 된다면 다 작성을 해줘야 한다.
	/*
	 * public void order(Tv tv) {
	 * 
	 * } public void order(Computer com) {
	 * 
	 * } public void order(Radio radio) {
	 * 
	 * }
	 */
	// 현재 주문한 제품의 목록을 보여주는 요구사항 추가!
	Gajun[] orderArray = new Gajun[3];
	int index = 0;
	
	public void order(Gajun gajun) {
		// Java에서 객체가 print 메소드의 인자로 지정되거나 "+" 
		// 연산이 되면 해당 객체의 toString() 메소드가 자동으로 호출된다. 
		System.out.println(gajun + " 을(를) 주문했습니다.");
		orderArray[index++] = gajun;
	}
	
	// Tv, Computer, Radio
	public void showOrderList() {
		String orderList = "";
		for (int i = 0; i < orderArray.length; i++) {
			orderList += (i == 0) ? orderArray[i] : "," + orderArray[i] ;
		}
		System.out.println("당신이 주문한 제품은 " + orderList + "입니다.");
	}
	
}


public class ParameterPolyTest2 {
	
	
	public static void main(String[] args) {
		
		Buyer buyer = new Buyer();
		buyer.order(new Tv());
		buyer.order(new Computer());
		buyer.order(new Radio());
		
		buyer.showOrderList();
		
		
		
	}

}
```

#### 4. Object 클래스
Object 클래스는 자바의 모든 클래스의 최상위 클래스이다.
모든 클래스들은 Object 클래스를 상속 받는다.
Object 클래스에 정의되어 있는 메소드 기능은 모든 클래스에서 사용 가능

#### 4.1 equals() 메소드
* equals 메소드
Object 에 정의되어 있는 equals 메소드는 단순히 == 의 역할을 하기 때문에 두 객체의 레퍼랜스 값이 같으면 true 다르면 false 를 반환해준다.

```java
// Object 클래스에서 제공하는 equlas() 메소드는 원래 기능이 == 이다. 
// 즉 reference 를 비교하는 것이다.
package Object;

class Student1 {
	String name;
	int idNumber;
	public Student1(String name, int idNumber) {
		super();
		this.name = name;
		this.idNumber = idNumber;
	}
	
	// 원하는 규칙으로 객체를 비교하려면 equals() 메소드를 규칙에 맞게 재정의해서 사용하면 된다.
	@Override
	public boolean equals(Object obj) {
		
		Student1 student = null;
		// instanceof 란? =>  obj 변수의 reference 타입을 Student1 타입으로 변환 할 수 있는가를 판단!
		if (obj  instanceof Student1) {
			student = (Student1)obj;
			
		}
		return student != null &&
			   name.equals(student.name) &&
			   idNumber == student.idNumber;
	}
}

public class EqualsTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student1 student1 = new Student1("오정원", 1510);
		Student1 student2 = new Student1("오정원", 1510);
		
		System.out.println("equlas() 메소드를 사용해서 비교 ");
		System.out.println("student1.equals(student2) : " + student1.equals(student2));
		
		
	}

}

```

#### 4.2 clone() 메소드

```java
// clone() : 자기자신 객체를 복제하는 메소드
// 메소드를 하나도 정의하지 않고 특정 기능을 사용할 수 있는지만 판단하는데 사용하는 인터페이스들
// 마커 인터페이스라고 함
// Cloneable은 clone() 메소드를 사용할 수 있느냐먄 판단하는 메소드임
package Object;

class Member implements Cloneable{
	// interface를 상속 받았는데 추상 메소드를 선언하지 않아도 빨간줄이 뜨지 않는다.
	// 왜? Cloneable interface는  추상 메소드가 존재하지 않기 때문이다.
	String name;
	int number;
	public Member(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		return super.clone();
	}
	
}

public class CloneTest {
	public static void main(String[] args) throws Exception{
		
		Member member1 = new Member("오정원", 1510);
		
		// throws 구문으로 있으면 try-catch 처리를 해줘야한다.
		Member member2 = (Member)member1.clone();
		
		System.out.println("member1.name = " + member1.name + ","
				+ " member1.number = " + member1.number);
		System.out.println("member2.name = " + member2.name + ","
				+ " member2.number = " + member2.number);
		
	}
}
```

#### 5. 자바 컬렉션 프레임워크 1
자바에서 얘기하는 Java Collections Framework는 객체들을 한 곳에 모아 관리하고 또 그것을 편하게 사용하기 위해 제공되는 환경이다.

#### 5.1 Set 인터페이스
Set내에 저장되는 객체들은 특별한 기준에 맞춰서 정렬되지 않는다.
그리고 저장되는 객체들간의 중복된 요소가 발생하지 못하도록 내부적으로 관리하고 있다.
* Set
   * HashSet : 일반적이 Set
   * TreeSet : HashSet + 정렬을 추가한 것!


#### 5.1.1 Collection의 특징
배열은 기본 타입의 데이터나 객체 타입의 데이터 모두 저장할 수 있었지만
컬렉션은 객체 타입만 저장할 수 있다.
배열은 크기가 한 번 정해지면 변경할 수 없지만,
컬렉션은 크기보다 더 많은 데이터를 저장하면 크기가 자동으로 늘어난다.

> HashSetTest.java
```java
// 배열은 기본 타입의 데이터나 객체 타입의 데이터 모두 저장할 수 있었지만
// 컬렉션은 객체 타입만 저장할 수 있다.
// 배열은 크기가 한 번 정해지면 변경할 수 없지만,
// 컬렉션은 크기보다 더 많은 데이터를 저장하면 크기가 자동으로 늘어난다.
package Object;

import java.util.HashSet;
import static java.lang.System.out;

public class HashSetTest {

	public static void main(String[] args) {
		
		String[] carArray = {"제네시스" ,"소나타", "K7", "벤츠", "아우디", "K7"};
		
		// carArray 가 HashSet에 들어가면?
		HashSet<String> hs1 = new HashSet<String>();
		HashSet<String> hs2 = new HashSet<String>();
		
		for (String car : carArray) {
			// 요소를 담는것을 실패하면 if문으로 들어간다. 즉 중복된 값이 존재할때 if문 안으로 들어간다.
			if(!hs1.add(car)) {
				// add : Collection 인터페이스에서 제공하는 메소드, 요소를 추가하는 메소드이다.
				// 요소가 정상적으로 담기면 true 반환
				// 중복되는 값은 hs2에 add한다.
				hs2.add(car);
			}
		}
		out.println("hs1 : " + hs1);
		out.println("hs2 : " + hs2);
		
		out.println("");
		out.println("삭제");
		hs1.remove("제네시스");
		out.println("hs1 : " + hs1);
		
		out.println("");
		out.println("hs2에 있는 K7을 제거한다.");
		hs1.removeAll(hs2);
		out.println("hs1 : " + hs1);
		
	}

}
```



