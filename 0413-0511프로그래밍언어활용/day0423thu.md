### 2020 0423 THU 
1. Class
2. Object

#### 1. 클래스란?
객체를 정의해 놓은 것 -> 객체를 생성하기위한 템플릿(template)
객체의 구성 및 생성에 쓰임.

#### 2. 객체란?
실생활에 존재하는 것들을 모두 표현함
속성(특성)과 기능으로 구성.

#### 2.1 객체지향이란?
객체지향이란 : 사물이란 개념을 "속성과 기능을 가진 객체"로 모델링하고, 이들간의 관계를 정의함. (Self-contained Component 스스로 존재하는 자)

#### 2.2 자바에서의 객체지향 3단계
* 자바에서의 객체지향 3단계
   * 1) 객체 모델링 - 프로그래밍 하고자 하는 객체의 속성과 필요한 기능을 정리한다.
   * 2) 클래스 정의 - 객체를 실제로 사용하기 위해서, 클래스라는 형태로 객체를 표현한다.
   * 3) 객체 생성과 사용 - 정의된 클래스를 이용해서, 메모리상에서 객체(Object)를 생성하고, 사용한다.

#### 2.3 생성자(constructor)
객체지향 프로그래밍(OOP)에 쓰이는 객체 초기화 함수
객체의 생성 시에만 호출되어 메모리 생성과 동시에 객체의 데이터를 초기화하는 역할을 함.
자바는 c와는 달리 소멸자가 없다. 소멸자가 없으면 메모리 회수를 하지 못한다. 이것을 어떻게 극복하나?
Java에서 소멸자란? == garbage collection
1. 1) 생성자의 이름은 클래스의 이름과 동일하게 사용.
2. 2) 오버로딩 (overlooding) 가능함.
3. 3) 반환형을 사용하지 않음.
4. 4) 기본 생성자의 개념이 존재함.
5. 5) 멤버 변수 초기화 역할을 가짐.

> 클래스를 사용하는 방법
```java
 - 클래스 표현 방법
 [접근제어자] class 클래스명 {
 	변수, 생성자, 메소드를 정의함.
 	}
```

#### 2.4 객체를 표현하는 방법
```java
// - 객체  표현하는 방법 :
//  [참조형] 참조 변수명 = new [클래스명]();
// new라는게 메모리를 사용한다는 개념이랑 비슷하다.
```

#### 2.5 변수의 메모리 사용
* 기본 타입 변수 - 실제 값을 변수 안에 저장 = stack area = static
* 참조 타입 변수 - 주소를 통해 객체 참조= heap area = dynamic


#### 2.6 Car class => 생성자, 메소드, 객체 사용 방법
```java
package _0423THU;

public class Car {
	// field , 멤버 변수
	// speed , wheel, color 
	String company;
	String color;
	int speed;
	int wheel;
	
	// 생성자
	Car(){
		company = "현대자동차";
		color = "red";
		speed = 40;
		wheel  = 4;
	}
	
	// wheel 개수로 오토바이, 자동차 그 외 구분하기하는 메소드 
	void vehicleCheck(int wheel) {
		if (wheel <= 2) {
			System.out.println("wheel " + wheel + "개인 오토바이입니다.");
		}
		else if(wheel == 4) {
			System.out.println("wheel " + wheel + "개인 자동차입니다.");
		}
		else {
			System.out.println("wheel " + wheel + "개의 속성은 알 수 없습니다.");
		}
	}
	// 인자 set을 받아서 wheel 개수를 초기화하는 함수
	void wheelSet(int set) {
		wheel = set;
	}
	
	// print Company, Color
	void printCompany() {
		System.out.println(company);
	}
	
	void printColor(){
		System.out.println(color);
	}
	
	// speed 를 계속해서 5를 추가, 75이상이 되면 고속도로 진입하므로 100까지 증가
	void speedUp() {
		speed += 5;
		System.out.println("현재 속도는 " + speed + "입니다.");
		if (speed >= 75) {
			System.out.println("고속도로에 진입했습니다.");
			for (int i = 1, j =5; i <= 5; i++) {
					speed += j;
					System.out.println("악셀 : 현재 속도는" + speed + "입니다.");
			}
		}
	}
	
	// speed 를 
	void speedDown() {
		System.out.println("현재 속도는" + speed + "입니다.");
		if (speed >= 100) {
			System.out.println("현재 안전구역 운행중입니다. 속도를  50이하으로 낮춰주세요.");
			for (int i = 1, j = 10; i <= 5; i++) {
					speed -= j;
					System.out.println("브레이크 : 현재 속도는 " + speed + "입니다.");
			}
		}
	}
	public static void main(String[] args) {
		
		Car car = new Car();
		
		// company, color 확인하기.
		car.printCompany();
		car.printColor();
		System.out.println();
		
		// wheel 개수를 vehicleCheck 메소드로 체크하고, wheelSet(인자)메소드로 값 초기화하고 다시 wheel 체크
 		car.vehicleCheck(car.wheel);
		car.wheelSet(2);
		car.vehicleCheck(car.wheel);
		car.wheelSet(5);
		car.vehicleCheck(car.wheel);
		System.out.println();
		
		// speed 45 => 75 up
		car.speedUp();
		car.speedUp();
		car.speedUp();
		car.speedUp();
		car.speedUp();
		car.speedUp();
		System.out.println();
		
		// 고속도로 진입하면 speed 75 => 100 up
		car.speedUp();
		System.out.println();
		
		// 안전구역진입 speed 100 => 45 down
		car.speedDown();
	}

}

```

