package ch12.exam08;

public class ReadThread extends Thread{
	private DataBox dataBox;
	
	public ReadThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}

	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			String data = dataBox.getData();
		}		
	}
}
