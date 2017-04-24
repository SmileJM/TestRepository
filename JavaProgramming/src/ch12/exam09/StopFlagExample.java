package ch12.exam09;

public class StopFlagExample {

	public static void main(String[] args) {
		PrintThread1 thread = new PrintThread1();
		thread.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {		}
		thread.setStop(true);
	
	}

}
