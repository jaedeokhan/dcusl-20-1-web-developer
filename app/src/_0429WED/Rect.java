package _0429WED;

public class Rect extends Shape{

	protected int width, height;
	public Rect() {
		this(0, 0);
	}
	
	public Rect(int w, int h) {
		width = w;
		height = h;
		x = getWidth();
		y = getHeight();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setSize(int w, int h) {
		width = w;
		height = h;
	}
	
	public int getSize() {
		return x * y;
	}
	
	@Override
	public double area() {
		return width * height;
	}

	@Override
	public double circumference() {
		return 2 * (width + height);
	}

}
