package ch08.exam01;

public class Car implements Menual{
	private int speed;
	
	@Override
	public void turnOn() {
		System.out.println("자동차를 구동합니다.");
		
	}

	@Override
	public void turnOff() {
		System.out.println("자동차를 정지합니다.");
		
	}

	@Override
	public void setSpeed(int speed) {
		if(speed < Menual.MIN_SPEED) {
			this.speed = Menual.MIN_SPEED;	
		} else if(speed > Menual.MAX_SPEED) {
			this.speed = Menual.MAX_SPEED;	
		} else {
			this.speed = speed;
		}
		System.out.println("속도를 " + this.speed +" 로 변경합니다.");				
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void run() {
		System.out.println("자동차가 " + speed +" 속도로 달립니다.");
	}

}
