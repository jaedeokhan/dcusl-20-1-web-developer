package _0416THU_If_Oper;

public class OperEx5 {

	public static void main(String[] args) {
		/*
		 * 복합 대입 연산자
		 * 연산 후 대입 연산자
		 * +=
		 * -=
		 * /=
		 * %=
		 */
		int su1 = 0;
		int su2 = 0;
		int sum = 0;
		su1 = 10;
		su2 = 20;
		sum = su1 + su2;
		sum += su1;
		sum -= su2;
		System.out.println("sum : "+ sum);
//		문제1) 1부터 10까지 합과 곱을 각각 출력하시오.
		int plus = 0;
		int multi = 1;
		
		for (int i=1; i<=10; i++) {
			plus += i;
			multi *= i;
		}
		System.out.println("plus : "  + plus);
		System.out.println("multi : "  + multi);
	}

}
