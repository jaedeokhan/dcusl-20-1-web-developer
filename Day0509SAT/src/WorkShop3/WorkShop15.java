package WorkShop3;

class SalarySuper1{
	String name;
	int salary;
	
	SalarySuper1(String n, int s){
		name = n;
		salary = s;
	}
	
	void printInformation() {
		System.out.println("이름 : " + name);
		System.out.println("급여 : " + salary);
	}
}

class SalarySub1 extends SalarySuper1{
	String department;
	SalarySub1(String n, int s) {
		super(n, s);
	}
	
	SalarySub1(String name, int salary, String department){
		super(name, salary);
		this.department = department;
		printInformation();
	}
	
	void printInformation() {
		System.out.println("이름 : " + name);
		System.out.println("급여 : " + salary);
		System.out.println("부서 : " + department);
	}
	
	
}

public class WorkShop15 {
	public static void main(String[] args) {
		SalarySub1  ob = new SalarySub1("오정원",15000000,"프리렌서");

	}

}
