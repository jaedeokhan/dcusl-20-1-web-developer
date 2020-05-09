/*
입력받아 계산하시오
    Input name : 오정원 
    kor : 90 
    eng : 70 
    mat : 75

   <출력>
    이름 : 오정원
    total점수 : 235점
    avg점수 :  78.3   <-- 소수 1자리까지
 */
package WorkShop1;

import java.util.Scanner;

public class WorkShop3 {
	
	/*
	 * public void proWorkShop3() { Scanner scan = new Scanner(System.in);
	 * System.out.print("Input name : "); String name = scan.next();
	 * System.out.print("kor : "); int kor = scan.nextInt();
	 * System.out.print("eng : "); int eng = scan.nextInt();
	 * System.out.print("mat : "); int mat = scan.nextInt();
	 * 
	 * // 연산 int total = (kor + eng + mat); // 피연산자 중에 하나를 double 값으로 변경을 해준다. 3을
	 * 3.0으로 변경해줘도 상관은 없다. double avg = (double)total / 3;
	 * 
	 * // 출력 System.out.println("total 점수 : " + total + "점");
	 * System.out.printf("avg 점수 : %.1f", avg); }
	 */
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String name = "";
		int kor = 0;
		int eng = 0;
		int mat = 0;
		int total = 0;
		double avg = 0.0;
		
		System.out.print("input name : ");
		name = scan.nextLine();
		System.out.print("kor : ");
		kor = scan.nextInt();
		System.out.print("eng : ");
		eng = scan.nextInt();
		System.out.print("mat : ");
		mat = scan.nextInt();
		
		total = (kor + eng + mat);
		avg = (double)total / 3;
		
		System.out.println("이름 : " + name);
		System.out.println("total 점수 : " + total + "점");
		System.out.printf("avg점수 : %.1f", avg);

	}

}
