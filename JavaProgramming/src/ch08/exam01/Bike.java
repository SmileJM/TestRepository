package ch08.exam01;

public class Bike implements Menual{
	// Field
	private int speed;
	// Constructor
	// Method
		
	@Override
	public void turnOn() {
		System.out.println("전동 자전거를 구동합니다.");
		
	}

	@Override
	public void turnOff() {
		System.out.println("전동 자전거를 정지합니다.");
		
	}

	@Override
	public void setSpeed(int speed) {
		System.out.println("속도를 " + speed +" 로 변경합니다.");
		this.speed = speed;		
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void run() {
		System.out.println("전동 자전거가 " + speed +" 속도로 달립니다.");
	}
	
}
