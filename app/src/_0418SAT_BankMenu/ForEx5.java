package _0418SAT_BankMenu;

public class ForEx5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		int j;
		for (i = 1; i <= 5; i++) {
			for (j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		System.out.println();
		
		int i1;
		int j1;
		for (i1 = 0; i1 > 5; i1++) {
			for (j1 = 5; j1 < i1; j1--) {
				System.out.println("*");
			}
			System.out.println();
		}

	}
}
