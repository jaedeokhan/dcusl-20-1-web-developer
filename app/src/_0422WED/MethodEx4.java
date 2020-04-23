package _0422WED;

public class MethodEx4 {
	static void prn(int... num) {
		// ... 은 무엇일까?
		for (int i = 0; i < num.length; i++) {
			System.out.println(num[i]);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		prn(1, 2);
		prn(3);
		
	}

}
