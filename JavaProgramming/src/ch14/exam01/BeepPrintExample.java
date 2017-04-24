package ch14.exam01;

import java.awt.Toolkit;

public class BeepPrintExample {

	public static void main(String[] args) throws InterruptedException {
		//소리 내는 코드

		Thread thread = new Thread( () -> {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for(int i=0; i<5; i++) {
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
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
