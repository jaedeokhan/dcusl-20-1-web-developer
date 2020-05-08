// 상수의 2 % 부족한 점
class Student{
	public static final int ELEMENTARY = 1;
	public static final int MIDDLE = 2;
	public static final int HIGH = 3;
	public static final int UNIVERSITY = 4 ;	
	int school;
	
	public Student(int school) {
		super();
		this.school = school;
	}
}
public class EnumTest1 {
	
	public static void main(String[] args) {
		
		Student student1 = new Student(Student.ELEMENTARY); // ok
		Student student2 = new Student(2); // 상수의 의도와 벗어났다. 
		
		System.out.println("상수 값을 출력 ");
		System.out.println("student1.school = " + student1.school);
		System.out.println("student2.school = " + student2.school);
		
		// 제어문을 돌릴 때 가독성이 좋아서 바로 파악이 되야 하는데...
		// 2번 if문을 보면 잘 파악이 되지 않는다.
		// 그래서 enum을 사용한다.
		if (student1.school == Student.ELEMENTARY) {
			System.out.println("당신은 초등학생");
		}
		if (student2.school == 2) {
			System.out.println("당신은 중학생");
		}
		
	}

}
