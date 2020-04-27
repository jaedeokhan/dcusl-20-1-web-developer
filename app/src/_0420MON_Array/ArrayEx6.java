package _0420MON_Array;

import java.util.Scanner;

public class ArrayEx6 {

	public static void main(String[] args) {		
// 1번문제 자리 배치 후 학생 이름을 입력받고, 몇 분단 몇번째 줄 오른쪽/왼쪽 자리인지 검색하여 출력하시오		 
        String student[] = {"홍길동", "이순신", "유관순", "윤봉길", "장영실", "임꺽정",
        					"장보고", "이태백", "김정희", "대조영", "김유신", "이사부"};
        String part1[][] = new String[3][2];
        String part2[][] = new String[3][2];
        int ctn = 0;
        Scanner scan = new Scanner(System.in);
        String name;
        
        System.out.println("===1분단===");
        for(int i = 0; i < part1.length; i++) {
        	for (int j = 0; j < part1[i].length; j++) {        		
        		part1[i][j] = student[ctn];
        		System.out.print(part1[i][j] + " ");
        		ctn++;
        	}
        	System.out.println();
        }
        
        System.out.println("===2분단===");
        for (int i = 0; i < part2.length; i++) {
        	for (int j = 0; j < part2[i].length; j++) {
        		part2[i][j] = student[ctn];
        		System.out.print(part2[i][j] + " ");
        		ctn++;
        	}
        	System.out.println();
        }
        
        System.out.print("학생의 이름을 입력하세요 : ");
        name = scan.nextLine();
        String postname = "";
        String rowname = "";
        String colname = "";
        
        for(int i = 0; i < part1.length; i++) {
        	for (int j = 0; j < part1[i].length; j++) {
        		if (part1[i][j].equals(name)) {
        			postname = "1분단";
        			if (i == 0) {
        				rowname = "1행";
        			}
        			else if(i == 1) {
        				rowname = "2행";
        			}
        			else {
        				rowname ="3행"; 
        			}
        			if (j == 0) {
        				colname = "왼쪽";
        			}
        			else {
        				colname ="오른쪽";
        			}
        			
//        			System.out.println(part1[i][j]);
//        			System.out.println("1분단 : [" + i + "]" + "[" + j + "]");
        			System.out.println(part1[i][j] + ": " + postname + rowname + colname);
        		}
        	}
        }
        
        for (int i = 0; i < part2.length; i++) {
        	for (int j = 0; j < part2[i].length; j++) {
        		if (part2[i][j].equals(name)) {
        			postname = "2분단";
        			if (i == 0) {
        				rowname = "1 번째 줄";
        			}
        			else if(i == 1) {
        				rowname = "2번째 줄";
        			}
        			else {
        				rowname ="3번째 줄"; 
        			}
        			if (j == 0) {
        				colname = "왼쪽";
        			}
        			else {
        				colname ="오른쪽";
        			}
//        			
//        			System.out.println(part2[i][j]);
//        			System.out.println("2분단 : [" + i + "]" + "[" + j + "]");
           			System.out.println(part2[i][j] + ": " + postname + rowname + colname);

        		}
        	}
        }
       

	}

}
