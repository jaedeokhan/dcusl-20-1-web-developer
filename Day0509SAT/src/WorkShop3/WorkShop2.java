/*
2번. Workshop2
1. Super클래스명 : SalarySuper
필드 :  String name(이름)
        int salary (급여)
생성자 : SalarySuper(String n, int s) 를 넘겨받아 name, salary에 대입
메소드 : getInfomation() - 이름과 연봉을 출력

2. Sub클래스(SalaySub) 에서 SalarySuper를 상속받음
필드 : String department (부서)
생성자 : SalarySub(String name, int salary, String department)를 넘겨받아
         name, salary,department에 대입(필요하면 부모 생성자 호출)
메소드 : getInfomation() – 이름,연봉,부서출력

main 메소드 : 
SalarySub  ob = new SalarySub(“오정원",15000000,“프리렌서");

<출력>
이름 : 오정원
급여 : 15000000
부서 : 프리렌서

 */
package WorkShop3;

class SalarySuper{
	String name;
	int salary;
	
	SalarySuper(String n, int s){
		// 넘겨받아 name, salary에 대입
		this.name = n;
		this.salary = s;
	}
	
	public void getInformation(){
		// 이름, 연봉, 부서 출력!
		System.out.println("이름 : " + this.name);
		System.out.println("부서 : " + this.salary);
	}
}

class SalarySub extends SalarySuper{
	String department;
	
	SalarySub(String name, int salary, String department){
		super(name, salary);
		this.department = department;
		// getInformation();
	}
	// 자식이 부모것을 오버라이딩하면 자식 것 출력
	@Override
	public void getInformation() {
		System.out.println("이름  : " + name);
		System.out.println("급여 : " + salary);
		System.out.println("부서 : " + department);
	}
}

public class WorkShop2 {

	public static void main(String[] args) {
		
		SalarySub  ob = new SalarySub("오정원", 15000000, "프리렌서");
		ob.getInformation();
	}

}
