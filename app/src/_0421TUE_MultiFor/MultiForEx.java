package _0421TUE_MultiFor;

public class MultiForEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 문제 1)
		 */
		int max = 0;
		int min = 0;
		int max2 = 0;
		int min2 = 0;
		int [] array = {1, 5, 3, 8, 2};
		int [][] array2 = {{1, 2, 3, 4, 5},
						   {6, 7, 8, 9, 10}};		
		
		for (int i = 0; i < array.length; i++) {
			if (max < array[i]) {
				max = array[i];
			}
			if (min > array[i]) {
				min = array[i];
			}
		}	
		System.out.println("1차원 배열 : " + max);
		System.out.println("1차원 배열 : " + min);
		
		
		for (int i = 0; i < array.length; i++) {
			max = (max < array[i]) ? array[i]  : max;
			min = (min > array[i]) ? array[i]  : min;
		}
		System.out.println("1차원 삼항 연산자 : "+max);
		System.out.println("1차원 삼항 연산자 : "+min);
		
		for (int i = 0 ; i < array2.length; i++) {
			for (int j = 0; j < array2[i].length; j++) {
				if (max2 < array2[i][j]) {
					max2 = array2[i][j];
				}
				else {
					min2 = array2[i][j];
				}
			}
		}
		System.out.println("2 차원 배열 max : " + max2);
		System.out.println("2 차원 배열 min : " + min2);
	}

}
