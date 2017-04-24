package ch13.test01;

public class BoxExample {

	public static void main(String[] args) {
		Box<String> box1 = new Box<String>();
		box1.set("hello");
		String str = box1.get();
		
		Box<Integer> box2 = new Box<Integer>();
		box2.set(5);
		int value = box2.get();
		
		
		System.out.println(str);
		System.out.println(value);
	}

}
