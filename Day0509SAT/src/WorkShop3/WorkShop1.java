/*
 * 1번. Workshop1
부모클래스 : Entry
 * 	     필드명 : String word;
 * 	     메소드 : Entry(); //****약어사전 *****(출력)
 * 		    Entry(String w);  /Entry()호출
 * 		    public void writeView(); // 약어출력
 * 
 자식클래스 : SubClass (Entry를 상속받음)
 * 	     필드명 : String definition;
 * 		 int year;
 * 	    생성자: SubClass(String w); // Entry(String w)호출
 * 	              SubClass(String w, String d, int y); // Subclass(String w)호출
 * 	              public void printView(); //원어. 시기출력
 * 
 * 3. main에서
 * 		SubClass pp = new SubClass("OOP","Object Oriented Programming", 1991);
 * 4. 출력
 * 
 * *****약어사전******
 * 약어 : OOP
 * 원어 : Object Oriented Programming
 * 시기 : 1991년
 */
package WorkShop3;
class Entry{
	String word;
	public void Entry() {
		// 약어사전 출력
		System.out.println("*****약어사전******");
	}
	Entry(String w){
		this.word = w;
		// Entry 호출
		Entry();
//		writeView();
	}
	
	public void writeView() {
		// 약어출력
		System.out.println("약어 : " + word);
	}
		
	
}

class SubClass extends Entry{
	String definition;
	int year;
	SubClass(String w){
		super(w);
	}
	
	SubClass(String w, String d, int y){
		// Subclass(String w)호출
		this(w);
		this.definition = d;
		this.year = y;
//		printView();
		
	}
	 public void printView() {
		 //원어. 시기출력
		 System.out.println("원어 : " + definition);
		 System.out.println("시기 : " + year);
	 }
}


public class WorkShop1 {

	public static void main(String[] args) {
		
		// **약어사전** 출력
		SubClass pp = new SubClass("OOP","Object Oriented Programming", 1991);
		
		// 약어 출력
		pp.writeView();
		
		// 원어, 시기 출력
		pp.printView();
	}

}
