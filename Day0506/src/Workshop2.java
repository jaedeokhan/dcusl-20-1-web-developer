
public class Workshop2 {

	public static void main(String[] args) {
		
		// 1 - 2 + 3 - 4 + 5 - 6 = 100 
		
		//  0 + 1 ==  1
		//  1 - 2 == -1
		// -1 + 3 ==  2
		//  2 - 4 == -2
		// -2 + 5 ==  3
		//  3 - 6 == -3
		
		int i = 1, ans = 0;
		
		while (ans <= 100) {
			
			ans += (i % 2 != 0) ? i : -i;
			i++;
//			if (i % 2 != 0) {
//				ans = ans + i; 
//			}
//			else {
//				ans = ans - i; 
//			}
//			i++;
//					
		}
		// 202 이 되면 멈춘다.
		System.out.println(i);
		
	}

}
