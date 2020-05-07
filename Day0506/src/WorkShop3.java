import java.util.Scanner;

public class WorkShop3 {

	public static void main(String[] args) {
		/*
		 */
		Scanner scan = new Scanner(System.in);
	
		// 1. 입력
		System.out.print("중간고사 : ");
		int mid = scan.nextInt();
		System.out.print("기말고사 : ");
		int fin = scan.nextInt(); 
		System.out.print("레포트 : ");		
		int report = scan.nextInt();
		System.out.print("출석 : ");
		int attend = scan.nextInt();
		
		// 2. 연산(비지니스로직)
		double score = (mid + fin) / 2.0 * 0.6 + report * 0.2 + attend * 0.2;
		
		// 2.1 학점
		String grade = "";
		
		if (score >= 90) {
			grade = "A";
			System.out.println(grade);
		}
		else if (score >= 80) {
			grade = "B";
		}
		else if (score >= 70) {
			grade = "C";
		}
		else if (score >= 60) {
			grade = "D";
		}
		else {
			grade ="F";
		}
		
		// 2.2 평가
		String test = "";
		switch (grade) {
			case "A":
			case "B":
				test = "excellent";
				break;
			case "C":
			case "D":
				test = "good";
				break;
			case "F":
				test = "poor";
				break;
			}
		
		// 3. 응답
		System.out.printf("성적 = %.2f\n", score);
		System.out.print("학점 = " + grade +"\n");
		System.out.print("평가 = " + test );
	}

}
