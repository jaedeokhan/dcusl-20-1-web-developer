/*
3번. Workshop3
생성자 함수 overloading이용

main()에서
Workshop4 ob1 = new Workshop4(10,20);
Workshop4 ob2 = new Workshop4(12.4, 9.45);
Workshop4 ob3 = new Workshop4('Z', 'p');

계산과 출력은 생성자에서 할것

<출력> 
max = 20
max = 12.4
max = p

 */
package WorkShop3;

public class WorkShop3 {
	int max = 0;
	
	WorkShop3(int x, int y){
		this.max = (x > y) ? x : y ;
		System.out.println("max = " + (this.max));
	}
	
	WorkShop3(double x, double y){
		double max = (x > y) ? x : y; 
		System.out.println("max = " + (max));

	}
	
	WorkShop3(char x, char y){
		this.max = (x > y) ? x : y; 
		System.out.println("max = " + (char)(this.max));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WorkShop3 ob1 = new WorkShop3(10,20);
		WorkShop3 ob2 = new WorkShop3(12.4, 9.45);
		WorkShop3 ob3 = new WorkShop3('Z', 'p');

	}

}
