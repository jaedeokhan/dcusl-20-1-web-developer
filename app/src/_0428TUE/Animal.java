package _0428TUE;

public abstract class Animal {
	String name;
	int age;
	
	public Animal() {
		this.name = "동물";
		this.age = 0;
	}
	
	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public abstract void bark();
	
	
}

class Elephant extends Animal{
	
	String name;
	int age;
	
	public Elephant() {
		this.name = "elephant";
		this.age = 15;
	}
	
	@Override
	public void bark() {
		System.out.println("???????");
	}
}


class Lion extends Animal{
	
	String name;
	int age;
	
	public Lion() {
		this.name = "lion";
		this.age = 5;
	}
	
	@Override
	public void bark() {
		System.out.println("어흥");
	}
}

	



