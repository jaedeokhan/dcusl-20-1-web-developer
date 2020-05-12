// LIFO (Last In Frist Out)
package HashSet;

import java.util.Iterator;
import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		
		String[] countryArray = {"대한민국", "영국", "미국", "사우디", "중국", "러시아", "필리핀"};
		
		Stack<String> countryStack = new Stack<String>();		
		
		// 요소 추가
		System.out.println("들어가는 순서 ");
		for (String country : countryArray) {
			// add를 사용해도 되지만, stack에서 사용하는 push가 더 유용함.
			countryStack.push(country);
			// 들어가는 순서
			System.out.println(country);
		}
		
		// 요소 조회
		System.out.println("나오는 순서");
		while (!countryStack.isEmpty()) {
			// isEmpty() : Collection 인터페이스에서 제공되는 메소드
			// 컬렉션이 요소가 하나도 없으면 true를 반환하는 메소드
			System.out.println(countryStack.pop());
			
		}
	}

}
