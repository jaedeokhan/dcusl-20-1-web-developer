package HashSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LottoHashSet {

	public static void main(String[] args) {
		
		HashSet<Integer> hashSet = new HashSet<Integer>();
		
		for (int i = 0; hashSet.size() < 6; i++) {
			// size() : Collection 인터페이스에서 제공하는 메소드 
			// 컬렉션에 추가되어 있는 요소의 개수를 반환하는 메소드이다.
			// Math.random() * 45 == 0 - 44 , 여기서 + 1을 하면 1 - 45
			int num = (int)(Math.random() * 45) + 1 ;
			hashSet.add(num);
		}
		
		// Set 타입의 컬렉션은 정렬이 되지 않는다.
		// 정렬을 List 타입으로 생성한다.
//		List listA = new ArrayList();
//		ArrayList<Integer> listB = new ArrayList<Integer>();
//		
//		
////		listA.add(hashSet);
//		listB.addAll(hashSet);
//		Collections.sort(listB);
//		
//		System.out.println(listB);
//		
		
		// List는 interface이다. List interface를 구현한 객체 중에 하나가 LinkedList이다.
		List<Integer> list1 = new LinkedList<Integer>();
		list1.addAll(hashSet);
		Collections.sort(list1);
		System.out.println(list1);
		
		// 위의 LinkedList에 인자를 안받고 해도 되고 받고 사용을 해도 상관은 없다.
		List<Integer> list2 = new LinkedList<Integer>(hashSet);
		Collections.sort(list2);
		System.out.println();
		System.out.println(list2);
	}

}
