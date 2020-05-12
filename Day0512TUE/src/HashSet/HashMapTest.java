package HashSet;

import java.util.HashMap;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		
		String[] houseArray = {"아파트", "원룸", "빌라", "고시원", "오피스텔"};
		
		
		// <generic type>
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		
		for (int i = 0; i < houseArray.length; i++) {
			hashMap.put(i, houseArray[i]);
		}
		
		System.out.println(hashMap);

		// 각 키에 대한 값들을 출력
		// Map에 저장된 요소들의 Key 값드만 얻어오는 메소드가 있다.
		Set<Integer> hashKey = hashMap.keySet();
		
		System.out.println("\nhashMap의 Key값만 저장된 hashKey를 출력");
		
		for (Integer key : hashKey) {
			// 각각의 키를 출력
			System.out.print(key + " ");
			
			// 각 키에 해당하는 요소들을 출력
			System.out.println(hashMap.get(key));
		}
	}

}
