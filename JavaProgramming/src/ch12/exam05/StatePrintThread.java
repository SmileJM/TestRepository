package ch12.exam05;

public class StatePrintThread extends Thread {	
	private Thread targetThread;

	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}

	public void run() {
		while(true) {
			Thread.State state = targetThread.getState();
			System.out.println("스레드상태: " + state);
			if(state == Thread.State.NEW) {
				targetThread.start();
			}
			if(state == Thread.State.TERMINATED) {
				break;
			}
			try {
				//0.5�ʰ� �Ͻ� ����
				Thread.sleep(100);
			} catch(Exception e) {}
		}
	}
}

