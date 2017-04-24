package ch12.exam08;

public class WaitNotifyExample {

	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		ReadThread readThread = new ReadThread(dataBox);
		WriteThread writeThread = new WriteThread(dataBox);
		
		readThread.start();
		writeThread.start();
	}

}
