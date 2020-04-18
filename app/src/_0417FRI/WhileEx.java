package _0417FRI;

public class WhileEx {

	public static void main(String[] args) {
		/* 반복문(while문)
		   	표현)
		   	int = 0 // 초기식
		   	while (조건식) }
		   		실행 구문;
		   		i++; // 증감식
		   	}
		 */
		
		int i = 0;
		while (i < 5) {
			i++;
			System.out.println(i);
		}
		
		int i2 = 1;
		int sum = 1;
		while (i2 <= 10) {
			sum *= i2;
			i2++;
		}
		System.out.println(sum);
		
		System.out.println("=========================");
		// 문제) 1 ~ 100 까지의 수의 합을 구하시오.
		int value = 1;
		int num = 0;
		
		while(value < 100) {
			num += value;
			value++;
		}
		System.out.println("1부터 100까지의 합: " + num);
		
		// 문제2) 1 ~ 100 까지의 짝수의 합을 구해라.
		int num2 = 0;
		int sum2= 0;
		while(num2 < 100) {
			num2++;
			if (num2 % 2 == 0) {
				sum2 += num2;
			}
		}
		System.out.println("1~100 까지 짝수의 합 : " + sum2);
		System.out.println("======================");
		
		// 문제2-1) 1-100 까지의 홀수의 합을 구해라.
		int num3 = 0;
		int sum3 = 0;
		while(num3 < 100) {
			num3++;
			if (num3 % 2 != 0) {
				sum3 += num3;
			}
		}
		System.out.println("1~100 까지 홀수의 합 : " + sum3);
		 
		

		
		
		
	}
	
}
