package ch06.exam08;

public class Calculator {
	// Field
	private static Calculator singleton = new Calculator();
	// Constructor
	private Calculator() {
		
	}
	
	static Calculator getInstance() {
		return singleton;
	}
}
