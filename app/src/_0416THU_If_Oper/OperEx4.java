package _0416THU_If_Oper;

public class OperEx4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 논리 연산자
		 * | == ||
		 * & == &&
		 */
		/*
		ex1)
		// 요 놈 | 은 앞이 거짓이라도 뒤에 참을 확인을 한다.
		참 = 참 | 거짓
		참 = 거짓 | 참
		
		참 = 참 & 참
		거짓 = 거짓 & 참
		거짓 = 참 & 거짓
		
		ex2)
		// 요 놈 ||은 앞이 참이라면 바로 뒤를 확인하지 않고 바로 연산한다.
		참 = 참 || 거짓
		참 = 거짓 || 참
		
		참 = 참  && 참
		거짓 = 거짓 && 참
		거짓 = 참 && 거짓
		*/
		
		int su1 = 10;
		int su2 = 20;
		
		boolean result1 = su1 < su2; // 10 < 20 == True
		System.out.println(result1);
		 
		boolean result2 = su1 > su2; // 10 > 20 == False
		System.out.println(result2);
		
		boolean logic1 = result1 | result2; // True
		System.out.println(logic1);
		
		boolean logic2 = result1 & result2; // False
		System.out.println(logic2);
		
		// su1 = 10
		// su2 = 20
		// || or이 두 개이면  or 조건에서 앞이 참이라면 그냥 통과, 거짓이라면 뒤도 체크한다.
		boolean logic3 = su1 < su2 || (su1 = 5) > su2;
		System.out.println(logic3); 
				
		// && and가 두 개이면 and 조건에서 앞이 거짓이라면 바로 수행하고, 참이라면 뒤도 확인한다.
		boolean logic4 = su1 > su2 && (su1 = 5) < su2;
		System.out.println(logic4);
		
		
		int num = 10;
		System.out.println(num); // 10
		num = 15;
		System.out.println(num); // 15
		
		
		
		
		
		
		
		
		
	}

}
