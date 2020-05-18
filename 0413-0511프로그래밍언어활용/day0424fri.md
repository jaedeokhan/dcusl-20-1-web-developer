### 2020 0424 FRI Day 11
1. class
2. 멤버변수 = 클래스 변수 = 전역변수
3. 메소드 => get, set
4. 생성자(constructor)
5. 자바 데이터 타입
   * 기본 타입=> stack area == 정적
   * 참조 타입=> heap area  == 동적
6. this : 메소드를 호출해서 객체가 저장되어있는 특성!
   * this.age = age; 라고 가정을 하면 객체의 멤버 변수에 값이 들어간다.

#### 1.1 객체를 사용하고, 객체의 참조를 끊는 방법!
객체의 참조를 끊는 방법은 ==> 객체 = null 을 집어넣어준다.
> Class.java
```java
package _0424FRI;

public class ClassEx {
	int age;
	int height;
	int weight;
	String name;

	int getAge() {
		return age;
	}

	void setAge(int age) {
		this.age = age;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}


	public static void main(String[] args) {
		ClassEx deok = new ClassEx();
//		deok = null;  // 객체의 참조를 끊는 방법!
		int age;
		deok.setAge(25);
		age = deok.getAge();
		System.out.println(age);

		String name;
		deok.setName("Han");
		name = deok.getName();
		System.out.println(name);
	}

}
```
#### 1.2 Java 의 표기법
Method는 Camel case
Variables 는 소문자로 대부분!
```java
// 변수 표기법
int Age;

// 상수 표기법
int AGE;
```

#### 1.3 this를 사용하는 이유는?
this는 객체 자기 자신의 필드를 가르키기 위해서 사용한다.
다르게 this.age = a 라고 한다면 age의 의미가 훼손이된다.

#### 1.4 Car2 class를 만들어서 사용하기.

```java


```

#### 1.5 overroading 이란?
메소드, 생성자를 데이터 타입, 매개변수의 개수에 따라서 사용하는 방법
1. 매개변수의 데이터 타입
2. 매개변수의 개수
3. 매개변수의 순서

> Constructor.java
```java
package _0424FRI;

public class ConstructorEx {
	// 메소드, 생성자 오버로딩(Overroading)
	// 생성자의 이름은 모두 같아야한다.
	// 구별되는 것은 매개변수의 타입 , 매개변수의 수, 
	ConstructorEx(){
		System.out.println("생성자 호출");
	}
	
	ConstructorEx(int a){
		System.out.println(a);
	}
	
	ConstructorEx(String a){
		System.out.println(a);
	}
	
	ConstructorEx(int a, String b){
		System.out.println(a + b);
	}
        ConstructorEx(String a, int b){
	System.out.println(a + b);
	}
	public static void main(String[] args) {
			int a = 10;
			String b = "두 번째 인자";
			new ConstructorEx(); // 생성자 호출
			new ConstructorEx(a); // 10
			new ConstructorEx(b); // 두 번째 인자
			new ConstructorEx(a, b); //10두 번째 인자
			new ConstructorEx(b, a); // 두 번재 인자10		
	}

}
```

#### 1.5.1 overroading의 주의사항은?
인자의 구분이 반드시 필요하다. 기계에게 정확하게 알려줘야한다.
overroad를 하면 default 생성자는 존재하지 않는다.

#### 1.6 this를 사용하는 방법 
1. 변수나 메소드 호출할때 사용! this.speed = speed; 
2. 생성자를 부를때 사용한다! this()
3. this() , super() => 생성자의 맨 위에 쓰일 수 있다. 

```java
package _0424FRI;

public class ConstructorEx {
	// 메소드, 생성자 오버로딩(Overroading)
	// 생성자의 이름은 모두 같아야한다.
	// 구별되는 것은 매개변수의 타입 , 매개변수의 수, 매개변수의 순서도 포함이 된다.
	
	// overroad시에 주의할 사항 : 오버로드를 하면 default 생성자는 존재하지 않는다.
	// this. super. 사용 
	// this(), super()
	ConstructorEx(){
		// 
		this(10);
		System.out.println("생성자 호출");
		// this(10); 밑에 생성자를 호출하는 this()가 있으면 실행이 되지 않는다.
	}
	
	ConstructorEx(int a){
		this("String");
		System.out.println(a);
	}
	
	ConstructorEx(String a){
		System.out.println(a);
	}
	
	ConstructorEx(int a, String b){
		System.out.println(a + b);
	}
	
	ConstructorEx(String a, int b){
		System.out.println(a + b);
	}
	public static void main(String[] args) {
			int a = 10;
			String b = "두 번째 인자";
			
			new ConstructorEx(); // 생성자 호출
			// 위에서 default 생성자에서 this(10); 을 먼저 호출에서 그 에 맞는 생성자로 두 번째 생성자로 가서 this("String")에서 1> String 출력 그 다음 2> 10 3> 생성자 호출 생성자의 안쪽으로 접근을 한다.
	}
}
```







