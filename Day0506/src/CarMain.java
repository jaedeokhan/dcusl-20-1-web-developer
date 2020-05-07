import java.util.Scanner;

interface Speed{
	int SPEED = 10;	
	int upSpeed(int amount);
	int downSpeed(int amount);
	void stop();
}

interface Display{
	void disp(); // 계산, 출력
}

class Car implements Speed, Display{
	int inputVelocity;
	int velocity = SPEED;
	
	void input() {
		// 속도를 입력받고 disp() 호출하라.
		Scanner scan = new Scanner(System.in);
		System.out.print("속도입력 : ");
		inputVelocity = scan.nextInt();
		disp();
	}
	
	@Override
	public void disp() {
		// 속도 계산, 출력
		if (inputVelocity >= 0) {
			upSpeed(inputVelocity);
		}
		else {
			downSpeed(inputVelocity);
		}
		
		if (velocity == 0) {
			stop();
		}
		else {
			System.out.println("현재 속도는 " + velocity);
		}
	}
	@Override
	public int downSpeed(int amount) {
		velocity += amount;
		return 0;
	}
	@Override
	public int upSpeed(int amount) {
		velocity += amount;
		return 0;
	}
	@Override
	public void stop() {
		System.out.println("멈췄습니다.");
	}
}

public class CarMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car car = new Car();
		boolean stop = false;
		while (!stop) {
			car.input();
			if (car.velocity == 0) {
				stop = true;
				
			}
		}
	}

}
