package ch12.exam01;

import java.awt.Toolkit;

public class BeepPrintExample {

	public static void main(String[] args) throws InterruptedException {
		//소리 내는 코드
//		BeepTask beepTask = new BeepTask();
//		Thread thread = new Thread(beepTask);
//		thread.start();
		
		// 스레드 객체를 직접 만든 것
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for(int i=0; i<5; i++) {
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {

					}
				}
			}
		});
		thread.start();
		
		// 프린트 코드
		for(int i=0; i<5; i++) {
			System.out.println("띵");
			Thread.sleep(500);
		}
	}

}
