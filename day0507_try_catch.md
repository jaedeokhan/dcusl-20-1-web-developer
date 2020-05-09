### 2020 0507 THU Day 2 => try - exception
1. 예외처리와 핵심 클래스들
java에서는 0을 나누면 에러가 발생한다. => <b>java.lang.ArithmeticException: / by zero</b> 다음과 같은 에러가 발생

1.1 tyr - catch 문

```java

public class ExceptionTest1 {

	public static void main(String[] args){
		/*
		 예외처리 : 정수를 0으로 나눔
		 */

		try {
			int num = Integer.parseInt(args[0]);
			// num 값이 0이 전달되면 에러가 발생한다.
			int result = 10 / num;
			System.out.println("result : " + result);
		}
		catch(ArithmeticException e)
		{
			System.out.println("정수를 0으로 나누면 안돼요.");
		}
		System.out.println("프로그램 계속 실행");
		
	}

}
```

1.2 여러개의 예외처리하는 방법은?
* ArrayIndexOutOfBountException => args[0] 를 하나도 입력 x 
* ArithmeticeException => 0으로 나누려고 해서 안된다.
* NumberFormatException => Integer.parseInt(args[0])를 "C"를 받으면 number를 입력해야한다고 에러가 난다.

```java

public class ExceptionTest1 {

	public static void main(String[] args){
		/*
		 예외처리 : 정수를 0으로 나눔
		 */
		int result = 0;

		try {
			int num = Integer.parseInt(args[0]);
			// num 값이 0이 전달되면 에러가 발생한다.
			result = 10 / num;
			System.out.println("result : " + result);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("args를 하나 입력해주세요.");
		}
		catch(ArithmeticException e)
		{
			System.out.println("정수를 0으로 나누면 안돼요.");
		}
		catch(NumberFormatException e) {
			System.out.println("인자를 정수 타입으로 입력해주세요.");
		}
		System.out.println(result);
		System.out.println("프로그램 계속 실행");

	}

}
```

#### 1.3 try-catch-finally구문
finally를 왜 사용해야 하는가?
try {
   예외처리 할 구문
}
catch (){
   예외처리
}
finally{
   무조건 처리해야하는 구문
}

```java

public class ExceptionTest2 {

	public void excptionMethod(String[] args) {
		int result = 0;
		
		try {
			int num = Integer.parseInt(args[0]);
			// num 값이 0이 전달되면 에러가 발생한다.
			result = 10 / num;
			return;
		}
		catch(ArrayIndexOutOfBoundsException e) 
		{
			System.out.println("args를 하나 입력해주세요.");
			return;
		}
		catch(ArithmeticException e)
		{
			System.out.println("정수를 0으로 나누면 안돼요.");
			return;
		}
		catch(NumberFormatException e) 
		{
			System.out.println("인자를 정수 타입으로 입력해주세요.");
			return;
		}
		finally {
			System.out.println("반드시 실행되어야하는 부분");
		}
		// 이것을 간단하게 하고 싶다면?
//		catch(Exception e) {
//			// Exception으로 모든 에러를 받아서 getClass해서 getName으로 클래스의 이름을 가져온다.
//			System.out.println(e.getClass().getName());
//		}
//		System.out.println(result);
//		System.out.println("프로그램 계속 실행");

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExceptionTest2 et2 = new ExceptionTest2();
		et2.excptionMethod(args);
//		try {
//			
//		}
//		catch() {
//			
//		}
//		finally {
//			
//		}
	}

}
```

#### 1.4 return의 정의란? 
* return은 꼭 값을 반환해야 하는가? => 아니다. 
```java
// return 의 정의는?
// 메소드의 실행을 종료하고 메소드를 호출한 쪽으로 되돌아 가는 예약어
// return 예약어를 사용할 때는 특정값을 반환 할 수 도 있고, 반환하지 않을 수 도 있다.
public class ReturnTest {
	public int add(int x, int y) {
		
		return x + y;
	}
	
	public void printInfo(String info) {
		// 리턴 타입이 void일 때는 return 을 생략을 할 수 있다.
		System.out.println(info);
		return;
	}
	
	public static void main(String[] args) {
		
		ReturnTest rt = new ReturnTest();
		System.out.println("10 + 10 = " + rt.add(10, 10));
		
		rt.printInfo("정보");
	}

}
```

#### 1.5 예외의 종류 
* Object 클래스는 모든 클래스의 최상위 클래스이다.
* Throwable 클래스는 Exception, Error 클래스에게 상속을 해준다.
* Error의 클래스에는 정말 최악의 Error가 들어있다. => ThreadDeath, AssertionError, VirtualMachineError => JVM이 갑자기 죽어버린 경우 등 처리할 수 없는 치명적 에러
* Exception 클래스에서는 일반적인 Exception, 실행시 Execption

#### 1.5.1 RuntimeException 이란? => Run as 를 하고나서 발생하는 에러!
compile time에는 에러가 발생하지 않는다. 즉 논리적인 사용자의 오류를 말한다.
실행시에 에러가 발생하는 것!!!
만약? compile time에 오류가 있다면? 작성을 하면서 빨간줄이 나타난다.
요 놈은 UnCheckedException이라고도 한다.
런타임 예외는 대부분 개발자가 프로그램을 정확히 구현하지 못해서 발생한다고 생각하면 된다! 줸장!!

NullPointerException => 레퍼런스 변수값을 초기화하지 않고 해당 객체의 변수나 메소드를 호출하는 경우 발생!
ArrayIndexOutOfBoundException => index 범위 초과!!
ArithmeticExecption => by zero / 0

일반 Exception들:
런타임 예외를 제외한 일반 예외는 
즉 일반 요 놈은 CheckedException이라고 한다. 그리고 일반 예외들은 개발자들의 문제라기 보다는 발생할 것들이 예상되는 예외들이다.
FileNotFoundException
IOException 은 네트워크에서 오류가 날 수 있다. 반드시 예외 처리를 해줘야 함.

#### 1.5.2 Java에서 제공하는 API Documents
https://docs.oracle.com/javase/8/docs/api/index.html

InterruptedException

#### 1.6 throws 예약어
자바에서 예외처리는 원래 예외가 발생한 메소드 안에서 처리하는 것이 기본.
그렇지만 throws 예약어를 이용하면 예외처리를 넘겨준다.

#### 1.7 pipe Exception 

```java
// Pipe Exception
// 여러 개의 error 가 났을 때 catch 구문을 여러 개를 사용해서 처리를 했는데,
// try 안에서 여러 개의 error가 나면 하나의 catch 문에서 여러 개의 예외를 처리한다.
// JDK 7 version 이상
public class PipeExceptionTest {

	public static void main(String[] args) {
	
		try {
			int num = Integer.parseInt(args[0]);
		}
		catch (ArrayIndexOutOfBoundsException 
			   |NumberFormatException 
			   |ArithmeticException e) {
			// Pipe Exception 으로 하나의 catch 구문에서 예외 처리하기.
			// 여러 개를 나열하면 어느 에러가 들어왔는지 판단을 해야한다.
			if(e.getClass().getName().equals("java.lang.ArrayIndexOutOfBoundsException")) {
				System.out.println("args를 하나라도 입력하세요.");
			}
			else if(e.getClass().getName().equals("java.lang.NumberFormatException")){
				System.out.println("정수형을 입력해주세요.");
			}
			else {
				System.out.println("0으로는 나눌 수 없습니다.");
			}
		}

	}

}

```

#### 1.8 사용자 정의 예외
자바에서 제공되는 예외 클래스만으로는 특정 상황에 대한 예외 정보를 지정할 수 없을 때는 사용자 정의 예외를 만들어서 발생한 상황에 대한 정보를 저장할 수 있다.
일반적으로 Exception 클래스를 상속을 받아서 사용한다.

> 프로그램을 실행할 때 인자를 두 개 입력하지 않으면 발생시킬 예외를 정의하고 사용하는 예제를 작성해보자.
```java

class ArgsException extends Exception{

	private int argsNumber;

	// ctrl + space
	public ArgsException(String msg) {
		super(msg); // Exception 클래스의 정의된 속성에 할당한다.
	}

	// alt + shift + s
	public int getArgsNumber() {
		return argsNumber;
	}

	public void setArgsNumber(int argsNumber) {
		this.argsNumber = argsNumber;
	}

}

public class ExceptionTest7 {

	public static void main(String[] args) {
		// tyr 입력 후 ctrl + space
		try {
			if (args.length != 2) {
				ArgsException ae = new ArgsException("인자를 2 개 입력하세요.");
				ae.setArgsNumber(args.length);
				throw ae;
			}
			else {
				int num1 = Integer.parseInt(args[0]);
				int num2 = Integer.parseInt(args[1]);
				System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
			}
		} catch (ArgsException e) {
			System.out.println(e.getMessage());
			System.out.println("당신이 입력한 인자수는 " + e.getArgsNumber() + "개 입니다.");
		}
	}

}
```
#### 1.9


#### 2. String 클래스
- 자바에서 문자열을 다루는 클래스
- 문자열은 내부적으로 char 배열로 다루어짐.

#### 2.1 String 객체생성방법
1. 문자열 리터럴을 이용해서 String 객체 생성하는 방법
String string1 = "testString";
String string2 = "testString";






