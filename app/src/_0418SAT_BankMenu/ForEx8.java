package _0418SAT_BankMenu;

public class ForEx8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
			for(j = 0; j < i - 1; j++) {
				System.out.print("*");
			}

		System.out.println();
		}
		
	}

}
