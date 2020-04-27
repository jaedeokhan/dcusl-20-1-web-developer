package _0416THU_If_Oper;

public class OperEx2 {

	public static void main(String[] args) {
		/* 비교 연산자
		 * x < y : x가 y보다 작다. 미만
		 * > : 초과
		 * <= : 이하
		 * >= : 이상
		 * == : 같다
		 * != : 다르다
		 * !  : True -> False, False -> True
		 * instanceof
		 */
		int x = 30;
		int y = 20;
		boolean result1 = x < y;
		boolean result2 = x > y;
		boolean result3 = x <= y;
		boolean result4 = x >= y;
		boolean result5 = x == y;
		boolean result6 = x != y;
		boolean result7 = ! result1;
		
		System.out.println("result1 : " + result1);
		System.out.println("result2 : " + result2);
		System.out.println("result3 : " + result3);
		System.out.println("result4 : " + result4);
		System.out.println("result5 : " + result5);
		System.out.println("result6 : " + result6);
		System.out.println("result7 : " + result7);
			

	}

}
