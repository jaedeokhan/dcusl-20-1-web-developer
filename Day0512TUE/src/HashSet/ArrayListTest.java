package HashSet;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>(); 
		
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
		list.add("555");
		list.add("111");
			
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 다른 방법으로 add하는 방법은? => 요 방법은 C(Create)
		list.add(0, "aaa");
		System.out.println();
		System.out.println("0번 인덱스 출력 : ");
		System.out.print(list.get(0));
	
		System.out.println("\n\n다시 모두 출력!");
		// 요 놈은 R(Read)
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		ArrayList<String> addListA = new ArrayList<String>();
		addListA.add("add1");
		addListA.add("add2");
		addListA.add("add3");
		
		// list에 위에서 새로 만든 arrayList를 한 번에 모두 넣기!
		list.addAll(addListA);
		System.out.println("\nlist에서 addAll을 사용해서 addListA를 한 번에 넣어서 출력하기!!!");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 다시 0번 리스트부터 addListA에 있는 모든 데이터를 차례대로 넣기
		list.addAll(0, addListA);
		System.out.println("\n 다시 addListA에 있는 데이터를 addAll을 사용해서 0번 인덱스부터 넣기");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	
		ArrayList<String> addListB = new ArrayList<String>(list);
		System.out.println("\n arrListB에서 객체 생성과 동시에 list를 한 번에 받아서 출력 !!");
		System.out.println(addListB);
		
		// U (Update) 
		System.out.println("\n0번 인덱스 영역에 있는 요소를 수정하기!!!");
		System.out.println(list.get(0));

		// 수정
		list.set(0, "0번 인덱스 데이터 변경");
		System.out.println(list.get(0));
		
		// D (Delete)
		list.remove(0);
		System.out.println("\n0번 인덱스인 0번 인덱스 데이터 변경을 제거하면 나머지는 위로 당겨진다.\n"
				+ "==========================================");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 인덱스 번호를 사용해서 삭제하는 방법말고 특정 데이터의 값을 선택해서 삭제를 하기.
		list.remove("add2");
		System.out.println("\n바로 위에서 맨 위로 당겨진 add2를 직접적으로 선택해서 삭제해본다."
				+ "삭제를 하면! 당연하게도 add3가 위로 당겨진다!!"
				+ "=======================================");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 위에서 ad
		
	}
	
}
