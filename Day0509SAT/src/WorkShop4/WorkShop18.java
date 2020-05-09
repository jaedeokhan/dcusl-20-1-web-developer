package WorkShop4;

import java.util.Scanner;

class HealthSuper1{
	char gender;
	double tall;
	double weight;
	
	void input() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***** 비만도 Check *****");
		System.out.print("성별(M/F) : ");
		gender = scan.next().charAt(0);
		System.out.print("신장(Cm) : ");
		tall = scan.nextDouble();
		System.out.print("체중(Kg) : ");
		weight = scan.nextDouble();
		
	}
	
	public void output() {
		System.out.println("성별 : " + gender);
		System.out.println("신장 : " + tall + "Cm");
		System.out.println("체중 : " + weight + "Kg");
		System.out.println();
	}
}

class HealthChild1 extends HealthSuper1{
	private double s_weight; // standard weight
	private double fat;      // 비만도
	private String result;   // 결과값
	
	public void calculate(){
		if (gender == 'M') {
			s_weight = (tall - 100) * 0.9;
			fat = ((weight / s_weight) * 100);
		}
		else {
			s_weight = (tall - 100) * 0.85;
			fat = ((weight / s_weight) * 100);
		}
		
		if (fat > 150) {
			result = "고도비만";
		}
		else if (fat >= 131 && fat <= 150) {
			result = "중도비만";
		}
		else if (fat >= 121 && fat <= 130) {
			result = "경도비만";
		}
		else if (fat >= 111 && fat <= 120) {
			result = "과체중";
		}
		else if (fat >= 91 && fat <= 110) {
			result = "정상(표준체중)";
		}
		else {
			result = "저체중";
		}
	}
	
	public void output2() {
		System.out.printf("당신의 비만도는 %.2f%%이고, %s입니다.", fat, result);
	}
}
public class WorkShop18 {

	public static void main(String[] args) {
		HealthChild1 hc1 = new HealthChild1();
		hc1.input();
		hc1.output();
		
		hc1.calculate();
		hc1.output2();

	}

}
