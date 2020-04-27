package _0425SAT_Package;

import java.util.Scanner;

public class Student {
	java.util.Scanner scan;
	// 생성자를 줘서 외부에서 객체를 생성할 수 있게 해준다.
	public Student() {
		 scan = new java.util.Scanner(System.in);
//		Scanner scan = new Scanner(System.in);
		
	}
	public void menu() {
		boolean run = true;
		int [] students = null;
		int scores = 0;
		int studentsNum = 0;
		int max = 0;
		int min = 0;
		int sum = 0;
		double avg;
		
		do {
			System.out.println("-----------------------------------------");
			System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
			System.out.println("-----------------------------------------");
			System.out.print("선택 > ");
			int selectNo = scan.nextInt();
			
			if ( selectNo == 1 ) {
				System.out.print("학생 수 : ");
				// 1. 학생 수 입력
				studentsNum = scan.nextInt();
				students = new int[studentsNum];
				
			}
			else if ( selectNo == 2 ) {
				// 2. 학생 수 만큼 각 점수 입력
				for (int i = 0; i < students.length; i++) {
						System.out.print("점수 입력 : ");
						scores = scan.nextInt();
						students[i] = scores;
				}
			}
			else if ( selectNo == 3 ) {
				// 3. 학생 전체 점수리스트 출력
				System.out.print("점수 리스트 : ");
//				for (int i = 0; i < students.length; i++) {
//					System.out.print(students[i] + " ");
//				}
				for ( int s : students) {
					System.out.print(s + " ");
				}
				System.out.println();	
				
			}
			else if ( selectNo == 4 ) {
				// 4. 분석 ( max, min , avg)
				max = students[0];
				min = students[0];
				System.out.println("===분석결과 === ");
				for (int i = 0; i < students.length; i++) {
					max = (max < students[i] ) ? students[i] : max;
					min = (min > students[i] ) ? students[i] : min; 
//					if (max < students[i]) {
//						max = students[i];
//					}
//					if (min > students[i]) {
//						min = students[i];
//					}
					sum += students[i];
				}
				avg = (double) sum / students.length;
				System.out.println("최대값 : " + max);
				System.out.println("최소값 : " + min);
				System.out.printf(" 평균      : %.2f\n", avg);
			}
			else if ( selectNo == 5 ) {
				System.out.println("이용해주셔서 감사합니다.");
				run = false;
			}
		}while(run);
	}
	
	public static void main(String[] args) {
		/*
		 객체 선언 => 사용
		 */
		Student studentstart = new Student();
		studentstart.menu();
	}

}
