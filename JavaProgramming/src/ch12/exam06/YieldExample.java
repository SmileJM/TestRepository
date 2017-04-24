package ch12.exam06;

public class YieldExample {

	public static void main(String[] args) {
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		
		threadA.start();		
		threadB.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
		threadA.setWork(false);
//		threadA.setStop(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
		
		threadA.setWork(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
		threadA.setStop(true);
		threadB.setStop(true);
		
	}

}
