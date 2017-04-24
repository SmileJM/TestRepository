package ch08.exam01;

public class ManualExample {

	public static void main(String[] args) {
		move(new Bike());
		System.out.println();
		move(new Car());
	}

	public static void move(Menual menual) {
		menual.turnOn();
		menual.setSpeed(10);
		menual.run();
		menual.turnOff();
	}

}
