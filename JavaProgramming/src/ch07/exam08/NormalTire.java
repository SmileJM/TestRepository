package ch07.exam08;

public class NormalTire extends Tire{
	
	public NormalTire() {
		super();
		System.out.println("NormalTire 객체 생성");
	}

	@Override
	void roll() {
		System.out.println("NormalTire 가 굴러갑니다.");
	}
}
