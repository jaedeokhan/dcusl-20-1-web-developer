package Person;

class  PersonInfo{
	// 인자로 전달된 사람의 잠자는 스타일을 출력해주는 메소드 정의!
	// 다형성을 이용하지 않으면 어떤 식으로 정의를 해야하는가?
	/*
	 * public void printSleepInfo(President president) {
	 * president.showSleepingStyel(); } public void printSleepInfo(Student student)
	 * { student.showSleepingStyel(); } public void printSleepInfo(Employee
	 * employee) { employee.showSleepingStyel(); }
	 */
	
	// 위와 같이 하면 코드의 중복성이 발생하고 객체를 여러 개를 생성해야한다는 낭비가 존재!
	// 그래서, 다형성을 사용하면 parameter 타입이 person으로 사용한다.
	// 다형성을 사용하면 만약? 인자로 넘어온 사람이 학생일 때는 study()를 호출하라!
	public void printSleepingInfo(Person person) {
		person.showSleepingStyel();
		if (person instanceof Student) {
			// person 변수의 reference 타입을 Student 타입으로 변환 할 수 있는가를 판단!
			Student student = (Student)person;
			student.study();
		}
	}
	
}

public class PersonEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonInfo pi = new PersonInfo();
		President presient = new President();
		Student student = new Student();
		Employee employee = new Employee();
		
		pi.printSleepingInfo(presient);
		pi.printSleepingInfo(student);
		pi.printSleepingInfo(employee);
		
		// 배열로도 저장이 가능하다.
		System.out.println();
		System.out.println("배열을 사용해서 출력하는 방법!!!");
		Person[] personArray = new Person[3];
		personArray[0] = presient;
		personArray[1] = student;
		personArray[2] = employee;
		
		
		for (int i = 0; i < personArray.length; i++) {
			personArray[i].showSleepingStyel();
		}
		
		
	}

}
