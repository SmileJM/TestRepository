package ch07.homework01.parent;

public class ChildExample {

	public static void main(String[] args) {
		Parent parent = new Parent();
		parent.field1 = "data1";
		
		parent.method1();
		parent.method2();
		
//		parent.field2 = "date2";
//		parent.method3();
		
		Child child = new Child();
		child.field2 = "date2";
		child.method3();
		

	}

}
