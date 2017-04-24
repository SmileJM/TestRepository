package ch07.exam09;

public class SnowTire extends Tire{
	@Override
	public void roll() {
		System.out.println("SnowTire 가 굴러갑니다.");
	}
	
	public void notSlide(){
		System.out.println("눈에서 미끄러지지 않음");
	}
}
