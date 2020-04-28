package _0428TUE;

class X{
	protected int i = 10;
	protected String msg = "I am an X.";
	
	public void print() {
		System.out.println(msg);
	}
	
	public void play() {
		System.out.println("Play.."+msg);
	}
}

class Y extends X{
	protected int i = 20;
	protected String msg = "I am a Y.";
	
	@Override
	public void print() {
		System.out.println(msg);
	}
	
}

public class Z extends Y{
	int i = 30;
	protected String msg = "I am a Z.";
	
	@Override
	public void print() {
		System.out.println(msg);
	}
	
	@Override
	public void play() {
		System.out.println("Play.." +msg);
	}
	
	public void doZ() {
		System.out.println("do something in Z.");
	}
	
	public void test(int i) {
		// Z > Y > X
		Z z = new Z();
		Y y = z;
		X x = z;
		//문제)
		System.out.print("1 "); z.print(); // 1 = I am a Z.
		System.out.print("2 "); y.print();    // 2 = I am a Z.
		System.out.print("3 "); x.print();    // 3 = I am a Z.
		System.out.print("4 "); super.print();// 4 = I am a Z.      (x) => I am a Y.
		System.out.print("5 "); play();       // 5 = Play..I am a Z.
		System.out.print("6 "); super.play(); // 6 = Play..I am a Z.(x) => Play..I am X
		//y.doZ();    // null
		//super.super.print(); // super.super는 키워드를 사용할 수 없다.
		
		System.out.println("7 i =" + i);	// 7  = 15
		System.out.println("8 this.i =" + this.i ); // 8 = 30
		System.out.println("9 super.i = " + super.i ); // 9 = 20 
		System.out.println("10 y.i = " + y.i);  // 10 = 20 
		System.out.println("11 x.i = " + x.i);  // 11 = 10
		System.out.println("12 ((Y)this).i = " + ((Y)this).i); //12 = 20
		System.out.println("13 ((X)this).i = " + ((X)this).i); //13 = 10
		//super.super.i = 10;                               // error
	}
	public static void main(String[] args) {
		Z z = new Z();
		z.test(15);
		
		
		
		
	}

}
