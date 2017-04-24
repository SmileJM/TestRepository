package ch05.exam02;

public class ArrayExample03 {
	public static void main(String[] args) {
		int[] v1 = new int[5];
		v1[0] = 10;
		v1[2] = 10;
		
		String[] v2 = new String[5];
		v2[0] = new String("Java");
		v2[2] = new String("Java");
		v2[3] = "Java";
		v2[4] = "Java";
		
		System.out.println(v1[0] == v1[2]);
		System.out.println(v2[0] == v2[2]);
		System.out.println(v2[0].equals(v2[2]));
		System.out.println(v2[3] == v2[4]);
	}
	
}
