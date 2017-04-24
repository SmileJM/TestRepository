package ch06.exam02;

public class Car {
	// Field
	String company = "현대자동차";
	int speed;
	String color;
	boolean airback;
	
	// Constructor
	Car() {
		this("현대 자동차", null, false);
	}
	Car(String color, boolean airback) {
		this("현대 자동차", color, airback);
	}
	
	Car(String company, String color, boolean airback) {
		
		this.company = company;				
		this.color = color;
		this.airback = airback;
	}

	// Method
	void run() {
		System.out.println("현재 " + speed + "km/h 로 달립니다.");
	}
}
