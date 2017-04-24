package ch12.exam02;

import java.awt.Toolkit;

public class BeepPrintExample {

	public static void main(String[] args) throws InterruptedException {
		//소리 내는 코드
//		BeepThread beepThread = new BeepThread();		
//		beepThread.start();
		
		// 스레드의 자식객체를 만든 것
		Thread thread = new Thread(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for(int i=0; i<5; i++) {
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		thread.start();
		
		
		// 프린트 코드
		for(int i=0; i<5; i++) {
			System.out.println("띵");
			Thread.sleep(500);
		}
		System.out.println(Thread.currentThread().getName());
	}

}
