package ch07.homework01.car2;

public class KumhoTire extends Tire{

	public KumhoTire(String location, int maxRotation) {
		super(location, maxRotation);
	}
	@Override
	public boolean roll() {
		accumulatedRotation++;
		if(accumulatedRotation<maxRotation) {
			System.out.println(location + " Hankook Tire 수명: " + (maxRotation - accumulatedRotation) + " 회");
			return true;
		} else {
			System.out.println("**********" + location + " Hankook Tire 펑크");
			return false;
		}
	}

}
