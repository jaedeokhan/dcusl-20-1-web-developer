// 상수의 단점을 보완하기 위한 ENUM
class Student1{
	SchoolType shcool;

	public Student1(SchoolType shcool) {
		super();
		this.shcool = shcool;
	}
	
}
enum SchoolType{
	ELEMENTARY, MIDDLE, HIGH, UNIVERSITY;
}

public class EnumTest2 {

	public static void main(String[] args) {
		// 
		Student1 stu = new Student1(SchoolType.ELEMENTARY);
		
		System.out.println("enum 타입의 데이터 출력 ");
		System.out.println("student1.school = " + stu.shcool);
		
		if (stu.shcool == SchoolType.ELEMENTARY) {
			System.out.println("초등학생");
		}
		
//  요 놈은 당연히 되지 않는다! 
//		if (stu.school == 1) {
//			
//		}
	}

}
