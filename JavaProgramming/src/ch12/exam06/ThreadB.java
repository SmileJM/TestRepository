package ch12.exam06;

public class ThreadB extends Thread{
	private boolean stop;				// 스레드 종료 목적
	private boolean work = true; 	// 스레드 작업 양보 목적
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public void setWork(boolean work) {
		this.work = work;
	}

	@Override
	public void run() {
		while(!stop) {
			if(work){
				System.out.println("ThreadB 작업 중...");
			} else {
				yield();
			}
		}
		System.out.println("ThreadB 작업 종료");
	}
}
