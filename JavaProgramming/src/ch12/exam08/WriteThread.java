package ch12.exam08;

public class WriteThread extends Thread{
	private DataBox dataBox;
	
	public WriteThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}

	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			dataBox.setData(String.valueOf(i));
		}
	}

}
