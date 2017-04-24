package ch08.homework1;

public interface RemoteControl {
	// field
	public int MAX_VOLUME = 10;
	public int MIN_VOLUME = 0;
	
	// abstract method
	public void turnOn();
	public void turnOff();
	public void setVolume(int volume);
	
	// default method
	default void setMute(boolean mute) {
		if(mute) {
			System.out.println("무음 처리합니다.");
		} else {
			System.out.println("무음 해제합니다.");
		}
	}
	
	// static method
	static void changeBettery() {
		System.out.println("건전지를 교환합니다.");
	}
}
