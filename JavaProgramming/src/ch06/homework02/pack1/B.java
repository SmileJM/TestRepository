package ch06.homework02.pack1;

public class B {
	A a1 = new A(true);
	A a2 = new A(1);
//	A a3 = new A("문자열");
	public B(){
		a1.field1 = 1;
		a1.field2 = 1;
//		a1.field3 = 1;
		
		a1.method1();
		a1.method2();
//		a1.method3();
	}
}
