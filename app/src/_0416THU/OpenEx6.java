package _0416THU;

public class OpenEx6 {

	public static void main(String[] args) {
//		증감/ 감소 연산자
//		형식
//		--
//		++
//		int a = 0;
//		++a ; // result = 1
//		--a;  // result = 0
	
//		선행과 후행
//		int a = 0;
//		a++;
//		// 출력은 먼저하고, 후행으로 1을 증가시키는 것이다.
//		System.out.println(a++); // a++ 은 후행 연산이기때문에 나중에 연산된다.
//		System.out.println(++a); // ++a 은 선행 연산이기에 바로 연산된다.
		
//		나의 공부 -> 결과 예측해보기
		int a = 0;
		System.out.println(a++ + a++); // 0 + 1
		System.out.println(a + --a + ++a); // 2 + 1 + 2
		System.out.println(--a + a++); // 1 + 1
		System.out.println(a); // 2
		
	}

}
