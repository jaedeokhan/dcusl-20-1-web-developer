### 2020 0509 SAT

#### 1. WorkShop1 
기본급과 시간 수당을 입력받아 실수령액을 구하시요.
기본급(pay) : 1500000
시간수당(o_time) : 55000

조건>
세금(tax) : 기본급의 10%

일때  실수령액(t_pay)를 구하시오.
(실수령액 = 기본급 + 시간수당 - 세금)
--출력--
실수령액 : 1405000원
> my
```java
package WorkShop1;

import java.util.Scanner;

public class WorkShop1 {

	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int pay = 0;
		int o_time = 0;
		int tax = 0;
		int realestate = 0;
		System.out.print("기본급을 입력하세요 : ");
		pay = scan.nextInt();
		System.out.print("시간수당을 입력하세요 : ");
		o_time = scan.nextInt();
		tax = pay / 10;
		
		realestate = pay + o_time - tax;
		System.out.println("실수령액 : " + realestate + "원");
	}

}
```

#### 2. WorkShop2 
각 변수의 값을 Scanner 로 받아 출력하시요.
(변수명 :name,department,position,pay)
입력
이름 : 홍길동    
부서 : 자재부     
직위 : 대리       
급여 : 1500000원

출력
이름 : 홍길동    
부서 : 자재부     
직위 : 대리       
급여 : 1500000원

```java
package WorkShop1;

import java.util.Scanner;

public class WorkShop2 {

	/*
	 * public void proWorkShop2() { Scanner scan = new Scanner(System.in);
	 * System.out.print("이름 : "); String name = scan.next();
	 * System.out.print("부서 : "); String department = scan.next();
	 * System.out.print("직위 : "); String position = scan.next();
	 * System.out.print("급여 : "); int pay = scan.nextInt();
	 * 
	 * System.out.println("이름 : " + name); System.out.println("부서 : " + department);
	 * System.out.println("직위 : " + position); System.out.println("이름 : " + pay); }
	 */
	
	public static void main(String[] args) {
		String name = "";
		String department = "";
		String position = "";
		int pay = 0;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("이름을 입력하세요  : ");
		name = scan.nextLine();
		System.out.print("부서를 입력하세요 : ");
		department = scan.nextLine();
		System.out.print("직위를 입력하세요 : ");
		position = scan.nextLine();
		System.out.print("급여를 입력하세요 : ");
		pay = scan.nextInt();
		
		System.out.println("이름 : " + name);
		System.out.println("부서 : " + department);
		System.out.println("직위 : " + position);
		System.out.println("급여 : " + pay + "원");
	}

}
```

#### 3. WorkShop3 => 성적 계산 프로그램
입력받아 계산하시오
    Input name : 오정원 
    kor : 90 
    eng : 70 
    mat : 75

   <출력>
    이름 : 오정원
    total점수 : 235점
    avg점수 :  78.3   <-- 소수 1자리까지

```java
package WorkShop1;

import java.util.Scanner;

public class WorkShop3 {
	
	/*
	 * public void proWorkShop3() { Scanner scan = new Scanner(System.in);
	 * System.out.print("Input name : "); String name = scan.next();
	 * System.out.print("kor : "); int kor = scan.nextInt();
	 * System.out.print("eng : "); int eng = scan.nextInt();
	 * System.out.print("mat : "); int mat = scan.nextInt();
	 * 
	 * // 연산 int total = (kor + eng + mat); // 피연산자 중에 하나를 double 값으로 변경을 해준다. 3을
	 * 3.0으로 변경해줘도 상관은 없다. double avg = (double)total / 3;
	 * 
	 * // 출력 System.out.println("total 점수 : " + total + "점");
	 * System.out.printf("avg 점수 : %.1f", avg); }
	 */
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String name = "";
		int kor = 0;
		int eng = 0;
		int mat = 0;
		int total = 0;
		double avg = 0.0;
		
		System.out.print("input name : ");
		name = scan.nextLine();
		System.out.print("kor : ");
		kor = scan.nextInt();
		System.out.print("eng : ");
		eng = scan.nextInt();
		System.out.print("mat : ");
		mat = scan.nextInt();
		
		total = (kor + eng + mat);
		avg = (double)total / 3;
		
		System.out.println("이름 : " + name);
		System.out.println("total 점수 : " + total + "점");
		System.out.printf("avg점수 : %.1f", avg);

	}

}
```

#### 4. WorkShop4 => 삼각형의 넓이를 구하는 문제
* 계산을 하기전에 캐스팅을 잘해줘야한다!
입력받아 계산하시오
1.**** 삼각형의 넓이 구하기  ****
밑변 :  10  <---삼각형넓이 = (밑변 * 높이)/2
높이 :   3
결과 출력:
넓이 :   ?  <--- 실수형으로 구하기(형변환)

```java
package WorkShop1;

import java.util.Scanner;

public class WorkShop4 {
	
	/*
	 * public void proWorkShop4() { Scanner scan = new Scanner(System.in);
	 * System.out.println("**** 삼각형의 넓이 구하기  ****"); System.out.print("밑변 : "); int
	 * base = scan.nextInt(); System.out.print("높이 : "); int height =
	 * scan.nextInt();
	 * 
	 * // 연산 => 우측에 연산시의 값이 double이어야 한다. 아니면 값이 다 잘린다! double dimension =
	 * (double)(base * height) / 2;
	 * 
	 * // 출력 System.out.println("넓이 : " + dimension);
	 * 
	 * }
	 */
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int base = 0;
		int height = 0;
		
		System.out.print("밑변 : ");
		base = scan.nextInt();
		System.out.print("높이 : ");
		height = scan.nextInt();
		
		double dimension = (double)(base * height) / 2;
		
		System.out.println("넓이 : " + dimension);
		
	}

}
```

#### 5. WorkShop5 => 큰 수 찾기.
Scanner 로 입력 받는다.
Input a : 5
Input b : 13
큰수 : 13 
```java
package WorkShop1;

import java.util.Scanner;

public class WorkShop5 {
	public void proWorkShop5() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Input a : ");
		int a = scan.nextInt();
		System.out.print("Input b : ");
		int b = scan.nextInt();
		
		// 삼항 연산자에서 조건문 안에 또 조건문 넣어주기.
		System.out.println((a > b) ? "a가 크다 " : ( b > a) ? "b가 크다" : "같다");
	}
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int a = 0;
		int b = 0;
		int max = 0;
		
		System.out.print("Input a : ");
		a = scan.nextInt();
		System.out.print("Input b : ");
		b = scan.nextInt();
		
		max = (a > b) ? a : b;
		
		System.out.println("큰 수  : " + max);
	}
}
```

#### 6. WorkShop6 => 0보다 작거나 100보다 큰 수 류 나오게 하기!
값을 입력받아 0보다 작거나 100보다 크면 "입력오류!!"  출력
(  || 연산자 , if문 이용)
점수 : 120
입력오류!!

점수 : 55
정상처리됨!!

```java
package WorkShop1;

import java.util.Scanner;

public class WorkShop6 {
	/*
	 * public void proWorkShop6() { Scanner scan = new Scanner(System.in);
	 * System.out.print("점수 : "); int score = scan.nextInt();
	 * 
	 * System.out.println((score < 0 || score > 100) ? "입력오류!!" : "정상처리됨!!");
	 * 
	 * }
	 */
	public static void main(String[] args) {
		
		// ctrl + shift + o  : 해당 객체의 import 문을 자동 생성
		Scanner scan = new Scanner(System.in);
		int score = 0;
		String str = "";
		
		System.out.print("점수 : ");
		score = scan.nextInt();
		str = (score < 0 || score > 100) ? "입력오류!!" : "정상처리됨!!";
		System.out.println(str);
		
		
	}

}
```

#### 7. WorkShop7 => 홀수, 짝수 구분하는 프로그램
7. 짝수/홀수알아보기 (파일명 : WorkShop7.java)
( %연산자, if이용)

Scanner 로 숫자를 입력 받는다.
number : 243

결과 출력
홀수다!!

```java
package WorkShop1;

import java.util.Scanner;

public class WorkShop7 {

	public void proWorkShop7() {
		Scanner scan = new Scanner(System.in);
		System.out.print("number : ");
		int number = scan.nextInt();
		
		System.out.println((number % 2 == 0) ? "짝수다!!" : "홀수다!!");
		
	}
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int number = 0;
		String str = "";
		
		System.out.print("number : ");
		number  = scan.nextInt();
		str = (number % 2 == 0) ? "짝수다!!" : "홀수다!!";
		
		System.out.println(str);
		

	}

}
```

#### 8. WorkShop8 => bucket에는 10개를 담을 수 있다. 바구니에 몇 번 담을 수 있는지?
* 이러한 bucket 관련된 알고리즘은 페이징에 자주 쓴다.
* 한 페이지에 총 10개를 쓸 수 있는데, 그러면 페이지를 몇 개를 만들어야 하는가?와 동일하다.
1번:아래의 코드는 사과를 담는데 필요한 바구니(버켓)의 수를 구하는 코드이다. 만일
사과의 수가 123개이고 하나의 바구니에는 10개의 사과를 담을 수 있다면, 13개의 바구니
가 필요할 것이다. (1)에 알맞은 코드를 넣으시오.
```java
package WorkShop2;

import java.util.Scanner;

public class WorkShop8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int numOfApples = 123;
		int sizeOfBucket = 10;
		int numOfBucket = (numOfApples % sizeOfBucket == 0) ?
		(numOfApples / sizeOfBucket) : (numOfApples / sizeOfBucket) + 1;
		// int numberOfBucket = ( numOfApples / sizeOfBucket) + ((numOfApples % sizeOfbucket == 0 ) ? 0 : 1);

		System.out.println("필요한 바구니의 수  : " +  numOfBucket);
	}

}
```

#### 9. WorkShop9 => num 이 양수, 음수, 0인지 체크하는 프로그램
> mycode 

```java
/*
2번 : num 의 값에 따라 양수, 음수, 0을 출력하는 코드를 작성

 */
package WorkShop2;

import java.util.Scanner;

public class numJudge {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int num = 0;
		
		System.out.print("num 입력  :");
		num = scan.nextInt();
		
		if (num > 0) {
			System.out.println("양수");
		}
		else if (num < 0){
			System.out.println("음수");
		}
		else {
			System.out.println("0");
		}
		
		
	}

}
```

> professor
```java
package WorkShop2;

import java.util.Scanner;

public class WorkShop9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.print("num : ");
		int num = scan.nextInt();
		
		System.out.println((num > 0) ? "양수" : (num < 0) ? "음수" : "0");
	}

}
```

#### 10. WorkShop10 => 456 이라는 정수를 100의 자리인 400만 나오게 하기
```java
package WorkShop2;

public class WorkShop10 {

	public static void main(String[] args) {
		
		int num = 456;
		System.out.println(num - (num % 100));
		System.out.println((num / 100) * 100);
		
	}

}
```

#### 11. WorkShop11 => char 형인 변수가 영문자, 숫자인 경우 true 판단
11번
ch 값이 영문자이거나 숫자일 경우만 b의 값이 true 가 출력되게 코드를 작성하세요.
각 문자는 코드값 :
```java
// 모든 문자는 문자코드 값을 가진다.
// 자바에서 int 타입보다 작은 정수 값으로 연산하면 프로모션 캐스팅이 된다.
// 즉, 무조건 int 타입으로 char , byte가 바뀐다.
package WorkShop2;

public class WorkShop11 {

	public static void main(String[] args) {
		
//		char c = 'A';
//		System.out.println(c + 1); // 66 
		char ch = 'z';
		boolean b = (ch >= 'A' && ch <= 'Z' ||
				     ch >= 'a' && ch <= 'z' ||
				     ch >= '0' && ch <= '9' ? true : false);
		
		System.out.println(ch + " = " + b);
		
		
	}

}
```

#### 12. WorkShop12 => 4개의 값을 입력 받아 성별 판단하기.
5.
4개의 값을 입력받아 처리하기.
input name : 장윤정
input gender : F
input age : 25
input tall : 173.3
(성별 : M이면 "남자", 나머지 "여자" - 삼항 연산자 이용
```java
package WorkShop2;

import java.util.Scanner;

public class WorkShop12 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Input name : ");
		String name = scan.next();
		System.out.print("Input gender : ");
		String gender = scan.next();
		System.out.print("Input age : ");
		int age = scan.nextInt();
		System.out.print("Input tall : ");
		double tall = scan.nextDouble();
		
		String sex = (gender.equals("M") ? "남자" : "여자");
		
		System.out.println("이름 : " + name);
		System.out.println("성별 : " + sex);	
		System.out.println("나이 : " + age);
		System.out.println("키 : " + tall);
	}

}
```

#### 13. WorkShop13 => 주사위 두 개를 던져서 6이 되는 모든 경우의 수가 몇개인가?
문제 13 :
두 개의 주사위를 던졌을 때 두 수의 합이 6이 되는 모든 경우가 몇 개인지 계산하는 코드를 생성하시요.

```java
package WorkShop2;

public class WorkShop13 {

	public static void main(String[] args) {

		int count = 0;
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				if((i + j) == 6)
					count++;
			}
		}
		System.out.println("두 수의 합이 6이 되는 경우는 : " + count + " 번 입니다.");


	}
}
```

#### 14. WorkShop14 => 

```java
/*
 * 1번. Workshop1
부모클래스 : Entry
 * 	     필드명 : String word;
 * 	     메소드 : Entry(); //****약어사전 *****(출력)
 * 		    Entry(String w);  /Entry()호출
 * 		    public void writeView(); // 약어출력
 * 
 자식클래스 : SubClass (Entry를 상속받음)
 * 	     필드명 : String definition;
 * 		 int year;
 * 	    생성자: SubClass(String w); // Entry(String w)호출
 * 	              SubClass(String w, String d, int y); // Subclass(String w)호출
 * 	              public void printView(); //원어. 시기출력
 * 
 * 3. main에서
 * 		SubClass pp = new SubClass("OOP","Object Oriented Programming", 1991);
 * 4. 출력
 * 
 * *****약어사전******
 * 약어 : OOP
 * 원어 : Object Oriented Programming
 * 시기 : 1991년
 */
```

```java
package WorkShop3;

class Entry1{
	String word;

	public Entry1(){
		System.out.println("**** 약어사전 ****");
	}

	Entry1(String w){
		this();
		this.word = w;
		writeView1();
	}

	public void writeView1() {
		// 약어출력
		System.out.println("약어 : " + word);
	}
}

class SubClass1 extends Entry1{
	String defintion;
	int year;

	SubClass1(String w){
		super(w);
	}
	// this : 자기자신 객체를 가리키는 참조변수
	// 자기 자신 클래스내에서 변수나 메소드를 호출하면 기본적으로 앞에 this가 지정됨
	// 같은 클래스내에서는 this를 잘 사용하지 않는다.
	// 단, 파리미터 변수 이름과 멤버 변수 이름이 동일할 때는 파라미터 변수가 우선 인식이 된다.
	// 이 경우 멤버변수 값을 초기화 하려면 멤버변수 앞에 this.를 붙여야 한다.
	SubClass1(String w, String d, int y){
		this(w);
		this.defintion = d;
		this.year = y;
		printView1();
	}

	public void printView1() {
		//원어. 시기출력
		System.out.println("원어 : " + defintion);
		System.out.println("약어 : " + year);
	}
}



public class WorkShop14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubClass1 pp = new SubClass1("OOP","Object Oriented Programming", 1991);

	}

}
```

#### 15. WorkShop15 => 
2번. Workshop2
1. Super클래스명 : SalarySuper
필드 :  String name(이름)
        int salary (급여)
생성자 : SalarySuper(String n, int s) 를 넘겨받아 name, salary에 대입
메소드 : getInfomation() - 이름과 연봉을 출력

2. Sub클래스(SalaySub) 에서 SalarySuper를 상속받음
필드 : String department (부서)
생성자 : SalarySub(String name, int salary, String department)를 넘겨받아
         name, salary,department에 대입(필요하면 부모 생성자 호출)
메소드 : getInfomation() – 이름,연봉,부서출력

main 메소드 : 
SalarySub  ob = new SalarySub(“오정원",15000000,“프리렌서");

<출력>
이름 : 오정원
급여 : 15000000
부서 : 프리렌서

java에서는 get이라고 들어간 함수들은 return값이 보통 주어진다.
여기서 getInformation() 은 이름을 print로 바꿔준다.

```java
package WorkShop3;

class SalarySuper1{
	String name;
	int salary;
	
	SalarySuper1(String n, int s){
		name = n;
		salary = s;
	}
	
	void printInformation() {
		System.out.println("이름 : " + name);
		System.out.println("급여 : " + salary);
	}
}

class SalarySub1 extends SalarySuper1{
	String department;
	SalarySub1(String n, int s) {
		super(n, s);
	}
	
	SalarySub1(String name, int salary, String department){
		super(name, salary);
		this.department = department;
		printInformation();
	}
	
	void printInformation() {
		System.out.println("이름 : " + name);
		System.out.println("급여 : " + salary);
		System.out.println("부서 : " + department);
	}
	
	
}

public class WorkShop15 {
	public static void main(String[] args) {
		SalarySub1  ob = new SalarySub1("오정원",15000000,"프리렌서");

	}

}
```

#### 16. WorkShop16 => 
3번. Workshop3
생성자 함수 overloading이용

main()에서
Workshop4 ob1 = new Workshop4(10,20);
Workshop4 ob2 = new Workshop4(12.4, 9.45);
Workshop4 ob3 = new Workshop4('Z', 'p');

계산과 출력은 생성자에서 할것

<출력> 
max = 20
max = 12.4
max = p

```java
package WorkShop3;

public class WorkShop16 {
	
	WorkShop16(int a, int  b){
		System.out.println((a > b) ? "max = " + (a) : "max = " + (b));
	}
	
	WorkShop16(double a, double b){
		System.out.println((a > b) ? "max = " + (a) : "max = " + (b));
	}
	
	WorkShop16(char a, char b){
		System.out.println((a > b) ? "max = " + (a) : "max = " + (b));
	}
	public static void main(String[] args) {
		
		WorkShop16 ob1 = new WorkShop16(10,20);
		WorkShop16 ob2 = new WorkShop16(12.4, 9.45);
		WorkShop16 ob3 = new WorkShop16('Z', 'p');
	}

}
```

#### 17. WorkShop17 => Shape.java를 추상 클래스로 만든 후 Rectangle, Triangle, Trapezoid(사다리꼴) 클래스 들이 Shape 추상 클래스를 상속 받아서 사용한다.

> Shape.java
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
#### Rectangle 은 Shape 추상 클래스를 상속 받아서 넓이를 구한다.
> Rectangle.java
```java
package Shape;

import java.util.Scanner;

public class Rectangle extends Shape{
        public static Scanner scan = new Scanner(System.in);
        // 자식 클래스를 생성하기전에 부모 클래스를 먼저 생성을 해줘야한다.
        // 빈 생성자를 찾지 못해서 에러가 나기 때문에, 정의한 생성자를 따로 호출
을 해줘야 한다.
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

#### Triangle Class도 마찬가지로 Shape 추상 클래스를 상속 받아서 넓이를 구한다.
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

#### Trapezoid도 마찬가지로 Shape라는 추상 클래스를 상속 받아서 사용한다.
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

#### ShapeEx는 위의 세 가지 도형들을 다형성을 이용해서 출력을 한다.
```java

```

#### 18. WorkShop18 => 성별, 신장, 체중, 비만도 출력하기.
1. Super클래스명 : HealthSuper
필드명(protected) : char gender(성별)
                    double tall (신장)
                    double weight(체중)
메소드 : input() - 성별, 신장, 체중입력받기
           output1() - 출력

2. Sub클래스 : HealthChild
필드명(private) : double s_weight (표준체중)
                  double fat (비만도)
                  String result (결과값)
메소드 : calculate() - 비만도 계산
           output2()  - 출력

=======================================================
★표준체중계산법★
남성 : 표준체중 = (신장-100)*0.9
여성 : 표준체중 = (신장-100)*0.85

비만도 = 현재체중(Kg)/표준체중(Kg)*100
-------------------------------------------------------
90%이하  : 저체중               121-130% : 경도비만
91 -110% : 정상(표준체중)       131-150% : 중도비만
111-120% : 과체중               150%     : 고도비만

3. 출력폼

***** 비만도 Check *****
성별(M/F) : M          <---남(M) / 여(F)
신장(Cm) : 175.3
체중(Kg)  : 95.45

성별 - M 
신장 - 175.3Cm
체중 - 95.45Kg

당신은 비만도 ?.??이고,  중도비만 입니다.

```java
package WorkShop4;

import java.util.Scanner;

class HealthSuper1{
	char gender;
	double tall;
	double weight;
	
	void input() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***** 비만도 Check *****");
		System.out.print("성별(M/F) : ");
		gender = scan.next().charAt(0);
		System.out.print("신장(Cm) : ");
		tall = scan.nextDouble();
		System.out.print("체중(Kg) : ");
		weight = scan.nextDouble();
		
	}
	
	public void output() {
		System.out.println("성별 : " + gender);
		System.out.println("신장 : " + tall );
		System.out.println("체중 : " + weight);
	}
}

class HealthChild1 extends HealthSuper1{
	private double s_weight;
	private double fat;
	private String result;
	
	public void calculate(){

		s_weight = (gender == 'M') ? (tall - 100) * 0.9 : (tall - 100) * 0.85;
		fat = ((weight / s_weight) * 100);

		
		if (fat > 150) {
			result = "고도비만";
		}
		else if (fat >= 131 && fat <= 150) {
			result = "중도비만";
		}
		else if (fat >= 121 && fat <= 130) {
			result = "경도비만";
		}
		else if (fat >= 111 && fat <= 120) {
			result = "과체중";
		}
		else if (fat >= 91 && fat <= 110) {
			result = "정상(표준체중)";
		}
		else {
			result = "저체중";
		}
	}
	
	public void output2() {
		System.out.printf("당신의 비만도는 %.2f%%이고, %s입니다.", fat, result);
	}
}
public class WorkShop18 {

	public static void main(String[] args) {
		HealthChild1 hc1 = new HealthChild1();
		hc1.input();
		hc1.output();
		
		hc1.calculate();
		hc1.output2();

	}

}
```



