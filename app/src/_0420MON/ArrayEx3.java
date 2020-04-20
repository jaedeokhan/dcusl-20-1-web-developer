package _0420MON;

public class ArrayEx3 {

	public static void main(String[] args) {
		/*
		 다차원 배열
		 */
		int i_size = 3;
		int j_size = 4;
		int [][]arr = new int[i_size][j_size];
		int su = 0;
	
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = su++;
				System.out.print("arr" + "[" + i + "]" + "[" + j +
						"]" + "=" + arr[i][j] +"\t");
			}
			System.out.println();
		}
		
		
		
		
		
	}
}

