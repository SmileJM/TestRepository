package ch12.exam07;

public class JoinExample {

	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		
		sumThread.start();
		try {
			sumThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long result = sumThread.getSum();
		System.out.println(result);
	}

}
