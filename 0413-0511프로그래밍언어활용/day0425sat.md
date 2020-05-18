### 2020 0425 SAT Day 12
1. Package
2. Encapsulation == 캡슐화
3. 접근 제어자(Access modifiers)
   - 객체의 사용을 제어하기 위한 제어자
   - 종류 : private, default(friendly), protected, public
4. Java refer class => Interger.parseInt
5. NumberFormatException
6. charAt() method


```java
package app.b.c; // 패키지 추가 작성

public class HelloJava {

        public static void main(String[] args) {

                System.out.println("Hello Java");
                System.out.println("JaedeokHan");

        }

}
```


#### 1.1 JAVA 오류 ㅣ 기본 클래스를 찾거나 로드할 수 없습니다.
   * JAVA 프로그램에서는 file class name을 가지고 클래스 파일들을 찾기 때문에 상위 디렉토리 또는 패키지의 루트 디렉토리에서 실행해야한다.
   * 즉, Java명령은 패키지의 parent 디렉터리 위치에서 실행해야한다.
package app.b.c를 해놓으면 cmd에서 compile을 한다면? 저 패키지안에 HelloJava.class가생성이 된다.

```cmd
// compile
$ javac -d HelloJava.java


// execute
$ java app.b.c.HelloJava
```


#### 1.2 접근 제어자 표현 방법

```java
// 클래스 표현 형식)
// [접근 지정자] class 클래스명
// 메서드 표현 형식)
// [접근 지정자] [자료형] 메서드 명(인자들)
```

#### 1.3 Calculator class 만들고, args를 사용하기.

```java
package _0425SAT_Package;

public class Calculator {
	
	int a;
	int b;
	char operation;
	
	void setNumber(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	int  getPlus() {
		return this.a + this.b;
	}
	
	int getMinus() {
		return this.a - this.b;
	}
	
	int getMultiple() {
		return this.a * this.b;
	}
	
	int getDivision() {
		return this.a / this.b;
	}
	
	void result(char operation, int a, int b) {
		int result;
		switch(operation) {
		case '+':
			result = getPlus();
			System.out.println(result);
			break;
		case '-':
			result = getMinus();
			System.out.println(result);
			break;
		case '*':
			result = getMultiple();
			System.out.println(result);
			break;
		case '/':
			result = getDivision();
			System.out.println(result);
			break;
		}
	}
	
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		int plus;
		int minus;
		int multiple;
		int division;
		
		cal.setNumber(10, 5);
		plus = cal.getPlus();
		minus = cal.getMinus();
		multiple = cal.getMultiple();
		division = cal.getDivision();
		
		System.out.println("plus     : " + cal.a + " + " + cal.b + " = " + plus);
		System.out.println("minus    : " + cal.a + " - " + cal.b + " = " + minus);
		System.out.println("multiple : " + cal.a + " * " + cal.b + " = " + multiple);
		System.out.println("division : " + cal.a + " / " + cal.b + " = " + division);
		
		int a = 10, b = 5;
		cal.result('+', a, b);
		cal.result('-', a, b);
		cal.result('*', a, b);
		cal.result('/', a, b);
		
		
		// refer class 
		int n1 = Integer.parseInt(args[0]); // 5
		int n2 = Integer.parseInt(args[1]); // 10
		char oper = args[2].charAt(0); // + 
		cal.result(oper, n1, n2); // 15	
	}
}
```

#### 1.4 Student 점수 산출하기 만들기
```java
package _0425SAT_Package;

import java.util.Scanner;

public class Student {
	java.util.Scanner scan;
	// 생성자를 줘서 외부에서 객체를 생성할 수 있게 해준다.
	public Student() {
		 scan = new java.util.Scanner(System.in);
//		Scanner scan = new Scanner(System.in);
		
	}
	public void menu() {
		boolean run = true;
		int [] students = null;
		int scores = 0;
		int studentsNum = 0;
		int max = 0;
		int min = 0;
		int sum = 0;
		double avg;
		
		do {
			System.out.println("-----------------------------------------");
			System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
			System.out.println("-----------------------------------------");
			System.out.print("선택 > ");
			int selectNo = scan.nextInt();
			
			if ( selectNo == 1 ) {
				System.out.print("학생 수 : ");
				// 1. 학생 수 입력
				studentsNum = scan.nextInt();
				students = new int[studentsNum];
				
			}
			else if ( selectNo == 2 ) {
				// 2. 학생 수 만큼 각 점수 입력
				for (int i = 0; i < students.length; i++) {
						System.out.print("점수 입력 : ");
						scores = scan.nextInt();
						students[i] = scores;
				}
			}
			else if ( selectNo == 3 ) {
				// 3. 학생 전체 점수리스트 출력
				System.out.print("점수 리스트 : ");
//				for (int i = 0; i < students.length; i++) {
//					System.out.print(students[i] + " ");
//				}
				for ( int s : students) {
					System.out.print(s + " ");
				}
				System.out.println();	
				
			}
			else if ( selectNo == 4 ) {
				// 4. 분석 ( max, min , avg)
				max = students[0];
				min = students[0];
				System.out.println("===분석결과 === ");
				for (int i = 0; i < students.length; i++) {
					max = (max < students[i] ) ? students[i] : max;
					min = (min > students[i] ) ? students[i] : min; 
//					if (max < students[i]) {
//						max = students[i];
//					}
//					if (min > students[i]) {
//						min = students[i];
//					}
					sum += students[i];
				}
				avg = (double) sum / students.length;
				System.out.println("최대값 : " + max);
				System.out.println("최소값 : " + min);
				System.out.printf(" 평균      : %.2f\n", avg);
			}
			else if ( selectNo == 5 ) {
				System.out.println("이용해주셔서 감사합니다.");
				run = false;
			}
		}while(run);
	}
	
	public static void main(String[] args) {
		/*
		 객체 선언 => 사용
		 */
		Student studentstart = new Student();
		studentstart.menu();
	}

}
```

#### 1.5 JavaBean이란?
자바빈 패턴은 인자없는 생성자를 호출하여 객체부터 만든 다음, 설정 메서드(setter)들을 호출하여 필수, 선택 필드의 값을 채우는 방식이다.

> Member.java => 정보화 객체!!

```java
package _0426SAT;

public class Member {

	private String name;
	private String id;
	private String pw;

	public Member(String name, String id, String pw) {
		this.name = name;
		this.id = id;
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}

```

#### 1.5.1 MemberService.java 는 Merber 객체의 생성자를 사용해서 login() 기능 구현
* 대상문자열.equals(비교문자열)
   * equals()는 String 끼리의 문자열이 동일한 지 확인할 때 사용한다.
* contentEquals()는 String과 다른 객체와의 문자열이 동일한 지를 확인할 때도 사요잉 가능하다는것이 핵심이다.

```java
package _0426SAT;

public class MemberService {

	public Member member;
	public MemberService() {
		member = new Member("한재덕", "deok", "1234");
	}
	
	public boolean login(String id, String pw) {
		boolean result = false;
		if (id.contentEquals(member.getId()) && pw.contentEquals(member.getPw())) {
			System.out.println(member.getName() + "님 로그인 성공!");
			result = true; 
		}
		return result;
		
	}

}
```

#### 1.5.2 홈페이지마다 보안 정책에 따라, id가 틀립니다. passwd가 틀립니다. 사이트마다 처리를 하는게 다르다.
* 분기문을 나누었나? 나누지 않았나? 이것이 차이다. 
* if문으로 id와 passwd를 동시에 물어보는게 id와 passwd가 틀립니다.
* id, passwd를 두 분기문으로 나누면 id를 틀렸다고 나오면 id를 추측이 가능하니 그러한 보안 문제로 두 개를 동시에 처리하는 곳이 있다.


