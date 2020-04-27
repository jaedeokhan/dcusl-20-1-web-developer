package _0418SAT_BankMenu;

public class ForContinueEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int k = 0;
		
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				continue;
			}
			k += i;
			System.out.println("얘는 홀수만 나온다. : " + i );
		}
		System.out.println("홀수의 값 : " + k);
		
	}

}
