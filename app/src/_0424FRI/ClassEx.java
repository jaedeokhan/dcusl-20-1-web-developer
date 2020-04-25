package _0424FRI;

public class ClassEx {
	// field == 멤버 변수 == 클래스 변수 그리고 전역 변수와도 유사
	int age;
	int height;
	int weight;
	String name;
	
	// method
	int getAge() {
		return age;
	}
	
	void setAge(int age) {
		this.age = age;
	}
	
	String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	
	public static void main(String[] args) {
		ClassEx deok = new ClassEx();
//		deok = null;  // 객체의 참조를 끊는 방법!
		int age;
		deok.setAge(25);
		age = deok.getAge();
		System.out.println(age);
		
		String name;
		deok.setName("Han");
		name = deok.getName();
		System.out.println(name);
		
		
		
		
		
		
		
		
	}
}
