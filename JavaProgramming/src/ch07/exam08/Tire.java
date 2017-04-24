package ch07.exam08;

public abstract class Tire {
	int diameter;
	
	
	public Tire() {
		System.out.println("Tire 객체 생성");
	}
	public int getDiameter() {
		return diameter;
	}
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	
	abstract void roll();
}
