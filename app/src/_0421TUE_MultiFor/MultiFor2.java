package _0421TUE_MultiFor;

public class MultiFor2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 문제2) 
		int [][] array = {
				{95, 86},
				{83, 92, 96},
				{78, 83, 93, 87, 88}
		};
		int sum = 0;
		double avg = 0;
		int su = 0;
		
		for (int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				sum += array[i][j];
				su++;
			}
		}
		avg = sum / su;
		System.out.println("sum : " + sum);
		System.out.println("avg : " + avg);
		
		
		
		

	}

}
