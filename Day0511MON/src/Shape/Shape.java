package Shape;

public abstract class Shape {
	// 생성자 생성 단축키 => Shift + Alt + s
	int nData1, nData2, nData3;

	public Shape(int nData1, int nData2) {
		super();
		this.nData1 = nData1;
		this.nData2 = nData2;
	}
	
	
	// 각 자식 클래스에서 오버라이딩할 메소드 정의
	// 메소드 기능 => 면접 즉 넓이를 구하는 메소드!	
	abstract public double getArea();
}


