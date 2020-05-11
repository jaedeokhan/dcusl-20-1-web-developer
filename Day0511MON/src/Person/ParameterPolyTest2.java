// 가전제품 여러 종류를 주문하는 기능을 다형성을 이용해서 처리하라.
package Person;

class Gajun{
	
}

class Tv extends Gajun{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Tv";
	}
}

class Computer extends Gajun{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Computer";
	}
}

class Radio extends Gajun{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Radio";
	}
}

// 제품을 구매하는 기능을 구현하는 클래스
class Buyer {
	// 제품을 구매하는 메소드를 정의하기.
	// 만약 다형성을 사용하지 않는다면?
	// method overloading 
	// 다형성을 이용하지 않는다면? 즉 가전제품이 수억개가 된다면 다 작성을 해줘야 한다.
	/*
	 * public void order(Tv tv) {
	 * 
	 * } public void order(Computer com) {
	 * 
	 * } public void order(Radio radio) {
	 * 
	 * }
	 */
	// 현재 주문한 제품의 목록을 보여주는 요구사항 추가!
	Gajun[] orderArray = new Gajun[3];
	int index = 0;
	
	public void order(Gajun gajun) {
		// Java에서 객체가 print 메소드의 인자로 지정되거나 "+" 
		// 연산이 되면 해당 객체의 toString() 메소드가 자동으로 호출된다. 
		System.out.println(gajun + " 을(를) 주문했습니다.");
		orderArray[index++] = gajun;
	}
	
	// Tv, Computer, Radio
	public void showOrderList() {
		String orderList = "";
		for (int i = 0; i < orderArray.length; i++) {
			orderList += (i == 0) ? orderArray[i] : "," + orderArray[i] ;
		}
		System.out.println("당신이 주문한 제품은 " + orderList + "입니다.");
	}
	
}


public class ParameterPolyTest2 {
	
	
	public static void main(String[] args) {
		
		Buyer buyer = new Buyer();
		buyer.order(new Tv());
		buyer.order(new Computer());
		buyer.order(new Radio());
		
		buyer.showOrderList();
		
		
		
	}

}
