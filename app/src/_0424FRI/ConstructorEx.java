package _0424FRI;

public class ConstructorEx {
	// 메소드, 생성자 오버로딩(Overroading)
	// 생성자의 이름은 모두 같아야한다.
	// 구별되는 것은 매개변수의 타입 , 매개변수의 수, 매개변수의 순서도 포함이 된다.
	
	// overroad시에 주의할 사항 : 오버로드를 하면 default 생성자는 존재하지 않는다.
	// this. super. 사용 
	// this(), super()
	ConstructorEx(){
		// 
		this(10);
		System.out.println("생성자 호출");
	}
	
	ConstructorEx(int a){
		this("String");
		System.out.println(a);
	}
	
	ConstructorEx(String a){
		System.out.println(a);
	}
	
	ConstructorEx(int a, String b){
		System.out.println(a + b);
	}
	
	ConstructorEx(String a, int b){
		System.out.println(a + b);
	}
	public static void main(String[] args) {
			int a = 10;
			String b = "두 번째 인자";
			
			new ConstructorEx(); // 생성자 호출
//			new ConstructorEx(a); // 10
//			new ConstructorEx(b); // 두 번째 인자
//			new ConstructorEx(a, b); //10두 번째 인자
//			new ConstructorEx(b, a); // 두 번째 인자10
	}

}
