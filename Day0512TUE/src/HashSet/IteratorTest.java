package HashSet;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorTest {

	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		System.out.println(list);
		
		// 해당 컬렉션 객테를 다루는 iterator()가 반환이 되는 메소드 == iterator()
		// <String>을 주지 않으면 Object를 반환한다.
		Iterator<String> iter = list.iterator();
		
		System.out.println("\niterator 출력 시작!");
		// hasNext() : 작업할 다음 요소가 있으면 true를 반환, 없으면 false를 반환한다.
		while (iter.hasNext()) {
			String string = iter.next();  // next() : 요소를 반환하고 다음 작업할 요소 위치로 이동
			System.out.println(string);
			
		}
	}

}
