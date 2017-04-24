package ch12.exam02;

import java.awt.Toolkit;

public class BeepThread extends Thread{	
	@Override
	public void run() {
		//소리 내는 코드
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for(int i=0; i<5; i++) {
			toolkit.beep();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}
		}
	}
}
