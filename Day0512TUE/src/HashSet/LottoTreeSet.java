// TreeSet
// HashSet 에 정렬기능이 추가된 클래스
package HashSet;

import java.util.TreeSet;

public class LottoTreeSet {

	public static void main(String[] args) {
		
		TreeSet<Integer> lotto = new TreeSet<Integer>();
		
		for (int i = 0; lotto.size() < 6; i++) {
			int num = (int)(Math.random() * 45) + 1;
			lotto.add(num);
		}
		
		System.out.println(lotto);
	}

}
