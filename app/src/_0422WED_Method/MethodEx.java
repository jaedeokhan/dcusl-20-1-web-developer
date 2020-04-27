package _0422WED_Method;

public class MethodEx {

	void gugu(int a, int b) {
		int sum = 0;
		
		
	}
	
	int intA() {
		int num = 10;
		System.out.println("호출되나?");
		return num;
	}
	
	static void intB(int a, String b) {
		System.out.println("intB 가 실행됨" + a + b);
	
	}
	public static void sum() {
		int sum = 0;
		for(int i =0; i < 10; i++) {
			sum += i;
		}
		System.out.println("10의 합: " + sum);
	
	}

	public static int sumSu(int a) {
		int sum = 0;
		for(int i =0; i < a; i++) {
			sum += i;
		}
//		System.out.println("10의 합: " + sum);
		return sum;
	}
	public static void main(String[] args) {
		/*
		 메소드(Method)
		 [접근 제어자] [수정자] [반환형] 메소드명 (인자들){
		 }
		 접근 제어자(Modifiers)
		 private, default(friendly), protected, public
		 
		 기타 제어자 
		 static, final, abstract, native '''
		 
		 반환형(return type)
		 - 자바 데이터 자료형(기본형, 참조형) 전부 사용할 수 있음.
		 - void: 반환형이 없는 메소드 정의시 사용.
		 
		 메소드명 : 식별자로 임의의 이름 정의.
		 
		 인자(Arguments)
		 매개 변수라고 하며 메소드 호출 시 데이터를 전달 하기 위한 용도로 사용함.
		 */
//		int aa = 10;
//		String bb = "test";
////		a = intA();
//		intB(aa, bb);	
//		
		int a = 15;
		sum();
		int result = sumSu(a);
		System.out.println("result : " + result);
		
		
	}
	
}
