package ch06.homework02.pack1;

public class A {
	
	public int field1;
	int field2;
	private int field3;
	
	A a1 = new A(true);
	A a2 = new A(1);
	A a3 = new A("문자열");
	
	public A(boolean b){
		field1 = 1;
		field2 = 1;
		field3 = 1;
		
		method1();
		method2();
		method3();
	}
	A(int num){

	}
	private A(String str){
		field1 = 1;
		field2 = 1;
		field3 = 1;
	}
	public void method1() {
		
	}
	void method2() {
		
	}
	private void method3() {
		
	}
}
