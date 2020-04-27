package _0420MON_Array;

public class ArrayEx5 {

	public static void main(String[] args) {
//  	12명의 학생들을 출석부 순으로 2열 6행으로 자리 배치를 하여 분단을 나누고,
//		1분단 왼쪽부터 오른쪽, 1행에서 아래 행으로 번호 순으로 자리를 배치하시오		 
		String student[] = {"홍길동", "이순신", "유관순", "윤봉길", "장영실", "임꺽정",
							"장보고", "이태백", "김정희", "대조영", "김유신", "이사부"};
        String part1[][] = new String[3][2];
        String part2[][] = new String[3][2];
        int ctn = 0;
        
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
	}

}
