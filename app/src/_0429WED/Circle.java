package _0429WED;

public class Circle extends Shape{
	
	protected int r;
	
	public Circle() {
		r = 0;
	}
	
	public Circle(int r) {
		this.r = r;
	}
	
	public void setRadius(int r) {
		this.r = r;
	}
	public int getRadius() {
		return r;
	}

	@Override
	public double area() {
		return Math.PI * r * r;
	}

	@Override
	public double circumference() {
		return Math.PI * 2 * r;
		
	}
	
}
