package _0425SAT_Package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BufferReadTest {

	int addFunction(int start, int end) {
		return (end - start  + 1)  * (start + end) /2;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		String s  = bf.readLine(); //String
		System.out.print("입력해주세요 : ");
		int i = Integer.parseInt(bf.readLine()); //Int
		System.out.print("다음 : ");
		int j = Integer.parseInt(bf.readLine());
		int n = 0;
		
		BufferReadTest test = new BufferReadTest();
		n = test.addFunction(i, j);
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		bw.write(n+"\n"); // 출력
		bw.flush(); // 남아있는 데이터를 모두 출력시킴
		bw.close(); //스트림을 닫음
		// BufferedWriter의 경우 버퍼를 잡아 놓았기 때문에 반드시 flush() / close()를 호출해준뒤 처리해야한다.
		// bw.write에는 sysout 처럼 자동개행이 없어서 개행을 해줘야한다.
	}

}
