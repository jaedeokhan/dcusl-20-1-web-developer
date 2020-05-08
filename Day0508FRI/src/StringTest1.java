
public class StringTest1 {

	public static void main(String[] args) {
		
		// 1. 문자열 상수를 사용해서 String 객체를 생성
		String string1 = "Stringtest";
		String string2 = "Stringtest";
		
		System.out.println("string1 == string2 : " +  (string1==string2));
		System.out.println("string1 equals string2 : " + (string1.equals(string2)));
		
		// 2. 생성자를 사용해서 String 객체를 생성
		String string3 = new String("Stringtest");
		String string4 = new String("Stringtest");
		
		// ctrl + F 를 사용해서 replace all을 이용하기!
		System.out.println("string3 == string4 : " + (string3==string4));
		System.out.println("string3 equals string4 : " + (string3.equals(string4)));
		
	}

}
