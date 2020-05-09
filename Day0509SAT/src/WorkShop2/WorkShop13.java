package WorkShop2;

public class WorkShop13 {

	public static void main(String[] args) {
		
		int count = 0;
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				if((i + j) == 6)
					count++;
			}
		}
		System.out.println("두 수의 합이 6이 되는 경우는 : " + count + " 번 입니다.");
		
		
	}
}
