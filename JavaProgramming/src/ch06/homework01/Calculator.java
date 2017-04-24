package ch06.homework01;

public class Calculator {
	void powerOn() {
		System.out.println("전원을 켭니다.");
	}
	
	int plus(int x, int y) {
		int result = x + y;
		return result;
	}
	
	double divide(double x, double y) {
		double result = x / y;
		return result;
	}
	
	void powerOff() {
		System.out.println("전원을 끕니다.");
	}
}
