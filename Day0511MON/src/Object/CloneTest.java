// clone() : 자기자신 객체를 복제하는 메소드
// 메소드를 하나도 정의하지 않고 특정 기능을 사용할 수 있는지만 판단하는데 사용하는 인터페이스들
// 마커 인터페이스라고 함
// Cloneable은 clone() 메소드를 사용할 수 있느냐먄 판단하는 메소드임
package Object;

class Member implements Cloneable{
	// interface를 상속 받았는데 추상 메소드를 선언하지 않아도 빨간줄이 뜨지 않는다.
	// 왜? Cloneable interface는  추상 메소드가 존재하지 않기 때문이다.
	String name;
	int number;
	public Member(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		return super.clone();
	}
	
}

public class CloneTest {
	public static void main(String[] args) throws Exception{
		
		Member member1 = new Member("오정원", 1510);
		
		// throws 구문으로 있으면 try-catch 처리를 해줘야한다.
		Member member2 = (Member)member1.clone();
		
		System.out.println("member1.name = " + member1.name + ","
				+ " member1.number = " + member1.number);
		System.out.println("member2.name = " + member2.name + ","
				+ " member2.number = " + member2.number);
		
	}
}
