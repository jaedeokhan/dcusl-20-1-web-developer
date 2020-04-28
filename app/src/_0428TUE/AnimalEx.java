package _0428TUE;

public class AnimalEx {

	public static void main(String[] args) {
		Elephant ele = new Elephant();
		System.out.print("정보 : " + ele.name + "\t" + ele.age + "\t");
		ele.bark();
		
		
		Lion lion = new Lion();
		System.out.print("정보 : " + lion.name + "\t" + lion.age + "\t");
		lion.bark();
		
		
	}
}


