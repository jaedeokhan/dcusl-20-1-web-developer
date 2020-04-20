package _0420MON;

public class ArrayEx8 {

	public static void main(String[] args) {
		   // 성적표 출력
        int arr[][] = {{80, 90, 77}, {78, 97, 86},{71, 68, 88}};
        String name = "";
        int sum = 0;
        int avg = 0;
        
        System.out.println("================= A반 성적표 =================");
        System.out.println("  이름        국어          영어          수학          합계            평균");
        System.out.println("------------------------------------------");
        
        for (int i = 0; i < arr.length; i++) {
        	for (int j = 0; j < arr[i].length; j++) {
        		System.out.print(arr[i][j] + " ");
        	}
        	System.out.println();
        }
	}
}
