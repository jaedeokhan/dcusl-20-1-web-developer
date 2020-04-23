package _0423THU;

public class Car {
	// field , 멤버 변수
	// speed , wheel, color 
	String company;
	String color;
	int speed;
	int wheel;
	
	// 생성자
	Car(){
		company = "현대자동차";
		color = "red";
		speed = 40;
		wheel  = 4;
	}
	
	// wheel 개수로 오토바이, 자동차 그 외 구분하기하는 메소드 
	void vehicleCheck(int wheel) {
		if (wheel <= 2) {
			System.out.println("wheel " + wheel + "개인 오토바이입니다.");
		}
		else if(wheel == 4) {
			System.out.println("wheel " + wheel + "개인 자동차입니다.");
		}
		else {
			System.out.println("wheel " + wheel + "개의 속성은 알 수 없습니다.");
		}
	}
	// 인자 set을 받아서 wheel 개수를 초기화하는 함수
	void wheelSet(int set) {
		wheel = set;
	}
	
	// print Company, Color
	void printCompany() {
		System.out.println(company);
	}
	
	void printColor(){
		System.out.println(color);
	}
	
	// speed 를 계속해서 5를 추가, 75이상이 되면 고속도로 진입하므로 100까지 증가
	void speedUp() {
		speed += 5;
		System.out.println("현재 속도는 " + speed + "입니다.");
		if (speed >= 75) {
			System.out.println("고속도로에 진입했습니다.");
			for (int i = 1, j =5; i <= 5; i++) {
					speed += j;
					System.out.println("악셀 : 현재 속도는" + speed + "입니다.");
			}
		}
	}
	
	// speed 를 
	void speedDown() {
		System.out.println("현재 속도는" + speed + "입니다.");
		if (speed >= 100) {
			System.out.println("현재 안전구역 운행중입니다. 속도를  50이하으로 낮춰주세요.");
			for (int i = 1, j = 10; i <= 5; i++) {
					speed -= j;
					System.out.println("브레이크 : 현재 속도는 " + speed + "입니다.");
			}
		}
	}
	public static void main(String[] args) {
		
		Car car = new Car();
		
		// company, color 확인하기.
		car.printCompany();
		car.printColor();
		System.out.println();
		
		// wheel 개수를 vehicleCheck 메소드로 체크하고, wheelSet(인자)메소드로 값 초기화하고 다시 wheel 체크
 		car.vehicleCheck(car.wheel);
		car.wheelSet(2);
		car.vehicleCheck(car.wheel);
		car.wheelSet(5);
		car.vehicleCheck(car.wheel);
		System.out.println();
		
		// speed 45 => 75 up
		car.speedUp();
		car.speedUp();
		car.speedUp();
		car.speedUp();
		car.speedUp();
		car.speedUp();
		System.out.println();
		
		// 고속도로 진입하면 speed 75 => 100 up
		car.speedUp();
		System.out.println();
		
		// 안전구역진입 speed 100 => 45 down
		car.speedDown();
	}

}
