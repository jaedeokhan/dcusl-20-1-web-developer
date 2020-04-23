package _0421TUE;

public class MultiForEx3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 문제 2)) 홀수, 짝수 구분하기
		int [][] array = {
				{95, 86},
				{83, 92, 96},
				{78, 83, 93, 87, 88}
		};
		int [] even = new int[5];
		int [] odd = new int[5];
		int even_ct = 0;
		int odd_ct = 0;
		
		for (int i = 0;  i< array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(array[i][j] % 2 == 0) {
					even[even_ct] = array[i][j];
					System.out.println("짝수 : " + even[even_ct]);
					even_ct++;
				}
				else {
					odd[odd_ct] = array[i][j];
					System.out.println("홀수 : " + odd[odd_ct]);
					odd_ct++;
				}
			}
		}
	
//		System.out.println("짝수 : " + even);
//		System.out.println("홀수 : " + odd);
	}

}
