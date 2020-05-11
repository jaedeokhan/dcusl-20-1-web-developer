package Person;

public class Student extends Person{
	
	void study() {
		System.out.println("공부를 한다.");
	}
	
	@Override
	public void showSleepingStyel() {
		System.out.println("학생도 사람이다~");
	}
}
