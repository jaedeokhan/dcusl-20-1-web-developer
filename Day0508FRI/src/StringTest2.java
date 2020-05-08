// 특히 모바일같이 리소스가 제한적인 환경에서는 절대로 String 클래스를 사용하지 마라!
// 문자열이 빈번하게 사용이 되면!! 
// 그렇다면? 어떤 API를 사용을 해서 조작을 해야 하는가?
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
