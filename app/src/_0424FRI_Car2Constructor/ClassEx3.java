package _0424FRI_Car2Constructor;

public class ClassEx3 {

	public static void main(String[] args) {
		Car2 car = new Car2();
		car.display();
		System.out.println();
		
		car.wheel = 4;
		car.speed = 150;
		car.name = "르망";
		car.color ="red";
		car.display();
		
		
		System.out.println();
		Car2 car2 = new Car2();
		car2.setSpeed(0);
		car2.display();
		System.out.println();
		
		Car2 k3 = new Car2("K3");
		k3.setSpeed(500);
		k3.display();
		System.out.println();
		
		// this의 사용 유의!
		k3.display("K7", 700, 4);
	}

}

