package _0429WED;

public class ShapeUser {

	public static void main(String[] args) {
		// 도형을 담는 배열을 만들어라.
		Circle circle = new Circle(5);
		Circle circle2 = new Circle(7);
		Rect rect  = new Rect(5, 5);
		
		// 다형성 비유 설명
		Shape[] shape = new Shape[3];
		shape[0] = circle;
		shape[1] = circle2;
		shape[2] = rect;
		
		// 피자통!! -> 주문이 왔어. 계속해서 왔다갔다 하는 것보다는 피자통에 넣어서 한 번에 가져다 주는 것이 좋다.
		// 필요한거를 원할때 툭툭 끄집어낸다. 얘는 원이다. 원이다. 사각형이다!! 
		Circle c2  = (Circle)shape[0]; // 강제 캐스팅 => 자식으로 돌아간다. 
		for (int i = 0; i < shape.length; i++) {
		    System.out.println(i + "=area : " + shape[i].area()); // 오버라이드 메소드라서 가능하다 
		    										// 엄밀히 말하자면 강제 캐스팅을 해줘야한다.
		    // 부모의 타입인 shape로는 자식의 것을 사용하지 못한다.
		    System.out.println(i + "=cicum : " +shape[i].circumference());
		    System.out.println();
		}
		
		// 부모의 타입에서는 자식의 클래스에 접근을 하지 못하기 때문에 강제 형 변환을 해서 오버라이딩 한 메소드말고도 모두 다 접근을
		// 가능하게 만드는 것이다.
		Rect r = (Rect)shape[2];
		
		System.out.println();
		System.out.println(r.getSize());
		
	}
		

}
