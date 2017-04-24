package ch05.homework01;

public class ForMultiplicationTableExample {

	public static void main(String[] args) {
		for(int i=2; i<=9; i++) {
			System.out.println("*** " + i + " 단 ***");
			for(int k=1; k<=9; k++) {
				System.out.println(i + " x " + k + " = " + (i*k));
			}
		}
	}

}
