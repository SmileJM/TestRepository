package ch06.exam04;

public class Tire {
	// Field
	String location;
	// Constructor
	Tire(String location) {
		this.location = location;
	}
	// Method
	void roll() {
		System.out.println(location + " 바퀴가 돌아감");
	}
}
