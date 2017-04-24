package ch08.homework1;

public class VehicleExample {

	public static void main(String[] args) {
		Driver driver = new Driver();
		
		driver.driver(new Bus());
		driver.driver(new Taxi());
		
		Vehicle vehicle = new Bus();
		Bus bus = (Bus) vehicle;
		bus.run();
		bus.checkFare();
		

	}

}
