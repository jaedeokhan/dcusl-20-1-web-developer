package _0418SAT;

public class ForEx3 {

	public static void main(String[] args) {
		// 1 ~ 100 까지의 수의 합을 출력 
		// 1 ~ 100 까지 홀수, 짝수의 합을 출력
		
//		int i;
//		int sum = 0;
//		int even = 0;
//		int odd = 0;
//		
//		for (i=1; i < 100; i++) {
//			sum += i;
//			if (i % 2 != 0) {
//				odd += i;
//			}
//			else {
//				even += i;
//			}
//		}
//		System.out.println("1~100 합  : "+sum);
//		System.out.println("1~100 홀수 : "+odd);
//		System.out.println("1~100짝수 : "+even);
		int j = 0;
		int sum = 0;
		while (j < 100) {
			j++; // j + 1
			sum = sum + j;
		}
		System.out.println("sum : " +sum);
		
	}

}
