### 2020 0422 wed.
### Method
### 1. 메소드(Method)
* [접근 제어자] [수정자] [반환형] 메소드명 (인자들){ }
#### 1.1 접근 제어자(Modifiers)
private, default(friendly), protected, public
		 
#### 1.2 기타 제어자 
static, final, abstract, native '''
		 
#### 1.3 반환형(return type)
- 자바 데이터 자료형(기본형, 참조형) 전부 사용할 수 있음.
- void: 반환형이 없는 메소드 정의시 사용.
		 
#### 1.4 메소드명 : 식별자로 임의의 이름 정의.
		 
#### 1.5 인자(Arguments)
매개 변수라고 하며 메소드 호출 시 데이터를 전달 하기 위한 용도로 사용함.

> 메소드 표현 방법 - 1
```java
// 반환형이 있는 타입의 메소드=> 자료형 + return
int intA(){
	구현;
	return n; // n -> int 기본 자료형에 맞는 데이터 반환
}
```

> 메소드 표현 방법 -2 
```java
void intB(int a, ...){ // (int a, int b, String c) 이렇게 얼마든지 만들 수 있다.
	구현;
	// return 구문을 사용하지 않음.
```

#### 1.6 메소드를 사용

```java
	public static void sum() {
		int sum = 0;
		for(int i =0; i < 10; i++) {
			sum += i;
		}
		System.out.println("10의 합: " + sum);
	
	}

	public static int sumSu(int a) {
		int sum = 0;
		for(int i =0; i < a; i++) {
			sum += i;
		}
//		System.out.println("10의 합: " + sum);
		return sum;
	}
	public static void main(String[] args) {
		int a = 15;
		sum();
		int result = sumSu(a);
		System.out.println("result : " + result); // 105
		
}	



```

> MethodEx2 == 홀수와 짝수의 값을 각각 입력받아서 합을 출력해주는 메소드 작성
```java
package _0422WED;

import java.util.Scanner;

public class MethodEx2 {

	// 짝수의 합을 구하는 메소드 - oddSum
	public static int oddSum(int value) {
		int sum = 0;
		for (int i = 1; i <= value; i++) {
			if (i % 2 != 0) {
				sum += i;
			}
		}
		return sum;
	}
	// 홀수의 합을 구하는 메소드- evenSum
	public static int evenSum(int value) {
		int sum = 0;
		for (int i = 1; i <= value; i++) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
	public static void main(String[] args) {
		/*
		 문제 ) 홀수의 합을 구하는 메소드를 정의하시오.
		 */
		Scanner scan = new Scanner(System.in);
		System.out.print("원하는 홀수의 값의 크기를 입력 : ");
		int odd = scan.nextInt();
		int odd_result = oddSum(odd);
		System.out.println("홀수의 합  :" + odd_result);

		System.out.print("원하는 짝수의 값의 크기를 입력 : ");
		int even = scan.nextInt();
		int even_result = evenSum(even);
		System.out.println("짝수의 합 : " + even_result);

	}

}
```

#### 1.6.1

















