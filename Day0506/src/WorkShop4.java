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

	}

}















