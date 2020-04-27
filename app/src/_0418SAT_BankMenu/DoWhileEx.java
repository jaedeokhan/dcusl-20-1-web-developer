package _0418SAT_BankMenu;

public class DoWhileEx {

	public static void main(String[] args) {
		// do ~ while 문 은 while 문과는 다른 점은 한 번 실행시키고, 다음 조건문을 작성을 하는 것이다.
		/*
		 )표현
		  do {
		  		실행 구문;
		  	} while( 조건식 );
		 */
		
		int count = 0;
		
		do {
			count++;
			System.out.println("count : " + count);
		}while(count < 10); 			
	

	}

}
