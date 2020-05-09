/*
Workshop2
1. Super클래스명 : HealthSuper
필드명(protected) : char gender(성별)
                    double tall (신장)
                    double weight(체중)
생성자 : Health() - 초기화
메소드 : input() - 성별, 신장, 체중입력받기
           output1() - 출력

2. Sub클래스 : HealthChild
필드명(private) : double s_weight (표준체중)
                  double fat (비만도)
                  String result (결과값)
메소드 : calculate() - 비만도 계산
           output2()  - 출력

=======================================================
★표준체중계산법★
남성 : 표준체중 = (신장-100)*0.9
여성 : 표준체중 = (신장-100)*0.85

비만도 = 현재체중(Kg)/표준체중(Kg)*100
-------------------------------------------------------
90%이하  : 저체중               121-130% : 경도비만
91 -110% : 정상(표준체중)       131-150% : 중도비만
111-120% : 과체중               150%     : 고도비만

3. 출력폼

***** 비만도 Check *****
성별(M/F) : M          <---남(M) / 여(F)
신장(Cm) : 175.3
체중(Kg)  : 95.45

성별 - M 
신장 - 175.3Cm
체중 - 95.45Kg

당신은 비만도 ?.??이고,  중도비만 입니다.

 */
package WorkShop4;

import java.util.Scanner;

class HealthSuper{
	public static Scanner scan = new Scanner(System.in);
	protected char gender;
	protected double tall;
	protected double weight;
	
	HealthSuper(char gender, double tall, double weight){
		this.gender = gender;
		this.tall = tall;
		this.weight = weight;
	}
	
	public void input() {
		// 성별, 신장, 체중 입력받기.
		System.out.println("**** 비만도 Check ****");
		System.out.print("성별(M/F) : ");
		gender = scan.next().charAt(0);
		System.out.print("신장(Cm) : ");
		tall = scan.nextDouble();
		System.out.print("체중(Kg) : ");
		weight = scan.nextDouble();
	}
	
	public void output1() {
		System.out.println();
		System.out.println("성별 : " + gender);
		System.out.println("신장 : " + tall );
		System.out.println("체중 : " + weight);
		System.out.println();
	}
}

class HealthChild extends HealthSuper{
	HealthChild(char gender, double tall, double weight) {
		super(gender, tall, weight);
	}

	private double s_weight;
	private double fat;
	private String result;
	
	public void calculate(){
		s_weight = (gender == 'M') ? (tall - 100) * 0.9 : (tall - 100) * 0.85;
		fat = ((weight / s_weight) * 100);
		
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
		System.out.printf("당신의 비만도는 %.2f%% 이고, 결과는 %s 입니다.", fat, result);
	}
	
}

public class WorkShop2 {

	public static void main(String[] args) {
		HealthChild hc = new HealthChild('M', 180.1, 75.5);
		hc.input();
		hc.output1();
		
		hc.calculate();
		hc.output2();

	}

}
