package HashSet;
// 배열은 기본 타입의 데이터나 객체 타입의 데이터 모두 저장할 수 있었지만
// 컬렉션은 객체 타입만 저장할 수 있다.
// 배열은 크기가 한 번 정해지면 변경할 수 없지만,
// 컬렉션은 크기보다 더 많은 데이터를 저장하면 크기가 자동으로 늘어난다.


import java.util.HashSet;
import static java.lang.System.out;

public class HashSetTest {

	public static void main(String[] args) {
		
		String[] carArray = {"제네시스" ,"소나타", "K7", "벤츠", "아우디", "K7"};
		
		// carArray 가 HashSet에 들어가면?
		HashSet<String> hs1 = new HashSet<String>();
		HashSet<String> hs2 = new HashSet<String>();
		
		for (String car : carArray) {
			// 요소를 담는것을 실패하면 if문으로 들어간다. 즉 중복된 값이 존재할때 if문 안으로 들어간다.
			if(!hs1.add(car)) {
				// add : Collection 인터페이스에서 제공하는 메소드, 요소를 추가하는 메소드이다.
				// 요소가 정상적으로 담기면 true 반환
				// 중복되는 값은 hs2에 add한다.
				hs2.add(car);
			}
		}
		out.println("hs1 : " + hs1);
		out.println("hs2 : " + hs2);
		
		out.println("");
		out.println("제네시스 삭제");
		hs1.remove("제네시스");
		out.println("hs1 : " + hs1);
		
		out.println("");
		out.println("hs2에 있는 K7을 제거한다.");
		hs1.removeAll(hs2);
		out.println("hs1 : " + hs1);
		
	}

}
