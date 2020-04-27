package _0418SAT_BankMenu;

public class ForEx9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		
		for (int i = 5; i > 0; i--) {
			for (int j = 0; j < 5; j++) {
				if(i > j) {
					System.out.print("*");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		
		
		for (int i=0; i < 5; i++) {
			for(int j=0; j < 5; j++) {
				if (j >= i) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		
		
		for(int i = 5; i > 0; i--) {
			for (int j=1; j <= 5; j++) {
				if (j < i) {
					System.out.print(" ");
				}else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}
	

}
