package _0416THU_If_Oper;

public class OperEx3 {

	public static void main(String[] args) {
		/*
		 * 삼항 연산자
		 * - 형식 )
		 * 데이터형 변수 = 조건식 ? 값1 : 값2;
		 */
		String result = 10 > 20 ? "참" : "거짓";
		System.out.println("결과: " +result);
		
		int result2 = 10 > 20 ? 50 : 100 ;
		System.out.println("결과2: " +result2);
		
		// max, min 값 반환하기.
		int x = 30;
	    int y = 4;
	    int z = 15;
	    int max;
	    int min;
	    
	    
	    max = ( x > y && x > z) ? x : ( y > z) ? y : z;
	    min = ( x < y && x < z) ? x : ( y < z) ? y : z;
	    
	    System.out.println(max);
	    System.out.println(min);
	}

}
