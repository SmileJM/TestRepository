package ch12.exam04;

public class User2 extends Thread{
	private Calculator calculator;

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	public User2() {
		super("User2");
	}

	@Override
	public void run() {
		calculator.setMemory(50);
//		calculator.setMemory2(50);
	}
}
