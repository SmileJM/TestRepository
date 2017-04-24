package ch07.exam08;

public class TireExample {

	public static void main(String[] args) {
		Tire tire = new NormalTire();
		tire.roll();
		
		tire = new SuperTire();
		tire.roll();
		

	}

}
