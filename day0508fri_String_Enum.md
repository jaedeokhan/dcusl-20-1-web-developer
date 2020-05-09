### 2020 0508 FRI 
1. String 객체

#### 1. String 객체
생성하는 방법은 두 가지이다.
1. 문자열 상수를 사용해서 String 객체를 생성하는 방법
2. 생성자를 사용해서 String 객체를 생성

> 두 가지 방법으로 생성한 string을 ==, equals 비교해서 true, false 값이 나오는 프로그램
```java

public class StringTest1 {

	public static void main(String[] args) {
		
		// 1. 문자열 상수를 사용해서 String 객체를 생성
		String string1 = "Stringtest";
		String string2 = "Stringtest";
		
		System.out.println("string1 == string2 : " +  (string1==string2)); // true
		System.out.println("string1 equals string2 : " + (string1.equals(string2))); // true
		
		// 2. 생성자를 사용해서 String 객체를 생성
		String string3 = new String("Stringtest");
		String string4 = new String("Stringtest");
		
		// ctrl + F 를 사용해서 replace all을 이용하기!
		System.out.println("string3 == string4 : " + (string3==string4)); // false
		System.out.println("string3 equals string4 : " + (string3.equals(string4))); // true
		
	}

}

```

#### 1.1 String 객체의 불변성(immutable)
concat 을 사용하면 Java라는 문자열 뒤에 바로 Fighting이 붙을거 같지만 이 둘은 heap 영역에 다른곳에 생성이 된다.

> concat을 하면 별도의 heap 영역에 객체가 만들어지는 것이다.
```java
public class StringTest2 {

	public static void main(String[] args) {
		// String 객체의 immutable 특성! 
		String original = new String("Java ");
		String last = original.concat("Fighing");
		
		System.out.println(original);
		System.out.println(last);
		System.out.println("original == last : " + (original == last));
	}

}
```

#### 1.2 StringBuffer

> StringBuffer는 동적으로 변경할 때 사용!
```java
// 문자열을 빈번하게 조작해야 하는 작업을 할 때는 StringBuffer를 사용하면 된다.
// 얘는 불변성이 없다. 즉 해당 객체의 문자열이 바로바로 바뀐다.
// 메모리에서 동적으로 변경
public class StringTest3 {

	public static void main(String[] args) {
		
		StringBuffer sb1 = new StringBuffer("Java");
		// Stirng 객체의 concat과 동일하다.
		StringBuffer sb2 = sb1.append(" Fighting");
		
		System.out.println(sb1);
		System.out.println(sb2);
		System.out.println("sb1 == sb2 : " + (sb1 == sb2));
		
	}

}
```

#### 1.3 다양한 String 클래스의 메소드들!
1. char charAt(int index);
2. int indexOf(String searchString);
3. String substring(int startIndex)
4. String substring(int startIndex, int endIndex)
5. String replace(String oldString, String newString);
6. boolean endsWith(String endString);
7. boolean startsWith(String startString);
8. boolean contains(String containsString);
9. int length();
10. boolean equals(String str);
11. boolean equalsIgnoreCase(String str);
12. String toUpperCase()
13. String toLowerCase()
14. String[] split(String delims);
15. String trim();

```java
// 다양한 String 클래스의 메소드들
public class StringTest4 {

	public static void main(String[] args) {
		
		String source1 = "aaabbbcccdddfffaaabbbcccddd";
		String source2 = "AAAbbbcccdddfffaaabbbcccddd";
		
		// source2의 대문자를 체크하는 String API
		System.out.println("원본 문자열 : " + source1);
		System.out.println();
		
		// char charAt(int index) => 문자열은 char 배열로 구성되어있다. 그래서 다음과 같은 메소드들 사용 가능
		// 원본 문자열에서 인자로 지정된 인덱스 위치의 문자 하나를 반환하는 메소드이다.
		System.out.println("첫 번째 문자 : source1.charAt(0) : " + source1.charAt(0));
		System.out.println("첫 번째 문자 : source2.charAt(0) : " + source2.charAt(0));
		System.out.println();
		
		// int indexOf(String searchString)
		// 원본 문자열에서 인자로 지정된 문자열이 있는 인덱스 번호를 반환
		System.out.println("첫 번째 인덱스 : source1.indexOf(\"a\") : " + source1.indexOf("a"));
		System.out.println("첫 번째 인덱스 : source2.indexOf(\"A\") : " + source2.indexOf("A"));
		System.out.println();
		
		// String substring(int startIndex)
		// 원본문자열에서 인자로 지정된 인덱스 위치의 문자부터 마지막 문자까지 반환
		System.out.println("3번 인덱스 문자부터 마지막 문자까지 출력 : source1.substring(3)" 
						+ source1.substring(3));
		System.out.println();
		
		// String substring(int startIndex, int endIndex)
		// 원본문자열에서 인자로 지정된 startIndex, 위치 부터 (endIndex => -1) 인덱스 위치 문자까지 반환
		System.out.println("3번 인덱스 문자부터 마지막 문자까지 출력 : source1.substring(3, n)" 
				+ source1.substring(3, 6));
		System.out.println();
		
		// String replace(String oldString, String newString)
		// 원본 문자열에서 OldString을 검색해서 newString으로 치환!
		System.out.println("원본문자열에서 aaa 문자열을 ggg 문자열로 변경 : source1.replace(\"aaa\", \"ggg\") : " 
				+ source1.replace("aaa", "ggg"));
		System.out.println();
		
		// boolean endsWith(String endString)
		// 원본 문자열이 인자로 지정된 문자열로 끝나는지 판단! =>  true, false
		// .hwp 와 같은 확장자들을 판단할 때 주로 사용한다.
		System.out.println("원본문자열이 ddd로 끝나는지 확인 : source1.endsWith(\"ddd\") : " 
				+ source1.endsWith("ddd"));
		System.out.println();
		
		// boolean startsWith(String startString)
		// 원본 문자열이 인자로 지정된 문자열로 시작되는지 판단! => true, false
		// 쇼핑몰에서 오늘 본 상품 이미지를 탭 메뉴에 보여주는 기능! = > 이때 쿠키라는 기능을 이용해서 본 이미지를 심어준다.
		// 쿠키의 이름이 today인 것을 가져와서 사용자의 페이지에 뿌려주는 것이다.
		System.out.println("원본문자열이 aaa로 시작되는지 판단 : source1.startsWith(\"aaa\") : " 
				+ source1.startsWith("aaa"));
		System.out.println();
		
		// boolean contains(String containsString)
		// 원본 문자열이 인자로 지정한 문자열을 포함하고 있는지 판단 => true, false
		System.out.println("원본문자열이 aaa를 포함하는지 판단 : source1.contains(\"aaa\") : " 
				+ source1.contains("aaa"));
		System.out.println();
		
		// int length()
		// 문자열에 포함된 문자 수를 반환.
		System.out.print("source1 문자열의 문자 개수 :source1.length() : ");
		System.out.println(source1.length());
		System.out.println();
		
		// boolean equals(String str)
		// 대소문자를 구분하여 두 문자열 비교
		System.out.print("source1 문자열과 source2 문자열을 대소문자 구분하여 비교 : source1.equals(source2) : ");
		System.out.println(source1.equals(source2));
		System.out.println();
		
		// boolean equalsIgnoreCase(String str)
		// 대소문자를 구분하지 않고 문자열 비교
		System.out.print("source1 문자열과 source2 문자열을 대소문자 구분하지 않고 비교 : source1.equalsIgnoreCase(source2) : ");
		System.out.println(source1.equalsIgnoreCase(source2));
		System.out.println();

		// String toUpperCase()
		// 원본 문자열의 모든 문자를 대문자로 만들어준다.
		System.out.println("원본문자열 모든 문자를 대문자로 변경 : source1.toUpperCase() : " 
				+ source1.toUpperCase());
		System.out.println();
		
		// String toLowerCase()
		// 원본 문자열의 모든 문자를 소문자로 만들어준다.
		System.out.println("원본문자열 모든 문자를 소문자로 변경 : source1.toLowerCase() : " 
				+ source1.toLowerCase());
		System.out.println();
		
		// String[] split(String delims)
		// 
		String source3 = "aaa,bbb,ccc";
		System.out.println("원본 문자열 : " + source3);
		
		String[] splitArray = source3.split(",");
		System.out.println("source3.split(\",\")을 실행한 결과 문자열");
//		for (int i = 0; i < splitArray.length; i++) {
//			System.out.print(splitArray[i]);
//		}
		// 개선된 for 문 사용
		for(String str : splitArray) {
			System.out.print(str);
		}
		System.out.println();
		
		// String trim()
		// 문자열의 공백을 제거!!
		String source4 = " java ";
		// 공백이 있는지 확인을 위해 어포스트로피를 쏴준다.
		System.out.println("원본문자열 : '" + source4 + "'");
		System.out.println("공백 제거후 source4 문자열 : source4.trim() : '" + source4.trim() + "'");		
		
	}

}
```

#### 2. 상수의 2 % 부족한 점!!
상수로는 조금 더 직관적으로 표현하기에는 어려움이 존재한다.
제어문을 돌릴 때 가독성이 좋아야 바로 파악이 되는데
상수를 사용하면 잘 파악이 되지 않는다.
그래서, enum을 사용한다.
```java
// 상수의 2 % 부족한 점
class Student{
	public static final int ELEMENTARY = 1;
	public static final int MIDDLE = 2;
	public static final int HIGH = 3;
	public static final int UNIVERSITY = 4 ;	
	int school;
	
	public Student(int school) {
		super();
		this.school = school;
	}
}
public class EnumTest1 {
	
	public static void main(String[] args) {
		
		Student student1 = new Student(Student.ELEMENTARY); // ok
		Student student2 = new Student(2); // 상수의 의도와 벗어났다. 
		
		System.out.println("상수 값을 출력 ");
		System.out.println("student1.school = " + student1.school);
		System.out.println("student2.school = " + student2.school);
		
		// 제어문을 돌릴 때 가독성이 좋아서 바로 파악이 되야 하는데...
		// 2번 if문을 보면 잘 파악이 되지 않는다.
		// 그래서 enum을 사용한다.
		if (student1.school == Student.ELEMENTARY) {
			System.out.println("당신은 초등학생");
		}
		if (student2.school == 2) {
			System.out.println("당신은 중학생");
		}
		
	}

}
```

#### 2.1 enum을 사용하면 조금 더 직관적으로 처리가 가능하다.
> EnumTest2.java
```java
// 상수의 단점을 보완하기 위한 ENUM
class Student1{
	SchoolType shcool;

	public Student1(SchoolType shcool) {
		super();
		this.shcool = shcool;
	}
	
}
enum SchoolType{
	ELEMENTARY, MIDDLE, HIGH, UNIVERSITY;
}

public class EnumTest2 {

	public static void main(String[] args) {
		// 
		Student1 stu = new Student1(SchoolType.ELEMENTARY);
		
		System.out.println("enum 타입의 데이터 출력 ");
		System.out.println("student1.school = " + stu.shcool); // ELEMENTARY
		
		if (stu.shcool == SchoolType.ELEMENTARY) {
			System.out.println("초등학생");
		}
		
//  요 놈은 당연히 되지 않는다! 
//		if (stu.school == 1) {
//			
//		}
	}

}
```

#### 3. 오토박싱
JDK 업데이트가 되지 않을 때는 아래와 같다.
아래와 같이 생성자를 이용해서 객체를 생성해야 객체타입응로 사용이 가능했다.
```java
int intVar = 3;
Integer intObj = new Integer(intVar);
```

그렇지만, 지금은 오토밗이을 지원해서 기본 타입의 데이터를 객체타입의 데이터에 할당하면 기본 타입의 데이터가 객체타입의 데이터로 자동 변환되면서 레퍼런스 변수에 할당된다.
```java
int intVar = 3;
Integer intObj = intVar;
```

#### 3.2 오토언박싱
JDK 업데이트 이전에는 객체 타입의 데이터를 기본 변수 타입의 변수에 할당할려면 해당 Wrapper Class에서 제공되는 메소드들을 이용해서 객체타입을  기본타입으로 변환한 후에 기본타입의 변수에 할당해야 했다.
```java
Integer intObj = new Integer("1");
int intVar = intObj.intValue();
```

그렇지만, 지금은 오토언박싱을 지원해서 아래와 같이 가능하다.
```java
Int a  = new Integer("3");
```


