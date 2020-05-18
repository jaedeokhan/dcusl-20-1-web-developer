### 2020 0506 WED 오정원 교수님과 첫 수업 => 문제 풀기.
1. for 문 두 문제
2. while, if 활용
3. 성적 산출 프로그램
4. 족구팀 짜기.
5. Car interface

#### 1. for 문제1)
```java
		for (int i = 1; i <= 5; i++) {
			
			for (int j = 1; j <= 5 - i; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= 2 * i - 1; j++) {
				System.out.print(j);
			}
			System.out.println();	
		}
		
		for (int i = 1; i <= 4; i++) {
			
			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= 9 - 2 * i; j++) {
				System.out.print(j);
			}
			System.out.println();	
		}
		
		System.out.println();

		// 2. 이중 for문 하나로 해결
		for (int i = 1; i <= 9; i++) {
			if (i <= 5) {
			for (int j = 1; j <= 5 - i; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= 2 * i - 1; j++) {
				System.out.print(j);
			}
			System.out.println();	
			}
			else {
				// i = 6
				for (int j = 1; j <= i - 5; j++) {
					System.out.print(" ");
				}
				for (int j = 1; j <= 19 - 2 * i; j++) {
					System.out.print(j);
				}
				System.out.println();	
			}
			
	}	
		System.out.println();
		// 3. 삼항 연산자를 사용해서 보다 간단하게 처리
		// (조건식) ? 조건식이 true일 때 반환할 값 : 조건식이 false일 때 반환할 값
		for (int i = 1; i <= 9; i++) {
			int spaceNumber = (i <= 5) ? 5 - i : i - 5;
			int maxNumber = (i <= 5) ? 2 * i - 1 : 19 - 2 * i;
			
			for (int j = 1; j <= spaceNumber; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= maxNumber; j++) {
				System.out.print(j);
			}
			System.out.println();	
			}
```

#### 1.1 for 직사각형 찍기.
```java
		for (int i = 1; i<=5; i++) {
			for (int j = 1; j <= 10; j++) {
				if (i == 1 || i == 5) { 
				System.out.print("*");
				}
				else {
					if (j == 1 || j == 10) {
						System.out.print("*");
					}
					else {
					System.out.print(" ");	
					}
				}
			}
			System.out.println();
		}
```

#### 2. Workshop2 => 1 - 2 + 3 - 4 + 5 - 6 = 100이상이 되면 빠져나가고, i의 값은?
```java

public class Workshop2 {

	public static void main(String[] args) {
		
		// 1 - 2 + 3 - 4 + 5 - 6 = 100 
		
		//  0 + 1 ==  1
		//  1 - 2 == -1
		// -1 + 3 ==  2
		//  2 - 4 == -2
		// -2 + 5 ==  3
		//  3 - 6 == -3
		
		int i = 1, ans = 0;
		
		while (ans <= 100) {
			
			ans += (i % 2 != 0) ? i : -i;
			i++;
//			if (i % 2 != 0) {
//				ans = ans + i; 
//			}
//			else {
//				ans = ans - i; 
//			}
//			i++;
//					
		}
		// 202 이 되면 멈춘다.
		System.out.println(i);
		
	}

}
```

#### 3. 성적 산출 프로그램
> WorkShop3.java
```java
import java.util.Scanner;

public class WorkShop3 {

	public static void main(String[] args) {
		/*
		 */
		Scanner scan = new Scanner(System.in);
	
		// 1. 입력
		System.out.print("중간고사 : ");
		int mid = scan.nextInt();
		System.out.print("기말고사 : ");
		int fin = scan.nextInt(); 
		System.out.print("레포트 : ");		
		int report = scan.nextInt();
		System.out.print("출석 : ");
		int attend = scan.nextInt();
		
		// 2. 연산(비지니스로직)
		double score = (mid + fin) / 2.0 * 0.6 + report * 0.2 + attend * 0.2;
		
		// 2.1 학점
		String grade = "";
		
		if (score >= 90) {
			grade = "A";
			System.out.println(grade);
		}
		else if (score >= 80) {
			grade = "B";
		}
		else if (score >= 70) {
			grade = "C";
		}
		else if (score >= 60) {
			grade = "D";
		}
		else {
			grade ="F";
		}
		
		// 2.2 평가
		String test = "";
		switch (grade) {
			case "A":
			case "B":
				test = "excellent";
				break;
			case "C":
			case "D":
				test = "good";
				break;
			case "F":
				test = "poor";
				break;
			}
		
		// 3. 응답
		System.out.printf("성적 = %.2f\n", score);
		System.out.print("학점 = " + grade +"\n");
		System.out.print("평가 = " + test );
	}

}
```


#### 4. 족구팀 프로그램
> WorkShop4.java

```java
import java.util.Random;

public class WorkShop4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 2반 체육 대회를 하기 위해서 족구 팀을 나누려 한다.
		 총 5팀으로 구성한다.
		 우선 각 팀에 조장은 5명을 조장으로 배치한다.
		 나머지 학생들은 나머지 팀 인원으로 배치하여 총 5팀을 랜덤하게 구성하여 출력한다.
		 
		 최종적으로 5개 배열에 각 학생들이 참조 되게 처리한다.
		 결과는 랜덤하게 구성 되어야 한다.
		 
		 최종 출력결과
		 1팀
		 조장 : 이재혁
		 팀원 : 박지용, 권용구, 김민석, 이은지 , 이희창, 박예슬
		 이렇게 세 팀을 출력되어야 한다.
		 */
		String[][] team = new String[5][6];
		String[] player = {"team1", "team2", "team3", "team4", "team5",
							 "1", "2","3","4","5",
							 "6","7","8","9","10",
							 "11","12","13","14","15",
							 "16","17","18","19","20",
							 "21", "22", "23","24","25"};
		
		// 팀장 배정
		for (int i = 0; i < team.length; i++) {
							// 정수 0-4를 랜덤으로 반환!
			int leaderIndex = (int)(Math.random() * 5);
			
			// 해당 인덱스의 이름이 이미 쓰였으면 해당 인덱스 영역에는 null을 할당한다.
			if (player[leaderIndex] == null) {
				i--;
			}
			else {
				//해당 팀의 0의 인덱스에 팀장을 넣어준다.
				team[i][0] = player[leaderIndex];
				player[leaderIndex] = null;
			}
		}
		//팀장 출력
//		for (int i = 0; i < team.length; i++) {
//			System.out.println((i + 1) + "팀 :" + team[i][0]);
//		}
		
		// 팀원  배정
		for (int i = 0; i < team.length; i++) {
			for (int j = 1; j <= 5; j++) {
				int memberIndex = (int)(Math.random() * 25) + 5;
				
				if (player[memberIndex] == null) {
					j--;
				}
				else {
					//해당 팀의 인덱스에 랜덤으로 넣어준다.
					team[i][j] = player[memberIndex];
					player[memberIndex] = null;
				}
			}
		}
		
		// 팀원 출력
		for (int i = 0; i < team.length; i++) {
			System.out.println((i + 1) + "팀 :" + team[i][0]);
			
			System.out.print("팀원 : ");
			for (int j = 1; j <= 5; j++) { 
				System.out.print(team[i][j] + " ");
				
			}
			System.out.println();
		}
```


#### 5. interface , car 클래스 
> CarMain.java

```java
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
```
