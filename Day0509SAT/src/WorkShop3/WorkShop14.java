package WorkShop3;

class Entry1{
	String word;
	
	public Entry1(){
		System.out.println("**** 약어사전 ****");
	}
	
	Entry1(String w){
		this();
		this.word = w;
		writeView1();
	}
	
	public void writeView1() {
		// 약어출력 
		System.out.println("약어 : " + word);
	}
}

class SubClass1 extends Entry1{
	String defintion;
	int year;
	
	SubClass1(String w){
		super(w);
	}
	// this : 자기자신 객체를 가리키는 참조변수
	// 자기 자신 클래스내에서 변수나 메소드를 호출하면 기본적으로 앞에 this가 지정됨
	// 같은 클래스내에서는 this를 잘 사용하지 않는다.
	// 단, 파리미터 변수 이름과 멤버 변수 이름이 동일할 때는 파라미터 변수가 우선 인식이 된다.
	// 이 경우 멤버변수 값을 초기화 하려면 멤버변수 앞에 this.를 붙여야 한다.
	SubClass1(String w, String d, int y){
		this(w);
		this.defintion = d;
		this.year = y;
		printView1();
	}
	
	public void printView1() {
		//원어. 시기출력
		System.out.println("원어 : " + defintion);
		System.out.println("약어 : " + year);
	}
}



public class WorkShop14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubClass1 pp = new SubClass1("OOP","Object Oriented Programming", 1991);

	}

}
