package WorkShop3;

public class WorkShop16 {
	
	WorkShop16(int a, int  b){
		System.out.println((a > b) ? (a) + " > " + (b) : 
						   (a < b) ? (a) + " < " + (b) :
						   (a) + " = " + (b) );
	}
	
	WorkShop16(double a, double b){
		System.out.println((a > b) ? (a) + " > " + (b) : 
			   (a < b) ? (a) + " < " + (b) :
			   (a) + " = " + (b) );
	}
	
	WorkShop16(char a, char b){
		System.out.println((a > b) ? (a) + " > " + (b) : 
			   (a < b) ? (a) + " < " + (b) :
			   (a) + " = " + (b) );
	}
	public static void main(String[] args) {
		
		
		WorkShop16 ob1 = new WorkShop16(10,20);
		WorkShop16 ob2 = new WorkShop16(12.4, 9.45);
		WorkShop16 ob3 = new WorkShop16('Z', 'p');
	}

}
