package app;

public class CastEx {

	public static void main(String[] args) {
//		데이터 타입 및 형변환 (묵시적, 명시적)
		byte byte1 = 127;
		short short1;
		char char1 = 'a';
		int int1;
		float float1;
		long long1;
		double double1;
		
		short1 = byte1; // byte(8 bits) => short (1 bits)
		int1 = short1;  // short(16 bits) => int(32 bits)
//		int1 = char1;   // char(16 bits) => int(32 bits)
		long1 = int1;   // int(32 bits) => long(64 bits)
		float1 = int1;  // int(32 bits) => float(32 bits)
		double1 = long1;// long(64 bits) => double(64 bits)
		
		System.out.println("short : " + short1);
		System.out.println("int : " + int1);
		System.out.println("long : " + long1);
		System.out.println("float : " + float1);
		System.out.println("double : " + double1);
		
//		byte byte1 = 127;
//		int sum = 128;
//		
//		byte1 = (byte)sum;
//		
//		System.out.println(byte1);
		
		
	}

}
