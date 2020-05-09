// 모든 문자는 문자코드 값을 가진다.
// 자바에서 int 타입보다 작은 정수 값으로 연산하면 프로모션 캐스팅이 된다.
// 즉, 무조건 int 타입으로 char , byte가 바뀐다.
package WorkShop2;

public class WorkShop11 {

	public static void main(String[] args) {
		
//		char c = 'A';
//		System.out.println(c + 1); // 66 
		char ch = 'z';
		boolean b = (ch >= 'A' && ch <= 'Z' ||
				     ch >= 'a' && ch <= 'z' ||
				     ch >= '0' && ch <= '9' ? true : false);
		
		System.out.println(ch + " = " + b);
		
		
	}

}
