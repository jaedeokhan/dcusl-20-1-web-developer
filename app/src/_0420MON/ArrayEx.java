package _0420MON;

public class ArrayEx {

	public static void main(String[] args) {
		
		/* 
		 배열(array)
		 -같은 자료형의 데이터를 여러개 저장하기 위한 메모리 공간을 말함.
		 -생성된 메모리 공간에 index를 이용하여 각 기억장소를 구분함.
		 -배열 크기는 length를 사용하여 구함.
		 
		 표현)
		 - int [] arr = new int[n];
		 				// n개만큼 0으로 초기화.
		 - int [] arr = { 1, 2, 3};
		 - int [] arr = new int[]{1, 2, 3};
		 */
//		int [] arr  = new int[]{1,2,3};
//		
//		for (int i = 0; i <= 2; i++) {
//			System.out.println(arr[i]);
//		}
//		
//		String [] s_arr = new String[] {"test1", "test2", "test3"};
//		for (int i = 0; i <= 2; i++) {
//			System.out.println(s_arr[i]);
//		}
//		
//		char [] c_arr = new char[] {'A', 'B', 'C'};
//		for (int i = 0; i <= 2; i++) {
//			System.out.println(c_arr[i]);
//		}
		
		// list_size 와 .length 를 사용하기. 
		int list_size = 5;
		int [] arr = new int[list_size];
		int count = 0;
		
		for (int j = 0; j < 5; j++) {
			arr[j] = j + 1; 
		}
		
		for (int i = arr.length - 1; i >= 1; i--) {
			arr[i]  = arr[i - 1];
			if (i == 1) {
				arr[i] = 100;
				break;
			}
			count++;
		}
		System.out.println("count 개수 : " + count);
		System.out.println("내가 원하는 값 : " + arr[1]);
		
	}

}
