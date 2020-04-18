package _0418SAT;

public class ForEx7 {

	public static void main(String[] args) {
		int N = 6;
		int i;
		int j;
		
		for ( i = 0; i < N; i++) {
			for( j = N - i - 1; j > 0; j--) {
				System.out.print("-");
			}
			for(j = 0; j < i; j++) {
				System.out.print("*");
			}
		System.out.println();
		}
		
	}

}
