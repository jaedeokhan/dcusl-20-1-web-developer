package _0424FRI_Car2Constructor;

public class Car2 {
	int wheel;
	int speed;
	String name;
	String color;
	
	public Car2(){
		wheel = 4;
		speed = 200;
		name = "genesis";
		color = "gray";
		}
	
	Car2(String name){
		this.name = name;
		speed  =200;
		wheel = 4;

	}
	
	Car2(String name, int speed){
		this.name = name;
		this.speed = speed;
		wheel = 4;
	}
	
	Car2(String name, int speed, int wheel){
		this.name = name;
		this.speed = speed;
		this.wheel = wheel;
	}
	
	
	void setSpeed(int speed) {
		this.speed = speed;
	}
	
	int getSpeed() {
		return speed;
	}

	void display() {
		System.out.println("차 이름 : " + name + "\n최대 속도 : " + speed
							+ "\n바퀴수 : " + wheel + "\n차 색상 : " + color);
	}
	
	void display(String name, int speed, int wheel) {
		System.out.println("차 이름 : " + name + "\n최대 속도 : " + this.speed
							+ "\n바퀴수 : " + this.wheel + "\n차 색상 : " + color);
	}
	
	
	
	public static void main(String[] args) {
		Car2 car = new Car2();
		

	}

}
