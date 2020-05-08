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
