package _0425SAT_Package;

public class EncapEx {

	public static void main(String[] args) {
		/*
		접근 제어자(Access modifiers)
		 public < protected < default < private
		
		 -클래스 표현 형식)
		 [접근 지정자] class 클래스명
		 
		 -메서드 표현 형식)
		 [접근 지정자] [자료형] 메서드 명(인자들)
		 
		 -생성자 표현
		 [접근 지정자] 클래스 명(인자들)
		 
		 -변수 표현
		 [접근지정자][자료형]  변수명 = 데이터;
		*/
		Encap encap = new Encap();
		int su1 = encap.a; // 접근 허용
		int su2 = encap.b; // protected는 같은 패키지, 또는 다른 패키지 상속 모두 가능
		int su3 = encap.c; // default 해당 클래스, 같은 패키지 , 다른 패키지는 불가능
//		int su4 = encap.d(); // private 은 불가능 , 클래스내에서만 가능
		encap.setD(100);
		int su5 = encap.getD();
		
		System.out.println("public : " + su1);
		System.out.println("protected : " + su2);
		System.out.println("default : " + su3);
		System.out.println("privtate 은 불가능! : su4");
		System.out.println("public method : " + su5);
	}

}


class Encap{
	public int a = 10;
	protected int b = 20;
	int c = 30;
	private int d = 40;
	public void setD(int d) {
		this.d = d;
	}
	public int getD() {
		
		return this.d;
	}
}
