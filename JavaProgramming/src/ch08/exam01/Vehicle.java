package ch08.exam01;

public class Vehicle{
	private Menual menual;

	public Vehicle(Menual menual) {
		this.menual = menual;
	}
	
	public void move() {
		menual.turnOn();
		menual.setSpeed(10);
		menual.run();
		menual.turnOff();
	}
}
