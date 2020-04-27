package _0427MON_extends;

public class InherEX3 {

	public static void main(String[] args) {
		// upcasting => 하위 클래스가 상위 클래스를 참조
		Food food = new SeaFood();
		food.print();
		
		SeaFood sea_food = (SeaFood)food; // DownCasting - 하위 SeaFood에 상위 Food를 담음
		sea_food.print();
	}

}

class Food {
	
	void print() {
		System.out.println("FOOD");
	}
}

class SeaFood extends Food{
	
	void print() {
		System.out.println("SEAFOOD");
	}
	
}