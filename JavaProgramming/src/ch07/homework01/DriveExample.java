package ch07.homework01;

public class DriveExample {

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.drive(new Vehicle());
		driver.drive(new Bus());
		driver.drive(new Taxi());

	}

}
