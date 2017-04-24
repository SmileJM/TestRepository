package ch14.exam01;

import java.awt.Toolkit;

public class BeepTask implements Runnable{
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
