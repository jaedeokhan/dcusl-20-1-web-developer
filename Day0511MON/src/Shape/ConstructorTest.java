package Shape;

public class ConstructorTest {

	public static void main(String[] args) {
		
		// Child.java는  Parent.java를 상속을 받아서 
		// 당연히 상속받은 부모의 객체가 먼저  즉 생성자가 생성이 되고, 그 다음에 자식의 생성자가 생성이 된다.
		Child ch = new Child();
		
	}

}
