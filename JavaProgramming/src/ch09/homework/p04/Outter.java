package ch09.homework.p04;

public class Outter {
	public void method(final int arg) {
		final int localVariable = 1;
//		arg = 100;
//		localVariable = 10;
		class Inner {
			public void method() {
				int result = arg + localVariable;
			}
		}
	}
	
	public void method2(int arg) {
		int localVariable = 1;
//		arg = 100;
//		localVariable = 10;
		class Inner {
			public void method() {
				int result = arg + localVariable;
			}
		}
	}
}
