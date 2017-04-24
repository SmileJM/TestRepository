package ch11.exam05;

public class GcExample {

	public static void main(String[] args) {
		for( int i=0; i<1000; i++) {
			Member m1 = new Member(String.valueOf(i));
			System.gc();
		}
	}
}
