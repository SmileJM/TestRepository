package ch12.exam04;

public class User1 extends Thread{
	private Calculator calculator;

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	public User1() {
		super("User1");
	}

	@Override
	public void run() {
		calculator.setMemory(100);
	}
}
