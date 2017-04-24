package ch06.homework01.car05;

public class Car {
	String model;
	int speed;
	
	Car(String model) {
		this.model = model;
	}
	
	void setSpeed(int speed) {
		this.speed = speed;
	}
	
	void run() {
		for(int i=10; i<=50; i+=10) {
			speed = i;
			System.out.println(this.model + "가 달립니다. (시속: " + speed + "km/h)");
		}
	}
}
