package HashSet;

import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorTest {

	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		// listIterator의 객체를 반환해주는 메소드
		ListIterator<String> it = list.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			
		}
		System.out.println("\n\n previous를 사용해서 이전 출력!!");
		
		// 이전으로 되돌아가서
		while(it.hasPrevious()) {
			System.out.println(it.previous());
		}
	}

}
