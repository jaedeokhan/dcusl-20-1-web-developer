// Object 클래스에서 제공하는 equlas() 메소드는 원래 기능이 == 이다. 
// 즉 reference 를 비교하는 것이다.
package Object;

class Student1 {
	String name;
	int idNumber;
	public Student1(String name, int idNumber) {
		super();
		this.name = name;
		this.idNumber = idNumber;
	}
	
	// 원하는 규칙으로 객체를 비교하려면 equals() 메소드를 규칙에 맞게 재정의해서 사용하면 된다.
	@Override
	public boolean equals(Object obj) {
		
		Student1 student = null;
		// instanceof 란? =>  obj 변수의 reference 타입을 Student1 타입으로 변환 할 수 있는가를 판단!
		if (obj  instanceof Student1) {
			student = (Student1)obj;
			
		}
		return student != null &&
			   name.equals(student.name) &&
			   idNumber == student.idNumber;
	}
}

public class EqualsTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student1 student1 = new Student1("오정원", 1510);
		Student1 student2 = new Student1("오정원", 1510);
		
		System.out.println("equlas() 메소드를 사용해서 비교 ");
		System.out.println("student1.equals(student2) : " + student1.equals(student2));
		
		
	}

}
